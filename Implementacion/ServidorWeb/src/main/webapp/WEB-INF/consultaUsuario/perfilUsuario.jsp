<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.List"%>
<%@page import="webservices.DtAgrupa"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtPostulante"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="webservices.DtPostulacion"%>
<%@page import="webservices.DtCompra"%>
<%@page import="webservices.DtOfertaLaboral"%>
<%@page import="webservices.Estado"%>
<%@page import="webservices.OfertaDAO"%>
<%@page import="webservices.PostulacionDAO"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar" %>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<% DtUsuario user = (DtUsuario) request.getAttribute("detalleUsuario");
	String estadoConexion = "Postulante";
	String nickname = user.getNickname();
	String nombre = user.getNombre();
	String apellido = user.getApellido();
	String mail = user.getMail();
	Date fechaNac = new Date();	
	String fechaFormato = "";
	String fechaDatePicker = "";
	DateFormat formatoNacimiento = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	String nacionalidad = "";
	String descripcion = "";
	String link = "";
	Set<DtCompra> compras = null;
	Set<DtPostulacion> postulaciones = null;
	List<PostulacionDAO> postFinalizadas = null;
	Set<DtOfertaLaboral> ofertas = null;
	List<OfertaDAO> ofertasFinalizadas = null;
	DtUsuario usuarioLogueado = (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
	if(user instanceof DtPostulante){
		XMLGregorianCalendar fechaGregorian = ((DtPostulante)user).getFechaNacimiento();
		fechaNac = new Date(fechaGregorian.getYear() - 1900,fechaGregorian.getMonth()-1, fechaGregorian.getDay());
		fechaFormato = formatter.format(fechaNac);
		fechaDatePicker = formatoNacimiento.format(fechaNac);
		nacionalidad = ((DtPostulante)user).getNacionalidad();
		postulaciones = (Set<DtPostulacion>) request.getAttribute("postulaciones");
		postFinalizadas = (List<PostulacionDAO>) request.getAttribute("postFinalizadas");
		if(usuarioLogueado != null){
			estadoConexion = (usuarioLogueado.getNickname().equals(nickname))?"PropioPostulante":"Postulante";
		}else{
			estadoConexion = "Postulante";
		}
	}else if(user instanceof DtEmpresa){
		descripcion = ((DtEmpresa)user).getDescripcion();
		link = ((DtEmpresa)user).getLink();
		ofertas = (Set<DtOfertaLaboral>) request.getAttribute("ofertas");
		compras = (Set<DtCompra>) request.getAttribute("compras");
		ofertasFinalizadas = (List<OfertaDAO>) request.getAttribute("ofertasFinalizadas");
		if(usuarioLogueado != null){
			estadoConexion = (usuarioLogueado.getNickname().equals(nickname))?"PropioEmpresa":"Empresa";
		}else{
			estadoConexion = "Empresa";
		}
	}
	String mensaje = (String) request.getAttribute("mensaje");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <title>perfilUsuario</title>
    <link rel="stylesheet" href="css/perfilUsuario.css">
    <script src="js/perfilUsuario.js"></script>
</head>

<body class="bg-primary-subtle body-center">
    <jsp:include page="/WEB-INF/template/header.jsp" />
    <jsp:include page="/WEB-INF/template/sideBar.jsp" />
    <div class="profile">
        <div class="profile-header">
            <img src="Imagenes?id=<%=user.getImagen()%>&tipo=usuarios" alt="Avatar" class="u-avatar">
            <div class="u-info" style="width: 100%">
            	<div class="d-flex" style="flex-wrap: nowrap; flex-direction: row; width: 100%; justify-content: space-between;">
		            <h1><%=nombre + " " + apellido%></h1>
            </div>
                <p><%=nickname%></p>
                <p><%=mail%></p>
            </div>
            	<% if (usuarioLogueado != null && !usuarioLogueado.getNickname().equals(user.getNickname())) {
						if (usuarioLogueado.getSeguidos().contains(user.getNickname())){ %>
				            <i class="bi bi-heart-fill" onclick="window.location.href = 'Seguir?seguir=<%=URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString())%>&origen=perfil'" style="font-size: 60px; color:"#FF1E1E;"></i>	
		            <%} else {%>
		        			<i class="bi bi-heart" onclick="window.location.href = 'Seguir?seguir=<%=URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString())%>&origen=perfil'" style="font-size: 60px"></i>	
		            <%} %>
	            <%} %>
        </div>
        <div class="alert alert-danger align-self-center" id="mensajeError" role="alert" style="display: none;">
	         <%=mensaje%>
	    </div>
        <div class="profile-tabs">
        	<button id="perfilbtn" class="tablink active" onclick="openTab('perfil')">Perfil</button>
        	<button id="seguidosbtn" class="tablink" onclick="openTab('seguidos')">Seguidos</button>
        	<button id="seguidoresbtn" class="tablink" onclick="openTab('seguidores')">Seguidores</button>
        	<%if(estadoConexion.equals("Empresa")){%>
        		<button id="ofertasbtn" class="tablink" onclick="openTab('ofertas')">Publicaciones</button>
        	<%}else if(estadoConexion.equals("PropioPostulante")){%>
        		       	<button id="ofertasbtn" class="tablink" onclick="openTab('ofertas')">Postulaciones</button>
        	<%}else if(estadoConexion.equals("PropioEmpresa")){%>
           				<button id="ofertasbtn" class="tablink" onclick="openTab('ofertas')">Publicaciones</button>
            			<button id="paquetesbtn" class="tablink" onclick="openTab('paquetes')">Compras de Paquetes</button>
            <%}%>
        </div>
        <div class="tabcontent" id="perfil">
	        <%if(estadoConexion.equals("PropioEmpresa") || estadoConexion.equals("PropioPostulante")){ %>
	        <form class="form" method="POST" id="idform" enctype= "multipart/form-data" onsubmit="return comprobarContrasenas()">
	        	<div class="profile-form">
	                <div class="row">
	                    <div class="label">
	                        <label for="nickname">Nickname:</label>
	                    </div>
	                    <div class="info">
	                        <input type="text" id="nickname" style="outline: none;" value="<%=nickname%>" readonly>
	                        <div class="edit-button" style="visibility: collapse;">
	                            <button type="button" onclick="enableEdit('name')"></button>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="email">Email:</label>
	                    </div>
	                    <div class="info email-info">
	                        <input type="email" id="email" style="outline: none;" value="<%=mail%>" readonly>
	                        <div class="edit-button" style="visibility: collapse;">
	                            <button type="button" onclick="enableEdit('name')"></button>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="lastname">Cambiar foto de perfil:</label>
	                    </div>
	                    <div class="info">
	                    	<input name="imagen" type="file" accept="image/*" id="foto" class="form-control">
	                        <div class="edit-button" style="visibility: collapse;">
	                            <button type="button" onclick="enableEdit('foto')"></button>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="name">Nombre:</label>
	                    </div>
	                    <div class="info">
	                        <input type="text" id="name" name="nombre" value="<%=nombre%>" readonly required>
	                        <div class="edit-button">
	                            <button type="button" onclick="enableEdit('name')"></button>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="lastname">Apellido:</label>
	                    </div>
	                    <div class="info">
	                        <input type="text" id="lastname" name="apellido" value="<%=apellido%>" readonly required>
	                        <div class="edit-button">
	                            <button type="button" onclick="enableEdit('lastname')"></button>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="pass">Contraseña:</label>
	                    </div>
	                    <div class="info">
	                        <div class="form-group password-toggle">
	                            <input type="password" class="form-control" id="password-input" name="pass" value="" placeholder="**********" readonly>
	                            <span class="toggle-password" ><i class="bi bi-eye-slash" onclick="visibilidadClave('ojoPass', 'password-input')" id="ojoPass"></i></span>
	                        </div>
	                        <div class="edit-button">
	                            <button type="button" onclick="enableEdit('password-input')"></button>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="pass">Confirmar Contraseña:</label>
	                    </div>
	                    <div class="info">
	                        <div class="form-group password-toggle">
	                            <input type="password" class="form-control" id="confirmacion" name="confirmacion" value="" placeholder="**********" readonly>
	                            <span class="toggle-password" ><i class="bi bi-eye-slash" onclick="visibilidadClave('ojoConfirma', 'confirmacion')" id="ojoConfirma"></i></span>
	                        </div>
	                        <div class="edit-button">
	                            <button type="button" onclick="enableEdit('confirmacion')"></button>
	                        </div>
	                    </div>
	                </div>
	                <%if(estadoConexion.equals("PropioEmpresa")){ %>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="descripcion">Descripción:</label>
	                    	</div>
	                    	<div class="info">
	                        	<textarea id="desc" name="descripcion" readonly required><%=descripcion%></textarea>
	                        	<div class="edit-button">
	                            	<button type="button" onclick="enableEdit('desc')"></button>
	                        	</div>
	                    	</div>
	                	</div>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="link">Link:</label>
	                    	</div>
	                    	<div class="info">
	                        	<input type="text" id="link" name="link" value="<%=link%>" readonly>
	                        	<div class="edit-button">
	                            	<button type="button" onclick="enableEdit('link')"></button>
	                        	</div>
	                    	</div>
	                	</div>
	                <%} %>
	                <%if(estadoConexion.equals("PropioPostulante")){ %>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="fecha">Fecha de Nacimiento:</label>
	                    	</div>
	                    	<div class="info">
	                        	<input type="date" id="fecha" name="fecha" value="<%=fechaDatePicker%>" readonly required>
	                        	<div class="edit-button">
	                            	<button type="button" onclick="enableEdit('fecha')"></button>
	                        	</div>
	                    	</div>
	                	</div>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="nacionalidad">Nacionalidad:</label>
	                    	</div>
	                    	<div class="info">
	                        	<input type="text" id="nac" name="nacionalidad" value="<%=nacionalidad%>" readonly required>
	                        	<div class="edit-button">
	                            	<button type="button" onclick="enableEdit('nac')"></button>
	                        	</div>
	                    	</div>
	                	</div>
	                <%} %>
	                <div class="row-button">
	                   <button type="submit" class="btn btn-primary" id="btnGuardar">Guardar</button>
	                </div>
	            </div>
	        </form>
	        <%}else {%>
	        	<div class="profile-form">
	                <div class="row">
	                    <div class="label">
	                        <label for="nickname">Nickname:</label>
	                    </div>
	                    <div class="info">
   							<span id="nickname" class="bg-white border border-secondary-subtle border-1 rounded-1 w-100 p-2"><%=nickname%></span>
						</div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="email">Email:</label>
	                    </div>
	                    <div class="info">
   							<span id="email" class="bg-white border border-secondary-subtle border-1 rounded-1 w-100 p-2"><%=mail%></span>
						</div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="name">Nombre:</label>
	                    </div>
	                    <div class="info">
   							<span id="name" class="bg-white border border-secondary-subtle border-1 rounded-1 w-100 p-2"><%=nombre%></span>
						</div>
	                </div>
	                <div class="row">
	                    <div class="label">
	                        <label for="lastname">Apellido:</label>
	                    </div>
	                    <div class="info">
   							<span id="lastname" class="bg-white border border-secondary-subtle border-1 rounded-1 w-100 p-2"><%=apellido%></span>
						</div>
	                </div>
	                <%if(estadoConexion.equals("Empresa")){ %>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="descripcion">Descripción:</label>
	                    	</div>
	                    	<div class="info">
	                        	<textarea id="desc" style="outline: none;" readonly><%=descripcion%></textarea>
	                    	</div>
	                	</div>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="link">Link:</label>
	                    	</div>
	                    	<div class="info">
   								<span id="link" class="bg-white border border-secondary-subtle border-1 rounded-1 w-100 p-2"><%=link%></span>
							</div>
	                	</div>
	                <%} %>
	                <%if(estadoConexion.equals("Postulante")){ %>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="fecha">Fecha de Nacimiento:</label>
	                    	</div>
	                    	<div class="info">
   								<span id="fecha" class="bg-white border border-secondary-subtle border-1 rounded-1 w-100 p-2"><%=fechaFormato%></span>
							</div>
	                	</div>
	                	<div class="row">
	                    	<div class="label">
	                        	<label for="nacionalidad">Nacionalidad:</label>
	                    	</div>
	                    	<div class="info">
   								<span id="nac" class="bg-white border border-secondary-subtle border-1 rounded-1 w-100 p-2"><%=nacionalidad%></span>
							</div>
	                	</div>
	                <%} %>
	            </div>
	        <%} %>
        </div>
        <div class="tabcontent" id="seguidos">
        	<div class="d-flex flex-wrap">
        	<%
        	ArrayList<DtUsuario> seguidos = (ArrayList<DtUsuario>) request.getAttribute("seguidos");
        	for(DtUsuario userSeg : seguidos) {
        	%>
	        		 <div class="card cardUser p-2">
					    <img class="card-img-top" src="Imagenes?id=<%=userSeg.getImagen()%>&tipo=usuarios" alt="Card image cap">
					    <div class="card-body">
					      <h4 class="card-title"><%=userSeg.getNickname() %></h4>
					      <h5><%=userSeg.getNombre() + " " + userSeg.getApellido() %></h5>
					 	</div>
					 </div>
	        <%}%>
	        </div>
        </div>
        <div class="tabcontent" id="seguidores">
			<div class="d-flex flex-wrap">
        	<%
        	ArrayList<DtUsuario> seguidores = (ArrayList<DtUsuario>) request.getAttribute("seguidores");
        	for(DtUsuario userSeg : seguidores) {
        	%>
	        		 <div class="card cardUser p-2">
					    <img class="card-img-top" src="Imagenes?id=<%=userSeg.getImagen()%>&tipo=usuarios" alt="Card image cap">
					    <div class="card-body">
					      <h4 class="card-title"><%=userSeg.getNickname() %></h4>
					      <h5><%=userSeg.getNombre() + " " + userSeg.getApellido() %></h5>
					 	</div>
					 </div>
	        <%}%>
	    	</div>
        </div>
        <%if (!estadoConexion.equals("Postulante")){%>
        	<div class="tabcontent" id="ofertas">
             	<%if(estadoConexion.equals("Empresa") || estadoConexion.equals("PropioEmpresa")){%>
                	<div class="job-list">
                    	<ul class="job-list">
                    	<%for(DtOfertaLaboral dtOferta : ofertas){ %>
                    		<%if(estadoConexion.equals("PropioEmpresa") || dtOferta.getEstado() == Estado.CONFIRMADO) {%>
	                        	<li class="job-list-item">
	                            	<a style="color: inherit; text-decoration: inherit;" href="Oferta?n=<%=URLEncoder.encode(dtOferta.getNombre(), StandardCharsets.UTF_8.toString())%>">
	                                	<img src="Imagenes?id=<%=dtOferta.getImagen()%>&tipo=ofertas" alt="Oferta1">
	                                	<div>
	                                    	<h3><%=dtOferta.getNombre()%></h3>
	                                    	<h6 class="text-muted"><%=dtOferta.getEstado()%></h6>
	                                	</div>
	                            	</a>
	                        	</li>
                        	<%} %>
						<%} %>
						<%if(estadoConexion.equals("PropioEmpresa")){ %>
							<%for(OfertaDAO ofert : ofertasFinalizadas){ %>
								<li class="job-list-item" style="background-color: lightgray;">
	                            	<a style="color: inherit; text-decoration: inherit;" href="Oferta?n=<%=URLEncoder.encode(ofert.getNombreOferta(), StandardCharsets.UTF_8.toString())%>">
	                                	<img src="Imagenes?id=<%=ofert.getImagen()%>&tipo=ofertas" alt="Oferta1">
	                                	<div>
	                                    	<h3><%=ofert.getNombreOferta()%></h3>
	                                    	<h6 class="text-muted"><%="Finalizada"%></h6>
	                                	</div>
	                            	</a>
	                        	</li>
							<%} %>
						<%} %>
                    	</ul>
                	</div>
             	<% }else{%>
                	<div class="job-list">
                    	<ul class="job-list">
                    	<%for(DtPostulacion dtPost : postulaciones){ %>
                        	<li class="job-list-item">
                            	<a href="Postulacion?n=<%=URLEncoder.encode(dtPost.getNombreOferta(), StandardCharsets.UTF_8.toString())%>&p=<%=URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString())%>" class="sinEstilo">
                                	<img src="Imagenes?id=<%=dtPost.getImagenOferta()%>&tipo=ofertas" alt="Oferta1">
                                	<div>
                                    	<h3>Oferta: <%=dtPost.getNombreOferta()%></h3>
                                    	<%XMLGregorianCalendar fechaG = dtPost.getFechaPostulacion();
                                    		Date fechaPost = new Date(fechaG.getYear()-1900, fechaG.getMonth()-1, fechaG.getDay()); %>
                                    	<p>Fecha Postulación: <%=formatter.format(fechaPost)%></p>
                                	</div>
                            	</a>
                            	<%if (dtPost.getOrden() != 0){%>
                            	<button class="btn btn-primary" onclick="window.open('PDFFile?oferta=<%=URLEncoder.encode(dtPost.getNombreOferta(), StandardCharsets.UTF_8.toString())%>')">Obtener PDF</button>
                            	<%}%>
                        	</li>
                        <%} %>
                        <%for(PostulacionDAO post : postFinalizadas){ %>
                        	<li class="job-list-item" style="background-color: lightgray;">
                            	<a href="PostulacionFinalizada?oferta=<%=URLEncoder.encode(post.getOferta().getNombreOferta(), StandardCharsets.UTF_8.toString())%>&p=<%=URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString())%>" class="sinEstilo">
                                	<img src="Imagenes?id=<%=post.getOferta().getImagen()%>&tipo=ofertas" alt="Oferta1">
                                	<div>
                                    	<h3>Oferta: <%=post.getOferta().getNombreOferta()%> (Oferta Finalizada)</h3>
                                    	<%
                                    	XMLGregorianCalendar fechaG = post.getFechaPostulacion();
                                    	Date fechaPost = new Date(fechaG.getYear()-1900, fechaG.getMonth()-1, fechaG.getDay());
                                    	fechaG = post.getOferta().getFechaBaja();
                                    	Date fechaFin = new Date(fechaG.getYear()-1900, fechaG.getMonth()-1, fechaG.getDay());
                                    		%>
                                    	<p>Fecha Postulación: <%=formatter.format(fechaPost)%></p>
                                    	<p>Fecha de Finalización: <%=formatter.format(fechaFin)%></p>
                                	</div>
                            	</a>
                        	</li>
                        <%} %>
                    </ul>
                </div>
               	<%} %>
        	</div>
        	<%if(estadoConexion.equals("PropioEmpresa")){ %>
        		<div class="tabcontent" id="paquetes">
                    		<ul class="job-list">
                    		<%for(DtCompra dtCompra : compras){ %>
                        		<li class="job-list-item">
                            		<a href="DetallesPaquete?paquete=<%=URLEncoder.encode(dtCompra.getProducto(), StandardCharsets.UTF_8.toString()) %>" class="sinEstilo">
                                		<img src="Imagenes?id=<%=dtCompra.getImagenPaquete()%>&tipo=paquetes" alt="Oferta1">
                                		<div>
                                    		<h3><b><%=dtCompra.getProducto()%></b></h3>
                                    		<ul class="list-group list-group-flush">
                                    			<%
                                    			Date fecha = formatoNacimiento.parse(dtCompra.getFechaVencimiento());
                                    			String fechaFinal = formatter.format(fecha);
                                    			%>
                                    			<li class="list-group-item">Fecha Vencimiento: <%=fechaFinal%></li>
												<li class="list-group-item">
													<table class="table">
														<thead class="thead-dark">
															<tr>
																<th scope="col">Tipo de Publicación</th>
																<th scope="col">Cantidad</th>
															</tr>
														</thead>
														<tbody>
															<%for (DtCompra.TiposNoCanjeados.Entry entry : dtCompra.getTiposNoCanjeados().getEntry()) {%>
															<tr>
																<td><%=entry.getValue().getTipo()%></td>
																<td><%=entry.getValue().getCant()%></td>
															</tr>
															<%}%>
															</tbody>
													</table>
												</li>
											</ul>
                                		</div>
                            		</a>
                        		</li>
                        	<%} %>
                    	</ul>
        		</div>
        	<%} %>
        <% }%> 
    </div>
    <script>
        let passwordVisible = false;

        function visibilidadClave(idOjo, idCampo) {
        	const toggleButton = document.getElementById(idOjo);
            const passwordInput = document.getElementById(idCampo);

            passwordVisible = !passwordVisible;
            if (passwordVisible) {
            	toggleButton.className = "bi bi-eye";
                passwordInput.type = 'text';
                //toggleButton.innerHTML = '<i class="fas fa-eye"></i>';
            } else {
            	toggleButton.className = "bi bi-eye-slash";
                passwordInput.type = 'password';
                //toggleButton.innerHTML = '<i class="fas fa-eye-slash"></i>';
            }
        };
        function comprobarContrasenas() {
            let password = document.getElementById("password-input").value;
            let confirmacion = document.getElementById("confirmacion").value;

            if (password === confirmacion) {
            	document.getElementById("mensajeError").style.display = "none";
                return true;
            } else {
            	mostrarError("Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
                return false;
            }
        }
        function mostrarError(mensajeCliente){
        	let mensaje = mensajeCliente || "<%=mensaje%>";
        	if (!mensaje) return;
        	const mensajeError = document.getElementById("mensajeError");
        	mensajeError.textContent = mensaje;
        	mensajeError.style.display = "block";
        }
        if("<%=mensaje%>") mostrarError();

    </script>
    <script src="js/code.jquery.com_jquery-3.5.1.slim.min.js"></script>
    <script src="js/stackpath.bootstrapcdn.com_bootstrap_4.5.2_js_bootstrap.min.js"></script>
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>