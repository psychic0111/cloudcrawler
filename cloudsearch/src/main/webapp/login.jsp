<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.xdtech.cloudsearch.util.AppConf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=AppConf.get().get("system.name") %></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
</head>
<body class="land_body">
<div class="land">
  <div class="title">
    <div class="left"><img src="${pageContext.request.contextPath}/<%= AppConf.get().get("system.land.logo")%>" /></div>
    <div class="right"><img src="${pageContext.request.contextPath}/images/land_welcome.png"/></div>
    <div class="clear"></div>
  </div>
  <div class="content"> 
  <form action="${pageContext.request.contextPath}/user/login.do" id="frm" method="post">
    <div class="left"><img src="${pageContext.request.contextPath}/images/land_pic.jpg" width="500" height="263" /></div>
    <div class="login">
      <div class="input"><img src="${pageContext.request.contextPath}/images/land_user.png" width="58" height="19" /><br />
        <input name="username" id="username" style="line-height:27px;outline:none;" type="text" value="" onblur="loadPassword()" oninput="loadPassword()" onpropertychange="loadPassword()"/>
      </div>
      <div class="input"><img src="${pageContext.request.contextPath}/images/land_psw.png" width="58" height="19" /><br />
        <input name="password" id="password" style="line-height:27px;outline:none;" type="password" value="" />
      </div>
      <div class="enter"> <img src="${pageContext.request.contextPath}/images/land_button.png" onclick="frm.submit()" type="submit" width="104" height="38" /> <img src="${pageContext.request.contextPath}/images/land_line.png"  width="43" height="31" />
        <input type="checkbox" name="rememberUser" id="rememberUser" value="1"/>
        记住登录信息 <img src="${pageContext.request.contextPath}/images/lock.png" width="28" height="21" /></div>
    </div>
    <div class="clear"></div>
    </form>
  </div>
  <div class="land_foot"><%=AppConf.get().get("system.copyright") %></div><img src="${pageContext.request.contextPath}/images/land_cloudbg_bottom.png" width="928" height="93" />
</div>
<script type="text/javascript" language="javascript">
var message='${message}';
if(message!=''){
	alert(message);
}
document.onkeydown=function(){
	if(event.keyCode==13){
		frm.submit();
	}
}
$("input[name='username']").focus();

function loadPassword(){
	var userName = $("#username").val();
	var password = $("#password").val();
	if(userName != '' && userName.length > 2){
		 if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = $.trim(cookies[i]);
                if (cookie.substring(0, userName.length + 1) == (userName + '=')) {
                	password = cookie.substring(userName.length + 1);
                	if($("#password").val() == ""){
                		$("#password").val(password);
                	}
                    break;
                }
            }
	    }
		//var password = $.cookie(userName);
	}
}
//取最近登录成功的用户名
$(document).ready(function(){
	var type = "${type}";   //1.注销 2.登录失败
	var userName = '';
	var password = '';
	var prefix = "username_";
	if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = $.trim(cookies[i]);
            if(cookie.substring(0,9) == prefix){
            	cookie = cookie.replace(prefix,"");
            	userName = cookie.substring(0,cookie.indexOf("="));
            }
            if (cookie.substring(0, userName.length + 1) == (userName + '=')) {
            	password = cookie.substring(userName.length + 1);
            	$("#username").val(userName);
            	$("#password").val(password);
                break;
            }
        }
    }
	if(type != "1" && type != "2"){
		if(userName != '' && password != ''){
			$("#frm").submit();   	
        }
	} else{
		$("#password").val("");
	}
});
</script>
</body>
</html>
