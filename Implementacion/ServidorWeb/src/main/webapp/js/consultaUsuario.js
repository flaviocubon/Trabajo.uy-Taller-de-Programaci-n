function mostrarOcultarDatos(){
    var blur= document.getElementById('blur');
    blur.classList.toggle('active');
    var blur= document.getElementById('popup');
    blur.classList.toggle('active');
}
function habilitaModificar(habilita){
    document.getElementById('txtNombre').disabled= habilita;
    document.getElementById('txtApellido').disabled= habilita;
    document.getElementById('passClave').disabled= habilita;
    document.getElementById('dtFecha').disabled= habilita;
    document.getElementById('txtNacionalidad').disabled= habilita;
    document.getElementById('txtDescripcion').disabled= habilita;
    document.getElementById('txtLink').disabled= habilita;
    document.getElementById('txtImagen').disabled= habilita;
}
function postulante(){
    if(document.getElementById("empresa").style.display == "none"){
        document.getElementById("empresa").style.display = "block";
        document.getElementById("postulante").style.display = "none";
    } else {
        document.getElementById("empresa").style.display = "none";
        document.getElementById("postulante").style.display = "block";
    }
}