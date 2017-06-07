var score = {
	classid:null,
	score:null,
	classname:null
}
var info = {
	info:null,
}
var tcclass = {
	classnum:null,
	classadd:null,
	classtime:null,
	subname:null,
	teachername:null
}
var teachers = {
	teachername:null,
	phone:null
}
function getTeacher(JSONObject){
	teachers.teachername = JSONObject.teachername;
	teachers.phone = JSONObject.phone;
}
function getScoreclass(JSONObject){
	tcclass.classnum = JSONObject.classnum;
	tcclass.classadd = JSONObject.classadd;
	tcclass.classtime = JSONObject.classtime;
	tcclass.subname = JSONObject.subname;
	tcclass.teachername = JSONObject.teachername;
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
function showteacher(data){
	var dataset = data;
	alert("data"+data);
	document.getElementById("div2").innerHTML = "老师如下";
	for(var i=0;i<dataset.length;i++){
		getTeacher(dataset[i]);
		$("#div2").append("</br>"+i+"</br>");
		var teachername = dataset[i].teachername;
		var phone = dataset[i].phone;
		$("#div2").append(teachername+" "+phone);
	}
};
function showclass(data){
	var dataset = data;
	alert("data"+data);
	document.getElementById("div2").innerHTML = "班级如下";
	for(var i=0;i<dataset.length;i++){
		getScoreclass(dataset[i]);
		$("#div2").append("</br>"+i+"</br>");
		var classnum = dataset[i].classnum;
		var classadd = dataset[i].classadd;
		var classtime = dataset[i].classtime;
		var subname = dataset[i].subname;
		var teachername = dataset[i].teachername;
		$("#div2").append(subname+" "+classnum+" "+classadd+" "+classtime+" "+teachername);
	}
};
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
function sendclassPost(){
	var userid = localStorage.getItem("userid");
	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:"studentclass.do",
		type:"post",
		data:{
			"userid":userid
		},
		dataType: "JSON",
		success: function(data) {
			showclass(data);
		},
		error: function() {
			alert("错误");
		}
	});
}
function sendteacherPost(){
	var userid = localStorage.getItem("userid");
	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:"studentteachers.do",
		type:"post",
		data:{
			"userid":userid
		},
		dataType: "JSON",
		success: function(data) {
			showteacher(data);
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
$("#b3").click(function(){
	alert("正在查询课程信息");
	sendclassPost();
});
$("#b4").click(function(){
	alert("正在查询教师信息");
	sendteacherPost();
});