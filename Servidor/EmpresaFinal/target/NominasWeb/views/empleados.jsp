<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Empleado" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de empleados</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            background: #f1f3f5;
            color: #212529;
        }

        h1 {
            color: #243b55;
        }

        .mensaje {
            padding: 12px 15px;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        .correcto {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .tabla-contenedor {
            width: 100%;
            overflow-x: auto;
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #243b55;
            color: white;
        }

        tr:nth-child(even) {
            background: #f5f5f5;
        }

        .boton-eliminar {
            background: #dc3545;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        .boton-eliminar:hover {
            background: #b02a37;
        }

        .volver {
            display: inline-block;
            margin-top: 20px;
            color: #243b55;
            text-decoration: none;
        }

        .volver:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<h1>Listado de empleados</h1>

<%
    List<Empleado> empleados =
            (List<Empleado>) request.getAttribute("empleados");

    String eliminado = request.getParameter("eliminado");
    String error = request.getParameter("error");
%>

<% if ("si".equals(eliminado)) { %>
    <div class="mensaje correcto">
        Empleado eliminado correctamente, incluida su nómina.
    </div>
<% } %>

<% if ("dni".equals(error)) { %>
    <div class="mensaje error">
        No se ha recibido un DNI válido.
    </div>
<% } %>

<% if (empleados == null || empleados.isEmpty()) { %>

    <p>No hay empleados registrados.</p>

<% } else { %>

    <div class="tabla-contenedor">
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>DNI</th>
                    <th>Sexo</th>
                    <th>Categoría</th>
                    <th>Años trabajados</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>
                <% for (Empleado e : empleados) { %>
                    <tr>
                        <td><%= e.nombre %></td>
                        <td><%= e.dni %></td>
                        <td><%= e.sexo %></td>
                        <td><%= e.getCategoria() %></td>
                        <td><%= e.anyosTrabajados %></td>

                        <td>
                            <form action="${pageContext.request.contextPath}/eliminar-empleado"
                                  method="post"
                                  onsubmit="return confirm('¿Seguro que quieres eliminar este empleado y su nómina?');">

                                <input type="hidden"
                                       name="dni"
                                       value="<%= e.dni %>">

                                <button type="submit" class="boton-eliminar">
                                    Eliminar
                                </button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

<% } %>

<a class="volver"
   href="${pageContext.request.contextPath}/index.jsp">
    Volver al menú principal
</a>

</body>
</html>