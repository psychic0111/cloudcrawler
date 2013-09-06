<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" language="javascript">
jQuery.validator.addMethod("checkName", function(value, element) {
	var checkName = /^[a-zA-Z|\d|\u0391-\uFFE5]*$/;
	return this.optional(element) || (checkName.test(value));
	}, "只能输入汉字、字母、数字");
$().ready(function() {
	$("#addSiteCategoryForm").validate({
			rules:{
				name:{required:true,checkName:true,minlength:2,maxlength:50,remote:{url:'${pageContext.request.contextPath}/siteCategory/checkSiteCategory.do'}}
			},
			messages:{
				name:{remote:'该站点分类已存在!'}
			}
		}
	);
});
</script>
<form id="addSiteCategoryForm" method="post" 	action="${pageContext.request.contextPath}/siteCategory/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">分类名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点分类名称')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="name" type="text" name="name" style="width: 140px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit()" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>
