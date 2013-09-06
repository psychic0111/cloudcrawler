<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" language="javascript">
jQuery.validator.addMethod("ip", function(value, element) { 
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "Ip地址格式错误");
jQuery.validator.addMethod("charnum", function(value, element) {
var chrnum = /^([a-zA-Z0-9]+)$/;
return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)"); 
$().ready(function() {
	$("#addCrawlerForm").validate({
			rules:{
				name:{required:true,minlength:2,maxlength:15,remote:{url:"${pageContext.request.contextPath}/crawler/checkCrawler.do?id="+$("#crawlerId").val()}},
				crawlerAddress:{required:true,url:true,maxlength:200},
				code:{required:true,charnum:true,minlength:2,maxlength:50,remote:{url:"${pageContext.request.contextPath}/crawler/checkCode.do?id="+$("#crawlerId").val()}},
				crawlerThread:{required:true,digits:true,range:[1,300]},
				downloadThread:{required:true,digits:true,range:[1,300]},
				siteThread:{required:true,digits:true,range:[1,300]},
				crawlerType:{required:true},
				serverUrl:{required:true,minlength:0,maxlength:100},
				webdbUrl:{required:true,minlength:0,maxlength:100},
				imageSize:{required:true,digits:true,range:[1,1024]},
				imagePath:{required:true,maxlength:100},
				dataPath:{required:true,maxlength:100},
				dbip:{required:true,ip:true},
				dbport:{required:true,digits:true,range:[1,99999]},
				dbuser:{required:true,maxlength:50},
				dbpassword:{required:true,maxlength:50},
				dbname:{required:true,maxlength:50},
				tableName:{required:true},
				dburl:{required:true,maxlength:500}
			},
			messages:{
				name:{remote:'该爬虫已存在!'}
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
	<div class="cur"><a href="javascript:;" onclick="changeTab(this,'${pageContext.request.contextPath}/field/list.do?nested=true',2)">高级属性</a></div>
	<div class="clear"></div>
</div>
<form id="addCrawlerForm" class="indexForm" method="post" 	action="${action}">
	<input type="hidden" name="_token" value="${token}">
	<input type="hidden" name="id" id="crawlerId" value="${crawler.id}">
	<input type="hidden" name="isDeath" value="${crawler.isDeath}">
	<input type="hidden" name="autoConnects" value="${crawler.autoConnects}">
	<input type="hidden" name="databaseId" value="${crawler.databaseId}">
	<input type="hidden" name="crawlerStatus" value="${crawler.crawlerStatus}">
	<div id="tabContent1" style="display:block;">
		<jsp:include page="/module/sitecrawler/crawler/basic.jsp"></jsp:include>
	</div>
	<div id="tabContent2" style="display:none;">
		<jsp:include page="/module/sitecrawler/crawler/plugin.jsp"></jsp:include>
	</div> 
</form>
<div class="function2">
	<input name="" type="button" class="botton_style3" value="保存" onclick="addFrmTemplate_submit();" />
	<input name="" type="button" class="botton_style3"	onclick="closeDg()" value="取消" />
</div>





