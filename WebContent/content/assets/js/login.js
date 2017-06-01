var reurl = {
    url:null,
    msg:null,
    username:null
};
function getInfo(JSONObject){
   reurl.url = JSONObject.url;
   reurl.msg = JSONObject.msg;	
   reurl.username = JSONObject.username;
}
function loadOtherPage(data){
	getInfo(data);
	sessionStorage.setItem("msg",data.msg);
	sessionStorage.setItem("username",data.username);
	alert(data.msg+data.username);
	window.open("/JavaeeBigTest"+reurl.url);
}
function goStatePage(url){
	window.open("/JavaeeBigTest"+url);
}
function sendPost(){
    var username = $("#u1").val();
    var pwd = $("#pwd").val();
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