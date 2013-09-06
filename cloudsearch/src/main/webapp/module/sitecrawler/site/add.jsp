<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" language="javascript">
jQuery.validator.addMethod("ip", function(value, element) { 
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "Ip地址格式错误");
// change content tab
function changeTab(ths,url,number){
	if($(ths).parent().hasClass("cur")){
		//切换选项卡样式
		$(ths).parent().siblings(".other").removeClass("other").addClass("cur");
		$(ths).parent().removeClass("cur").addClass("other");
		//改变内容
		currentNumber = number;
		for(var i = 1;i<=3;i++){
			if(i == number){
				$("#tabContent"+i).show();
			}else{
				$("#tabContent"+i).hide();
			}
		}
		if(number==3){
			$("#nameTempleteId").val($("#nameSiteId").val());
		}
	}
}
function changeshow(flag){
	if(flag==1){
		document.getElementById("viewdiv").style.display="none";
		document.getElementById("sourcecode").style.display="block";
	}else{
		document.getElementById("sourcecode").style.display="none";
		document.getElementById("viewdiv").style.display="block";
	}
}
function closeCurrent(){
	window.history.go(-1);
}
</script>
<div id="allTable">
<div class="tab" >
	<div class="other"><a href="javascript:;" onclick="changeTab(this,'${pageContext.request.contextPath}/field/list.do?nested=false',1)">基本属性</a></div>
	<div class="cur"><a href="javascript:;" onclick="changeTab(this,'${pageContext.request.contextPath}/field/list.do?nested=true',2)">扩展属性</a></div>
	<div class="cur"><a href="javascript:;" id="gatherTemplate" onclick="changeTab(this,'${pageContext.request.contextPath}/field/list.do?nested=true',3)">采集模板</a></div>
	<div class="clear"></div>
</div>
<form id="addSiteForm" class="indexForm" method="post" 	action="${action}">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" id="id" name="id" value="${site.id}">
	<input type="hidden" id="ispart" name="ispart" value="${site.ispart}">
	<input type="hidden" name="siteCategoryName" id="siteCategoryName" value="${site.siteCategoryName}">
	<div id="tabContent1" style="display:block;">
		<jsp:include page="/module/sitecrawler/site/basic.jsp"></jsp:include>
	</div>
	<div id="tabContent2" style="display:none;">
		<jsp:include page="/module/sitecrawler/site/plugin.jsp"></jsp:include>
	</div> 
	<div id="tabContent3" style="display:none;">
		<jsp:include page="/module/sitecrawler/site/template.jsp"></jsp:include>
	</div>
</form>
<div class="function2">
	<input name="" type="button" class="botton_style3" value="保存" onclick="addFrmTemplate_submit();" />
	<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
</div>  
</div>
<form id="testForm" style="display: none;" class="indexForm" method="post" action="${pageContext.request.contextPath}/site/test.do">
	<input type="hidden" id="rule" name="rule" value="">
	<div id="urlDivTable" class="table_list3" style="width:100%;height: 500px;overflow:auto;">
	</div>
	<div class="function2">
		<input name="" type="button" class="botton_style3" value="返回" onclick="ruledyClose();" />
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
	$("#allTable").show();
	$("#templateTestForm").hide();
}
function showTestResult(){
	var testUrl = $("#testUrl").val();
	if(testUrl == ""){
		MSG.alert('测试地址不能为空！');
		return;
	}
	var form = document.getElementById("addSiteForm");
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
		if(retiveUrls != "" && (retiveUrls+"") != 'undefined'){
			for(var i = 0;i<retiveUrls.length;i++){
				if("" != retiveUrlStr){
					retiveUrlStr += '<br/>';
				}
				retiveUrlStr += retiveUrls[i];
			}
		}
		var fenyeUrls = data.fenyeUrls;
		var fenyeUrlStr = "";
		if(fenyeUrls != "" && (fenyeUrls+"") != 'undefined'){
			for(var i = 0;i<fenyeUrls.length;i++){
				if("" != fenyeUrlStr){
					fenyeUrlStr += '<br/>';
				}
				fenyeUrlStr += fenyeUrls[i];
			}
		}
		$("#parserRsult_retiveUrls").html(retiveUrlStr);
		$("#parserRsult_fenyeUrls").html(fenyeUrlStr);
		$("#allTable").hide();
		$("#templateTestForm").show();
	});
	
}
</script>






