var buttonid = null;
var tcclass = {
		classnum:null,
		classsid:null,
		classadd:null,
		subname:null,
		classtime:null,
		students:null
}
var student = {
		username:null,
		userid:null,
		classname:null,
		classid:null
}
var info = {
		username:null,
		classname:null,
		context:null,
		sendtime:null
}
function getInfo(JSONObject){
	info.username = JSONObject.username;
	info.classname = JSONObject.classname;
	info.context = JSONObject.context;
	info.sendtime = JSONObject.sendtime;
}
function getStudents(JSONObject){
	student.username = JSONObject.username;
	student.userid = JSONObject.userid;
	student.classname = JSONObject.classname;
	student.classid = JSONObject.classid;
}
function getTclass(JSONObject){
	tcclass.classnum = JSONObject.classnum;
	tcclass.classsid = JSONObject.classsid;
	tcclass.subname = JSONObject.subname;
	tcclass.classtime = JSONObject.classtime;
}

$(document).ready(function() {
//	alert("test id"+localStorage.getItem("userid"));
	document.getElementById("title1").innerHTML = "欢迎老师："+localStorage.getItem("username")+"登录信息速查系统";
//	document.getElementById("div2").innerHTML = "test";
//	var btn=$("<button type='button' id='testBtn' value='Test'>TEST</button>");
//	var box = $("<input type='checkbox' id= 'tt"+localStorage.getItem("userid")+"'>checkbox</input>");
//	$("#div2").append(box);
//	$("#div2").append(btn);
	if (localStorage.getItem("usertype") != 'teacher'){
		alert("您不是老师");
		window.opener = null;
		window.open("", "_self");
		window.close();
	}
});
function showReciveInfo(data,str,struser){
	var dataset = data;
//	document.getElementById("div2").innerHTML = str;
	document.getElementById("tableh").innerHTML = "<thead>";
	$("#tableh").append("<tr><th>"+struser+"</th><th>消息内容</th><th>所属班级编号</th><th>发送时间</th></tr></thead>");
	for(var i=0;i<dataset.length;i++){
		getInfo(dataset[i]);
		var info = dataset[i].context;
		var sendtime = dataset[i].sendtime;
		var username = dataset[i].username;
		var classname = dataset[i].classname;
		if(i%2 == 0){
			$("#tableh").append("<tbody><tr class='success'><td>"+username+"</td><td>"+info+"</td><td>"+classname+"</td><td>"+sendtime+"</td></tr></tbody>");
		}else {
			$("#tableh").append("<tbody><tr><td>"+username+"</td><td>"+info+"</td><td>"+classname+"</td><td>"+sendtime+"</td></tr></tbody>");	
		}
	}
	document.getElementById("divinput").innerHTML = "";
};
function showClass(data){
	var dataset = data;
	document.getElementById("tableh").innerHTML = "<thead>";
	$("#tableh").append("<tr><th>选中</th><th>班级编号</th><th>上课地址</th><th>上课时间</th></tr></thead>");
	for(var i=0;i<dataset.length;i++){
		getTclass(dataset[i]);
		var classname = dataset[i].classnum;
		var classid = dataset[i].classid;
		var subname = dataset[i].subname;
		var classadd = dataset[i].classadd;
		var classtime = dataset[i].classtime;
//		$("#div2").append(classid + " "+subname+" "+classname+" "+classadd+" "+classtime);
//		var box = $("<input type='radio' name='r' value='"+classid+"'id= '"+classid+"'>"+classname+"</input>");
//		var textarea=$("<textarea name='MSG' cols=40 rows=4 id='texta'>输入消息</textarea>");
//		$("#div2").append(box);
//		$("#div2").append("</br>");
//		$("#div2").append(textarea);
//		$("#div2").append("</br>"+"</div>");
//		var box = $("<input type='radio' name='r' value='"+classid+"'id= '"+classid+"'>"+"选中"+"</input>");
		if(i%2 == 0){
			//"<input type='radio' name='r' value='"+classid+"'id= '"+classid+"'>"+选中+"</input>"
			$("#tableh").append("<tbody><tr class='success'><td>"+"<input type='radio' name='r' value='"+classid+"'id= '"+classid+"'>"+"选中</td><td>"+subname+classname+"</td><td>"+classadd+"</td><td>"+classtime+"</td></tr></tbody>");
//			$("#tableh").append("</tr></tbody>");
//			$("#tableh").append("<tbody><tr class='success'><td>"+subname+classname+"</td><td>"+classadd+"</td><td>"+classtime+"</td><td  class='bs-checkbox'>");
		}else {
			$("#tableh").append("<tbody><tr><td>"+"<input type='radio' name='r' value='"+classid+"'id= '"+classid+"'>"+"选中</td><td>"+subname+classname+"</td><td>"+classadd+"</td><td>"+classtime+"</td></tr></tbody>");
//			$("#tableh").append("</tr></tbody>");
		}
	}
	document.getElementById("divinput").innerHTML = "<input type='text' id='texta' class='form-control'><span class='input-group-btn'><button class='btn btn-default' type='button' id='sendinfos' onclick='sendInfo()'>根据班级发送信息</button></span>";
//	var btn=$("<button type='button' id='sendinfos' value='Test' onclick='sendinfo()'>群发消息</button>");
//	$("#div2").append(textarea);
//	$("#div2").append(btn);
};
function showStudent(data){
	var dataset = data;
//	document.getElementById("div2").innerHTML = "班级如下";
	document.getElementById("tableh").innerHTML = "<thead>";
	$("#tableh").append("<tr><th>选中</th><th>班级编号</th><th>学生名字</th></tr></thead>");
	for(var i=0;i<dataset.length;i++){
		getStudents(dataset[i]);
		var username = dataset[i].username;
		var touserid = dataset[i].userid;
		var classname = dataset[i].classname;
		var classid = dataset[i].classid;
//		$("#div2").append(classname + " "+username+classid+" "+touserid);
//		var box = $("<input type='radio' name='r' value='"+classid+"'value2= '"+touserid+"'>"+"选择"+"</input>");
//		var textarea=$("<textarea name='MSG' cols=40 rows=4 id='texta'>输入消息</textarea>");
//		$("#div2").append(box);
//		$("#div2").append("</br>");
//		$("#div2").append(textarea);
//		$("#div2").append("</br>"+"</div>");
		if(i%2 == 0){
			//"<input type='radio' name='r' value='"+classid+"'id= '"+classid+"'>"+选中+"</input>"
			$("#tableh").append("<tbody><tr class='success'><td>"+"<input type='radio' name='r' value='"+classid+"'value2= '"+touserid+"'>"+"选中</td><td>"+classname+"</td><td>"+username+"</td></tr></tbody>");
//			$("#tableh").append("</tr></tbody>");
//			$("#tableh").append("<tbody><tr class='success'><td>"+subname+classname+"</td><td>"+classadd+"</td><td>"+classtime+"</td><td  class='bs-checkbox'>");
		}else {
			$("#tableh").append("<tbody><tr><td>"+"<input type='radio' name='r' value='"+classid+"'value2= '"+touserid+"'>"+"选中</td><td>"+classname+"</td><td>"+username+"</td></tr></tbody>");
//			$("#tableh").append("</tr></tbody>");
		}
	}
	//还需要测试发送给用户
	document.getElementById("divinput").innerHTML = "<input type='text' id='texta' class='form-control'><span class='input-group-btn'><button class='btn btn-default' type='button' id='sendinfos' onclick='sendInfoByUser()'>选择学生发送信息</button></span>";
//	var btn=$("<button type='button' id='sendinfos' value='Test' onclick='sendinfobyuser()'>发送信息</button>");
//	$("#div2").append(textarea);
//	$("#div2").append(btn);
};
function sendInfo(){
	//获取选择的班级id
	var v = $('input:radio:checked').val();
	//获取文本框中输入的内容
	var text = $('#texta').val();
	var userid = localStorage.getItem("userid");
//	alert("点击的value是:"+v+text);
	if (text == ''){
		alert("请输入内容");
	}else{

		$.ajax({
			url:"teachersendinfobyclass.do",
			type:"post",
			data:{
				"userid":userid,
				"classid":v,
				"context":text
			},
			dataType: "JSON",
			success: function(data) {
				alert(data.msg);
			},
			error: function() {
				alert("错误");
			}
		});
	}
}
function sendInfoByUser(){
	//获取选择的班级id
	var classid = $('input:radio:checked').attr("value");
	var touserid = $('input:radio:checked').attr("value2");
	//获取文本框中输入的内容
	var text = $('#texta').val();
	var userid = localStorage.getItem("userid");
//	alert("点击的value是:"+classid+touserid);
	if(text == ''){
		alert("请输入内容");
	}else{


		$.ajax({
			url:"teachersendinfobyuser.do",
			type:"post",
			data:{
				"userid":userid,
				"classid":classid,
				"context":text,
				"touserid":touserid
			},
			dataType: "JSON",
			success: function(data) {
				alert(data.msg);
			},
			error: function() {
				alert("错误");
			}
		});
	}
}
function sendClassPost(){
	var userid = localStorage.getItem("userid");
//	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:"teacherclass.do",
		type:"post",
		data:{
			"teacherId":userid
		},
		dataType: "JSON",
		success: function(data) {
			showClass(data);
		},
		error: function() {
			alert("错误");
		}
	});
}
function sendStudentPost(){
	var userid = localStorage.getItem("userid");
//	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:"teacherstudent.do",
		type:"post",
		data:{
			"userid":userid
		},
		dataType: "JSON",
		success: function(data) {
			showStudent(data);
		},
		error: function() {
			alert("错误");
		}
	});
};
function sendHistoryInfoPost(str,url,struser){
	var userid = localStorage.getItem("userid");
//	alert(userid);
//	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:url,
		type:"post",
		data:{
			"userid":userid
		},
		dataType: "JSON",
		success: function(data) {
			showReciveInfo(data,str,struser);
		},
		error: function() {
			alert("错误");
		}
	});
};

$(function(){
	$("button").click(function(){
		var id = $(this).attr("id"); //获取点击的id
//		alert(id);
		if(id == 'b1'){
//			alert("点击的id是:"+id);
			sendClassPost();
		}else if(id == 'b4'){
			alert("查询学生列表");
			sendStudentPost();
		}else if (id == 'b2'){
			//未完成查看历史信息功能
			alert("正在查询历史信息");
			sendHistoryInfoPost("正在查询历史信息","teacherreceiveinfo.do","发送者");
		}else if (id == 'b5'){
			//功能尚未实现
			alert("正在查询您发送过的消息");
			sendHistoryInfoPost("正在查询您发送过的消息","teachershowsendinfo.do","接收者");
		}
	})
})