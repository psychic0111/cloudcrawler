<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>全网监控-定时器</title>
	<%@include file="/include/common.jsp" %>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
   <script>
refresh();
function refresh(){
	var url="${pageContext.request.contextPath}/engineControl/ajaxList.do";
	alert(url);
	AT.get(url,function(data){
		var html = "";
		if(data == "1"){
			html = "全网监控正常运行！";
			window.parent.$("#startButton").hide();
			window.parent.$("#endButton").show();
		}else if(data == "2"){
			html = "搜索引擎未启动或者爬虫出现故障，请您启动爬虫！";
			window.parent.$("#startButton").show();
			window.parent.$("#endButton").hide();
		}else if(data == "3"){
			html = "全网监控地址加载模块出现故障，请您重启系统！";
			window.parent.$("#startButton").hide();
			window.parent.$("#endButton").hide();
		}else if(data == "4"){
			html = "全网监控地址生成模块出现故障，请您重启系统！";
			window.parent.$("#startButton").hide();
			window.parent.$("#endButton").hide();
		}
		window.parent.$("#webCrawlerStatus").html(html);
	});
}
intervalid  = window.setInterval(refresh,1000);

</script>
  </body>
</html>
