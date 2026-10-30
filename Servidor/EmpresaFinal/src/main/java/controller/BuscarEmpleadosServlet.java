package controller;

import dao.EmpleadosDAO;
import exceptions.DatosNoCorrectos;
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
 * Servlet encargado de buscar empleados por sus datos.
 */
@WebServlet("/buscar-empleados")
public class BuscarEmpleadosServlet extends HttpServlet {

    /**
     * Muestra el formulario de búsqueda de empleados.
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

        request.getRequestDispatcher("/views/buscarEmpleados.jsp")
                .forward(request, response);
    }

    /**
     * Recoge los filtros del formulario y muestra los empleados encontrados.
     *
     * @param request petición con los datos de búsqueda.
     * @param response respuesta enviada.
     * @throws ServletException si ocurre un error del servlet o de la base de datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String categoria = request.getParameter("categoria");
        String anyos = request.getParameter("anyos");

        try {
            ArrayList<Empleado> empleados =
                    EmpleadosDAO.buscarEmpleados(
                            dni, nombre, sexo, categoria, anyos
                    );

            request.setAttribute("empleados", empleados);

            if (empleados.isEmpty()) {
                request.setAttribute(
                        "mensaje",
                        "No se han encontrado empleados."
                );
            }

            request.getRequestDispatcher(
                    "/views/resultadosBusqueda.jsp"
            ).forward(request, response);

        } catch (DatosNoCorrectos e) {
            request.setAttribute("mensaje", e.getMessage());
            request.getRequestDispatcher(
                    "/views/buscarEmpleados.jsp"
            ).forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al buscar empleados en la base de datos.", e
            );
        }
    }
}