
function openTab(tabName) {
    let tabs = document.getElementsByClassName("tabcontent");
    let tabsBtns = document.getElementsByClassName("tablink");
    for (let i = 0; i < tabs.length; i++) {
        tabs[i].style.display = "none";
        tabsBtns[i].classList.remove("active");
    }
    document.getElementById(tabName).style.display = "block";
    document.getElementById(tabName + "btn").classList.add("active");
}

function enableEdit(fieldId) {
    let field = document.getElementById(fieldId);
    field.readOnly = !field.readOnly;
}