<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <link rel="stylesheet" href="css/index.css">
 
    <title>Pagina Principal</title>
</head>

<body class="bg-primary-subtle body-center">
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<jsp:include page="/WEB-INF/template/sideBar.jsp" />
    <div class="container mt-5 headerInicio">
        <h1>Bienvenido a Trabajo.uy</h1>
        <div class="container mt-5">
            <div class="row">
                <div class="col mb-4">
                    <div class="card mb-4">
                        <a href="ConsultaUsuario" class="sinEstilo">
                            <img class="card-img-top" src="https://tinyurl.com/45nsf34m" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Usuarios</h5>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col mb-4">
                    <div class="card mb-4">
                        <a href="Oferta" class="sinEstilo">
                            <img class="card-img-top" src="https://tinyurl.com/4n2vpurk" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Ofertas Laborales</h5>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col mb-4">
                    <div class="card mb-4">
                        <a href="ConsultaPaquetes" class="sinEstilo">
                            <img class="card-img-top" src="https://shorturl.at/ceCD2" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Paquetes de Tipos de Publicacion</h5>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            </button>
        </div>
    </div>
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>
