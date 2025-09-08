<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@page import="webservices.DtOfertaLaboral"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtPostulante"%>
<%@page import="webservices.DtPostulacion"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%DtOfertaLaboral oferta= (DtOfertaLaboral) request.getAttribute("oferta");  
  String linkImagen="";
  Set<String> keywords= new HashSet(oferta.getKeywords());
  XMLGregorianCalendar fechaG = oferta.getFecha();
  Date fechaAltaDate=new Date(fechaG.getYear()-1900, fechaG.getMonth(), fechaG.getDay());
  String fechaAlta="";
  String anio = String.valueOf(fechaAltaDate.getYear());
  String mes = fechaAltaDate.getMonth()<10 ? "0"+String.valueOf(fechaAltaDate.getMonth()) : String.valueOf(fechaAltaDate.getMonth());
  String dia= fechaAltaDate.getDay()<10 ? "0"+String.valueOf(fechaAltaDate.getDay()): String.valueOf(fechaAltaDate.getDay());
  fechaAlta=anio+"-"+mes+"-"+dia;
  fechaAlta=fechaAlta.substring(1);
  
  String keywordStr="";
  for(String key:keywords){
	  keywordStr+=key+ ", ";
  }
  if(keywordStr.length()>0)keywordStr=keywordStr.substring(0, keywordStr.length()-2);
  
  
  boolean esPostulanteNoPostulado=false;
  boolean esPostulantePostulado=false;
  boolean esEmpresaDuenia=false;
  
  DtUsuario usuario= (DtUsuario) session.getAttribute("usuarioLogueado");
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
    <div class="container mt-5" style="width: 25rem;">
        <div class="rounded-5 p-4 text-white bg-primary">
            <form method="post" class="form">
                <div class="form-group">
                    <label for="inputNombre">Nombre</label>
                    <input type="Text" class="form-control" id="inputNombre" value="<%=oferta.getNombre() %>" readonly>
                </div>
                <div class="form-group">
                    <label for="inputDescripcion">Descripcion</label>
                    <textarea name="message" rows="2" cols="30" class="form-control" id="inputDescripcion" readonly><%=oferta.getDescripcion()%></textarea>
                </div>
                <div class="form-group">
                    <label for="inputInicio">Horario inicio</label>
                    <input type="time" class="form-control" id="inputInicio" value="<%=oferta.getHorario().getHorarioInicio().toString() %>" readonly>
                </div>
                <div class="form-group">
                    <label for="inputFin">Horario fin</label>
                    <input type="time" class="form-control" value="18:00" id="inputFin" readonly>
                </div>
                <div class="form-group">
                    <label for="inputRenumeracion">Remuneracion</label>
                    <input type="number" class="form-control" id="inputRenumeracion" value="<%=String.valueOf(oferta.getRemuneracion())%>" min="0"
                        oninput="validity.valid||(value='');" readonly>
                </div>
                <div class="form-group">
                    <label for="inputDepartamento">Departamento</label>
                    <input type="text" class="form-control" id="inputDepartamento" value="<%=oferta.getDepartamento() %>" readonly>
                </div>
                <div class="form-group">
                    <label for="inputCiudad">Ciudad</label>
                    <input type="text" class="form-control" id="inputCiudad" value="<%=oferta.getCiudad() %>" readonly>
                </div>
                <div class="form-group">
                    <label for="keywords" class="">Keywords</label>
                    <textarea name="message" rows="2" cols="30" class="form-control" id="inputKeywords"
                        placeholder="keywords" readonly><%=keywordStr%></textarea>
                </div>
                <%if(esEmpresaDuenia){ %>
                <div class="form-group">
                    <label for="selectOption" class="">Postulaciones</label>
                    <select name="selectOption" id="selectOption" class="form-control" oninput="redirect()" multiple="multiple">
                    	<%for(DtPostulacion post:postulaciones){ %>
                        <option value="Postulacion?n=<%=URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString()) %>&p=<%=URLEncoder.encode(post.getPostulante(), StandardCharsets.UTF_8.toString()) %>"><%=post.getPostulante()%></option>
                        <%} %>
                    </select>
                </div>
                <%}%>
                <div class="form-group">
                    <label for="date">Fecha de alta</label>
                    <input type="date" class="form-control" value="<%=fechaAlta%>" id="date" readonly>
                </div>
                <%if(esPostulantePostulado){ %>
                <a class="btn btn-light mt-3" href="Postulacion?n=<%=URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString()) %>&p=<%=URLEncoder.encode(usuario.getNickname(), StandardCharsets.UTF_8.toString()) %>">Ver Postulacion</a>
                <%}%>
                <%if(esPostulanteNoPostulado){ %>
				<%}%>
				
                <div class="form-group btn-pad">
                    <a href="Oferta">
				<a class="btn btn-light mt-3" href="Postularse?n=<%=URLEncoder.encode(oferta.getNombre(), StandardCharsets.UTF_8.toString())%>">Postularse</a>
                        <button type="button" class="btn btn-light btn-lg">Ok</button>
                    </a>
                </div>
            </form>
        </div>
    </div>
    <script src="js/cdn.jsdelivr.net_npm_bootstrap@5.3.1_dist_js_bootstrap.bundle.min.js"></script>
   	<script src="js/code.jquery.com_jquery-3.5.1.slim.min.js"></script>
    <script src="js/cdn.jsdelivr.net_npm_@popperjs_core@2.5.3_dist_umd_popper.min.js"></script>
    <script src="js/stackpath.bootstrapcdn.com_bootstrap_4.5.2_js_bootstrap.min.js"></script>
</body>

</html>