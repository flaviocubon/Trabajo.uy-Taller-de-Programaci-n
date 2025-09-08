<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.util.Set" %>
<%Set<String> dataTipos = (Set<String>)request.getAttribute("tiposPublicacion"); %>
<head>
	<jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
  	<title>Consulta de Tipo de Publicacion</title>
  	<link rel="stylesheet" href="css/consultaUsuario.css" />
</head>

<body class="bg-primary-subtle body-center">
   <jsp:include page="/WEB-INF/template/header.jsp" />
  	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
  <div class="container mainContainer mt-5 " id="blur">
    <div class="job-list-title">
        Tipos de Publicación
    </div>
        <div class="job-list">
        <ul class="job-list">
        <%for (String tipo:dataTipos){
        	String urlTipo = URLEncoder.encode(tipo,"UTF-8");
        	%>
        	<li>
           
                <div class="user-card">
                    <div class="user-info">
                     <a href="Tipos?t=<%=urlTipo%>" class="sinEstilo">
                        <h3><%=tipo%></h3>
                       </a>
                    </div>
                </div> 
           
        </li>
        	
        <%}%>
        </ul>
    </div>
  </div>
  <jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>
</html>