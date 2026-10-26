<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Empleado" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de empleados</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            background: #f1f3f5;
            color: #212529;
        }

        h1 {
            color: #243b55;
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

        a {
            display: inline-block;
            margin-top: 20px;
            color: #243b55;
        }
    </style>
</head>
<body>

<h1>Listado de empleados</h1>

<%
    List<Empleado> empleados =
            (List<Empleado>) request.getAttribute("empleados");
%>

<% if (empleados == null || empleados.isEmpty()) { %>

    <p>No hay empleados registrados.</p>

<% } else { %>

<table>
    <thead>
    <tr>
        <th>Nombre</th>
        <th>DNI</th>
        <th>Sexo</th>
        <th>Categoría</th>
        <th>Años trabajados</th>
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
    </tr>
    <% } %>

    </tbody>
</table>

<% } %>

<a href="${pageContext.request.contextPath}/index.jsp">
    Volver al menú principal
</a>

</body>
</html>
