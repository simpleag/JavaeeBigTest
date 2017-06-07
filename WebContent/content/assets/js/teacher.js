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
		usernum:null,
		username:null
}

function gettclass(JSONObject){
	tcclass.classnum = JSONObject.classnum;
	tcclass.classsid = JSONObject.classsid;
	tcclass.subname = JSONObject.subname;
	tcclass.classtime = JSONObject.classtime;
	
}
function getstudents(JSONObject){
	student.usernum = JSONObject.usernum;
	student.username = JSONObject.username;
}
$(document).ready(function() {
	alert("test id"+localStorage.getItem("userid"));
	document.getElementById("div2").innerHTML = "test";
	var btn=$("<button type='button' id='testBtn' value='Test'>TEST</button>");
	var box = $("<input type='checkbox' id= 'tt"+localStorage.getItem("userid")+"'>checkbox</input>");
	$("#div2").append(box);
	$("#div2").append(btn);
});
function showclass(data){
	var dataset = data;
	alert("data"+data);
	document.getElementById("div2").innerHTML = "班级如下";
	for(var i=0;i<dataset.length;i++){
		gettclass(dataset[i]);
		$("#div2").append("<div></br>"+i+"</br>");
		var classname = dataset[i].classnum;
		var classid = dataset[i].classid;
		var subname = dataset[i].subname;
		var classadd = dataset[i].classadd;
		var classtime = dataset[i].classtime;
		$("#div2").append(classid + " "+subname+" "+classname+" "+classadd+" "+classtime);
		var box = $("<input type='radio' name='r' value='"+classid+"'id= '"+classid+"'>"+classname+"</input>");
		var textarea=$("<textarea name='MSG' cols=40 rows=4 id='texta'>输入消息</textarea>");
		$("#div2").append(box);
		$("#div2").append("</br>");
//		$("#div2").append(textarea);
		$("#div2").append("</br>"+"</div>");
	}
	var btn=$("<button type='button' id='sendinfos' value='Test' onclick='sendinfo()'>群发消息</button>");
	$("#div2").append(textarea);
	$("#div2").append(btn);
};
function sendinfo(){
	//获取选择的班级id
	var v = $('input:radio:checked').val();
	//获取文本框中输入的内容
	var text = $('#texta').val();
	alert("点击的value是:"+v+text);
}
function sendclassPost(){
	var userid = localStorage.getItem("userid");
	alert("userid:"+userid+"and"+localStorage.getItem("userid"));
	$.ajax({
		url:"teacherclass.do",
		type:"post",
		data:{
			"teacherId":userid
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
$(function(){
	$("button").click(function(){
		var id = $(this).attr("id"); //获取点击的id
		alert(id);
		if(id == 'b1'){
			alert("点击的id是:"+id);
			sendclassPost();
		}
	})
})