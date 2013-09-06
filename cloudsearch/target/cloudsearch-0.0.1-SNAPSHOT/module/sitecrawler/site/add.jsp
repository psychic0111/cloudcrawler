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

</script>
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
<form id="addTemplateForm" class="indexForm" method="post" 	action="${pageContext.request.contextPath}/template/doAddForSite.do">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" name="id" value="${template.id}">
	
</form>   
<form id="testForm" class="indexForm" method="post" action="${pageContext.request.contextPath}/site/test.do">
	<input type="hidden" id="rule" name="rule" value="">
</form>
<div class="function2">
	<input name="" type="button" class="botton_style3" value="保存" onclick="addFrmTemplate_submit();" />
	<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
</div>
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




