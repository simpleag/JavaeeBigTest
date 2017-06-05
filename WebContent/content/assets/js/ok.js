$(document).ready(function() {
	alert("ok"+localStorage.getItem("msg")+localStorage.getItem("username"));
	document.getElementById("p1").innerHTML = localStorage.getItem("username");
})