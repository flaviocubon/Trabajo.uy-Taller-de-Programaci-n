function mostrarOcultarDatos(){
    var blur= document.getElementById('blur');
    blur.classList.toggle('active');
    var blur= document.getElementById('popup');
    blur.classList.toggle('active');
}

function redirect() {
    const selectedOption = document.getElementById("selectOption").value;
    if (selectedOption) {
        window.location.href = selectedOption; // Redirect to the selected option's value
    }
}