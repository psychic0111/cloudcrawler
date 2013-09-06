<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" language="javascript">
//添加监控词
function addFrm_submit(){
	//转化表单为AJAX表单
	AT.postFrm("addKeywordForm",function(data){
		$("#iframepage").load("${pageContext.request.contextPath}/keyword/keywordList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
		closeDg();
	},true);
}
//修改监控词
function editFrm_submit(){
	//转化表单为AJAX表单
	AT.postFrm("editKeywordForm",function(data){
		$("#iframepage").load("${pageContext.request.contextPath}/keyword/keywordList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
		closeDg();
	},true);
}
//删除监控词
function delKeyword(){
	var $checked = checkAll();
	if($checked.length==0){
		MSG.alert('请选择要删除的监控词!');
	}else{
		MSG.confirm('确认要删除选中的监控词吗？',null,function(){
			AT.post("${pageContext.request.contextPath}/keyword/del.do",$checked.serialize(),function(data){
				AT.load("iframepage","${pageContext.request.contextPath}/keyword/keywordList.do?num="+Math.random(),iframeHeight);
			});
		},null);
		
	}
}
//启用监控词
function useKeyword(){
	$checked = $("[type=checkbox]:checked");
	if($checked.length==0){
		MSG.alert('请选择要启用的监控词!');
	}else{
		MSG.confirm('确认要启用选中的监控词吗？',null,function(){
			AT.post("${pageContext.request.contextPath}/keyword/use.do",$checked.serialize(),function(data){
				AT.load("iframepage","${pageContext.request.contextPath}/keyword/keywordList.do?num="+Math.random(),iframeHeight);
			});
		},null);
	}
	
}
//禁用监控词
function noUseKeyword(){
	$checked = $("[type=checkbox]:checked");
	if($checked.length==0){
		MSG.alert('请选择要禁用的监控词!');
	}else{
		MSG.confirm('确认要禁用选中的监控词吗？',null,function(){
			AT.post("${pageContext.request.contextPath}/keyword/noUse.do",$checked.serialize(),function(data){
				AT.load("iframepage","${pageContext.request.contextPath}/keyword/keywordList.do?num="+Math.random(),iframeHeight);
			});
		},null);
	}
	
}
function doPage(ths,pageNo,pageSize){
	AT.load("iframepage","${pageContext.request.contextPath}/keyword/keywordList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&name="+$("#name").val(),iframeHeight);
}
function _pageFrmsubmit(){
	AT.postFrm("searchKeywordProxy",function(data){
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
	   		if (key.toString() == "13" && e.target.tagName != "TEXTAREA") {
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
       <td class="c2"><img src="${pageContext.request.contextPath}/images/collect.png"  /> 监控词管理</td>
       <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
       <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
     <form id="searchKeywordProxy" class="indexForm"  action="${pageContext.request.contextPath}/keyword/keywordList.do" method="post">
	     <div class="function">
	        <div class="left">
		        <input  type="button" class="botton_style1" value="添加" onclick="openDg('${pageContext.request.contextPath}/keyword/add.do','+ 添加监控词',600,200);" />
		        <input  type="button" class="botton_style2" value="删除" onclick="delKeyword()"  />
		        <input  type="button" class="botton_style3" value="启用" onclick="useKeyword()"  />
		        <input  type="button" class="botton_style3" value="禁用" onclick="noUseKeyword()"  />
	        </div>
	   		<div class="right">
	   			<span>名称：</span>
	            <input id="name" type="text" name="name" style="width: 140px;display:inline;" value="${keyword.name }" class="inputTxt1" maxlength="100" />
	            <span>监控词：</span>
	            <input id="web_words" type="text" name="words" style="width: 140px;display:inline;" value="${keyword.words }" class="inputTxt1" maxlength="100" />
	            <input  type="button" class="botton_style3" style="display:inline;" value="检索" onclick="_pageFrmsubmit();" />
	      	</div>
	        <div class="clear"></div>
	     </div>
     </form>
   	 <div class="table_list" id="iframepage">
   		<jsp:include page="/module/webcrawler/keyword/list.jsp"></jsp:include>
     </div>
</div>
