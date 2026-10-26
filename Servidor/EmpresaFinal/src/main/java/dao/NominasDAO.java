package dao;

import modelo.Nomina;
import modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NominasDAO {

    public static int obtenerSueldo(String dni) throws SQLException {

        String sql = "SELECT sueldo FROM Nominas WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("sueldo");
            }
        }

        return -1;
    }

    public static void actualizarSueldo(Empleado empleado) throws SQLException {

        String sql = "UPDATE Nominas SET sueldo = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Nomina.sueldo(empleado));
            ps.setString(2, empleado.dni);

            ps.executeUpdate();
        }
    }

    public static void altaNomina(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO Nominas (dni, sueldo) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.dni);
            ps.setInt(2, Nomina.sueldo(empleado));

            ps.executeUpdate();
        }
    }
}
