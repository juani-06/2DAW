<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar salario</title>

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

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ced4da;
            border-radius: 6px;
            font-size: 16px;
        }

        input:focus {
            outline: 2px solid #8ba9c9;
            border-color: #243b55;
        }

        button {
            background: #243b55;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.2s;
        }

        button:hover {
            background: #344f70;
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
    <h2>Consultar salario</h2>
    <p>Introduce el DNI del empleado para consultar su nómina.</p>

    <div class="tarjeta">
        <form action="${pageContext.request.contextPath}/salario"
              method="post">

            <label for="dni">DNI del empleado</label>

            <input type="text"
                   id="dni"
                   name="dni"
                   maxlength="9"
                   placeholder="Ej.: 12345678A"
                   required>

            <button type="submit">Consultar salario</button>
        </form>
    </div>

    <a class="volver"
       href="${pageContext.request.contextPath}/index.jsp">
        ← Volver al menú principal
    </a>
</main>

<footer>
    <p>Proyecto de gestión de nóminas</p>
</footer>

</body>
</html>