package controller;

import dao.NominasDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet encargado de consultar el salario de un empleado.
 */
@WebServlet("/salario")
public class SalarioServlet extends HttpServlet {

    /**
     * Muestra el formulario para consultar el salario.
     *
     * @param request petición recibida.
     * @param response respuesta enviada.
     * @throws ServletException si ocurre un error del servlet.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/salario.jsp")
                .forward(request, response);
    }

    /**
     * Busca el salario del empleado a partir de su DNI.
     *
     * @param request petición con el DNI del empleado.
     * @param response respuesta con el resultado de la consulta.
     * @throws ServletException si ocurre un error del servlet o de la base de datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");

        if (dni == null || dni.trim().isEmpty()) {
            request.setAttribute("mensaje", "Introduce un DNI.");
            request.getRequestDispatcher("/views/resultadoSalario.jsp")
                    .forward(request, response);
            return;
        }

        try {
            int sueldo = NominasDAO.obtenerSueldo(dni.trim());

            if (sueldo == -1) {
                request.setAttribute("mensaje",
                        "No se ha encontrado un salario para ese DNI.");
            } else {
                request.setAttribute("dni", dni.trim());
                request.setAttribute("sueldo", sueldo);
            }

            request.getRequestDispatcher("/views/resultadoSalario.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al consultar el salario", e);
        }
    }
}