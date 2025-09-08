document.addEventListener("DOMContentLoaded", function() {
    const userDropdownToggle = document.getElementById("user-dropdown-toggle");
    const userDropdown = document.getElementById("user-dropdown");

	if(!userDropdownToggle) return
	
    userDropdownToggle.addEventListener("click", function(event) {
        event.stopPropagation(); // Evita que el clic se propague al documento
        if (userDropdown.style.display === "block") {
            userDropdown.style.display = "none";
        } else {
            userDropdown.style.display = "block";
        }
    });

    // Oculta el menú al hacer clic en cualquier parte del documento
    document.addEventListener("click", function() {
    userDropdown.style.display = "none";
    });

    // Evita que el clic en el menú desplegable cierre el menú
    userDropdown.addEventListener("click", function(event) {
    event.stopPropagation();
    });
});