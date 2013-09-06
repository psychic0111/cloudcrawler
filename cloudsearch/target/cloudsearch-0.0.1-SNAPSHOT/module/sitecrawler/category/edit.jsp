<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
jQuery.validator.addMethod("checkName", function(value, element) {
	var checkName = /^[a-zA-Z|\d|\u0391-\uFFE5]*$/;
	return this.optional(element) || (checkName.test(value));
	}, "只能输入汉字、字母、数字");
$().ready(function() {
	$("#editSiteCategoryForm").validate({
			rules:{
				name:{required:true,checkName:true,minlength:2,maxlength:50,remote:{url:"${pageContext.request.contextPath}/siteCategory/checkSiteCategory.do?id="+$('#id').val()}}
			},
			messages:{
				name:{remote:'该站点分类已存在!'}
			}
		}
	);
});
</script>
<form id="editSiteCategoryForm" method="post" 	action="${pageContext.request.contextPath}/siteCategory/editDo.do">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" name="id" value="${siteCategory.id}" id="id"/>
	<input type="hidden" name="operateUser" value="${siteCategory.operateUser}" id="operateUser"/>
	<input type="hidden" name="countSite" value="${siteCategory.countSite}" id="countSite"/>
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">分&nbsp;&nbsp;&nbsp;类&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点分类名称')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="name" type="text" name="name" value="${siteCategory.name}" style="width: 140px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">分类下站点数量: <a href="javascript:void(0)" onclick="toolTip(this,'定义分类下站点数量')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				${siteCategory.countSite}
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">操&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人: <a href="javascript:void(0)" onclick="toolTip(this,'定义创建人')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				${siteCategory.operateUser}
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">操&nbsp;&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'定义更新时间')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<fmt:formatDate value ="${siteCategory.operateTime}" pattern="yyyy-MM-dd HH:mm" />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="editFrm_submit();" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>
