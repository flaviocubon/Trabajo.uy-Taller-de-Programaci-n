function postulante() {
	document.getElementById("empresa").style.display = "none";
	document.getElementById("postulante").style.display = "block";
	document.getElementById("descripcion").removeAttribute("required");
	document.getElementById("nacionalidad").setAttribute("required", "");
	document.getElementById("fecha").setAttribute("required", "");
}

function empresa() {
	document.getElementById("empresa").style.display = "block";
	document.getElementById("postulante").style.display = "none";
	document.getElementById("descripcion").setAttribute("required", "");
	document.getElementById("nacionalidad").removeAttribute("required");
	document.getElementById("fecha").removeAttribute("required");
}

$(document).ready(function() {
	$("#nickname").on("input", function() {
		$("#mensajeError").prop("hidden", true);
		if ($("#nickname").val().length === 0) {
			$("#divExisteUser").prop("hidden", true);
		} else {
			$.post("AltaUsuario",
				{
					nickname: $("#nickname").val(),
					existeNick: "",
				},
				function(data) {
					$("#divExisteUser").prop("hidden", false);
					if (data == "true") {
						$("#existeUser").text("El nickname ingresado ya se encuentra en uso");
						$("#divExisteUser").removeClass("alert-success");
						$("#divExisteUser").addClass("alert-danger");
					} else if (data == "false") {
						$("#divExisteUser").removeClass("alert-danger");
						$("#divExisteUser").addClass("alert-success");
						$("#existeUser").text("El nickname ingresado se encuentra disponible");	
					}
				})
		}
	})
	$("#email").on("input", function() {
		$("#mensajeError").prop("hidden", true);
		if ($("#email").val().length === 0) {
			$("#divExisteEmail").prop("hidden", true);
		} else {
			$.post("AltaUsuario",
				{
					email: $("#email").val(),
					existeEmail: "",
				},
				function(data) {
					$("#divExisteEmail").prop("hidden", false);
					if (data == "true") {
						$("#existeEmail").text("El correo ingresado ya se encuentra en uso");
						$("#divExisteEmail").removeClass("alert-success");
						$("#divExisteEmail").addClass("alert-danger");
					} else if (data == "false") {
						$("#divExisteEmail").removeClass("alert-danger");
						$("#divExisteEmail").addClass("alert-success");
						$("#existeEmail").text("El correo ingresado se encuentra disponible");
					}
				})
		}
	})
});

