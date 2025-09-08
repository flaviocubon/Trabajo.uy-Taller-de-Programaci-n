<%@page import="webservices.DtPaquete.TiposPublicacion.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="webservices.DtPaquete"%>
<%@page import="webservices.DtAgrupa"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="java.util.Map"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>

<%
DtPaquete detallesPaquete = (DtPaquete) request.getAttribute("detallesPaquete");
DtUsuario usuario = (DtUsuario) session.getAttribute("usuarioLogueado");
%>

<div class="d-flex container mt-5 divPostulacion justify-content-center"
	style="height: fit-content;">
	<div class="rounded-5 p-4 text-white bg-primary custom-shadow">
		<form class="formOferta" method="POST">
			<div class="form-group card-oferta">
				<div class="card">
					<img src="Imagenes?id=<%=detallesPaquete.getImagen()%>&tipo=paquetes" class="card-img-top"
						alt="imagen de <%=detallesPaquete.getNombre()%>" id="foto"/>
					<div class="card-body">
						<h1 class="card-title"><%=detallesPaquete.getNombre()%></h1>
						<p class="card-text"><%=detallesPaquete.getDescripcion()%></p>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<table class="table">
								<thead class="thead-dark">
									<tr>
										<th scope="col">Tipo de Publicacion</th>
										<th scope="col">Cantidad</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (DtPaquete.TiposPublicacion.Entry entry : detallesPaquete.getTiposPublicacion().getEntry()) {
									%>
									<tr>
										<td><a href="Tipos?t=<%=URLEncoder.encode(entry.getValue().getTipo(), StandardCharsets.UTF_8.toString())%>"><%=entry.getValue().getTipo()%></a></td>
										<td><%=entry.getValue().getCant()%></td>
									</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</li>
						<li class="list-group-item">Periodo: <%=detallesPaquete.getPeriodoValidez()%>
							días
						</li>
						<li class="list-group-item">Descuento: <%=detallesPaquete.getDescuento()%>%
						</li>
						<li class="list-group-item">Costo: $<%=detallesPaquete.getCosto()%></li>
					</ul>
				</div>
				<div class="form-group d-flex btnsCompra pt-2">
					<jsp:include
						page="/WEB-INF/detallePaquete/detallePaqueteBotones.jsp" />
				</div>
			</div>
		</form>
	</div>
</div>