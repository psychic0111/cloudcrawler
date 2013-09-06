<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>线点科技云采集产品</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
</head>
<body class="land_body">
<div class="land">
  <div class="title">
    <div class="left"><img src="${pageContext.request.contextPath}/images/land_logo.png" /></div>
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
  <div class="land_foot">Copyright © 2005 - 2013. All Rights Reserved &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线点科技 &nbsp;&nbsp;&nbsp;版权所有</div><img src="${pageContext.request.contextPath}/images/land_cloudbg_bottom.png" width="928" height="93" />
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
                	$("#password").val(password);
                    break;
                }
            }
	    }
		//var password = $.cookie(userName);
	}
}
//取最近登录成功的用户名
$(document).ready(function(){
	var userName = '';
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
});
</script>
</body>
</html>
