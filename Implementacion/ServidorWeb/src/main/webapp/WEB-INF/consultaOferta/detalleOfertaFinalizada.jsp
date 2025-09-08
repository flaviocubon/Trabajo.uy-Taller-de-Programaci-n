<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<%@page import="webservices.OfertaDAO"%>
<%@page import="webservices.PostulacionDAO"%>
<%@page import="webservices.PostulanteDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Comparator" %>

<%OfertaDAO oferta= (OfertaDAO) request.getAttribute("oferta");
List<PostulacionDAO> postulaciones = (List<PostulacionDAO>) request.getAttribute("postulaciones");

  XMLGregorianCalendar fechaG = oferta.getFechaAlta();
  Date fechaAltaDate=new Date(fechaG.getYear()-1900, fechaG.getMonth()-1, fechaG.getDay());
  fechaG =oferta.getFechaBaja();
  Date fechaBajaDate = new Date(fechaG.getYear()-1900, fechaG.getMonth()-1, fechaG.getDay());
  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  String fechaAlta= formatter.format(fechaAltaDate);
  String fechaBaja= formatter.format(fechaBajaDate);

  boolean esEmpresaDuenia=true;

  String nombre=oferta.getNombreOferta();
  
  DtUsuario usuario= (DtUsuario) session.getAttribute("usuarioLogueado");
  if(usuario!=null && usuario instanceof DtEmpresa){
	  DtEmpresa empresa= (DtEmpresa) usuario;
  }
  String tipo=oferta.getTipoPublicacion();
  String nombrePaquete="";
  if(oferta.getPaquete()!=null && !oferta.getPaquete().equals("")) {
	  nombrePaquete=oferta.getPaquete();
  }  
%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Oferta</title>
    <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <link rel="stylesheet" href="css/perfilUsuario.css">
    <link rel="stylesheet" href="css/consultaUsuario.css">
    <script src="js/perfilUsuario.js"></script>
</head>

<body class="bg-primary-subtle body-center">
	<jsp:include page="/WEB-INF/template/header.jsp" />
  	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
    <div class="profile">
        <div class="profile-header">
            <img src="Imagenes?id=<%=oferta.getImagen()%>&tipo=ofertas" alt="Avatar" class="u-avatar">
            <div class="u-info">
                <h1><%=oferta.getNombreOferta()%></h1> 
                <p><%=oferta.getEmpresa().getNickname()%></p>
            </div>
        </div>
        
        <div class="profile-tabs" style="width: 100%;" onload="openTab('detalles')">
            <button class="tablink" onload="openTab('detalles')" onclick="openTab('detalles')">Detalles</button>
            <%if(esEmpresaDuenia &&!postulaciones.isEmpty()){ %>
            <button class="tablink" onclick="openTab('postulaciones')">Postulaciones</button>
            <%} %>
        </div>
        <div class="tabcontent" id="detalles" style="width: 100%; display: block;">
   	        <% if(request.getAttribute("exito") != null || request.getParameter("mensaje") != null) {%>
        		<jsp:include page="/WEB-INF/mensajeAviso/Mensaje.jsp" />        	
        	<% } %>
            <div class="profile-form">
                <div class="row">
                    <div class="label">
                        <label for="inputDescripcion">Descripcion:</label>
                    </div>
                    <div class="info">
                        <textarea name="message" rows="2" cols="30" id="inputDescripcion" readonly><%=oferta.getDescripcion()%></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputInicio">Horario inicio:</label>
                    </div>
                    <div class="info">
                        <input type="time" id="inputInicio" value="<%=oferta.getHorarioInicio()%>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputFin">Horario fin:</label>
                    </div>
                    <div class="info email-info">
                        <input type="time" id="inputFin" value="<%=oferta.getHorarioFin() %>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputRenumeracion">Remuneracion:</label>
                    </div>
                    <div class="info">
                        <input type="number"  id="inputRenumeracion" value="<%=String.valueOf(oferta.getRemuneracion())%>" min="0"
                        oninput="validity.valid||(value='');" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputDepartamento">Departamento:</label>
                    </div>
                    <div class="info">
                        <input type="text" id="inputDepartamento" value="<%=oferta.getDepartamento()%>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputCiudad">Ciudad:</label>
                    </div>
                    <div class="info">
                        <input type="text" id="inputCiudad" value="<%=oferta.getCiudad()%>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="date">Fecha de alta</label>
                    </div>
                    <div class="info">
                        <input type="date" class="form-control" value="<%=fechaAlta%>" id="date" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="date">Fecha de baja</label>
                    </div>
                    <div class="info">
                        <input type="date" class="form-control" value="<%=fechaBaja%>" id="date2" readonly>
                    </div>
                </div>
				<div class="row">
                    <div class="label">
                        <label>Tipo de publicacion: </label>
                    </div>
                    <div class="info">
						<div class="rounded-5 p-4 text-white bg-primary">
		                    <div class="">
		                        <h3><%=tipo%></h3>
		                    </div>
		                </div> 
		                <!--  
		                <a href="Tipos?t=URLEncoder.encode(tipo,"UTF-8")%>" class="text-white">
		                        <h3>tipo%></h3>
		                </a>
		                -->
                    </div>
                </div>
				<%if(esEmpresaDuenia && !nombrePaquete.equals("")){ %>
	                <div class="row">
	                    <div class="label">
	                        <label>Paquete en el que se compro: </label>
	                    </div>
	                    <div class="info">
						<div class="col">
	                    	<div class="card mb-4" style="width:50%">
		                        <!--
		                        <a href="DetallesPaquete?paquete=URLEncoder.encode(nombrePaquete,"UTF-8")%>" class="sinEstilo">
		                            <div class="card-body">
		                                <h5 class="card-title" >nombrePaquete%></h5>
		                            </div>
		                        </a>
		                        -->
		                        <div class="card-body">
		                            <h5 class="card-title" ><%=nombrePaquete%></h5>
		                        </div>
	                    	</div>
	                	</div>
	                    </div>
	                </div>
                <%}else{%>
                	<div class="row">
					<%String mensaje = "Esta oferta no pertenecía a ningún paquete";%>
						<div class="rounded-5 p-4 text-white bg-secondary"style="margin-bottom: 1rem;">
		                    <div class="" style="text-align: center;">
		                        <h6 style="text-align: center; margin:auto;"><%=mensaje%></h6>
		                    </div>
		                </div> 
					</div>
                <%} %>
				<div class="row">
					<%String mensaje = "La oferta fue finalizada";%>
						<div class="rounded-5 p-4 text-white bg-secondary">
		                    <div class="" style="text-align: center;">
		                        <h3 style="text-align: center; margin:auto;"><%=mensaje%></h3>
		                    </div>
		                </div> 
				</div>
            </div>
        </div>
        <%if(esEmpresaDuenia && !postulaciones.isEmpty()){ %>
        <div class="tabcontent" id="postulaciones" style="width: 100%;">
            <div class="container user-profile">
                <div class="detalleOferta">
                    <ul class="detalleOferta">
	                    <%for(PostulacionDAO postulacion: postulaciones){ 
	                    	PostulanteDAO post = postulacion.getPostulante();
	                    %>
                    	<li>
                    		<a class="text-reset" href="PerfilUsuario?nickname=<%=URLEncoder.encode(post.getNickname(), StandardCharsets.UTF_8.toString())%>" style="text-decoration: none;">
                    			<div class="user-card">
	                        		<img src="Imagenes?id=<%=post.getImagen()%>&tipo=usuarios" alt="Oferta1">
	                                <div class="user-info">
		                                <ul>
		                                	<li><h2><%=post.getNickname()%></h2></li>
		                                	<li><%=post.getNombre() + " " + post.getApellido()%></li>
		                                	<li><%=post.getEmail()%></li>
		                                </ul>
	                                </div>
                           		</div>
                    		</a>
                        </li>
                        <!--  
                        	<a target="_blank" rel="noopener" href="Postulacion?n=URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString()) &p=URLEncoder.encode(postulacion.getPostulante(), StandardCharsets.UTF_8.toString()) " class="sinEstilo">
                            </a>
                         -->   
                    	<%}%>
                    </ul>
                </div>
            </div>
        </div>
        <%}%>
    </div> 
</body>

</html>