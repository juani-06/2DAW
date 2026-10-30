package controller;

import dao.EmpleadosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet encargado de eliminar empleados.
 */
@WebServlet("/eliminar-empleado")
public class EliminarEmpleadoServlet extends HttpServlet {

    /**
     * Elimina un empleado y redirige al listado.
     *
     * @param request petición con el DNI del empleado.
     * @param response respuesta enviada.
     * @throws ServletException si ocurre un error del servlet o de la base de datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");

        if (dni == null || dni.trim().isEmpty()) {
            response.sendRedirect(
                    request.getContextPath() + "/empleados?error=dni"
            );
            return;
        }

        try {
            EmpleadosDAO.eliminarEmpleado(dni.trim());

            response.sendRedirect(
                    request.getContextPath() + "/empleados?eliminado=si"
            );

        } catch (SQLException e) {
            throw new ServletException("Error al eliminar el empleado.", e);
        }
    }
}