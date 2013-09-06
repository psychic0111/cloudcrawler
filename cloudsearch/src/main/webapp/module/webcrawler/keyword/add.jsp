<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#addKeywordForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:50,regexp:{reg:/[\\|\/|\+|\-|\*]+/, match:false},remote:{url:'${pageContext.request.contextPath}/keyword/checkKeyword.do'}}
			   ,words:{required:true,minlength:2,maxlength:500,regexp:{reg:/[\\|\/|\+|\-|\*]+/, match:false},remote:{url:'${pageContext.request.contextPath}/keyword/checkKeyword.do'}}
			},
			messages:{
				name:{remote:'该监控词已存在!'}
			}
		}
	);
});
</script>
<form id="addKeywordForm" method="post" action="${pageContext.request.contextPath}/keyword/doAdd.do">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">名&nbsp;&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'定义监控词名称，根据监控词的内容和含义进行定义')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" align="left">
				<input id="name" type="text" name="name" style="width:140px;" class="inputTxt1" maxlength="100"/>
			</td>
		</tr>
		<tr height="30px;">
			<td class="TD_Bgcolor3" width="25%" valign="top">
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">监控词: <a href="javascript:void(0)" onclick="toolTip(this,'定义监控词，搜索引擎搜索时使用的关键字')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%">
				<table style="width:100%;height:100%;">
					<tr>
						<td valign="bottom" style="border-width:0px;text-align:left;">
							<textarea id="words" name="words" rows="10" cols="31" class="textarea" minlength=2 maxlength=200></textarea>
						</td>
						<td valign="bottom" style="border-width:0px;text-align:left;">
							<label id="wordsError" class="error" style="display:none;" for="words" generated="true" jQuery18006333871733214305="3">必填字段</label>
							<span class="txt_color9"><br>多个监控词请回车换行</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">是否启用: <a href="javascript:void(0)" onclick="toolTip(this,'定义监控词是否启用，若启用，则启动全网监控时，对该监控词进行搜索并采集结果页；否则，对该监控词不予采集')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
   				<input type="radio" id="isUse" checked="checked" name="isUse" value= "1"/>启用
				<input type="radio" id="isUse" name="isUse" value= "0"/>禁用 
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="保存" onclick="addFrm_submit();" />
		<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
	</div>
</form>
<script type="text/javascript">
function checkLength(){
	var length = document.getElementById("words").value.length;
	if(length == 0){
		$("#words").css("border","1px solid red");
		document.getElementById("wordsError").style.display="inline";
		return false;
	}
	if(length >=200){
		$("#words").css("border","1px solid red");
		document.getElementById("wordsError").style.display="inline";
		$("#wordsError").html("监控词长度不能超过200");
		return false;
	}else{
		$("#words").css("border","1px solid #ebebeb");
		return true;
	}
}
function cl(){
	var length = document.getElementById("words").value.length;
	if(length > 0 && length < 200){
		$("#words").css("border","1px solid #ebebeb");
	}
}
</script>
