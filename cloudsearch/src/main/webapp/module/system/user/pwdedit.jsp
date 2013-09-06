<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#pwdeditForm").validate( {
			rules : {
				oldpassword : { required : true, equalPwd : '#oldpwd' },
				password : { required : true, minlength : 6, maxlength : 20 },
				confirmpassword : { required : true, minlength : 6, maxlength : 20, equalTo : '#password' }
			}
		});

	});
</script>
<form id="pwdeditForm" method="post" action="${pageContext.request.contextPath}/user/editPwd.do">
	<input type="hidden" id="id" name="id" value="${user.id}">	
	<input type="hidden" id="oldpwd" name="oldpwd" value="${user.password}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr>
			<td class="TD_Bgcolor3" width="30%">
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">原始密码：<a href="javascript:void(0)" onclick="toolTip(this,'原始密码')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" >
				<input id="oldpassword" type="password" name="oldpassword" style="width: 140px;" class="inputTxt1" />
			</td>
		</tr>
		<tr>
			<td class="TD_Bgcolor3" width="30%">
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<a href="javascript:void(0)" onclick="toolTip(this,'定义用户密码')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" >
				<input id="password" type="password" name="password" style="width: 140px;" class="inputTxt1"/>
			</td>
		</tr>
		<tr>
			<td class="TD_Bgcolor3" width="30%">
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span>确认密码：<a href="javascript:void(0)" onclick="toolTip(this,'确认用户密码')">[?]</a>
			</td>
			<td class="TD_Bgcolor4">
				<input id="confirmpassword" type="password"  name="confirmpassword" style="width:140px;" class="inputTxt1"  />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="pwdeditFrm_submit()" />
		<input name="" type="button" class="botton_style2"	onclick="closeDg()" value="取消" />
	</div>
</form>
