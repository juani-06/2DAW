<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar empleados</title>

    <style>
        * {
            box-sizing: border-box;
        }

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

        header h1 {
            margin: 0;
        }

        main {
            max-width: 700px;
            margin: 35px auto;
            padding: 25px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 3px 12px #0002;
        }

        main p {
            line-height: 1.5;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            background: white;
        }

        input:focus, select:focus {
            outline: none;
            border-color: #243b55;
        }

        .botones {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
            margin-top: 20px;
        }

        button, .volver {
            display: inline-block;
            padding: 11px 16px;
            border: none;
            border-radius: 5px;
            background: #243b55;
            color: white;
            text-decoration: none;
            cursor: pointer;
            font-size: 14px;
        }

        button:hover, .volver:hover {
            background: #365779;
        }

        .mensaje {
            color: #b42318;
            background: #fef3f2;
            border: 1px solid #fecdca;
            padding: 10px;
            border-radius: 5px;
        }

        @media (max-width: 750px) {
            main {
                margin: 20px;
            }
        }
    </style>
</head>

<body>

<header>
    <h1>Buscar empleados</h1>
</header>

<main>

    <p>
        Introduce uno o varios datos para filtrar los empleados.
        Deja vacíos los campos que no quieras utilizar.
    </p>

    <% if (request.getAttribute("mensaje") != null) { %>
        <p class="mensaje">
            <%= request.getAttribute("mensaje") %>
        </p>
    <% } %>

    <form action="${pageContext.request.contextPath}/buscar-empleados"
          method="post">

        <label for="dni">DNI</label>
        <input type="text"
               id="dni"
               name="dni"
               placeholder="Introduce el DNI">

        <label for="nombre">Nombre</label>
        <input type="text"
               id="nombre"
               name="nombre"
               placeholder="Introduce el nombre">

        <label for="sexo">Sexo</label>
        <select id="sexo" name="sexo">
            <option value="">Cualquiera</option>
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
        </select>

        <label for="categoria">Categoría</label>
        <input type="number"
               id="categoria"
               name="categoria"
               min="1"
               placeholder="Introduce la categoría">

        <label for="anyos">Años trabajados</label>
        <input type="number"
               id="anyos"
               name="anyos"
               min="0"
               placeholder="Introduce los años trabajados">

        <div class="botones">
            <button type="submit">Buscar</button>

            <a class="volver"
               href="${pageContext.request.contextPath}/">
                Volver al menú
            </a>
        </div>

    </form>

</main>

</body>
</html>