<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashSet"%>
<%@page import="webservices.DtOfertaLaboral"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<% List<DtOfertaLaboral> ofertas= (List<DtOfertaLaboral>) request.getAttribute("ofertas");
%>
<head>
	<jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
  <title>consultaOferta</title>
  <script src="js/consultaOferta.js"></script>
  <link rel="stylesheet" href="css/consultaOferta.css">

</head>

<body class="bg-primary-subtle body-center">
  <jsp:include page="/WEB-INF/template/headerMobile.jsp" />
  <div class="container mainContainer mt-3 mobile">
    <div class="rounded-1 p-2 job-list-title">
      Ofertas Laborales
    </div>
    <div class="scrolling-section container mt-1 user-profile mobile">
      <div class="job-list">
        <ul class="job-list">
        <%for(DtOfertaLaboral oferta:ofertas){
        	Set<String> keywordsSet= new HashSet(oferta.getKeywords());
        	String keywords="";
        	for (String word: keywordsSet){
        		keywords=keywords.concat( word+ ", ");
        	}
        	keywords = !keywords.isEmpty() ? keywords.substring(0, keywords.length()-2) : "";
        	
        	String urlDetalles= URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString());
        	
        	%>
          <a class="text-reset" href="OfertaMobile?n=<%=urlDetalles%>" class="link-dark ">
            <li class="job-list-item" onclick="mostrarOcultarDatos()">
              <img src="Imagenes?id=<%=oferta.getImagen()%>&tipo=ofertas" alt="<%=oferta.getNombre()%>">
              <div>
                <h2><%=oferta.getNombre() %></h3>
                  <h4>Empresa: <%=oferta.getNombreEmpresa() %></h4>
                  <h5><%=keywords%></h5>
              </div>
          	</li>
          </a>
        <%}%>
        </ul>
      </div>
    </div>
  </div>
   	<script src="js/code.jquery.com_jquery-3.5.1.slim.min.js"></script>
    <script src="js/stackpath.bootstrapcdn.com_bootstrap_4.5.2_js_bootstrap.min.js"></script>
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>