<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alta completada</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f3f5;
            color: #243b55;
            text-align: center;
            padding: 40px 15px;
        }
        main {
            max-width: 550px;
            margin: auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 12px 16px;
            border-radius: 5px;
            background: #243b55;
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
<main>
    <h1>Alta de empleado</h1>
    <p>${mensaje}</p>
    <a href="${pageContext.request.contextPath}/alta-empleado">Crear otro</a>
    <a href="${pageContext.request.contextPath}/">Volver al menú</a>
</main>
</body>
</html>
