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

@WebServlet("/empleados")
public class ListarEmpleadosServlet extends HttpServlet {

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
