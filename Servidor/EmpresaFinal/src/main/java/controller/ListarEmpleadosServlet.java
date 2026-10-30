package controller;

import dao.EmpleadosDAO;
import modelo.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet encargado de mostrar el listado de empleados.
 */
@WebServlet("/empleados")
public class ListarEmpleadosServlet extends HttpServlet {

    /**
     * Obtiene los empleados de la base de datos y los envía a la vista.
     *
     * @param request petición recibida.
     * @param response respuesta enviada.
     * @throws ServletException si ocurre un error del servlet o de la base de datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ArrayList<Empleado> empleados =
                    EmpleadosDAO.obtenerEmpleados();

            request.setAttribute("empleados", empleados);

            request.getRequestDispatcher("/views/empleados.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al consultar los empleados", e);
        }
    }
}