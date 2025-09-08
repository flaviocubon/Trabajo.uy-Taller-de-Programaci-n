<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<%@page import="webservices.DtOfertaLaboral"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtPostulante"%>
<%@page import="webservices.DtPostulante"%>
<%@page import="webservices.Estado"%>
<%@page import="webservices.DtPostulacion"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="javax.xml.datatype.XMLGregorianCalendar" %>
<%DtOfertaLaboral oferta= (DtOfertaLaboral) request.getAttribute("oferta");  
  String linkImagen="";
  Set<String> keywords= new HashSet(oferta.getKeywords());
  XMLGregorianCalendar fechaGregorian = oferta.getFecha();
  Date fechaAltaDate= new Date(fechaGregorian.getYear() - 1900,fechaGregorian.getMonth()-1, fechaGregorian.getDay());
  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  String fechaAlta= formatter.format(fechaAltaDate);
  
  String keywordStr="";
  for(String key:keywords){
	  keywordStr+=key+ ", ";
  }
  if(keywordStr.length()>0)keywordStr=keywordStr.substring(0, keywordStr.length()-2);
  
  
  boolean esPostulanteNoPostulado=false;
  boolean esPostulantePostulado=false;
  boolean esEmpresaDuenia=false;
  Date fecha = formatter.parse(oferta.getFechaVencimiento());
  boolean estaVencida = fecha.before(new Date());
  boolean esFinalizable= estaVencida && oferta.getEstado()==Estado.CONFIRMADO;
  String nombre=oferta.getNombre();
  
  DtUsuario usuario= (DtUsuario) session.getAttribute("usuarioLogueadoMobile");
  if(usuario!=null){
	  if(usuario.getClass() == DtPostulante.class){
		  DtPostulante postulante= (DtPostulante) usuario;
		  Set<String> ofertasPostuladas= new HashSet(postulante.getNombreOfertas());
		  if(ofertasPostuladas.contains(oferta.getNombre())){
			  esPostulantePostulado=true;
		  }else{
			  esPostulanteNoPostulado=true;
		  }
	  }
	  else{
		  DtEmpresa empresa= (DtEmpresa) usuario;
		  Set<String> ofertasPropias= new HashSet(empresa.getNombreOfertas());
		  esEmpresaDuenia=ofertasPropias.contains(oferta.getNombre());
	  }
  }

  Set<DtPostulacion> postulaciones= new HashSet(oferta.getPostulaciones());
  Map<String,String> imagenesPostulantes= (Map<String,String>) request.getAttribute("imagenesPostulantes");
  String tipo=oferta.getTipo();
  String nombrePaquete=null;
  String imagenPaquete=null;
  if(oferta.getPaquete()!=null && !oferta.getPaquete().equals("")) {
	  nombrePaquete=oferta.getPaquete();
	  Object imagen= request.getAttribute("imagenPaquete");
	  imagenPaquete= imagen!=null&& !imagen.equals("") ? (String) imagen: null;
  }
  String imagenOferta= oferta.getImagen()!=null && !oferta.getImagen().equals("") ? oferta.getImagen(): "img/placeholder.png";
  
%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>perfilEmpresa</title>
    <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <link rel="stylesheet" href="css/perfilUsuario.css">
    <script src="js/perfilUsuario.js"></script>
</head>

<body class="bg-primary-subtle body-center">
	<jsp:include page="/WEB-INF/template/headerMobile.jsp" />
    <div class="profile" style="width:90%">
        <div class="profile-header">
            <img src="Imagenes?id=<%=oferta.getImagen()%>&tipo=ofertas" alt="Avatar" class="u-avatar">
            <div class="u-info">
                <h1><%=oferta.getNombre()%></h1> 
                <p><%=oferta.getNombreEmpresa()%></p>
            </div>
        </div>
        `
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
                        <input type="time" id="inputInicio" value="<%=oferta.getHorario().getHorarioInicio().toString()%>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputFin">Horario fin:</label>
                    </div>
                    <div class="info email-info" style="width: 50%">
                        <input type="time" id="inputFin" value="<%=oferta.getHorario().getHorarioFin().toString() %>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputRenumeracion">Remuneracion:</label>
                    </div>
                    <div class="info">
                        <input type="text"  id="inputRenumeracion" value="<%=String.valueOf(oferta.getRemuneracion())%>"  readonly>
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
                        <label for="inputKeywords">Keywords:</label>
                    </div>
                    <div class="info">
                        <textarea name="message" rows="2" cols="30" id="inputKeywords"
                        placeholder="" readonly><%=keywordStr%></textarea>
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
	                <%if(esPostulantePostulado){ %>
	                <a class="btn bg-primary mt-3 text-white" href="PostulacionMobile?n=<%=URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString())%>&p=<%=URLEncoder.encode(usuario.getNickname(), StandardCharsets.UTF_8.toString())%>">Ver Postulacion</a>
	                <%}%>
	                <%if(esPostulanteNoPostulado && !estaVencida){ %>
					<a class="btn bg-primary mt-3 text-white" href="PostularseMobile?n=<%=oferta.getNombre()%> ">Postularse</a>
					<%}%>
				</div>
				<div class="row">
					<%if(estaVencida){ 
					String mensaje = oferta.getEstado()==Estado.FINALIZADO? "La oferta fue finalizada":"ATENCION: la oferta esta vencida.";
					%>
						<div class="rounded-5 p-4 text-white bg-secondary">
		                    <div class="" style="text-align: center;">
		                        <h3 style="text-align: center; margin:auto;"><%=mensaje%></h3>
		                    </div>
		                </div> 
					<%}%>
				</div>
            </div>
        </div>
   	<script src="js/code.jquery.com_jquery-3.5.1.slim.min.js"></script>
    <script src="js/cdn.jsdelivr.net_npm_@popperjs_core@2.5.3_dist_umd_popper.min.js"></script>
    <script src="js/stackpath.bootstrapcdn.com_bootstrap_4.5.2_js_bootstrap.min.js"></script>
    <jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>