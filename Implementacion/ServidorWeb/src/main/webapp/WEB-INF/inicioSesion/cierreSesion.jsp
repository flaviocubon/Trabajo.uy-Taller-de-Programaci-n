<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <title>Cerrar Sesion</title>
</head>

<body class="bg-primary-subtle body-center">
    <jsp:include page="/WEB-INF/template/header.jsp" />
  	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
  	
    <div class="container mt-5" style="width: 25rem;">
        <div class="rounded-5 p-4 text-white bg-primary">
            <label>Se ha cerrado correctamente la sesion</label>
            <button onclick="location.href ='Home'" class="btn btn-light btn-lg asd">Volver a la pagina
                principal</button>
        </div>
    </div>
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>