<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#addUserForm").validate({
			rules:{
				username:{required:true,minlength:5,maxlength:20,remote:{url:'${pageContext.request.contextPath}/user/checkuser.do'}},
				password:{required:true,minlength:6,maxlength:20},
				confirmpassword:{required:true,minlength:6,maxlength:20,equalTo:'#password'},
				description:{maxlength:100}
		},
		messages:{
			username:{remote:'该用户已存在!'}
		}}
		);
	
});
</script>
<form id="addUserForm" method="post" action="${pageContext.request.contextPath}/user/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr >
			<td class="TD_Bgcolor3" width="30%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">用&nbsp;户&nbsp;名: <a href="javascript:void(0)" onclick="toolTip(this,'定义用户用户名，登陆该系统时的用户名')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" >
				<input id="username" type="text" name="username" style="width: 140px;"
					class="inputTxt1" />
			</td>
		</tr>
		<tr>
			<td class="TD_Bgcolor3" width="30%">
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">密&nbsp;&nbsp;&nbsp;&nbsp;码：<a href="javascript:void(0)" onclick="toolTip(this,'定义用户密码，登陆该系统时的密码')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" >
				<input id="password" type="password" name="password" style="width: 140px;"
					class="inputTxt1"/>
			</td>
		</tr>
		<tr>
			<td class="TD_Bgcolor3" width="30%">
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span>确认密码:<a href="javascript:void(0)" onclick="toolTip(this,'确认用户密码，两次输入的用户密码必须一致')">[?]</a>
			</td>
			<td class="TD_Bgcolor4">
				<input id="confirmpassword" type="password"  name="confirmpassword" style="width:140px;" class="inputTxt1"  />
			</td>
		</tr>
		<tr>
			<td class="TD_Bgcolor3">
				说&nbsp;&nbsp;&nbsp;&nbsp;明：<a href="javascript:void(0)" onclick="toolTip(this,'定义用户说明，对用户的其他信息进行备注')">[?]</a>
			</td>
			<td class="TD_Bgcolor4">
				<textarea id="description" rows="5" cols="40" name="description" ></textarea>
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit()" />
		<input name="" type="button" class="botton_style2"	onclick="closeDg()" value="取消" />
	</div>
</form>
