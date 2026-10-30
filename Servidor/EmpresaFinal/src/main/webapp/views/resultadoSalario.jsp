<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado del salario</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f1f3f5;
            color: #212529;
        }

        header {
            background: #243b55;
            color: white;
            padding: 25px;
            text-align: center;
        }

        main {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
        }

        .tarjeta {
            background: white;
            padding: 30px;
            border-radius: 10px;
            border: 1px solid #dee2e6;
            box-shadow: 0 3px 10px #00000008;
        }

        h2 {
            color: #243b55;
            margin-top: 0;
        }

        .dato {
            padding: 15px;
            margin: 12px 0;
            background: #f1f3f5;
            border-radius: 6px;
        }

        .acciones {
            display: flex;
            flex-wrap: wrap;
            gap: 12px;
            margin-top: 25px;
        }

        .boton {
            display: inline-block;
            padding: 12px 18px;
            border-radius: 6px;
            background: #243b55;
            color: white;
            text-decoration: none;
            transition: background 0.2s;
        }

        .boton:hover {
            background: #344f70;
        }

        .boton-secundario {
            background: white;
            color: #243b55;
            border: 1px solid #243b55;
        }

        .boton-secundario:hover {
            background: #e9ecef;
        }

        .mensaje {
            padding: 15px;
            background: #fff3cd;
            border: 1px solid #ffe69c;
            border-radius: 6px;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #666;
        }

        @media (max-width: 600px) {
            main {
                margin: 20px auto;
                padding: 15px;
            }

            .tarjeta {
                padding: 22px;
            }
        }
    </style>
</head>

<body>

<header>
    <h1>Gestión de nóminas</h1>
    <p>Consulta de salarios de empleados</p>
</header>

<main>
    <h2>Resultado de la consulta</h2>

    <div class="tarjeta">

        <% if (request.getAttribute("mensaje") != null) { %>

            <p class="mensaje">${mensaje}</p>

        <% } else { %>

            <div class="dato">
                <strong>DNI:</strong> ${dni}
            </div>

            <div class="dato">
                <strong>Salario:</strong> ${sueldo} €
            </div>

        <% } %>

        <div class="acciones">
            <a class="boton"
               href="${pageContext.request.contextPath}/salario">
                Consultar otro DNI
            </a>

            <a class="boton boton-secundario"
               href="${pageContext.request.contextPath}/index.jsp">
                Volver al menú
            </a>
        </div>

    </div>
</main>

<footer>
    <p>Proyecto de gestión de nóminas</p>
</footer>

</body>
</html>