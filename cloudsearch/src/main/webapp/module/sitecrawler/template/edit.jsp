<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#editTemplateForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:50,remote:{url:'${pageContext.request.contextPath}/template/checkTemplate.do?id='+$("#id").val()}},
				categoryId:{required:true},
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
	var firstLevel = $("#editFristLevel").val();
	var url = '${pageContext.request.contextPath}/template/getSecondLevel.do?parentId='+firstLevel;
    AT.post(url,null,function(data){
         if (data != null && data != "" &&  data != undefined)   
         {   
	         // 清空表格   
	         $("#editSecondLevel").empty();   
	         // 转换成json对象   
	         var option = "";
             // 循环组装下拉框选项   
	         $.each(data, function(k, v)   
	         {   
         		option += "<option value=\"" + v.id + "\">" + v.name + "</option>";
	         }); 
	         $("#editSecondLevel").append(option); 
         }
    });
}
</script>
<form id="editTemplateForm" method="post" 	action="${pageContext.request.contextPath}/template/editDo.do">
	<input type="hidden" name="id" value="${template.id}" id="id"/>
	<input type="hidden" name="operateTime" value="${template.operateTime}" id="operateTime"/>
	<input type="hidden" name="operateUser" value="${template.operateUser}" id="operateUser"/>
	<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">模板名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板名称，模板名称可以根据本模板所属模板分类和应用于的站点以及个人习惯来定义')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="name" type="text" name="name" value="${template.name }" style="width: 292px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">模板分类: <a href="javascript:void(0)" onclick="toolTip(this,'选择模板分类，选择本模板所属的模板分类')">[?]</a></span>
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
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">地址匹配: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板地址匹配，地址匹配是指爬虫在抓取地址的过程中，只有符合该地址匹配的地址才会使用该模板进行抓取解析')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="urlRule" type="text" name="urlRule" value="<c:out value="${ template.urlRule}" escapeXml="true"/>"  style="width: 292px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">标题过滤: <a href="javascript:void(0)" onclick="toolTip(this,'定义模板标题过滤，标题过滤是指爬虫在保存数据的过程中，如果标题中出现应该过滤的字符，则将该过滤字符删掉后才保存到数据库中')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="titleFilterChars" type="text" name="titleFilterChars" value="${template.titleFilterChars}"  style="width: 292px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%">
				<span class="label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题: <a href="javascript:void(0)" onclick="toolTip(this,'定义标题解析规则，标题是指文章的标题出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="titleRules" type="text" name="titleRules" value="<c:out value="${ template.titleRules}" escapeXml="true"/>"  style="width: 292px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">发布时间: <a href="javascript:void(0)" onclick="toolTip(this,'定义发布时间解析规则，发布时间是指文章的发布时间出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="publishTime" type="text" name="publishTime" value="<c:out value="${ template.publishTime}" escapeXml="true"/>" style="width: 292px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" style="padding-left:0px;">
				<span class="label">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者: <a href="javascript:void(0)" onclick="toolTip(this,'定义作者解析规则，作者是指文章的作者出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4_temp">
				<input id="author" type="text" name="author" value="<c:out value="${ template.author}" escapeXml="true"/>" style="width: 150px;" class="inputTxt1" />
			</td>
			<td class="TD_Bgcolor3" width="25%" style="padding-left:0px;">
				<span class="label">来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源: <a href="javascript:void(0)" onclick="toolTip(this,'定义来源解析规则，来源是指文章的来源出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4_temp">
				<input id="srcSites" type="text"  name="srcSites" value="<c:out value="${ template.srcSites}" escapeXml="true"/>" style="width: 150px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" style="padding-left:0px;">
				<span class="label">相关链接: <a href="javascript:void(0)" onclick="toolTip(this,'定义相关链接解析规则，相关链接是指与该文章相关的一些文章列表，出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4_temp">
				<input id="relationRule" type="text" name="relationRule" value="<c:out value="${ template.relationRule}" escapeXml="true"/>" style="width: 150px;" class="inputTxt1" />
			</td>
			<td class="TD_Bgcolor3" width="25%" style="padding-left:0px;">
				<span class="label">分页链接: <a href="javascript:void(0)" onclick="toolTip(this,'定义分页链接解析规则，分页链接是指与该文章的分页链接，出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4_temp">
				<input id="pageRules" type="text" name="pageRules" value="<c:out value="${ template.pageRules}" escapeXml="true"/>"  style="width: 150px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" style="padding-left:0px;">
				<span class="label">浏&nbsp;&nbsp;览&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义浏览数解析规则，浏览数是指文章的浏览数出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4_temp">
				<input id="views" type="text" name="views" value="<c:out value="${ template.views}" escapeXml="true"/>" style="width: 150px;" class="inputTxt1" />
			</td>
			<td class="TD_Bgcolor3" width="25%" style="padding-left:0px;">
				<span class="label">评&nbsp;&nbsp;论&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义评论数解析规则，评论数是指文章的评论数出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4_temp">
				<input id="comments" type="text" name="comments" value="<c:out value="${ template.comments}" escapeXml="true"/>" style="width: 150px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">正文内容: <a href="javascript:void(0)" onclick="toolTip(this,'定义文章正文解析规则，正文是指文章的正文出现在目标网站的所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="contentRules" type="text" name="contentRules" value="<c:out value="${ template.contentRules}" escapeXml="true"/>" style="width: 292px;" class="inputTxt1" />
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="label">测试URL: <a href="javascript:void(0)" onclick="toolTip(this,'指定您要测试的文章在浏览器地址栏中的地址')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" colspan="3">
				<input id="testUrl" type="text" name="testUrl"  style="width: 292px;" class="inputTxt1" /><input name="" style="margin-left:30px;" type="button" class="botton_style3" value="测试" onclick="showTestResult();" />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="editFrm_submit();" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>

<form id="templateTestForm" style="display: none;" method="post" action="">
<div style="height: 500px;overflow:auto;">
	<table width="100%;" style="border:1px #97B859 solid;" cellpadding="0" cellspacing="0" >
		<tr height="30px;" >
			<td class="rightTable bottomTable"  style="width:80px" valign="middle"><b>网页标题</b></td>
			<td id = "parserRsult_title" class="bottomTable" style="text-align: left; padding-left: 10px;">
			</td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>发布时间</b></td>
			<td id = "parserRsult_pubtime" class="bottomTable" style="text-align: left; padding-left: 10px;"></td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>网页作者</b></td>
			<td id="parserRsult_author" class="bottomTable" style="text-align: left; padding-left: 10px;"></td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>网页来源</b></td>
			<td id="parserRsult_source" class="bottomTable" style="text-align: left; padding-left: 10px;"></td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>评&nbsp;&nbsp;论&nbsp;&nbsp;数</b></td>
			<td id="parserRsult_commentCount" class="bottomTable" style="text-align: left; padding-left: 10px;"></td>
		</tr>
		<tr height="30px;" >
			<td class="rightTable bottomTable" valign="middle"><b>浏&nbsp;&nbsp;览&nbsp;&nbsp;数</b></td>
			<td id="parserRsult_viewCount" class="bottomTable" style="text-align: left; padding-left: 10px;"></td>
		</tr>
		<tr>
			<td class="rightTable bottomTable" valign="top" style="padding-top: 10px;"><b>分页URL</b></td>
			<td id="parserRsult_fenyeUrls" class="bottomTable" style="text-align: left; padding-left: 10px;">
			</td>
		</tr>
		<tr>
			<td class="rightTable bottomTable" valign="top" style="padding-top: 10px;"><b>网页正文</b>&nbsp;&nbsp;&nbsp;</td>
			<td class="bottomTable">
				<table width="100%;">
					<tr>
						<td align="right"><a style="cursor:pointer;" onclick="changeshow(0)">[<b>视图</b>]</a>&nbsp;&nbsp;&nbsp;<a onclick="changeshow(1)" style="cursor: pointer">[<b>源代码</b>]</a></td>
					</tr>
					<tr>
						<td>
							<br/><br/><br/>
							<div id="viewdiv" style="display:block">${parserRsult.content }</div>
							<div id="sourcecode" style="display:none">${parserRsult.content}</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="45px;">
			<td class="rightTable bottomTable" valign="top" style="padding-top: 10px;"><b>关联规则</b></td>
			<td id="parserRsult_retiveUrls" class="bottomTable" style="text-align: left; padding-left: 10px;">
			</td>
		</tr>
	</table>
</div>
<div class="function2">
	<input name="" type="button" class="botton_style3" value="返回" onclick="showAddTem();" />
</div> 
</form>



<script type="text/javascript">
function changeshow(num){
	if(num == 0){
		$("#changeshow").show();
		$("#sourcecode").hide();
	}else{
		$("#changeshow").hide();
		$("#sourcecode").show();
	}
}
function showAddTem(){
	$("#editTemplateForm").show();
	$("#templateTestForm").hide();
}
function showTestResult(){
	var testUrl = $("#testUrl").val();
	if(testUrl == ""){
		MSG.alert('测试地址不能为空！');
		return;
	}
	var form = document.getElementById("editTemplateForm");
	var param = $(form).serialize();
	var url = "${pageContext.request.contextPath}/template/test.do?"+param+"&num="+Math.random();
	AT.post(url,null,function(data){
		$("#parserRsult_title").html(data.title);
		$("#parserRsult_pubtime").html(data.pubtime);
		$("#parserRsult_author").html(data.author);
		$("#parserRsult_source").html(data.source);
		$("#parserRsult_commentCount").html(data.commentCount);
		$("#parserRsult_viewCount").html(data.viewCount);
		$("#viewdiv").html(data.content);
		var retiveUrls = data.retiveUrls;
		var retiveUrlStr = "";
		for(var i = 0;i<retiveUrls.length;i++){
			if("" != retiveUrlStr){
				retiveUrlStr += '<br/>';
			}
			retiveUrlStr += retiveUrls[i];
		}
		var fenyeUrls = data.fenyeUrls;
		var fenyeUrlStr = "";
		for(var i = 0;i<fenyeUrls.length;i++){
			if("" != fenyeUrlStr){
				fenyeUrlStr += '<br/>';
			}
			fenyeUrlStr += fenyeUrls[i];
		}
		$("#parserRsult_retiveUrls").html(retiveUrlStr);
		$("#parserRsult_fenyeUrls").html(fenyeUrlStr);
		$("#editTemplateForm").hide();
		$("#templateTestForm").show();
	});
	
}
</script>
