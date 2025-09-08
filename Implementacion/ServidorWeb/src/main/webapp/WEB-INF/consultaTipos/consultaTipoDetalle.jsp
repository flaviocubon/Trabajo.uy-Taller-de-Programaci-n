<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@page import ="webservices.DtTipoPublicacion" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.Date" %>

<% 
	DtTipoPublicacion dataTipo =(DtTipoPublicacion)request.getAttribute("tipoPublicacion");
	XMLGregorianCalendar fechaAltaXML = dataTipo.getFecha();
	Date fechaAltaDate = new Date(fechaAltaXML.getYear()-1900, fechaAltaXML.getMonth(), fechaAltaXML.getDay());
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	String fechaAlta = formatter.format(fechaAltaDate).toString();
%>

<head>
  <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Consulta de Tipo de Publicación</title>
  <link rel="stylesheet" href="css/consultaPostulacion.css" />
</head>

<body class="bg-primary-subtle body-center">
   <jsp:include page="/WEB-INF/template/header.jsp" />
   <jsp:include page="/WEB-INF/template/sideBar.jsp" />
   
  <main class="pt-3 px-1">
    <h1 class="mb-4 text-capitalize text-center">Detalle de Tipo de Publicación</h1>
    <section class="container">
      <div class="row bg-primary rounded-3 text-white border-bottom border-2 border-info px-0 pt-1">
        <h2 id="tipo-nombre"><%=dataTipo.getNombre() %></h2>
      </div>
      <div class="row d-flex flex-column py-2  justify-content-between">
        <div class="d-flex flex-wrap justify-content-between my-2">
          <div class="rounded mt-1">
            <p id="tipo-exposicion">Exposición:<span class="text-secondary"><%=dataTipo.getExposicion() %></span></p>
          </div>
          <div class="rounded mt-1">
            <p id="tipo-duracion">Duración:<span class="text-secondary"> <%=(int)dataTipo.getDuracion() %> dias</span></p>
          </div>
          <div class="rounded mt-1">
            <p id="tipo-costo">Costo:<span class="text-secondary"> $<%=dataTipo.getCosto() %></span></p>
          </div>
          <div class="rounded mt-1">
            <p id="tipo-alta-fecha">Fecha de alta:<span class="text-secondary"> <%=fechaAlta%></span></p>
          </div>
        </div>
        <div class="bg-white rounded border border-2 border-secondary-subtle my-2">
          <h3 class="fw-bold text-decoration-underline mt-2">Descripción</h3>
          <p class="fs-6"><%=dataTipo.getDescripcion() %></p>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>