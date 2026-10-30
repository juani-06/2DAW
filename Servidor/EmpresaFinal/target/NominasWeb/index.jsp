<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de nóminas</title>

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

        .menu {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 20px;
        }

        .opcion {
            display: block;
            min-height: 180px;
            padding: 25px;
            background: white;
            border-radius: 10px;
            text-decoration: none;
            color: #243b55;
            border: 1px solid #dee2e6;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .opcion:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 15px #00000015;
        }

        .opcion h2 {
            margin-top: 0;
            font-size: 20px;
        }

        .opcion p {
            color: #555;
            line-height: 1.5;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #666;
        }

        @media (max-width: 600px) {
            main {
                margin: 20px auto;
            }
        }
    </style>
</head>

<body>

<header>
    <h1>Gestión de nóminas</h1>
    <p>Aplicación de gestión de empleados y salarios</p>
</header>

<main>
    <h2>Menú principal</h2>
    <p>Selecciona la operación que deseas realizar.</p>

    <div class="menu">

        <a class="opcion"
           href="${pageContext.request.contextPath}/empleados">
            <h2>Listado de empleados</h2>
            <p>Consultar los empleados registrados en la base de datos.</p>
        </a>

        <a class="opcion"
           href="${pageContext.request.contextPath}/salario">
            <h2>Consultar salario por DNI</h2>
            <p>Consultar el salario de un empleado introduciendo su DNI.</p>
        </a>

        <a class="opcion"
           href="${pageContext.request.contextPath}/buscar-empleados">
            <h2>Buscar empleados</h2>
            <p>Localizar empleados utilizando sus datos.</p>
        </a>

        <a class="opcion"
           href="${pageContext.request.contextPath}/alta-empleado">
            <h2>Alta de empleados</h2>
            <p>Registrar un nuevo empleado y calcular su nómina inicial.</p>
        </a>

    </div>
</main>

<footer>
    <p>Proyecto de gestión de nóminas</p>
</footer>

</body>
</html>