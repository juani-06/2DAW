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

/**
 * Servlet encargado de gestionar el alta de empleados.
 */
@WebServlet("/alta-empleado")
public class AltaEmpleadoServlet extends HttpServlet {

    /**
     * Muestra el formulario de alta de empleados.
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
        request.getRequestDispatcher("/views/altaEmpleado.jsp")
                .forward(request, response);
    }

    /**
     * Recoge los datos del formulario y registra el empleado
     * junto con su nómina.
     *
     * @param request petición con los datos del empleado.
     * @param response respuesta enviada.
     * @throws ServletException si ocurre un error del servlet o de la base de datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String categoriaTexto = request.getParameter("categoria");
        String anyosTexto = request.getParameter("anyos");

        try {
            if (dni == null || dni.trim().isEmpty()
                    || nombre == null || nombre.trim().isEmpty()
                    || sexo == null || !(sexo.equals("F") || sexo.equals("M"))
                    || categoriaTexto == null || anyosTexto == null) {
                throw new IllegalArgumentException("Completa todos los campos correctamente.");
            }

            int categoria = Integer.parseInt(categoriaTexto);
            int anyos = Integer.parseInt(anyosTexto);

            if (categoria < 1 || anyos < 0) {
                throw new IllegalArgumentException(
                        "La categoría debe ser positiva y los años no pueden ser negativos.");
            }

            Empleado empleado = new Empleado(
                    nombre.trim(),
                    dni.trim(),
                    sexo.charAt(0),
                    categoria,
                    anyos
            );

            EmpleadosDAO.altaEmpleadoConNomina(empleado);

            request.setAttribute("mensaje", "Empleado creado correctamente.");
            request.getRequestDispatcher("/views/resultadoAlta.jsp")
                    .forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("mensaje", e.getMessage());
            request.getRequestDispatcher("/views/altaEmpleado.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                request.setAttribute("mensaje", "Ya existe un empleado con ese DNI o hay un conflicto en la base de datos.");
                request.getRequestDispatcher("/views/altaEmpleado.jsp")
                        .forward(request, response);
            } else {
                throw new ServletException("Error al crear el empleado.", e);
            }
        } catch (DatosNoCorrectos e) {
            throw new RuntimeException(e);
        }
    }
}