<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
	<script type="text/javascript" language="javascript">
	//添加搜索引擎
	var currentNumber = 1;
	function addFrmTemplate_submit(){
		//转化表单为AJAX表单
		AT.postFrm("addEngineForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/engine/engineList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
		
	}

	//修改搜索引擎
	function editFrm_submit(){
		//转化表单为AJAX表单
		if(currentNumber == 3){
			AT.postFrm("addTemplateForm",function(data){
				alert('模板保存成功！');
			},true);
		}else{
			AT.postFrm("addSiteForm",function(data){
				$("#listDiv").load("${pageContext.request.contextPath}/site/siteList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
				closeDg();
			},true);
		}
	}
	//删除搜索引擎
	function delEngine(){
		var $checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的搜索引擎!');
		}else{
			MSG.confirm('确认要删除选中的搜索引擎吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/engine/del.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/engine/engineList.do",iframeHeight);
				});
			},null);
		}
	}
	//启用搜索引擎
	function useEngine(){
		$checked = $("[type=checkbox]:checked");
		if($checked.length==0){
			MSG.alert('请选择要启用的搜索引擎!');
		}else{
			MSG.confirm('确认要启用选中的搜索引擎吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/engine/use.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/engine/engineList.do?num="+Math.random(),iframeHeight);
				});
			},null);
		}
		
	}
	//禁用搜索引擎
	function noUseEngine(){
		$checked = $("[type=checkbox]:checked");
		if($checked.length==0){
			MSG.alert('请选择要禁用的搜索引擎!');
		}else{
			MSG.confirm('确认要禁用选中的搜索引擎吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/engine/noUse.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/engine/engineList.do?num="+Math.random(),iframeHeight);
				});
			},null);
		}
		
	}
	function doPage(ths,pageNo,pageSize){
		AT.load("iframepage","${pageContext.request.contextPath}/engine/engineList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&enginName="+encodeURIComponent($("#enginName").val()),iframeHeight);
	}
	function searchFrm_submit(){
		AT.postFrm("engineForm",function(data){
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
	
	//开始搜索引擎
	function startEngineControl(){
		
	}
	</script>
<div class="title">
   <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
        <td class="c2"><img src="${pageContext.request.contextPath}/images/collect.png"  /> 搜索引擎管理</td>
        <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
        <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
	<div class="function">
	<form id="engineForm" class="indexForm" action="${pageContext.request.contextPath}/engine/engineList.do" method="post">
       <div class="left">
         <input  type="button" class="botton_style1"  value="添加" onclick="openDg('${pageContext.request.contextPath}/engine/add.do','+ 添加搜索引擎',850,200);" />
         <input  type="button" class="botton_style2"  value="删除" onclick="delEngine()"  />
         <input  type="button" class="botton_style3"  value="启用" onclick="useEngine();" />
         <input  type="button" class="botton_style3"  value="禁用" onclick="noUseEngine();"  />
         <input  type="button" class="botton_style3"  value="启动采集" onclick="redirectTo('全网采集','netMonitor','${pageContext.request.contextPath}/nav/navTo.do?id=dataCollect','/engineControl/engineControl.do');"  />
       </div>
       <div class="right">
       	<span>搜索引擎：</span>
		<input id="enginName" type="text" name="enginName" style="width: 140px;display:inline;" value="${engine.enginName}" class="inputTxt1" maxlength="100" />
        <input  type="button" class="botton_style3 siteButton" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
        </div>
       <div class="clear"></div>
      </form>
     </div>
	<div id="listDiv">
		<div class="table_list" id="iframepage">
			<jsp:include page="/module/webcrawler/engine/list.jsp"></jsp:include>
		</div>
	</div>
</div>
