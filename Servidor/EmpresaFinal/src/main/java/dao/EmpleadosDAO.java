package dao;

import modelo.Empleado;

import java.sql.*;
import java.util.ArrayList;

public class EmpleadosDAO {

    public static ArrayList<Empleado> obtenerEmpleados() throws SQLException {

        ArrayList<Empleado> e = new ArrayList<>();
        String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM Empleados";

        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {


            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");

                Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
                e.add(empleado);
            }
        } catch (Exception ex) {
            throw new SQLException("Error al obtener los empleados",ex);
        }
        return e;
    }

    public static void altaEmpleado(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos) VALUES (?,?, ?,?,?)";

        try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.dni);
            ps.setString(2,empleado.nombre);
            ps.setString(3, String.valueOf(empleado.sexo));
            ps.setInt(4,empleado.getCategoria());
            ps.setInt(5,empleado.anyosTrabajados);

            ps.executeUpdate();
        }
    }

    public static void actualizarNombre(String dni, String nombre) throws SQLException {

        String sql = "UPDATE Empleados SET nombre = ? WHERE dni = ?";

        try (Connection con =ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2,dni);

            ps.executeUpdate();
        }
    }



    public static void actualizarSexo(String dni, char sexo) throws SQLException {

        String sql = "UPDATE Empleados SET sexo = ? WHERE dni = ?";

        try (Connection con =ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, String.valueOf(sexo));
            ps.setString(2,dni);

            ps.executeUpdate();
        }
    }

    public static void actualizarCategoria(String dni, int categoria) throws SQLException {

        String sql = "UPDATE Empleados SET categoria = ? WHERE dni = ?";

        try (Connection con =ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, categoria);
            ps.setString(2,dni);

            ps.executeUpdate();
        }
    }

    public static void actualizarAnyos(String dni, int anyos) throws SQLException {

        String sql = "UPDATE Empleados SET anyos = ? WHERE dni = ?";

        try (Connection con =ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, anyos);
            ps.setString(2,dni);

            ps.executeUpdate();
        }
    }

    public static void actualizarDni(String dniActual, String dniNuevo) throws SQLException {

        String sql1 = "UPDATE Nominas SET dni = ? WHERE dni = ?";
        String sql2 = "UPDATE Empleados SET dni = ? WHERE dni = ?";

        try (Connection con =ConexionBD.getConexion();
             PreparedStatement ps1 =con.prepareStatement(sql1);
             PreparedStatement ps2 = con.prepareStatement(sql2)) {

            ps1.setString(1, dniNuevo);
            ps1.setString(2, dniActual);
            ps1.executeUpdate();

            ps2.setString(1, dniNuevo);
            ps2.setString(2, dniActual);
            ps2.executeUpdate();
        }
    }

}