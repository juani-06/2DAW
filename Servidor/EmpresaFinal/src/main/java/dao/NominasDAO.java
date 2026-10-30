package dao;

import modelo.Nomina;
import modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar las nóminas en la base de datos.
 */
public class NominasDAO {

    /**
     * Consulta el sueldo de un empleado usando su DNI.
     *
     * @param dni DNI del empleado.
     * @return sueldo del empleado o -1 si no se encuentra.
     * @throws SQLException si ocurre un error en la consulta.
     */
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

    /**
     * Actualiza el sueldo de un empleado en la base de datos.
     *
     * @param empleado empleado cuyo sueldo se va a actualizar.
     * @throws SQLException si ocurre un error al actualizar.
     */
    public static void actualizarSueldo(Empleado empleado) throws SQLException {

        String sql = "UPDATE Nominas SET sueldo = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Nomina.sueldo(empleado));
            ps.setString(2, empleado.dni);

            ps.executeUpdate();
        }
    }

    /**
     * Crea una nómina para un empleado.
     *
     * @param empleado empleado para el que se crea la nómina.
     * @throws SQLException si ocurre un error al insertar la nómina.
     */
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