var score = {
	classid:null,
	score:null,
	classname:null
}
var info = {
	info:null,
}
function getScoreInfo(JSONObject){
	score.classid = JSONObject.classid;
	score.score = JSONObject.score;
}
$(document).ready(function() {
	alert("test id"+localStorage.getItem("userid"));
	document.getElementById("div2").innerHTML = "student\r\nlist";
	$("#div2").append("</br>"+"list3");
});

function showscore(data){
//	var dataset = $.parseJSON(data);
	var dataset = data;
	alert("data"+data+" "+dataset+"and"+dataset[0].classid+"and"+dataset[0].score);
	document.getElementById("div2").innerHTML = "成绩如下";
	for(var i=0;i<dataset.length;i++){
		getScoreInfo(dataset[i]);
		$("#div2").append("</br>"+i+"</br>");
		var classid = dataset[i].classid;
		var score = dataset[i].score;
		var classname = dataset[i].classname;
		$("#div2").append(classid+" "+classname+" "+score);
	}
};
function showinfo(data){
	var dataset = data;
	alert("data"+data);
	document.getElementById("div2").innerHTML = "成绩如下";
	for(var i=0;i<dataset.length;i++){
		getScoreInfo(dataset[i]);
		$("#div2").append("</br>"+i+"</br>");
		var info = dataset[i].info;
		var sendtime = dataset[i].sendtime;
		var fromuser = dataset[i].fromuser;
		var classname = dataset[i].classname;
		$("#div2").append(fromuser+" "+classname+" "+ info+" "+sendtime);
	}
};
function sendscorePost(){
	var userid = localStorage.getItem("userid");
	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:"showscore.do",
		type:"post",
		data:{
			"userid":userid
		},
		dataType: "JSON",
		success: function(data) {
			showscore(data);
		},
		error: function() {
			alert("错误");
		}
	});
}
function sendinfoPost(){
	var userid = localStorage.getItem("userid");
	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:"studentinfo.do",
		type:"post",
		data:{
			"userid":userid
		},
		dataType: "JSON",
		success: function(data) {
			showinfo(data);
		},
		error: function() {
			alert("错误");
		}
	});
}
$("#b1").click(function(){
	alert("ok");
	sendscorePost();
});
$("#b2").click(function(){
	alert("正在查询");
	sendinfoPost();
});