<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.css">
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
<script src="${pageContext.request.contextPath}/js/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<script type="text/javascript" language="javascript">
//添加地区
function addFrm_submit(){
	//转化表单为AJAX表单
	AT.postFrm("addTemplateCategoryForm",function(data){
		$("#treeAndList").load("${pageContext.request.contextPath}/templateCategory/templateCategoryList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
		closeDg();
	},true);
}

//修改地区
function editFrm_submit(){
	//转化表单为AJAX表单
	AT.postFrm("editTemplateCategoryForm",function(data){
		$("#treeAndList").load("${pageContext.request.contextPath}/templateCategory/templateCategoryList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
		closeDg();
	},true);
}
//删除地区
function delTemplateCategory(){
	var $checked = checkAll();
	var iscontinue = true;
	var parentName = "";
	for(var i = 0;i<$checked.length;i++){
		parentName += menutree.getItemText($checked[i].value + "_1");
		if(parentName!=""&&parentName!="0"){
			iscontinue = false;
			break;
		}
	}
	if($checked.length==0){
		MSG.alert('请选择要删除的模板分类!');
	}else{
		if(iscontinue){
			MSG.confirm('确认要删除选中的模板分类吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/templateCategory/del.do",$checked.serialize(),function(data){
					AT.load("treeAndList","${pageContext.request.contextPath}/templateCategory/templateCategoryList.do",iframeHeight);
				});
			},null);
		}else{
			MSG.alert("在模板分类["+parentName+"]下存在子类，暂时无法执行删除操作!");
		}
	}
}
var currentLevel = 1;
function doPage(ths,pageNo,pageSize){
	AT.load("iframepage","${pageContext.request.contextPath}/templateCategory/templateCategoryListTree.do?pageNo="+pageNo+"&pageSize="+pageSize+"&name="+encodeURIComponent($("#name").val())+"&parentId="+$("#parentId").val(),iframeHeight);
}
function searchFrm_submit(){
	AT.postFrm("searchTemplateCategory",function(data){
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
       <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 模板分类</td>
       <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
	<div id="treeAndList">
		<jsp:include page="/module/sitecrawler/templateCategory/templateCategoryTree.jsp"></jsp:include>
	 	<div class="table_list" id="iframepage">
			<jsp:include page="/module/sitecrawler/templateCategory/list.jsp"></jsp:include>
		</div>
	</div>
</div>
