<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <title>Alta de Usuario</title>
    <link rel="stylesheet" href="css/altaUsuario.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
    <script src="js/altaUsuario.js"></script>

</head>
<%
	String nickname = (String)request.getParameter("nickname") == null ? "":(String)request.getParameter("nickname");
	String nombre = (String)request.getParameter("nombre") == null ? "": (String)request.getParameter("nombre");
	String apellido = (String)request.getParameter("apellido") == null ? "": (String)request.getParameter("apellido");
	String contrasenia = (String)request.getParameter("contrasenia") == null ? "": (String)request.getParameter("contrasenia");
	String confirmacion = (String)request.getParameter("confirmacion") == null ? "": (String)request.getParameter("confirmacion");
	String correo = (String)request.getParameter("email") == null ? "": (String)request.getParameter("email");
	String tipo = (String)request.getParameter("tipoUsuario") == null ? "": (String)request.getParameter("tipoUsuario");
	String fecha = (String)request.getParameter("fecha") == null ? "": (String)request.getParameter("fecha");
	String nacionalidad = (String)request.getParameter("nacionalidad") == null ? "": (String)request.getParameter("nacionalidad");
	String descripcion = (String)request.getParameter("descripcion") == null ? "": (String)request.getParameter("descripcion");
	String link = (String)request.getParameter("url") == null ? "": (String)request.getParameter("url");
%>

<body class="bg-primary-subtle body-center">
    <jsp:include page="/WEB-INF/template/header.jsp" />
    <jsp:include page="/WEB-INF/template/sideBar.jsp" />
    <div class="container mt-5" style="width: 40rem;">
        <div class="rounded-5 p-4 text-white bg-primary">
        <% if(request.getAttribute("exito") != null || request.getParameter("mensaje") != null) {%>
        	<jsp:include page="/WEB-INF/mensajeAviso/Mensaje.jsp" />        	
        <% } %>
            <form id="formAlta" method="POST" enctype= "multipart/form-data">
                <div class="row g-3">
                    <div class="col">
                        <label for="nickname">Nickname:</label><br>
                        <input type="text" id="nickname" name="nickname" class="form-control" required value=<%=nickname%> ><br>
                        <div id="divExisteUser" class="alert alert-success" role="alert" hidden="true">
		  					<i id="existeUser"></i>
						</div>
                    </div>
                </div>
                <div class="row g-3">
                    <div class="col">
                        <label for="nombre">Nombre:</label><br>
                        <input type="text" id="nombre" name="nombre" class="form-control" required value="<%=nombre%>"><br>
                    </div>
                    <div class="col">
                        <label for="apellido">Apellido:</label><br>
                        <input type="text" id="apellido" name="apellido" class="form-control" required value="<%=apellido%>"><br>
                    </div>
                </div>
                <div class="row g-3">
                    <div class="col">
                        <label for="contraseña">Contraseña:</label><br>
                        <input type="password" id="contraseña" name="contrasenia" class="form-control" required value="<%=contrasenia%>"><br>
                    </div>
                    <div class="col">
                        <label for="confirmacion">Confirmación:</label><br>
                        <input type="password" id="confirmacion" name="confirmacion" class="form-control" required value="<%=confirmacion%>"><br>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Correo Electronico:</label><br>
                    <input type="email" id="email" name="email" class="form-control" required value="<%=correo%>"><br>
                    <div id="divExisteEmail" class="alert alert-success" role="alert" hidden="true">
		  				<i id="existeEmail"></i>
					</div>
                </div>
                <div class="form-group">

                </div>
                <div class="form-group">
                    <label for="imagen">Foto de Perfil (Opcional):</label><br>
                    <input name="imagen" type="file" accept="image/*" id="foto" class="form-control"><br>
                </div>
                <div class="form-group" id="tipoUser">
                    <label for="tipoUsuario">Tipo de Usuario:</label><br>
                    <div name="tipoUsuario">
                    <%if ("empresa".equals(tipo)){%>
                        <label for="postulante">Postulante:</label>
                        <input type="radio" onchange="postulante()" name="tipoUsuario" value="postulante">
                        <label for="empresa">Empresa:</label>
                        <input type="radio" onchange="empresa()" value="empresa" name="tipoUsuario" checked>
                    </div>
	                </div>
	                <div name="postulante" id="postulante" style="display: none">
	                    <div class="form-group">
	                        <label for="fecha">Fecha de Nacimiento:</label><br>
	                        <input type="date" id="fecha" name="fecha" class="form-control" value="<%=fecha%>"><br>
	                    </div>
	                    <div class="form-group">
	                        <label for="nacionalidad">Nacionalidad:</label><br>
	                        <input type="text" id="nacionalidad" name="nacionalidad" class="form-control" value="<%=nacionalidad%>"><br>
	                    </div>
	                </div>
	                <div name="empresa" id="empresa" style="display: block">
	                    <div class="form-group">
	                        <label for="descripcion">Descripcion:</label><br>
	                        <textarea id="descripcion" name="descripcion" class="form-control"
	                            placeholder="Somos una empresa..." required><%=descripcion%></textarea><br>
	                    </div>
	                    <div class="form-group">
	                        <label for="link">Link:</label><br>
	                        <input type="url" id="url" name="url" placeholder="trabajo.uy" class="form-control" value="<%=link%>"><br>
	                    </div>
	                </div>
                    <%} else { %>
                    	<label for="postulante">Postulante:</label>
                        <input type="radio" onchange="postulante()" name="tipoUsuario" value="postulante" checked>
                        <label for="empresa">Empresa:</label>
                        <input type="radio" onchange="empresa()" value="empresa" name="tipoUsuario">
	                </div>
	                <div name="postulante" id="postulante" style="display: block">
	                    <div class="form-group">
	                        <label for="fecha">Fecha de Nacimiento:</label><br>
	                        <input type="date" id="fecha" name="fecha" class="form-control" required value="<%=fecha%>"><br>
	                    </div>
	                    <div class="form-group">
	                        <label for="nacionalidad">Nacionalidad:</label><br>
	                        <input type="text" id="nacionalidad" name="nacionalidad" class="form-control" required value="<%=nacionalidad%>"><br>
	                    </div>
	                </div>
	                <div name="empresa" id="empresa" style="display: none">
	                    <div class="form-group">
	                        <label for="descripcion">Descripcion:</label><br>
	                        <textarea id="descripcion" name="descripcion" class="form-control"
	                            placeholder="Somos una empresa..."><%=descripcion%></textarea><br>
	                    </div>
	                    <div class="form-group">
	                        <label for="link">Link:</label><br>
	                        <input type="url" id="url" name="url" placeholder="trabajo.uy" class="form-control" value="<%=link%>"><br>
	                    </div>
	                </div>
                    <% } %>
                <input id="botonAceptar" type="submit" class="btn btn-light" value="Aceptar">
            </form>
        </div>
    </div>
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>