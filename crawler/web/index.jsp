<%@ page language="java" import="java.util.*,com.xdtech.platform.crawler.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>爬虫系统</title>
  </head>
  <body style="font-family:arial;font-size:12px;text-align:left;color:#333">
  	<%
  		
  		Properties properties = System.getProperties();
		Set<Object> keys = properties.keySet();
  	 %>
  	<table border=1>
  		<tr>
  			<td>接口定义</td>
  			<td><a href="<%=basePath%>service"><%=basePath%>service</a></td>
  		</tr>
  		<tr>
  			<td>分配内存</td>
  			<td><%=Runtime.getRuntime().maxMemory()>>10>>10%>M</td>
  		</tr>
  		<tr>
  			<td>剩余内存</td>
  			<td><%=Runtime.getRuntime().freeMemory()>>10>>10%>M</td>
  		</tr>
  		<tr>
  			<td>抓取页面</td>
  			<td><%=CrawlerStatus.getTotal() %></td>
  		</tr>
  		<tr>
  			<td>失败数量</td>
  			<td><%=CrawlerStatus.failCount%></td>
  		</tr>
  		<tr>
  			<td>活跃线程</td>
  			<td><%=CrawlerStatus.getCrawlThreadNum()%></td>
  		</tr>
  		<tr>
  			<td>当前流量</td>
  			<td><%=CrawlerStatus.getFlow()%></td>
  		</tr>
  		<%if(keys!=null){%>
  		<%for(Object obj:keys){%>
  		<tr>
  			<td><%=obj.toString() %></td>
  			<td><%=properties.getProperty(obj.toString(),"") %></td>
  		</tr>
  		<%} %>
  		<%}%>
  	</table>
  </body>
</html>
