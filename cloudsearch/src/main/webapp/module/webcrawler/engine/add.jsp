<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" language="javascript">
jQuery.validator.addMethod("ip", function(value, element) { 
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "地址格式错误");
jQuery.validator.addMethod("charnum", function(value, element) {
var chrnum = /^([a-zA-Z0-9]+)$/;
return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)"); 
jQuery.validator.addMethod("httpurl", function(value, element) {
	var start1 = 'http://';
	var start2 = 'https://';
	var iscontinus = false;
	if(value.indexOf(start1) > -1){
		iscontinus = true;
	} 
	if(value.indexOf(start2) > -1){
		iscontinus = true;
	} 
return this.optional(element) || (iscontinus); 
}, "地址格式错误");
$().ready(function() {
	$("#addEngineForm").validate({
			rules:{
				enginName:{required:true,minlength:2,maxlength:15,remote:{url:"${pageContext.request.contextPath}/engine/checkEngine.do?id="+$("#id").val()}},
				enginCode:{required:true,minlength:2,maxlength:50,regexp:{reg:/^[a-zA-Z]{2,50}$/, match:true},remote:{url:"${pageContext.request.contextPath}/engine/checkEngine.do?id="+$("#id").val()}},
				pageUrl:{required:true,httpurl:true},
				timeInterval:{required:true,digits:true,min:1},
				pages:{required:true,digits:true,min:1,max:99},
				listBlock:{required:true,maxlength:300},
				itemBlock:{maxlength:300},
				urlRule:{maxlength:300},
				hostUrl:{maxlength:100},
				publishTimeRule:{maxlength:300},
				titleRule:{maxlength:300},
				authorRule:{maxlength:300},
				durationRule:{maxlength:300},
				imagePath:{maxlength:300},
				perPageCount:{digits:true,min:1,max:300}
			},
			messages:{
				enginName:{remote:'引擎名称已存在!'},
				enginCode:{remote:'引擎编码已存在!',regexp:'只能为英文字符!'}
			}
		}
	);
});
function changeTab(ths,url,number){
	if($(ths).parent().hasClass("cur")){
		//切换选项卡样式
		$(ths).parent().siblings(".other").removeClass("other").addClass("cur");
		$(ths).parent().removeClass("cur").addClass("other");
		//改变内容
		currentNumber = number;
		for(var i = 1;i<=2;i++){
			if(i == number){
				$("#tabContent"+i).show();
			}else{
				$("#tabContent"+i).hide();
			}
		}
		if(number == 3){
			$("#keepTemple").show();
		}else{
			$("#keepTemple").hide();
		}
	}
}
</script>
<div class="tab" >
	<div class="other"><a href="javascript:;" onclick="changeTab(this,'${pageContext.request.contextPath}/field/list.do?nested=false',1)">基本属性</a></div>
	<div class="cur"><a href="javascript:;" onclick="changeTab(this,'${pageContext.request.contextPath}/field/list.do?nested=true',2)">规则配置</a></div>
	<div class="clear"></div>
</div>
<form id="addEngineForm" class="indexForm" method="post" 	action="${action}">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" name="id" id="id" value="${engine.id}">
	<div id="tabContent1" style="display:block;">
		<jsp:include page="/module/webcrawler/engine/basic.jsp"></jsp:include>
	</div>
	<div id="tabContent2" style="display:none;">
		<jsp:include page="/module/webcrawler/engine/plugin.jsp"></jsp:include>
	</div> 
</form>
<div class="function2">
	<input name="" type="button" class="botton_style3" value="保存" onclick="addFrmTemplate_submit();" />
	<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
</div>





