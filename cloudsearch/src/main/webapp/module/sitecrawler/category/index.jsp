<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

	<script type="text/javascript" language="javascript">
	
	//添加站点类型
	function addFrm_submit(){
		AT.postFrm("addSiteCategoryForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/siteCategory/siteCategoryList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}
	//修改站点类型
	function editFrm_submit(){
		//转化表单为AJAX表单
		AT.postFrm("editSiteCategoryForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/siteCategory/siteCategoryList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}
	//删除站点分类
	function delSiteCategory(){
		var $checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的站点分类!');
		}else{
			MSG.confirm('确认要删除选中的站点分类吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/siteCategory/del.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/siteCategory/siteCategoryList.do",iframeHeight);
				});
			},null);
		}
	}
	function doPage(ths,pageNo,pageSize){
		AT.load("iframepage","${pageContext.request.contextPath}/siteCategory/siteCategoryList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&name="+$("#name").val(),iframeHeight);
	}
	function searchFrm_submit(){
		AT.postFrm("searchSiteCategory",function(data){
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
      <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 站点分类</td>
      <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
      <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
    </tr>
  </table>
</div>
<div class="lists">
	<div class="function">
	<form id="searchSiteCategory" class="indexForm" style="margin:0px;padding:0px;" action="${pageContext.request.contextPath}/siteCategory/siteCategoryList.do" method="post">
      <div class="left">
        <input  type="button" class="botton_style1" style="display:inline;" value="添加" onclick="openDg('${pageContext.request.contextPath}/siteCategory/add.do','+ 添加站点分类',600,100);" />
        <input  type="button" class="botton_style2" style="display:inline;" value="删除" onclick="delSiteCategory()"  />
      </div>
      <div class="right">
      	 <input type='hidden' name='pageNo'  id='_pageNo' value='${ pageResult.pageNo}'/>
      	 <input type='hidden' name='pageSize' id="_pageSize" value='${ pageResult.pageSize}'/>
      	 <span>分类名称：</span>
         <input id="name" type="text" name="name" style="width: 140px;display:inline;" value="${siteCategory.name }" class="inputTxt1" maxlength="100" />
         <input  type="button" class="botton_style3" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
      </div>
      <div class="clear"></div>
     </form>
    </div>
	<div id="listDiv">
		<div class="table_list" id="iframepage">
			<jsp:include page="/module/sitecrawler/category/list.jsp"></jsp:include>
		</div>
	</div>
</div>

