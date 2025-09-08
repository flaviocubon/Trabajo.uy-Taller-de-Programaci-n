<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@page import ="webservices.DtPostulacion" %>
<%@page import ="webservices.DtPostulante" %>
<%@page import="java.util.Date"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="javax.xml.datatype.XMLGregorianCalendar" %>
<%
DtPostulacion postulacion = (DtPostulacion)request.getAttribute("Postulacion");
DtPostulante postulante = (DtPostulante)request.getAttribute("Postulante");
String imagenOferta = (String)request.getAttribute("imagenOferta");
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
          <img src="Imagenes?id=<%=postulante.getImagen()%>&tipo=usuarios" alt="Usuario" id ="foto"  />
          <div style="padding:10px;">

            <h2><a href="PerfilUsuario?nickname=<%=URLEncoder.encode(postulante.getNickname(), StandardCharsets.UTF_8.toString()) %>" class="sinEstilo"><%=postulante.getNickname()%></a></h2>
            <p><%=postulante.getNombre()+' '+ postulante.getApellido()%></p>
            <p><%=postulante.getMail()%></p>
          </div>
        </div>
      </div>
    </div>
   
    <div class="container mainContainer mt-5" id="blur">
      <div class="row">
        <div class="d-flex w-100 flex-column align-items-start" style="width: 30rem">
          <h3>Postulación</h3>
          <div class="d-flex border-top border-bottom border-2 border-info justify-content-between w-100">
            <h4>
              <a class="text-decoration-none text-reset" href="Oferta?n=<%=URLEncoder.encode(postulacion.getNombreOferta(), StandardCharsets.UTF_8.toString()) %>"><%=postulacion.getNombreOferta()%></a>
            </h4>
            <img src="Imagenes?id=<%=imagenOferta%>&tipo=ofertas" id ="foto" style="height:auto; max-height:200px; float:right;" />
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
      <%if(!(postulacion.getVideo().equals(""))) {%>
		<button class="btn btn-primary login-button" id="botonPostulacion">Ver video de Postulación</button>
      <%}%>
    </div>
</div>
 <script>
  var htmlContent = "<div class='card'>" + 
  "<div class='mt-2 ratio ratio-4x3 align-self-center' style='width: 75%;''><iframe src='<%=postulacion.getVideo()%>' frameborder='0'></iframe></div></div>"
  document.getElementById('botonPostulacion').addEventListener('click',function(){
	  Swal.fire({
  
	  title: '<strong>Video de Postulación</strong>',
	  width: '50%',
	  html:
	    htmlContent,
	  showCloseButton: true,
	  focusConfirm: false,
	})})</script>
  <jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>