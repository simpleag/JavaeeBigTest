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
	if (data.msg == "成功登录"){
		
		window.open("/JavaeeBigTest"+reurl.url);
		window.opener = null;
		window.open("", "_self");
		window.close();
	}else {
		alert(data.msg);
	}
}
function goStatePage(url){
	window.open("/JavaeeBigTest"+url);
}
function sendPost(){
	var username = $("#u1").val();
	var pwd = $("#pwd").val();
	if (username == '' || pwd == ''){
		alert("请输入完整信息");
	}else {
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
				alert("学号不存在引起的错误");
				goStatePage(data);
			}
		});
	}
}
$("#b1").click(function(){
	sendPost();
});