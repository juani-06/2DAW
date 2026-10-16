package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ProductoDAO;
import model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoController", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");
        if (opcion == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        try {
            switch (opcion) {
                case "crear":
                    request.getRequestDispatcher("/views/crear.jsp").forward(request, response);
                    break;

                case "listar":
                    List<Producto> lista = productoDAO.obtenerProductos();
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("/views/listar.jsp").forward(request, response);
                    break;

                case "meditar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Producto p = productoDAO.obtenerProducto(idEditar);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("/views/editar.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    productoDAO.eliminar(idEliminar);
                    response.sendRedirect(request.getContextPath() + "/productos?opcion=listar");
                    break;

                default:
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error de base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String opcion = request.getParameter("opcion");
        if (opcion == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        java.sql.Date hoy = new java.sql.Date(System.currentTimeMillis());

        try {
            if (opcion.equals("guardar")) {
                Producto producto = new Producto();
                producto.setNombre(request.getParameter("nombre"));
                producto.setCantidad(leerNumero(request.getParameter("cantidad")));
                producto.setPrecio(leerNumero(request.getParameter("precio")));
                producto.setFechaCrear(hoy);
                productoDAO.guardar(producto);

            } else if (opcion.equals("editar")) {
                Producto producto = new Producto();
                producto.setId(Integer.parseInt(request.getParameter("id")));
                producto.setNombre(request.getParameter("nombre"));
                producto.setCantidad(leerNumero(request.getParameter("cantidad")));
                producto.setPrecio(leerNumero(request.getParameter("precio")));
                producto.setFechaActualizar(hoy);
                productoDAO.editar(producto);
            }
            response.sendRedirect(request.getContextPath() + "/productos?opcion=listar");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error de base de datos", e);
        }
    }

    // Acepta tanto "12.5" como "12,5"
    private double leerNumero(String texto) {
        return Double.parseDouble(texto.trim().replace(',', '.'));
    }
}