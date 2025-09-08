<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%@page import="java.util.ArrayList"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%
ArrayList<DtUsuario> usuarios = (ArrayList<DtUsuario>) request.getAttribute("usuarios");
%>
<head>
<jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
<title>consultaUsuario</title>
<link rel="stylesheet" href="css/consultaUsuario.css">
</head>

<body class="bg-primary-subtle body-center">
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
	<div class="containerUsers container mt-5">
		<div class="job-list-title mx-5">
			<label>Usuarios</label>
		</div>
		<div class="job-list mx-5">
			<ul class="job-list">
				<%
				for (DtUsuario usuario : usuarios) {
					String nickname = usuario.getNickname();
				%>
				<li>
					<div class="user-card">
						 <img src="Imagenes?id=<%=usuario.getImagen()%>&tipo=usuarios" alt="Usuario1">
							<div class="user-info">
								<div class="d-flex"
									style="flex-direction: row; flex-wrap: nowrap; justify-content: space-between;"
									flex-direction:="">
									<h3>
										<a href="PerfilUsuario?nickname=<%=URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString())%>" class="sinEstilo linkCard">
											<%=nickname%>
										</a>
									</h3>
									<%
									DtUsuario user = (DtUsuario) session.getAttribute("usuarioLogueado");
									if (user != null && !nickname.equals(user.getNickname())) {
										if (user.getSeguidos().contains(nickname)) {
									%>
									<div class="btnContainer">
										<i id="seguir"
											class="bi bi-heart-fill btnSeg" onclick="window.location.href = 'Seguir?seguir=<%=URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString())%>';" style="font-size: 30px; color: #FF1E1E;"></i>
									</div>
									<%
									} else {
									%>
									<div class="btnContainer">
										<i id="seguir"
											class="bi bi-heart btnSeg" onclick="window.location.href = 'Seguir?seguir=<%=URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString())%>';" style="font-size: 30px;"></i>
									</div>
								<%
									}
								}
								%>
								</div>
								<ul>
									<li><%=usuario.getNombre() + " " + usuario.getApellido()%></li>
									<li><%=usuario.getMail()%></li>
								</ul>
							</div>
					</div>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>