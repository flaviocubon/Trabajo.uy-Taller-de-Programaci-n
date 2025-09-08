<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webservices.DtOfertaLaboral"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
  <title>Postulacion a Oferta Laboral</title>
  <link rel="stylesheet" href="css/postulacionAOferta.css">
  <script src="js/cdn.jsdelivr.net_npm_@popperjs_core@2.5.3_dist_umd_popper.min.js"></script>
  <script src="js/code.jquery.com_jquery-3.5.1.slim.min.js"></script>
</head>

<body class="bg-primary-subtle body-center body-postOferta">
<%DtOfertaLaboral oferta = (DtOfertaLaboral) request.getAttribute("oferta");  %>
	<jsp:include page="/WEB-INF/template/headerMobile.jsp" />
	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
  <div class="d-flex container mt-5 divPostulacion" style="width: fit-content; height: fit-content;">
    <div class="rounded-5 p-4 text-white bg-primary custom-shadow" style="height: fit-content">
      <form class="formOferta" method="POST">
        <div class="form-group card-oferta">
          <div class="card">
            <img src="Imagenes?id=<%=oferta.getImagen()%>&tipo=ofertas" class="card-img-top" alt="Oferta Desarollador FrontEnd" />
            <div class="card-body scrolling-section">
              <h5 class="card-title"> <%=oferta.getNombre() %> </h5>
              <p class="card-text">
                <%=oferta.getDescripcion() %>
              </p>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item"> <%=oferta.getNombreEmpresa() %></li>
              <li class="list-group-item"> <%=oferta.getRemuneracion() %> </li>
              <li class="list-group-item"> <%=oferta.getHorario().getHorarioInicio() + ":" + oferta.getHorario().getHorarioFin() %> </li>
              <li class="list-group-item"> <%= oferta.getCiudad() + ", " + oferta.getDepartamento() %> </li>
            </ul>
          </div>
        </div>
        <div class="form-group">
          <label for="cv">CV:</label>
          <textarea id="cv" class="form-control"
            placeholder="Informacion basica, experiencia laboral..." name="cv" required></textarea><br>
        </div>
        <div class="form-group">
          <label for="motivacion">Motivación:</label>
          <textarea id="motivacion" class="form-control" name="motivacion" required></textarea>
        </div>
         <div class="form-group">
          <label for="motivacion">Link a video de Postulación (Opcional):</label>
          <textarea id="video" class="form-control" name="video"></textarea>
        </div>
        <div id="submitBtn">
          <input type="submit" class="btn btn-light" value="Aceptar">
          <input type="button" class="btn btn-light" value="Cancelar"
            onclick="document.location='Oferta'">
        </div>
       </form> 
    </div>
  </div>
  <jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>
</html>