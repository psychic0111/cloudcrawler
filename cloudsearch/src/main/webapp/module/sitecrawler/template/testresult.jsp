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
	</style>
</head>
<body  style="font-family:arial;font-size:14px;background:#ffffff;text-align: center;">
	<c:if test="${parserRsult.message == null || parserRsult.message == ''}">
		<table style="border:1px #97B859 solid;" cellpadding="0" cellspacing="0" >
		<tr height="45px;" style="background-color:#97B859;">
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable"  style="width:80px" valign="middle"><b>网页标题</b></td>
			<td class="bottomTable" style="padding-left: 5px">${ parserRsult.title}&nbsp;</td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>发布时间</b></td>
			<td class="bottomTable">${parserRsult.pubtime}&nbsp;</td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>网页作者</b></td>
			<td class="bottomTable">${parserRsult.author}&nbsp;</td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>网页来源</b></td>
			<td class="bottomTable">${parserRsult.source}&nbsp;</td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>评&nbsp;&nbsp;论&nbsp;&nbsp;数</b></td>
			<td class="bottomTable">${parserRsult.commentCount}&nbsp;</td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>浏&nbsp;&nbsp;览&nbsp;&nbsp;数</b></td>
			<td class="bottomTable">${parserRsult.viewCount}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTable bottomTable" valign="top" style="padding-top: 10px;"><b>分页URL</b></td>
			<td class="bottomTable">
				<c:forEach items="${parserRsult.fenyeUrls}" var="url" varStatus="number" >	
					${url }<br/>
				</c:forEach>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="rightTable bottomTable" valign="top" style="padding-top: 10px;"><b>网页正文</b>&nbsp;&nbsp;&nbsp;</td>
			<td class="bottomTable">
				<table>
					<tr>
						<td align="right"><a style="cursor:pointer;" onclick="changeshow(0)">[<b>视图</b>]</a>&nbsp;&nbsp;&nbsp;<a onclick="changeshow(1)" style="cursor: pointer">[<b>源代码</b>]</a></td>
					</tr>
					<tr>
						<td>
							<br/><br/><br/>
							<div id="viewdiv" style="display:block">${parserRsult.content }</div>
							<div id="sourcecode" style="display:none">${parserRsult.content}</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="45px;">
			<td class="rightTable bottomTable" valign="top" style="padding-top: 10px;"><b>关联规则</b></td>
			<td class="bottomTable">
				<c:forEach items="${parserRsult.retiveUrls}" var="url" varStatus="number" >	
					${url }<br/>
				</c:forEach>&nbsp;
			</td>
		</tr>
		<tr height="45px;" style="background-color:#97B859;">
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		</table>
	</c:if>
	<c:if test="${parserRsult.message != null && parserRsult.message != ''}">
		<span style="color:#B82F47">${parserRsult.message}</span>
	</c:if>
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
	</script>
</body>
</html>