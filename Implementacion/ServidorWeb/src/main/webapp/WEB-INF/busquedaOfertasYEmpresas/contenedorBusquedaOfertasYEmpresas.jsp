<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
 <title>Busqueda de ofertas laborales y empresas</title>
 <link rel="stylesheet" href="css/busquedaDeOfertasLaboralesYEmpresas.css">
</head>
<body class="bg-primary-subtle body-center">
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
	<jsp:include page="/WEB-INF/busquedaOfertasYEmpresas/busquedaOfertasYEmpresas.jsp" />
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>
</html>