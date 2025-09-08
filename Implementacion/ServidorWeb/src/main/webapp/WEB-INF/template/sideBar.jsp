<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtEmpresa"%>

<%@page import="java.util.Set"%>

<%
	Set<String> listaKeywords= (Set<String>) request.getAttribute("listaKeywords");
%>


<nav id="sidebarMenu" class="custom-sidebar collapse d-lg-block sidebar collapse .bg-info.bg-gradient custom-shadow" style="padding-top: 10%;">
        <div class="position-sticky">
          <div class="list-group list-group-flush mx-3 mt-4 ">
             <!-- contenedor links casos de uso -->
			<div class="border border-primary rounded-2 list-unstyled overflow-hidden">
		  		<jsp:include page="/WEB-INF/template/linksCasosDeUso.jsp" />
	  		</div>
            <div class="sidebarGroup">
              <div class="border border-primary rounded-2">
                <span class="list-group-item py-2 ripple"><b>Keywords</b></span>
                <%for(String keyword :listaKeywords){ %>
                    <a href="Oferta?k=<%=keyword%>" class="list-group-item list-group-item-action py-2 ripple"><span><%=keyword %></span></a>
                <%}%>
              </div>
          	</div>
          </div>
        </div>
    </nav>