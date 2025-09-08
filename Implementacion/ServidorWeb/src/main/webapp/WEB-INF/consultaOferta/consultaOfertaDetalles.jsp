<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<%@page import="webservices.DtOfertaLaboral"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
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
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Comparator" %>

<%DtOfertaLaboral oferta= (DtOfertaLaboral) request.getAttribute("oferta");  
  String linkImagen="";
  Set<String> keywords= new HashSet<String>(oferta.getKeywords());
  XMLGregorianCalendar fechaG = oferta.getFecha();
  Date fechaAltaDate=new Date(fechaG.getYear()-1900, fechaG.getMonth()-1, fechaG.getDay());
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
  
  DtUsuario usuario= (DtUsuario) session.getAttribute("usuarioLogueado");
  if(usuario!=null){
	  if(usuario.getClass() == DtPostulante.class){
		  DtPostulante postulante= (DtPostulante) usuario;
		  Set<String> ofertasPostuladas= new HashSet<String>(postulante.getNombreOfertas());
		  if(ofertasPostuladas.contains(oferta.getNombre())){
			  esPostulantePostulado=true;
		  }else{
			  esPostulanteNoPostulado=true;
		  }
	  }
	  else{
		  DtEmpresa empresa= (DtEmpresa) usuario;
		  Set<String> ofertasPropias= new HashSet<String>(empresa.getNombreOfertas());
		  esEmpresaDuenia=ofertasPropias.contains(oferta.getNombre());
	  }
  }

  Set<DtPostulacion> postulacionesAux= new HashSet<DtPostulacion>(oferta.getPostulaciones());
  ArrayList<DtPostulacion> postulaciones = new ArrayList<DtPostulacion>(postulacionesAux);
  postulaciones.sort(Comparator.comparing(DtPostulacion::getOrden));
  Map<String,String> imagenesPostulantes= (Map<String,String>) request.getAttribute("imagenesPostulantes");
  String tipo=oferta.getTipo();
  String nombrePaquete=null;
  String imagenPaquete=null;
  if(oferta.getPaquete()!=null && !oferta.getPaquete().equals("")) {
	  nombrePaquete=oferta.getPaquete();
	  Object imagen= request.getAttribute("imagenPaquete");
	  imagenPaquete= imagen!=null&& !imagen.equals("") ? (String) imagen: null;
  }
  //String imagenOferta= oferta.getImagen()!=null && !oferta.getImagen().equals("") ? oferta.getImagen(): "img/placeholder.png";
  
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

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Oferta</title>
    <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <link rel="stylesheet" href="css/perfilUsuario.css">
    <script src="js/perfilUsuario.js"></script>
</head>

<body class="bg-primary-subtle body-center">
	<jsp:include page="/WEB-INF/template/header.jsp" />
  	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
    <div class="profile">
        <div class="profile-header">
            <img src="Imagenes?id=<%=oferta.getImagen()%>&tipo=ofertas" alt="Avatar" class="u-avatar">
            <div class="u-info">
                <h1><%=oferta.getNombre()+"  "%>
                 <% if(userLogged){%>
                <i onclick="window.location.href = 'Favorito?fav=<%=urlDetalles%>&origen=detalles';"  style="position: relative; height:90px; <%=color%>" class="<%=icon%>"></i>
                <%=oferta.getCant_favoritos()%>
                <%}%> 
                </h1> 
                <p><%=oferta.getNombreEmpresa()%></p>
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
                        <input type="time" id="inputInicio" value="<%=oferta.getHorario().getHorarioInicio().toString()%>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="label">
                        <label for="inputFin">Horario fin:</label>
                    </div>
                    <div class="info email-info">
                        <input type="time" id="inputFin" value="<%=oferta.getHorario().getHorarioFin().toString() %>" readonly>
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
                    <div class="label">
                        <label>Tipo de publicacion: </label>
                    </div>
                    <div class="info">
						<div class="rounded-5 p-4 text-white bg-primary">
		                    <div class="">
		                     <a href="Tipos?t=<%=URLEncoder.encode(tipo,"UTF-8")%>" class="text-white">
		                        <h3><%=tipo%></h3>
		                       </a>
		                    </div>
		                </div> 
                    </div>
                </div>
                <div class="row">
	                <%if(esPostulantePostulado){ %>
	                <a href="Postulacion?n=<%=URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString())%>&p=<%=URLEncoder.encode(usuario.getNickname(), StandardCharsets.UTF_8.toString())%>">
	                	<div class="rounded-5 p-4 text-white bg-primary">
		                    <div class="" style="text-align: center;">
		                        <h3 style="text-align: center; margin:auto;">Ver Postulacion</h3>
		                    </div>
		                </div> 
	                </a>
	                <%}%>
	                <%if(esPostulanteNoPostulado && !estaVencida){ %>
	                <a href="Postularse?n=<%=oferta.getNombre()%> ">
	                	<div class="rounded-5 p-4 text-white bg-primary mt-3">
		                    <div class="" style="text-align: center;">
		                        <h3 style="text-align: center; margin:auto;">Postularse</h3>
		                    </div>
		                </div> 
					</a>
					<%}%>
				</div>
				<%if(esEmpresaDuenia&& nombrePaquete!=null){ %>
                <div class="row">
                    <div class="label">
                        <label>Paquete en el que se compro: </label>
                    </div>
                    <div class="info">
					<div class="col mb-4">
                    	<div class="card mb-4" style="width:50%">
	                        <a href="DetallesPaquete?paquete=<%=URLEncoder.encode(nombrePaquete,"UTF-8")%>" class="sinEstilo">
	                            <img class="card-img-top" src="Imagenes?id=<%=imagenPaquete %>&tipo=paquetes" alt="Card image cap">
	                            <div class="card-body">
	                                <h5 class="card-title" ><%=nombrePaquete%></h5>
	                            </div>
	                        </a>
                    	</div>
                	</div>
                    </div>
                </div>
                <%}%>
				<div class="row">
					<%if(estaVencida){ 
					String mensaje = oferta.getEstado()==Estado.FINALIZADO? "La oferta fue finalizada":"ATENCION: la oferta esta vencida.";
					%>
						<div class="rounded-5 p-4 text-white bg-secondary mt-3">
		                    <div class="" style="text-align: center;">
		                        <h3 style="text-align: center; margin:auto;"><%=mensaje%></h3>
		                    </div>
		                </div> 
					<%}%>
				</div>
            </div>
            <%if(esEmpresaDuenia && esFinalizable){ %>
		        <div class="row mt-3" style="margin: auto;   display: flex; justify-content: center; align-items: center;">    	
   		        	<form method="post" class="inline">
   		        		<input type="hidden" name="nOferta" value="<%=nombre%>">
		        		<button type="submit" name="finalizar" value="true" style="	background: none; color: inherit;border: none;padding: 0;font: inherit;cursor: pointer;outline: inherit;">
							<div class="rounded-5 p-4 text-white bg-primary">
	                   		 <div class="" style="text-align: center;">
			        			<h3 style="text-align: center; margin:auto;">Finalizar Oferta</h3>
		                    </div>
               			</div> 
						</button>
					</form>
				</div>
	        <%} %>
        </div>
        <%if(esEmpresaDuenia && !postulaciones.isEmpty()){ %>
        <div class="tabcontent" id="postulaciones" style="width: 100%;">
            <div class="container user-profile">
                <div class="job-list-title">
                    Postulaciones
                </div>
                <div class="job-list">
                <form method="POST">
                    <ul id="mySortable"class="job-list">
                    <%int j=1;%>
                    <%for(DtPostulacion postulacion: postulaciones){ 
                    %>
                        <li draggable ="true" class="job-list-item d-flex flex-wrap justify-content-start" style="align-content: center; display: flex; flex-wrap: wrap; height:25%; width: auto; max-height:200px">
                            <a target="_blank" rel="noopener" href="Postulacion?n=<%=URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString()) %>&p=<%=URLEncoder.encode(postulacion.getPostulante(), StandardCharsets.UTF_8.toString()) %>" class="sinEstilo">
                                <div>
                                	<img src="Imagenes?id=<%=imagenesPostulantes.get(postulacion.getPostulante())%>&tipo=usuarios" alt="Oferta1" style="display: flex; flex-wrap: wrap; height:60%; width: 60%; max-height:200px; max-width:200px" id="hp">
                                	<h3><%=postulacion.getPostulante()%></h3>
                                	<h3><%=postulacion.getOrden()%></h3>
                                </div>
                            </a>
                         <input type="hidden" name="<%=postulacion.getPostulante() %>" value=<%=j%>></li>
                         <%j++;%>
                    <%}%>
                    </ul>
                    <%if(estaVencida&& oferta.getEstado()==Estado.CONFIRMADO){%>
                    	<input type="hidden" name="nOferta" value="<%=nombre%>">
                    	<input type="hidden" value="si" name="ordenPostulantes">
                    	<input type="submit" value="Confirmar orden de Postulantes" class="btn btn-primary login-button">
                    <%}%>
                 </form>
                </div>
            </div>
        </div>
        <%}%>
       
   	<script src="js/code.jquery.com_jquery-3.5.1.slim.min.js"></script>
   	<script src="js/ajaxjquery.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.ui.touch-punch.min.js"></script>
    <script src="js/cdn.jsdelivr.net_npm_@popperjs_core@2.5.3_dist_umd_popper.min.js"></script>
    <script src="js/stackpath.bootstrapcdn.com_bootstrap_4.5.2_js_bootstrap.min.js"></script>
    <script>
    $(document).ready(function(){
    	  var list = $('#mySortable'),
    	      updatePosition = function() {
    	        list.children().each(function(i, e){
    	          $(this).children('input[type="hidden"]').val(++i);
    	        });
    	      };

    	  list.sortable({
    	    update: updatePosition
    	  });
    	});
    </script> 
</body>

</html>