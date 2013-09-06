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
	function refreshState(){
		//全网监控状态
		var url = "${pageContext.request.contextPath}/engineControl/ajaxList.do";
		AT.get(url, function(data){
			var html = "";
			$("#webcrawler_status",window.parent.document).empty();
			if(data == "1"){
				html = "全网监控正在运行！";
			}else if(data == "2"){
				html = "搜索引擎未启动或者爬虫出现故障，请您启动引擎或爬虫！";
			}else if(data == "3"){
				html = "全网监控地址加载模块出现故障，请您重启系统！";
			}else if(data == "4"){
				html = "全网监控地址生成模块出现故障，请您重启系统！";
			}else if(data == "0"){
				html = "全网监控出现故障，请您重启系统！";
			}else if(data == "-1"){
				html = "没找到可用的全网爬虫，请先创建全网爬虫或确认爬虫状态！";
			}
			$("#webcrawler_status",window.parent.document).html(html);
		}, false);
		
		url = "${pageContext.request.contextPath}/crawler/ajaxRuningCrawlers.do?crawlerStatus=2&pageNo=1&pageSize=10";
		AT.get(url, function(data){
			$("#running_crawler",window.parent.document).empty();
			if(data != null && data != ''){
				var strs = data.split(",");
				var html = "";
				for(var i = 0; i< strs.length; i++){
					var str = "<p style='display:inline;'>&nbsp;&nbsp;&nbsp;&nbsp;" + strs[i] + "</p>"
					$("#running_crawler",window.parent.document).append(str);
				}
			}else{
				$("#running_crawler",window.parent.document).append("无");
			}
		}, false);
		
		url = "${pageContext.request.contextPath}/task/runningTasks.do";
		AT.get(url, function(data){
			$("#task_status",window.parent.document).empty();
			if(data != null && data != ''){
				var strs = data.split(",");
				var html = "";
				for(var i = 0; i< strs.length; i++){
					var str = "<p style='display:inline;'>&nbsp;&nbsp;&nbsp;&nbsp;" + strs[i] + "</p>"
					$("#task_status",window.parent.document).append(str);
				}
			}else{
				$("#task_status",window.parent.document).append("无");
			}
		}, false);
	}
	var interId  = window.setInterval(refreshState,5000);
</script>
  </body>
</html>
