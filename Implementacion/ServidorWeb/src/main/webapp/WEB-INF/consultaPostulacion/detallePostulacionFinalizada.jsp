<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@page import ="webservices.PostulacionDAO" %>
<%@page import ="webservices.PostulanteDAO" %>
<%@page import="java.util.Date"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="javax.xml.datatype.XMLGregorianCalendar" %>
<%
PostulacionDAO postulacion = (PostulacionDAO)request.getAttribute("postulacion");
PostulanteDAO postulante = postulacion.getPostulante();
String imagen = postulacion.getOferta().getImagen();
XMLGregorianCalendar fechaGregorian = postulacion.getFechaPostulacion();
Date fechaAltaDate = new Date(fechaGregorian.getYear() - 1900,fechaGregorian.getMonth()-1, fechaGregorian.getDay());
DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
String fechaAlta= formatter.format(fechaAltaDate);
%>
<head>
  <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
  <title>Consulta de Postulacion</title>
  <link rel="stylesheet" href="css/consultaPostulacion.css" />
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body class="bg-primary-subtle body-center">
  	<jsp:include page="/WEB-INF/template/header.jsp" />
  	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
  	
  <div class="container mainContainer mt-5" id="blur">
    <div class="row">
      <div class="container mt-5 user-profile">
        <div class="user-info">
          <img src="Imagenes?id=<%=postulante.getImagen()%>&tipo=usuarios" alt="Usuario" id ="foto" />
          <div style="padding:10px;">
            <h2><%=postulante.getNickname()%></h2>
            <p><%=postulante.getNombre()+' '+ postulante.getApellido()%></p>
            <p><%=postulante.getEmail()%></p>
          </div>
        </div>
      </div>
    </div>
   
    <div class="container mainContainer mt-5" id="blur">
      <div class="row">
        <div class="d-flex w-100 flex-column align-items-start" style="width: 30rem">
          <h3>Postulación</h3>
          <div class="d-flex border-top border-bottom border-2 border-info justify-content-between">
            <h4>Oferta: <%=postulacion.getOferta().getNombreOferta() + " - Empresa: " + postulacion.getOferta().getEmpresa().getNickname()%></h4>
            <img src="Imagenes?id=<%=imagen%>&tipo=ofertas" id ="foto" />
          </div>
        </div>
      </div>

      <div class="row text-end">
        <p>
          <span class="fw-bold">Fecha de Postulacion: </span><time><%=fechaAlta%></time>
        </p>
      </div>
      <div class="card">
        <h3 class="fw-bold text-decoration-underline">CV Reducido</h3>
        <p>
         <%=postulacion.getResumenCV()%>
        </p>
        <br />
      </div>
      <div class="card">
        <h3 class="fw-bold text-decoration-underline">Motivacion</h3>
        <p>
          <%=postulacion.getDescripcion()%>
        </p>
        <br />
      </div>
    </div>
</div>
  <jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>