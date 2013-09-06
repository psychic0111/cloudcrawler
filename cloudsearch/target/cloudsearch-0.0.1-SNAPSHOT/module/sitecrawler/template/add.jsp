<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#addTemplateForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:50,remote:{url:'${pageContext.request.contextPath}/template/checkTemplate.do'}},
				categoryId:{required:true},
				urlRule:{required:true,maxlength:200},
				titleInclude:{maxlength:30},
				relationRule:{maxlength:200},
				pageRules:{maxlength:200},
				titleRules:{maxlength:200},
				author:{maxlength:200},
				publishTime:{maxlength:200},
				srcSites:{maxlength:200},
				views:{maxlength:200},
				comments:{maxlength:200},
				contentRules:{maxlength:200},
				titleFilterChars:{maxlength:30}
			},
			messages:{
				name:{remote:'该模板已存在!'}
			}
		}
	);
});
function changeTCategory()   
{   
	var firstLevel = $("#addFristLevel").val();
	var url = '${pageContext.request.contextPath}/template/getSecondLevel.do?parentId='+firstLevel;
    AT.post(url,null,function(data){
         if (data != null && data != "" &&  data != undefined)   
         {   
	         // 清空表格   
	         $("#addSecondLevel").empty();   
	         // 转换成json对象   
	         var option = "";
             // 循环组装下拉框选项   
	         $.each(data, function(k, v)   
	         {   
         		option += "<option value=\"" + v.id + "\">" + v.name + "</option>";
	         }); 
	         $("#addSecondLevel").append(option); 
         }
    });
}
</script>
<form id="addTemplateForm" method="post" 	action="${pageContext.request.contextPath}/template/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">模板名称: <a href="javascript:void(0)" onclick="toolTip(this,'例如：新浪新闻')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="name" type="text" name="name" style="width: 292px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类: <a href="javascript:void(0)" onclick="toolTip(this,'例如：新浪新闻')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<select id="categoryId" class="inputTxt1" style="width: 292px;"  name="categoryId" >
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
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">地址匹配: <a href="javascript:void(0)" onclick="toolTip(this,'只有符合条件的地址才使用该模板解析')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="urlRule" type="text" name="urlRule" style="width: 292px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">标题过滤: <a href="javascript:void(0)" onclick="toolTip(this,'多个字符之间用空格分开')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="titleFilterChars" type="text" name="titleFilterChars" style="width: 292px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章标题所在的模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="titleRules" type="text" name="titleRules" style="width: 292px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">发布时间: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章发布时间所在的模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="publishTime" type="text" name="publishTime" style="width: 292px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="10%" style="padding-left:0px;">
				<span class="label">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章作者所在的模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="40%" >
				<input id="author" type="text" name="author" style="width: 150px;" class="inputTxt1" maxlength="100" />
			</td>
			<td class="TD_Bgcolor3" width="20%" style="padding-left:0px;">
				<span class="label">来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章来源所在的模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="40%" >
				<input id="srcSites" type="text" name="srcSites" style="width: 150px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="10%" style="padding-left:0px;">
				<span class="label">相关链接: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章的相关文章所在的模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="40%" >
				<input id="relationRule" type="text" name="relationRule" style="width: 150px;" class="inputTxt1" maxlength="100" />
			</td>
			<td class="TD_Bgcolor3" width="20%" style="padding-left:0px;">
				<span class="label">分页链接: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章的分页后所在的其他模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="40%" >
				<input id="pageRules" type="text" name="pageRules" style="width: 150px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="10%" style="padding-left:0px;">
				<span class="label">浏&nbsp;&nbsp;览&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章浏览数所在的模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="40%" >
				<input id="views" type="text" name="views" style="width: 150px;" class="inputTxt1" maxlength="100" />
			</td>
			<td class="TD_Bgcolor3" width="20%" style="padding-left:0px;">
				<span class="label">评&nbsp;&nbsp;论&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章评论数所在的模块')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="40%" >
				<input id="comments" type="text" name="comments" style="width: 150px;" class="inputTxt1" maxlength="100" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">正文内容: <a href="javascript:void(0)" onclick="toolTip(this,'指定该文章正文所在的模块')">[?]</a></span>
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
				<input id="testUrl" type="text" name="testUrl"  style="width: 292px;" class="inputTxt1" maxlength="100" /><input name="" style="margin-left:30px;" type="button" class="botton_style3" value="测试" onclick="ruleTest();" />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit();" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>

<script type="text/javascript">
function ruleTest(){
	try{
		var form = document.getElementById("addTemplateForm");
		var param = $(form).serialize();
		window.open("${pageContext.request.contextPath}/template/test.do?"+param+"&num="+Math.random());
	}catch(e){
	}
}
</script>