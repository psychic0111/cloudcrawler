<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
$().ready(function() {
	$("#addSiteForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:15,remote:{url:'${pageContext.request.contextPath}/template/checkTemplate.do'}},
				urlRule:{required:true,maxlength:200},
				titleInclude:{maxlength:200},
				relationRule:{maxlength:200},
				pageRules:{maxlength:200},
				titleRules:{maxlength:200},
				author:{maxlength:200},
				publishTime:{maxlength:200},
				srcSites:{maxlength:200},
				views:{maxlength:200},
				comments:{maxlength:200},
				contentRules:{maxlength:200}
			},
			messages:{
				name:{remote:'该模板已存在!'}
			}
		}
	);
});
</script>
<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">模板名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板名称，根据目标网站的名称或者内容来定义模板名称')">[?]</a></span>
	</td>
	<td class="TD_Bgcolor4" width="80%" colspan="3">
		<input id="nameTempleteId" type="text" name="name" style="width: 292px;" class="inputTxt1" maxlength="100" />
	</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板分类，根据目标网站的内容对模板进行分类')">[?]</a></span>
	</td>
	<td class="TD_Bgcolor4" width="80%" colspan="3">
		<select id="fristLevel" class="inputTxt1" style="width: 300px;"  name="categoryId" >
			<option value="">请选择...</option>
			<c:forEach items="${fristCategory}" var="tc" varStatus="status">
				<option value = "${tc.id }" <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,tc.id)>-1}">selected="selected"</c:if> >${tc.name }</option>
				<c:forEach items="${tc.childList}" var="child" >
					<option value = "${child.id }" <c:if test="${status.index%2==0}">style="background: #ffffff;"</c:if> <c:if test="${status.index%2==0}">style="background: #d5dbdf;"</c:if>  <c:if test="${fn:indexOf(template.categoryId,child.id)>-1}">selected="selected"</c:if> >&nbsp;&nbsp;&nbsp;${child.name }</option>
				</c:forEach>
			</c:forEach>
		</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">地址匹配: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板地址匹配，地址匹配是指爬虫在数据解析的过程中，只有符合该地址匹配的地址才会使用该模板进行解析')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" colspan="3">
			<input id="urlRule" type="text" name="urlRule" style="width: 292px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">标题过滤: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板标题过滤，标题过滤是指爬虫在保存数据的过程中，如果标题中出现该过滤字符，则将该过滤字符删掉，在保存到数据库中')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" colspan="3">
			<input id="titleInclude" type="text" name="titleInclude" style="width: 292px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">相关链接: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板相关链接，相关链接是指与该文章相关的一些文章列表，出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" colspan="3">
			<input id="relationRule" type="text" name="relationRule" style="width: 292px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">分页链接: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板分页链接，分页链接是指与该文章的分页链接，出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" colspan="3">
			<input id="pageRules" type="text" name="pageRules" style="width: 292px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%"  style="padding-left:0px;">
			<span class="label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板标题，标题是指文章的标题出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="titleRules" type="text" name="titleRules" style="width: 150px;" class="inputTxt1" maxlength="100" />
		</td>
		<td class="TD_Bgcolor3" width="25%"  style="padding-left:0px;">
			<span class="label">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板作者，作者是指文章的作者出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="author" type="text" name="author" style="width: 150px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="10%"  style="padding-left:0px;">
			<span class="label">发布时间: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板发布时间，发布时间是指文章的发布时间出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="publishTime" type="text" name="publishTime" style="width: 150px;" class="inputTxt1" maxlength="100" />
		</td>
		<td class="TD_Bgcolor3" width="20%"  style="padding-left:0px;">
			<span class="label">来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板来源，来源是指文章的来源出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="srcSites" type="text" name="srcSites" style="width: 150px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="10%"  style="padding-left:0px;">
			<span class="label">浏&nbsp;&nbsp;览&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板浏览数，浏览数是指文章的浏览数出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="views" type="text" name="views" style="width: 150px;" class="inputTxt1" maxlength="100" />
		</td>
		<td class="TD_Bgcolor3" width="20%"  style="padding-left:0px;">
			<span class="label">评&nbsp;&nbsp;论&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板评论数，评论数是指文章的评论数出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="35%" >
			<input id="comments" type="text" name="comments" style="width: 150px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">正文内容: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板正文，正文是指文章的正文出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" colspan="3">
			<input id="contentRules" type="text" name="contentRules" style="width: 292px;" class="inputTxt1" maxlength="100" />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">测试URL: <a href="javascript:void(0)" onclick="toolTip(this,'指定您要测试的文章在浏览器地址栏中的地址')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" colspan="3">
			<input id="testUrl" type="text" name="testUrl" style="width: 292px;" class="inputTxt1" maxlength="100" /><input name="" style="margin-left:30px;" type="button" class="botton_style3" onclick="showTestResult()" value="测试"  />
		</td>
	</tr>
</table>
