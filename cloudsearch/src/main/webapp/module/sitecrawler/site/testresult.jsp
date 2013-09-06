<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>北京线点科技有限公司</title>
	<style>
		td{padding-left:5px}
		.rightTable{
			border-right:1px #97B859 solid;
		}
		.bottomTable{
			border-bottom:1px #97B859 solid;
		}
		.botton_style3{ padding:0px 15px; padding-top:1px;height:28px; color:#333; border:1px #9e9e9e solid; background:url(../images/btn3.png) top repeat-x; background-color:#dcdcdc }
	</style>
</head>
<%@include file="/include/common.jsp" %>
<body  style="font-family:arial;font-size:14px;background:#ffffff;text-align: center;">
	<table width="80%;" style="border:1px #97B859 solid;" cellpadding="0" cellspacing="0" >
		<tr height="45px;" style="background-color:#97B859;">
			<td colspan="2">
				<b>根据动态地址规则产生的新地址如下：</b>
			</td>
		</tr>
		<c:forEach items="${urls}" var="url" varStatus="number" >
			<tr height="25px;">
				<td class="bottomTable">
					${url}
				</td>
			</tr>
		</c:forEach>
		<tr height="45px;" style="background-color:#97B859;">
			<td colspan="2" align="center">
				<input name="" type="button" class="botton_style3" value="关闭" onclick="closeCurrent();" />
			</td>
		</tr>
	</table>
	<script>
		function changeshow(flag){
			if(flag==1){
				document.getElementById("viewdiv").style.display="none";
				document.getElementById("sourcecode").style.display="block";
			}else{
				document.getElementById("sourcecode").style.display="none";
				document.getElementById("viewdiv").style.display="block";
			}
		}
		function closeCurrent(){
			window.history.go(-1);
		}
	</script>
</body>
</html>