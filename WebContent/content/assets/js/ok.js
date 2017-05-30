$(document).ready(function() {
	alert("ok"+localStorage.getItem("msg"));
	document.getElementById("p1").innerHTML = localStorage.getItem("username");
})