<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alta de empleado</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f3f5;
            margin: 0;
            color: #243b55;
        }
        header {
            background: #243b55;
            color: white;
            padding: 25px;
            text-align: center;
        }
        main {
            max-width: 600px;
            margin: 35px auto;
            padding: 25px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 3px 12px #0002;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input, select {
            box-sizing: border-box;
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button, .volver {
            display: inline-block;
            margin-top: 20px;
            padding: 11px 16px;
            border: none;
            border-radius: 5px;
            background: #243b55;
            color: white;
            text-decoration: none;
            cursor: pointer;
        }
        .mensaje { color: #b42318; }
    </style>
</head>
<body>
<header>
    <h1>Alta de empleado</h1>
</header>
<main>
    <% if (request.getAttribute("mensaje") != null) { %>
        <p class="mensaje"><%= request.getAttribute("mensaje") %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/alta-empleado" method="post">
        <label for="dni">DNI</label>
        <input type="text" id="dni" name="dni" maxlength="9" required>

        <label for="nombre">Nombre completo</label>
        <input type="text" id="nombre" name="nombre" maxlength="100" required>

        <label for="sexo">Sexo</label>
        <select id="sexo" name="sexo" required>
            <option value="">Selecciona</option>
            <option value="F">F</option>
            <option value="M">M</option>
        </select>

        <label for="categoria">Categoría</label>
        <input type="number" id="categoria" name="categoria" min="1" required>

        <label for="anyos">Años trabajados</label>
        <input type="number" id="anyos" name="anyos" min="0" required>

        <button type="submit">Crear empleado</button>
        <a class="volver" href="${pageContext.request.contextPath}/">Volver al menú</a>
    </form>
</main>
</body>
</html>
