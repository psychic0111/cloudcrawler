<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>MyHtml.html</title>
	<%@include file="/include/common.jsp" %>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
   <script>
function refresh(){
	var state = new Array();
	var state1 = new Array();
	var url="${pageContext.request.contextPath}/crawler/ajaxList.do";
	AT.get(url,function(data){
		state=data.split(",");
		var allStart = "";
		var nowStart = "";
		for(var i=0;i<state.length;i++){
			allStart = allStart + "2";
			state1 = state[i].split(":");
			nowStart = nowStart + state1[1];
			if(state1[1]==1){
				 window.parent.$("#status_"+state1[0]).html("已停止");
				 window.parent.$("#dataStatusB_"+state1[0]).hide();
				 window.parent.$("#dataStatusA_"+state1[0]).show();
			} 
			if(state1[1]==2){
				 window.parent.$("#status_"+state1[0]).html("运行中");
				 window.parent.$("#dataStatusB_"+state1[0]).show();
				 window.parent.$("#dataStatusA_"+state1[0]).hide();
			}
			if(state1[1]==3){
				 window.parent.$("#status_"+state1[0]).html("停止中");
				 window.parent.$("#dataStatusB_"+state1[0]).show();
				 window.parent.$("#dataStatusA_"+state1[0]).hide();
			}
		}
		if(allStart == nowStart){
			window.parent.$("#startAllCrawler").hide();
			window.parent.$("#endAllCrawler").show();
		}else{
			window.parent.$("#startAllCrawler").show();
			window.parent.$("#endAllCrawler").hide();
		}
	});
}
intervalid  = window.setInterval(refresh,5000);
refresh();
</script>
  </body>
</html>
