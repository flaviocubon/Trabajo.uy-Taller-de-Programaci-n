<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">


    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Error 404</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    </head>


    <body class="bg-primary-subtle">
        <div class="d-flex align-items-center justify-content-center vh-100">
            <div class="text-center">
                <h1 class="display-1 fw-bold">404</h1>
                <p class="fs-3"> <span class="text-danger">Oops!</span> Pagina no encontrada.</p>
                <p class="lead">
                   Esta pagina web no esta disponible.
                </p>
                <a href="Home" class="btn btn-primary">Inicio</a>
            </div>
        </div>
    </body>
</html>
