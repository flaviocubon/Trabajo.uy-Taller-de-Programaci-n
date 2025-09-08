<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/WEB-INF/template/commonHeadElements.jsp" />
    <title>inicioSesion</title>
    <link rel="stylesheet" href="css/inicioSesion.css">
</head>

<body class="bg-primary-subtle body-center">
    <jsp:include page="/WEB-INF/template/header.jsp" />
    <jsp:include page="/WEB-INF/template/sideBar.jsp" />
    <div class="container mt-5">
        <div class="login-container">
            <div class="user-avatar"></div>
            <form method="POST">
            	<h6 style="color:#FF0000">Usuario y/o contraseña incorrectos</h6>
                <div class="form-group">
                    <input name="nickname" type="text" class="form-control" placeholder="Usuario" required>
                </div>
                <div class="form-group password-toggle">
                    <input name="pass" type="password" class="form-control" id="password-input" placeholder="Contraseña">
                    <span class="toggle-password" onclick="visibilidadClave()"><i class="bi bi-eye-slash"></i></span>
                </div>
                <button type="submit" class="btn btn-primary login-button">Iniciar Sesión</button>
            </form>
        </div>
    </div>
    <script>
        let passwordVisible = false;
        const toggleButton = document.querySelector('.toggle-password');

        function visibilidadClave() {
            const passwordInput = document.getElementById('password-input');

            passwordVisible = !passwordVisible;
            if (passwordVisible) {
                passwordInput.type = 'text';
                toggleButton.innerHTML = '<i class="bi bi-eye"></i>';
            } else {
                passwordInput.type = 'password';
                toggleButton.innerHTML = '<i class="bi bi-eye-slash"></i>';
            }
        }
    </script>
 	<script src="js/code.jquery.com_jquery-3.5.1.slim.min.js"></script>
    <script src="js/stackpath.bootstrapcdn.com_bootstrap_4.5.2_js_bootstrap.min.js"></script>
	<jsp:include page="/WEB-INF/template/commonScriptsElements.jsp" />
</body>

</html>