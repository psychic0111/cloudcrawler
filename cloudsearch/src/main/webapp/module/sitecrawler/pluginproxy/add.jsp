<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" language="javascript">
jQuery.validator.addMethod("ip", function(value, element) { 
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "Ip地址格式错误");
$().ready(function() {
	$("#addSiteProxyForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:15,remote:{url:'${pageContext.request.contextPath}/siteProxy/checkSiteProxy.do'}},
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
<form id="addSiteProxyForm" method="post" 	action="${pageContext.request.contextPath}/siteProxy/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">代理名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义代理名称，可以根据代理的作用和分布命名')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="name" type="text" name="name" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">IP&nbsp;&nbsp;地&nbsp;址: <a href="javascript:void(0)" onclick="toolTip(this,'定义代理IP，代理服务器对应的IP地址')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="proxyIp" type="text" name="proxyIp" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">端&nbsp;&nbsp;口&nbsp;号: <a href="javascript:void(0)" onclick="toolTip(this,'定义代理端口，代理服务器对外开放的访问端口')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="proxyPort" type="text" name="proxyPort" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">用&nbsp;&nbsp;户&nbsp;名: <a href="javascript:void(0)" onclick="toolTip(this,'定义代理用户名，代理服务器的访问的用户名')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="userName" type="text" name="userName" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码: <a href="javascript:void(0)" onclick="toolTip(this,'定义代理密码，代理服务器的访问的密码')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="proxyPwd" type="password" name="proxyPwd" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" valign="top" rowspan="2">
				<span class="label">代理描述: <a href="javascript:void(0)" onclick="toolTip(this,'定义代理描述，对该代理服务器进行备注')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<textarea id="remark" name="remark" rows="5" cols="30" class="textarea" ></textarea>
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit();" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>
