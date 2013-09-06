<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#netParamForm").validate({
			rules:{
				connetCount:{required:true,digits:true,range:[1,10] },
				timeoutSecond:{required:true,digits:true,range:[1,60] },
				waiCount:{required:true,digits:true,range:[1,10]},
				referCount:{required:true,digits:true,range:[1,10]}
			}
		}
	);
});
</script>
<div class="nav">
	<span style="font-size: 14px;" >全局设置</span>
</div>
<form id="netParamForm" method="post" 	action="${pageContext.request.contextPath}/netParam/editParamDo.do">
	<input type="hidden" name="id" value="${netParam.id}"/>
	
	<table  style=" border:#c1c7cb 1px solid; margin-top:10px;"  cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" style=" border-bottom:#c1c7cb 1px solid;border-right:#c1c7cb 1px solid;"  >
				<span class="txt_color2" style="margin-left: 25px;"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">网络连接尝试次数: <a href="javascript:void(0)" onclick="toolTip(this,'定义网络连接尝试次数，爬虫在下载页面的过程中，与目标服务器创建一个HTTP连接时失败后允许重试的次数，达到重试次数仍然未连接上时，则放弃该连接')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" style=" border-bottom:#c1c7cb 1px solid;" >
				<input  type="text" name="connetCount" value="${netParam.connetCount}" style="width: 140px;" class="inputTxt1" maxlength="100" />次
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" style=" border-bottom:#c1c7cb 1px solid;border-right:#c1c7cb 1px solid;" >
				<span class="txt_color2" style="margin-left: 25px;"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">网络连接超时时间: <a href="javascript:void(0)" onclick="toolTip(this,'定义网络连接超时时间，爬虫在下载页面的过程中，和目标服务器创建链接并获取该地址对应的页面的超时时间，超过此时间则放弃该页面')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" style=" border-bottom:#c1c7cb 1px solid;" >
				<input  type="text" name="timeoutSecond" style="width: 140px;" value="${netParam.timeoutSecond}" class="inputTxt1" maxlength="100" />秒
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor1" width="25%" style=" border-bottom:#c1c7cb 1px solid;border-right:#c1c7cb 1px solid;" >
				<span class="txt_color2" style="margin-left: 25px;"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">网络连接等待次数: <a href="javascript:void(0)" onclick="toolTip(this,'定义网络连接等待次数')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor1" width="80%" style=" border-bottom:#c1c7cb 1px solid;" >
				<input  type="text" name="waiCount" style="width: 140px;" value="${netParam.waiCount}" class="inputTxt1" maxlength="100" />次
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor1" width="25%" style=" border-right:#c1c7cb 1px solid;" >
				<span class="txt_color2" style="margin-left: 25px;"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">跳&nbsp;&nbsp;&nbsp;&nbsp;转&nbsp;&nbsp;&nbsp;&nbsp;次&nbsp;&nbsp;&nbsp;&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义允许跳转的次数')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor1" width="80%"  >
				<input  type="text" name="referCount" style="width: 140px;" value="${netParam.referCount}" class="inputTxt1" maxlength="100" />次
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="updateNetParam()" />
		<input name="" type="button" class="botton_style2"	onclick="clickCancel()" value="取消" />
	</div>
</form>
