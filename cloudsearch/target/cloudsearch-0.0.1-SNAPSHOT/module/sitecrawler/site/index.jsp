<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
	<script type="text/javascript" language="javascript">
	//添加代理
	var currentNumber = 1;
	function addFrmTemplate_submit(){
		//转化表单为AJAX表单
		/*
		if(currentNumber == 3){
			document.getElementById("addSiteForm").action = '${pageContext.request.contextPath}/template/doAddForSite.do';
			AT.postFrm("addTemplateForm",function(data){
				$("#templates").show();
				$("#templatesDiv").append("<input id=\"templateIds\" type=\"checkbox\" name=\"templateIds\" value=\""+data.id+"\" checked=\"checked\"/>"+data.name+"");
			},true);
		}
		else{
			*/
			AT.postFrm("addSiteForm",function(data){
				$("#iframepage").load("${pageContext.request.contextPath}/site/siteList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
				closeDg();
			},true);
		/*
		}
		*/
	}

	//修改代理
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
	//删除代理
	function delSite(){
		var $checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的站点!');
		}else{
			MSG.confirm('确认要删除选中的站点吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/site/del.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/site/siteList.do",iframeHeight);
				});
			},null);
		}
	}
	//启用站点
	function useSite(){
		$checked = $("[type=checkbox]:checked");
		if($checked.length==0){
			MSG.alert('请选择要启用的站点!');
		}else{
			MSG.confirm('确认要启用选中的站点吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/site/use.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/site/siteList.do",iframeHeight);
				});
			},null);
		}
		
	}
	//禁用站点
	function noUseSite(){
		$checked = $("[type=checkbox]:checked");
		if($checked.length==0){
			MSG.alert('请选择要禁用的站点!');
		}else{
			MSG.confirm('确认要禁用选中的站点吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/site/noUse.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/site/siteList.do",iframeHeight);
				});
			},null);
		}
		
	}
	//备份站点
	function backup(){
		$checked = $("[type=checkbox]:checked");
		window.open('${pageContext.request.contextPath}/site/backup.do?'+$checked.serialize()+'&num='+Math.random());
	}
	//导入站点信息
	function resotreFrm_submit(){
		AT.postFrm("restoreSiteForm",function(data){
			AT.load("listDiv","${pageContext.request.contextPath}/site/siteList.do?num="+Math.random(),iframeHeight);
			closeDg();
		},true);
	}
	function upload(){
		document.getElementById("restoreSiteForm").submit();
	}
	function doPage(ths,pageNo,pageSize){
		AT.load("iframepage","${pageContext.request.contextPath}/site/siteList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&siteCategoryId="+$("#siteCategoryId").val()+"&name="+encodeURIComponent($("#name").val()),iframeHeight);
	}
	function searchFrm_submit(){
		AT.postFrm("searchSite",function(data){
			$("#iframepage").html("");
			$("#iframepage").html(data);
			iframeHeight();
			closeDg();
		},true);
	}
	//上传成功以后刷新页面
	function uploadSuccess(){
		AT.load("iframepage","${pageContext.request.contextPath}/site/siteList.do",iframeHeight);
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
        <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 站点管理</td>
        <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
	<div class="function">
	<form id="searchSite" class="indexForm" action="${pageContext.request.contextPath}/site/siteList.do" method="post">
       <div class="left">
         <input  type="button" class="botton_style1 siteButton"  value="添加" onclick="openDg('${pageContext.request.contextPath}/site/add.do','+ 添加站点',600,200);" />
         <input  type="button" class="botton_style2 siteButton"  value="删除" onclick="delSite()"  />
         <input  type="button" class="botton_style3 siteButton"  value="启用" onclick="useSite();" />
         <input  type="button" class="botton_style3 siteButton"  value="禁用" onclick="noUseSite();"  />
         <input  type="button" class="botton_style3 siteButton"  value="导入" onclick="openDg('${pageContext.request.contextPath}/site/toRestore.do','+ 导入站点',600,150);" />
         <input  type="button" class="botton_style3 siteButton"  value="导出" onclick="backup();"  />
       </div>
       <div class="right">
		<span style="display:inline;">站点分类：</span>
		<select id="siteCategoryId" class="inputTxt1" style="width: 140px;"  name="siteCategoryId" >
			<option value="">请选择...</option>
			<c:forEach items="${siteCategory}" var="sc" varStatus="status">
				<option value = "${sc.id }" <c:if test="${fn:indexOf(site.siteCategoryId,sc.id)>-1}">selected="selected"</c:if> >${sc.name }</option>
			</c:forEach>
		</select>
		<span style="display:inline;">站点名称：</span>
		<input id="name" type="text" name="name" style="width: 140px;display:inline;" value="${site.name}" class="inputTxt1" maxlength="100" />
        <input  type="button" class="botton_style3 siteButton" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
        </div>
       <div class="clear"></div>
      </form>
     </div>
	<div id="listDiv">
		<div class="table_list" id="iframepage">
			<jsp:include page="/module/sitecrawler/site/list.jsp"></jsp:include>
		</div>
	</div>
</div>
<iframe id="uploadIframeId" name="uploadIframe" frameborder="0" width="100%;" height="100%;" style="display:none;"></iframe>
