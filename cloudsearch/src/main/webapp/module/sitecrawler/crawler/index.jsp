<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
	<script type="text/javascript" language="javascript">
	//开始搜索引擎
	function startCrawlerControl(code){
		$("#iframepage").load("${pageContext.request.contextPath}/crawler/start.do?num="+Math.random()+"&code="+code,iframeHeight);
	}
	//停止搜索引擎
	function endCrawlerControl(code){
		$("#iframepage").load("${pageContext.request.contextPath}/crawler/end.do?num="+Math.random()+"&code="+code,iframeHeight);
	}
	//添加爬虫
	var currentNumber = 1;
	function addFrmTemplate_submit(){
		//转化表单为AJAX表单
		AT.postFrm("addCrawlerForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/crawler/crawlerList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			var operate =$("#operateHidden").val();
			if(operate == "0"){
				$("#operate").hide();
			}else{
				document.getElementById("operate").style.display = "inline";
			}
			closeDg();
		},true);
		
	}
	//删除爬虫
	function delSite(){
		var $checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的爬虫!');
		}else{
			MSG.confirm('确认要删除选中的爬虫吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/crawler/del.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/crawler/crawlerList.do",iframeHeight);
				});
			},null);
		}
	}
	function doPage(ths,pageNo,pageSize){
		AT.load("iframepage","${pageContext.request.contextPath}/crawler/crawlerList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&name="+encodeURIComponent($("#name").val()),iframeHeight);
	}
	function searchFrm_submit(){
		AT.postFrm("crawlerForm",function(data){
			$("#iframepage").html("");
			$("#iframepage").html(data);
			iframeHeight();
			closeDg();
		},true);
	}
	$(function () {
		  $("*").each(function () {
		   $(this).keypress(function (e) {
		    var key = window.event ? e.keyCode : e.which;
		    if (key.toString() == "13") {
		     return false;
		    }
		   });
		  });
		 });
	</script>
<div class="title">
   <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
        <td class="c2"><img src="${pageContext.request.contextPath}/images/mag.png"  /> 爬虫管理</td>
        <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
        <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
	<div class="function">
	<form id="crawlerForm" class="indexForm" action="${pageContext.request.contextPath}/crawler/crawlerList.do" method="post">
       <div class="left">
         <input  type="button" class="botton_style1"  value="添加" onclick="openDg('${pageContext.request.contextPath}/crawler/add.do','+ 添加爬虫',650,200);" />
         <input  type="button" class="botton_style2"  value="删除" onclick="delSite()"  />
         <div id="operate" <c:if test="${operate ==1 }">style="display:none;"</c:if> <c:if test="${operate ==0 }">style="display:inline;"</c:if>>
	        <input id="startAllCrawler" style="display:none;" type="button" class="botton_style3"  value="启动所有爬虫" onclick="startCrawlerControl('');" />
	        <input id="endAllCrawler" style="display:none;" type="button" class="botton_style2"  value="停止所有爬虫" onclick="endCrawlerControl('');"  />
	     </div>
       </div>
       <div class="right">
        <span style="display:inline;">爬虫名称：</span>
		<input id="name" type="text" name="name" style="width: 140px;display:inline;" value="${site.name}" class="inputTxt1" maxlength="100" />
        <input  type="button" class="botton_style3 siteButton" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
        </div>
       <div class="clear"></div>
      </form>
     </div>
	<div id="listDiv">
		<div class="table_list" id="iframepage">
			<jsp:include page="/module/sitecrawler/crawler/list.jsp"></jsp:include>
		</div>
	</div>
</div>
<iframe id="uploadIframeId" src="${pageContext.request.contextPath}/module/sitecrawler/crawler/time.jsp" name="uploadIframe" frameborder="0" width="100%;" height="100%;" style="display:none;"></iframe>