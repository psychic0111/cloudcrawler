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
				var pageNo = $("#_pageNo").val();
				var pageSize = $("input[name='pageSize']").val();
				var params = $("#searchSite").serialize();
				var url = "${pageContext.request.contextPath}/site/siteList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&"+params+"&num="+Math.random();
				$("#iframepage").load(url,iframeHeight);	//刷新内容列表
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
				var pageNo = $("#_pageNo").val();
				var pageSize = $("input[name='pageSize']").val();
				var params = $("#searchSite").serialize();
				var url = "${pageContext.request.contextPath}/site/siteList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&"+params+"&num="+Math.random();
				$("#listDiv").load(url,iframeHeight);	//刷新内容列表
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
					var pageNo = $("#_pageNo").val();
					var pageSize = $("input[name='pageSize']").val();
					var params = $("#searchSite").serialize();
					var url = "${pageContext.request.contextPath}/site/siteList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&"+params+"&num="+Math.random();
					AT.load("iframepage",url,iframeHeight);
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
					var pageNo = $("#_pageNo").val();
					var pageSize = $("input[name='pageSize']").val();
					var params = $("#searchSite").serialize();
					var url = "${pageContext.request.contextPath}/site/siteList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&"+params+"&num="+Math.random();
					AT.load("iframepage",url,iframeHeight);
				});
			},null);
		}
		
	}
	
	//启用、禁用条件查询的站点
	function changeState(state){
		var siteCategoryId = $("#siteCategoryId").val();
		var siteName = $("#name").val();
		var param = "statue="+state+"&siteCategoryId=" + siteCategoryId + "&name=" + siteName;
		MSG.confirm('确认要启用查询条件下所有站点吗？',null,function(){
			AT.post("${pageContext.request.contextPath}/site/use.do",param,function(data){
				var pageNo = $("#_pageNo").val();
				var pageSize = $("input[name='pageSize']").val();
				var params = $("#searchSite").serialize();
				var url = "${pageContext.request.contextPath}/site/siteList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&"+params+"&num="+Math.random();
				AT.load("iframepage",url,iframeHeight);
			});
		},null);
		
	}
	
	//禁用站点
	function noUseSite(){
		$checked = $("[type=checkbox]:checked");
		if($checked.length==0){
			MSG.alert('请选择要禁用的站点!');
		}else{
			MSG.confirm('确认要禁用选中的站点吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/site/noUse.do",$checked.serialize(),function(data){
					var pageNo = $("#_pageNo").val();
					var pageSize = $("input[name='pageSize']").val();
					var params = $("#searchSite").serialize();
					var url = "${pageContext.request.contextPath}/site/siteList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&"+params+"&num="+Math.random();
					AT.load("iframepage",url,iframeHeight);
				});
			},null);
		}
		
	}
	
	//备份站点
	function backup(){
		$checked = $("[type=checkbox]:checked");
		window.open('${pageContext.request.contextPath}/site/backup.do?'+$checked.serialize()+'&num='+Math.random());
	}
	
	//备份查询的站点
	function backupQuery(){
		var siteCategoryId = $("#siteCategoryId").val();
		var siteName = $("#name").val();
		var param = "siteCategoryId=" + siteCategoryId + "&name=" + siteName;
		window.open('${pageContext.request.contextPath}/site/backupQuery.do?'+param+'&num='+Math.random());
	}
	
	//备份所有的站点
	function backupAll(){
		window.open('${pageContext.request.contextPath}/site/backupQuery.do?num='+Math.random());
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
	    if (key.toString() == "13" && e.target.tagName != "TEXTAREA") {
	     return false;
	    }
	   });
	  });
	 });
	</script>
<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
width:110px;
margin-left:2px;
display:inline-table;
height:28px;
}
/* remove the bullets and set the margin and padding to zero for the unordered list */
.menu ul {
padding:0;
margin:0;
list-style-type: none;
width:110px;
}
/* float the list so that the items are in a line and their position relative so that the drop down list will appear in the right place underneath each list item */
.menu ul li {
position:relative;
}
/* style the links to be 104px wide by 30px high with a top and right border 1px solid white. Set the background color and the font size. */
.menu ul li a, .menu ul li a:visited {
display:block;
text-align:center;
text-decoration:none;
width:110px;
height:27px;
color:#333333;
border:1px solid #C0C0C0;
border-width:1px 1px 1px 1px;
background:#c9c9a7;
line-height:33px;
}
/* make the dropdown ul invisible */
.menu ul li ul {
display: none;
}
/* specific to non IE browsers */
/* set the background and foreground color of the main menu li on hover */
.menu ul li:hover a {
color:#fff;
background:#b3ab79;
}
/* make the sub menu ul visible and position it beneath the main menu list item */
.menu ul li:hover ul {
display:block;
position:absolute;
top:28px;
left:0;
width:110px;
}
/* style the background and foreground color of the submenu links */
.menu ul li:hover ul li a {
display:block;
background:#faeec7;
color:#000;
}
/* style the background and forground colors of the links on hover */
.menu ul li:hover ul li a:hover {
background:#dfc184;
color:#000;
}
</style>
<!--[if lte IE 6]>
<style type="text/css">
/* styling specific to Internet Explorer IE5.5 and IE6. Yet to see if IE7 handles li:hover */
/* Get rid of any default table style */
table {
border-collapse:collapse;
margin:0;
padding:0;
}
/* ignore the link used by 'other browsers' */
.menu ul li a.hide, .menu ul li a:visited.hide {
display:none;
}
/* set the background and foreground color of the main menu link on hover */
.menu ul li a:hover {
color:#fff;
background:#b3ab79;
}
/* make the sub menu ul visible and position it beneath the main menu list item */
.menu ul li a:hover ul {
display:block;
position:absolute;
top:32px;
left:0;
width:110px;
}
/* style the background and foreground color of the submenu links */
.menu ul li a:hover ul li a {
background:#faeec7;
color:#000;
}
/* style the background and forground colors of the links on hover */
.menu ul li a:hover ul li a:hover {
background:#dfc184;
color:#000;
}
</style>
<![endif]-->
<div class="title">
   <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
        <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 站点管理</td>
        <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
        <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
     </tr>
   </table>
</div>
<div class="lists">
	<div class="function">
	<form id="searchSite" class="indexForm" action="${pageContext.request.contextPath}/site/siteList.do" method="post">
       <div class="left">
         <input  type="button" class="botton_style1 siteButton" style="padding-top:5px;" value="添加" onclick="openDg('${pageContext.request.contextPath}/site/add.do','+ 添加站点',600,200);" />
         <input  type="button" class="botton_style2 siteButton" style="padding-top:5px;" value="删除" onclick="delSite()"  />
         <!-- <input  type="button" class="botton_style3 siteButton"  value="启用" onclick="useSite();" />
         <input  type="button" class="botton_style3 siteButton"  value="禁用" onclick="noUseSite();"  /> -->
         <div class="menu">
         	<ul>
				<li>
					<a class="hide" href="../menu/index.html" _fcksavedurl="../menu/index.html">启用&nbsp;/&nbsp;禁用</a>
					<!--[if lte IE 6]>
					<a href="../menu/index.html">DEMOS
					<table><tr><td>
					<![endif]-->
					<ul>
						<li onclick="useSite();"><a href="#" title="启用选中站点">启用选中站点</a></li>
						<li onclick="changeState(0);"><a href="#" title="启用所有符合查询条件的站点">启用符合条件站点</a></li>
						<li onclick="noUseSite();"><a href="#" title="禁用选中站点">禁用选中站点</a></li>
						<li onclick="changeState(1);"><a href="#" title="禁用所有符合查询条件的站点">禁用符合条件站点</a></li>
					</ul>
					<!--[if lte IE 6]>
					</td></tr></table>
					</a>
					<![endif]-->
				</li>
			</ul>
         <div class="clear"> </div>
		 </div>
		 <div class="menu">
         	<ul>
				<li>
					<a class="hide" href="../menu/index.html" _fcksavedurl="../menu/index.html">导入&nbsp;/&nbsp;导出</a>
					<!--[if lte IE 6]>
					<a href="../menu/index.html">DEMOS
					<table><tr><td>
					<![endif]-->
					<ul>
						<li onclick="openDg('${pageContext.request.contextPath}/site/toRestore.do','+ 导入站点',600,150);"><a href="#" title="导入站点">导入站点</a></li>
						<li onclick="backup();"><a href="#" title="导出选中站点">导出选中站点</a></li>
						<li onclick="backupQuery();"><a href="#" title="导出所有符合查询条件站点">导出符合条件站点</a></li>
						<li onclick="backupQuery();"><a href="#" title="导出所有站点">导出所有站点</a></li>
					</ul>
					<!--[if lte IE 6]>
					</td></tr></table>
					</a>
					<![endif]-->
				</li>
			</ul>
         <div class="clear"> </div>
		 </div>
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
