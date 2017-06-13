var reurl = {
		url:null,
		msg:null,
		username:null,
		userid:null,
		type:null
};
function getInfo(JSONObject){
	reurl.url = JSONObject.url;
	reurl.msg = JSONObject.msg;	
	reurl.username = JSONObject.username;
	reurl.userid = JSONObject.userid;
	reurl.type = JSONObject.type;
}
function loadOtherPage(data){
	getInfo(data);
	localStorage.setItem("msg",data.msg);
	localStorage.setItem("username",data.username);
	localStorage.setItem("userid",data.userid);
	localStorage.setItem("usertype",data.type);
	alert(data.msg+"and"+data.userid);
	window.open("/JavaeeBigTest"+reurl.url);
	window.opener = null;
	window.open("", "_self");
	window.close();
}
function goStatePage(url){
	window.open("/JavaeeBigTest"+url);
}
function sendPost(){
	var username = $("#u1").val();
	var pwd = $("#pwd").val();
	alert(username+" "+pwd);
	$.ajax({
		url:"login.do",
		type:"post",
		data:{
			"username":username,
			"password":pwd
		},
		dataType: "JSON",
		success: function(data) {
			loadOtherPage(data);
		},
		error: function() {
			alert("错误");
			goStatePage(data);
		}
	});
}
$("#b1").click(function(){
	sendPost();
});