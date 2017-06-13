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
	alert("test id"+localStorage.getItem("userid")+localStorage.getItem("usertype"));
	document.getElementById("titleh").innerHTML = "欢迎登陆信息速查"+localStorage.getItem("username");
	if (localStorage.getItem("usertype") != 'student'){
		alert("您不是学生用户");
		window.opener = null;
		window.open("", "_self");
		window.close();
	}
//	document.getElementById("tableh").innerHTML = "<thead>";
//	$("#tableh").append("<tr><th>学生名</th><th>课程名称</th><th>成绩</th></tr></thead>");
});
function showteacher(data){
	var dataset = data;
	alert("data"+data);
	document.getElementById("tableh").innerHTML = "<thead>";
	$("#tableh").append("<tr><th>老师名称</th><th>老师联系方式</th></tr></thead>");
	for(var i=0;i<dataset.length;i++){
		getTeacher(dataset[i]);
		var teachername = dataset[i].teachername;
		var phone = dataset[i].phone;
		if(i%2 == 0){
			$("#tableh").append("<tbody><tr class='success'><td>"+teachername+"</td><td>"+phone+"</td></tr></tbody>");
		}else {
			$("#tableh").append("<tbody><tr><td>"+teachername+"</td><td>"+phone+"</td></tr></tbody>");	
		}
	}
};
function showclass(data){
	var dataset = data;
	alert("data"+data);
	document.getElementById("tableh").innerHTML = "<thead>";
	$("#tableh").append("<tr><th>班级编号</th><th>职教老师</th><th>上课地址</th><th>上课时间</th></tr></thead>");
	for(var i=0;i<dataset.length;i++){
		getScoreclass(dataset[i]);
		var classnum = dataset[i].classnum;
		var classadd = dataset[i].classadd;
		var classtime = dataset[i].classtime;
		var subname = dataset[i].subname;
		var teachername = dataset[i].teachername;
		if(i%2 == 0){
			$("#tableh").append("<tbody><tr class='success'><td>"+subname+classnum+"</td><td>"+teachername+"</td><td>"+classadd+"</td><td>"+classtime+"</td></tr></tbody>");
		}else {
			$("#tableh").append("<tbody><tr><td>"+subname+classnum+"</td><td>"+teachername+"</td><td>"+classadd+"</td><td>"+classtime+"</td></tr></tbody>");	
		}
	}
};
//完成成绩显示 需要网页有tableh的表格即可
function showscore(data){
//	var dataset = $.parseJSON(data);
	var dataset = data;
	alert("data"+data+" "+dataset+"and"+dataset[0].classid+"and"+dataset[0].score);
	document.getElementById("tableh").innerHTML = "<thead>";
	$("#tableh").append("<tr><th>学生名</th><th>课程名称</th><th>成绩</th></tr></thead>");
	
	for(var i=0;i<dataset.length;i++){
		getScoreInfo(dataset[i]);
		var classid = dataset[i].classid;
		var score = dataset[i].score;
		var classname = dataset[i].classname;
//		
		if(i%2 == 0){
			$("#tableh").append("<tbody><tr class='success'><td>"+localStorage.getItem("username")+"</td><td>"+classid+classname+"</td><td>"+score+"</td></tr></tbody>");
		}else {
			$("#tableh").append("<tbody><tr><td>"+localStorage.getItem("username")+"</td><td>"+classid+classname+"</td><td>"+score+"</td></tr></tbody>");	
		}

	}

};
function showinfo(data){
	var dataset = data;
	alert("data"+data);
	document.getElementById("tableh").innerHTML = "<thead>";
	$("#tableh").append("<tr><th>信息所属课程</th><th>发送信息老师名称</th><th>发送的内容</th><th>发送时间</th></tr></thead>");
	for(var i=0;i<dataset.length;i++){
		getScoreInfo(dataset[i]);
		var info = dataset[i].info;
		var sendtime = dataset[i].sendtime;
		var fromuser = dataset[i].fromuser;
		var classname = dataset[i].classname;
		if(i%2 == 0){
			$("#tableh").append("<tbody><tr class='success'><td>"+classname+"</td><td>"+fromuser+"</td><td>"+info+"</td><td>"+sendtime+"</td></tr></tbody>");
		}else {
			$("#tableh").append("<tbody><tr><td>"+classname+"</td><td>"+fromuser+"</td><td>"+info+"</td><td>"+sendtime+"</td></tr></tbody>");	
		}
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