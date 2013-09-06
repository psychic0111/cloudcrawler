<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
	<script type="text/javascript" language="javascript">
	//添加代理
	function addFrm_submit(){
		//转化表单为AJAX表单
		AT.postFrm("addSiteProxyForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/siteProxy/siteProxyList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}

	//修改代理
	function editFrm_submit(){
		AT.postFrm("editSiteProxyForm",function(data){
			$("#iframepage").load("${pageContext.request.contextPath}/siteProxy/siteProxyList.do?num="+Math.random(),iframeHeight);	//刷新内容列表
			closeDg();
		},true);
	}
	//删除代理
	function delSiteProxy(){
		var $checked = checkAll();
		if($checked.length==0){
			MSG.alert('请选择要删除的代理!');
		}else{
			MSG.confirm('确认要删除选中的代理吗？',null,function(){
				AT.post("${pageContext.request.contextPath}/siteProxy/del.do",$checked.serialize(),function(data){
					AT.load("iframepage","${pageContext.request.contextPath}/siteProxy/siteProxyList.do",iframeHeight);
				});
			},null);
		}
	}
	function doPage(ths,pageNo,pageSize){
		AT.load("iframepage","${pageContext.request.contextPath}/siteProxy/siteProxyList.do?pageNo="+pageNo+"&pageSize="+pageSize+"&name="+$("#name").val()+"&proxyIp="+$("#proxyIp").val()+"&proxyPort="+$("#proxyPort").val(),iframeHeight);
	}
	function searchFrm_submit(){
		AT.postFrm("searchSiteProxy",function(data){
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
        <td class="c2"><img src="${pageContext.request.contextPath}/images/index.png"  /> 代理管理</td>
        <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
        <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
      </tr>
    </table>
</div>
 <div class="lists">
 	<div class="function">
 		<form id="searchSiteProxy" class="indexForm" action="${pageContext.request.contextPath}/siteProxy/siteProxyList.do" method="post">
	       <div class="left">
	         <input  type="button" class="botton_style1" value="添加" onclick="openDg('${pageContext.request.contextPath}/siteProxy/add.do','+ 添加代理',600,100);"  />
	         <input  type="button" class="botton_style2" value="删除" onclick="delSiteProxy()"  />
	       </div>
	       <div class="right">
	        <input type='hidden' name='pageNo'  id='_pageNo' value='${ pageResult.pageNo}'/>
      	 	<input type='hidden' name='pageSize' id="_pageSize" value='${ pageResult.pageSize}'/>
	       	<span style="display:inline;">代理名称：</span><input id="name" type="text" name="name" style="width: 140px;display:inline;" value="${siteProxy.name }" class="inputTxt1" maxlength="100" />
			<span style="display:inline;">IP地址：</span><input id="proxyIp" type="text" name="proxyIp" style="width: 140px;display:inline;" value="${siteProxy.proxyIp}" class="inputTxt1" maxlength="100" />
			<!--<span style="display:inline;">端口号：</span><input id="proxyPort" type="text" name="proxyPort" style="width: 140px;display:inline;" value="${siteProxy.proxyPort}" class="inputTxt1" maxlength="100" />
	        --><input  type="button" class="botton_style3" style="display:inline;" value="检索" onclick="searchFrm_submit();" />
	        </div>
	       <div class="clear"></div>
       </form>
     </div>
	<div class="table_list" id="iframepage">
		<jsp:include page="/module/sitecrawler/pluginproxy/list.jsp"></jsp:include>
	</div>
</div>
