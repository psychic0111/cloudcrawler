<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
jQuery.validator.addMethod("ip", function(value, element) { 
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
}, "Ip地址格式错误");
$().ready(function() {
	$("#editSiteProxyForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:15,remote:{url:"${pageContext.request.contextPath}/siteProxy/checkSiteProxy.do?id="+$('#id').val()}},
				proxyIp:{required:true,ip:"^(([0-1]?\d{1,2}|2[0-4]\d|25[0-5])\.){3}([0-1]?\d{1,2}|2[0-4]\d|25[0-5])$"},
				proxyPort:{required:true,digits:true,range:[1,99999] },
				userName:{minlength:2,maxlength:50},
				proxyPwd:{minlength:2,maxlength:50},
				remark:{maxlength:150}
			},
			messages:{
				name:{remote:'该代理已存在!'}
			}
		}
	);
});
</script>
<form id="editSiteProxyForm" method="post" 	action="${pageContext.request.contextPath}/siteProxy/editDo.do">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" name="id" value="${siteProxy.id}" id="id"/>
	<input type="hidden" name="operateUser" value="${siteProxy.operateUser}" id="operateUser"/>
	<input type="hidden" name="proxyType" value="${siteProxy.proxyType}" id="proxyType"/>
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">代理名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点分类名称')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="name" type="text" name="name" value="${siteProxy.name }" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">IP&nbsp;&nbsp;地&nbsp;址: <a href="javascript:void(0)" onclick="toolTip(this,'定义IP地址')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="proxyIp" type="text" name="proxyIp" value="${siteProxy.proxyIp}" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">端&nbsp;&nbsp;口&nbsp;号: <a href="javascript:void(0)" onclick="toolTip(this,'定义端口号')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="proxyPort" type="text" name="proxyPort" value="${siteProxy.proxyPort }" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">用&nbsp;&nbsp;户&nbsp;名: <a href="javascript:void(0)" onclick="toolTip(this,'定义用户名')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="userName" type="text" name="userName" value="${siteProxy.userName }" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码: <a href="javascript:void(0)" onclick="toolTip(this,'定义密码')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="proxyPwd" type="password" name="proxyPwd" value="${siteProxy.proxyPwd }" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" valign="top">
				<span class="label">代理描述: <a href="javascript:void(0)" onclick="toolTip(this,'定义代理描述')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<textarea id="remark" name="remark" rows="5" cols="30" class="textarea" >${siteProxy.remark }</textarea>
				<label id="remarkError" class="error" style="display: none;" for="remark" generated="true" jQuery18006333871733214305="3">必填字段</label>
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">操&nbsp;&nbsp;作&nbsp;人: <a href="javascript:void(0)" onclick="toolTip(this,'定义操作人')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				${siteProxy.operateUser}
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">操作时间: <a href="javascript:void(0)" onclick="toolTip(this,'定义操作时间')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<fmt:formatDate value ="${siteProxy.operateTime}" pattern="yyyy-MM-dd HH:mm" />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="editFrm_submit();" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>
<script type="text/javascript">
function checkLength(){
	var length = document.getElementById("remark").value.length;
	if(length >=150){
		$("#remark").css("border","1px solid red");
		document.getElementById("remarkError").style.display="inline";
		$("#remarkError").html("代理描述长度不能超过150！");
		return false;
	}else{
		$("#remark").css("border","1px solid #ebebeb");
		return true;
	}
}
function cl(){
	var length = document.getElementById("remark").value.length;
	if(length > 0 && length < 150){
		$("#remark").css("border","1px solid #ebebeb");
	}
}
</script>
