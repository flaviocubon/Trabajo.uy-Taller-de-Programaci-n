<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashSet"%>
<%@page import="webservices.DtOfertaLaboral"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtPostulante"%>
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
  <jsp:include page="/WEB-INF/template/header.jsp" />
  <jsp:include page="/WEB-INF/template/sideBar.jsp" />
  <div class="container mainContainer mt-3">
    <div class="rounded-1 p-4 job-list-title">
      Ofertas Laborales
    </div>
    <div class="scrolling-section container mt-1 user-profile">
      <div class="job-list">
        <ul class="job-list" style="justify-content: unset">
        <%for(DtOfertaLaboral oferta:ofertas){
        	//String urlImagen=  oferta.getImagen()==null||oferta.getImagen()==""? "img/placeholder.png":oferta.getImagen();
        	Set<String> keywordsSet= new HashSet(oferta.getKeywords());
        	String keywords="";
        	for (String word: keywordsSet){
        		keywords=keywords.concat( word+ ", ");
        	}
        	keywords = !keywords.isEmpty() ? keywords.substring(0, keywords.length()-2) : "";
        	
        	String urlDetalles= URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString());
        	boolean fav=false;
        	DtUsuario user= (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
        	List<String> ofertasFavoritas;
        	boolean userLogged=true;
        	if(user!=null && user.getClass()== DtPostulante.class ) {
        		DtPostulante postu= (DtPostulante) user;
        		ofertasFavoritas= postu.getOfertasFavoritas();
        		fav= ofertasFavoritas.contains(oferta.getNombre());
        	}
        	else userLogged=false;
        	
        	String icon;
        	String onClick;
        	String color="";
        	if(fav) {
        		icon="bi-star-fill";
        		color="color: gold";
        	}
        	else{
        		icon="bi-star";
        	}
        	
        	%>
          
            <li class="job-list-item" onclick="mostrarOcultarDatos()" style="position:relative; margin: 4px;">
              <a class="link-dark linkCard" href="Oferta?n=<%=urlDetalles%>" >
	          <img src="Imagenes?id=<%=oferta.getImagen()%>&tipo=ofertas" alt="<%=oferta.getNombre()%>">
	          </a>
	          
              <div>
                <h2><%=oferta.getNombre()+"  " %> 
                <% if(userLogged){%>
                <i onclick="window.location.href = 'Favorito?fav=<%=urlDetalles%>';"  style="position: relative; height:90px; <%=color%>" class="<%=icon%>"></i>
                <%=oferta.getCant_favoritos()%>
                <%}%> 
                </h2>
                
                  <h4>Empresa: <%=oferta.getNombreEmpresa() %></h4>
                  <h5><%=keywords%></h5>
              </div>
          	</li>
          
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