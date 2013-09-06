<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#addTemplateCategoryForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:50,remote:{url:'${pageContext.request.contextPath}/templateCategory/checkTemplateCategory.do?parentId='+$('#tcParentId').val()}}
			},
			messages:{
				name:{remote:'该模板分类已存在!'}
			}
		}
	);
});
</script>
<form id="addTemplateCategoryForm" method="post" 	action="${pageContext.request.contextPath}/templateCategory/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">分类名称父节点: <a href="javascript:void(0)" onclick="toolTip(this,'添加完成以后，添加的节点将成为此节点的子节点')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<select id="fristLevel" class="inputTxt1" style="width: 140px;height:30px;" name="parentId" >
					<c:forEach items="${fristCategory}" var="tc" varStatus="status">
						<option value = "${tc.id }" <c:if test="${tc.id==templateCategory.parentId}">selected="selected"</c:if> <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,tc.id)>-1}">selected="selected"</c:if> >${tc.name }</option>
						<c:forEach items="${tc.childList}" var="child" >
							<option value = "${child.id }" <c:if test="${child.id==templateCategory.parentId}">selected="selected"</c:if> <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,child.id)>-1}">selected="selected"</c:if> >&nbsp;&nbsp;&nbsp;${child.name }</option>
						</c:forEach>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">分&nbsp;&nbsp;&nbsp;类&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'例如：新浪')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="name" type="text" name="name" style="width: 140px;" class="inputTxt1"  />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit();" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>
