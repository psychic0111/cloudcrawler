<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.css">
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script type="text/javascript" language="javascript">
	
	//添加代理
	function addFrm_submit(){
		//转化表单为AJAX表单
		AT.postFrm("addTemplateForm",function(data){
			$("#treeAndList").load("${pageContext.request.contextPath}/template/templateList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}

	//修改代理
	function editFrm_submit(){
		AT.postFrm("editTemplateForm",function(data){
			$("#treeAndList").load("${pageContext.request.contextPath}/template/templateList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}
	//删除模板
	function delTemplate(){
		var $checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的模板!');
		}else{
			MSG.confirm('确认要删除选中的模板吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/template/del.do",$checked.serialize(),function(data){
					AT.load("treeAndList","${pageContext.request.contextPath}/template/templateList.do",iframeHeight);
				});
			},null);
			
		}
	}
var currentLevel = 0;
function doPage(ths,pageNo,pageSize){
	AT.load("iframepage","${pageContext.request.contextPath}/template/templateContentList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&categoryId="+$("#fristLevel").val()+"&name="+encodeURIComponent($("#name").val())+"&isRoot=0",iframeHeight);
}
function searchFrm_submit(){
	AT.postFrm("searchTemplate",function(data){
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
       <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 模板管理</td>
       <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
	<div id="treeAndList">    
		<jsp:include page="/module/sitecrawler/template/templateCategoryTree.jsp"></jsp:include> 
   		<div class="table_list" id="iframepage">
   			<jsp:include page="/module/sitecrawler/template/list.jsp"></jsp:include>
   		</div>
     </div>
</div>