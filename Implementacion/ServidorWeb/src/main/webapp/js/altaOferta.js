function getPaquetes() {
	var form = document.getElementById("elform");
	var hiddenInput = document.createElement("input");
    hiddenInput.type = "hidden";
    hiddenInput.name = "getPaquete";
    hiddenInput.value = "";  
    form.appendChild(hiddenInput);
	form.submit();
}