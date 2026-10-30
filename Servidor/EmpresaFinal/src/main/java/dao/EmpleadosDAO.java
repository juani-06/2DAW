package dao;

import exceptions.DatosNoCorrectos;
import modelo.Empleado;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase encargada de realizar las operaciones con los empleados en la base de datos.
 */
public class EmpleadosDAO {

    /**
     * Obtiene todos los empleados de la base de datos.
     *
     * @return lista de empleados.
     * @throws SQLException si ocurre un error al consultar la base de datos.
     */
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
            throw new SQLException("Error al obtener los empleados", ex);
        }
        return e;
    }

    /**
     * Añade un empleado a la base de datos.
     *
     * @param empleado empleado que se va a guardar.
     * @throws SQLException si ocurre un error al insertar el empleado.
     */
    public static void altaEmpleado(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos) VALUES (?,?, ?,?,?)";

        try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.dni);
            ps.setString(2, empleado.nombre);
            ps.setString(3, String.valueOf(empleado.sexo));
            ps.setInt(4, empleado.getCategoria());
            ps.setInt(5, empleado.anyosTrabajados);

            ps.executeUpdate();
        }
    }

    /**
     * Actualiza el nombre de un empleado.
     *
     * @param dni DNI del empleado.
     * @param nombre nuevo nombre.
     * @throws SQLException si ocurre un error al actualizar.
     */
    public static void actualizarNombre(String dni, String nombre) throws SQLException {

        String sql = "UPDATE Empleados SET nombre = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, dni);

            ps.executeUpdate();
        }
    }

    /**
     * Actualiza el sexo de un empleado.
     *
     * @param dni DNI del empleado.
     * @param sexo nuevo sexo.
     * @throws SQLException si ocurre un error al actualizar.
     */
    public static void actualizarSexo(String dni, char sexo) throws SQLException {

        String sql = "UPDATE Empleados SET sexo = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, String.valueOf(sexo));
            ps.setString(2, dni);

            ps.executeUpdate();
        }
    }

    /**
     * Actualiza la categoría de un empleado.
     *
     * @param dni DNI del empleado.
     * @param categoria nueva categoría.
     * @throws SQLException si ocurre un error al actualizar.
     */
    public static void actualizarCategoria(String dni, int categoria) throws SQLException {

        String sql = "UPDATE Empleados SET categoria = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, categoria);
            ps.setString(2, dni);

            ps.executeUpdate();
        }
    }

    /**
     * Actualiza los años trabajados de un empleado.
     *
     * @param dni DNI del empleado.
     * @param anyos nuevos años trabajados.
     * @throws SQLException si ocurre un error al actualizar.
     */
    public static void actualizarAnyos(String dni, int anyos) throws SQLException {

        String sql = "UPDATE Empleados SET anyos = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, anyos);
            ps.setString(2, dni);

            ps.executeUpdate();
        }
    }

    /**
     * Actualiza el DNI de un empleado y el de su nómina.
     *
     * @param dniActual DNI actual del empleado.
     * @param dniNuevo nuevo DNI del empleado.
     * @throws SQLException si ocurre un error al actualizar.
     */
    public static void actualizarDni(String dniActual, String dniNuevo) throws SQLException {

        String sql1 = "UPDATE Nominas SET dni = ? WHERE dni = ?";
        String sql2 = "UPDATE Empleados SET dni = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps1 = con.prepareStatement(sql1);
             PreparedStatement ps2 = con.prepareStatement(sql2)) {

            ps1.setString(1, dniNuevo);
            ps1.setString(2, dniActual);
            ps1.executeUpdate();

            ps2.setString(1, dniNuevo);
            ps2.setString(2, dniActual);
            ps2.executeUpdate();
        }
    }

    /**
     * Busca empleados utilizando los filtros indicados.
     *
     * @param dni DNI que se quiere buscar.
     * @param nombre nombre que se quiere buscar.
     * @param sexo sexo del empleado.
     * @param categoria categoría del empleado.
     * @param anyos años trabajados.
     * @return lista de empleados encontrados.
     * @throws SQLException si ocurre un error en la consulta.
     * @throws DatosNoCorrectos si los datos de búsqueda no son correctos.
     */
    public static ArrayList<Empleado> buscarEmpleados(
            String dni, String nombre, String sexo,
            String categoria, String anyos) throws SQLException, DatosNoCorrectos {

        ArrayList<Empleado> empleados = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT nombre, dni, sexo, categoria, anyos FROM Empleados WHERE 1=1"
        );

        if (dni != null && !dni.trim().isEmpty()) {
            sql.append(" AND dni LIKE ?");
        }

        if (nombre != null && !nombre.trim().isEmpty()) {
            sql.append(" AND nombre LIKE ?");
        }

        if (sexo != null && !sexo.trim().isEmpty()) {
            sql.append(" AND sexo = ?");
        }

        if (categoria != null && !categoria.trim().isEmpty()) {
            sql.append(" AND categoria = ?");
        }

        if (anyos != null && !anyos.trim().isEmpty()) {
            sql.append(" AND anyos = ?");
        }

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int i = 1;

            if (dni != null && !dni.trim().isEmpty()) {
                ps.setString(i++, "%" + dni.trim() + "%");
            }

            if (nombre != null && !nombre.trim().isEmpty()) {
                ps.setString(i++, "%" + nombre.trim() + "%");
            }

            if (sexo != null && !sexo.trim().isEmpty()) {
                ps.setString(i++, sexo.trim());
            }

            if (categoria != null && !categoria.trim().isEmpty()) {
                ps.setInt(i++, Integer.parseInt(categoria.trim()));
            }

            if (anyos != null && !anyos.trim().isEmpty()) {
                ps.setInt(i++, Integer.parseInt(anyos.trim()));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getString("nombre"),
                            rs.getString("dni"),
                            rs.getString("sexo").charAt(0),
                            rs.getInt("categoria"),
                            rs.getInt("anyos")
                    );

                    empleados.add(empleado);
                }
            }
        }

        return empleados;
    }

    /**
     * Da de alta un empleado y crea su nómina en la misma transacción.
     *
     * @param empleado empleado que se va a registrar.
     * @throws SQLException si ocurre un error al guardar los datos.
     */
    public static void altaEmpleadoConNomina(Empleado empleado) throws SQLException {
        String sqlEmpleado = "INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
        String sqlNomina = "INSERT INTO Nominas (dni, sueldo) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement psEmpleado = con.prepareStatement(sqlEmpleado);
                 PreparedStatement psNomina = con.prepareStatement(sqlNomina)) {

                psEmpleado.setString(1, empleado.dni);
                psEmpleado.setString(2, empleado.nombre);
                psEmpleado.setString(3, String.valueOf(empleado.sexo));
                psEmpleado.setInt(4, empleado.getCategoria());
                psEmpleado.setInt(5, empleado.anyosTrabajados);
                psEmpleado.executeUpdate();

                psNomina.setString(1, empleado.dni);
                psNomina.setInt(2, modelo.Nomina.sueldo(empleado));
                psNomina.executeUpdate();

                con.commit();

            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    /**
     * Elimina un empleado y su nómina de la base de datos.
     *
     * @param dni DNI del empleado que se quiere eliminar.
     * @throws SQLException si ocurre un error o no existe el empleado.
     */
    public static void eliminarEmpleado(String dni) throws SQLException {
        String sqlNomina = "DELETE FROM Nominas WHERE dni = ?";
        String sqlEmpleado = "DELETE FROM Empleados WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion()) {
            con.setAutoCommit(false);

            try {
                try (PreparedStatement ps = con.prepareStatement(sqlNomina)) {
                    ps.setString(1, dni);
                    ps.executeUpdate();
                }

                int filas;

                try (PreparedStatement ps = con.prepareStatement(sqlEmpleado)) {
                    ps.setString(1, dni);
                    filas = ps.executeUpdate();
                }

                if (filas == 0) {
                    throw new SQLException("No existe ningún empleado con ese DNI.");
                }

                con.commit();

            } catch (SQLException e) {
                con.rollback();
                throw e;

            } finally {
                con.setAutoCommit(true);
            }
        }
    }

}