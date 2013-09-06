<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<table border=0 cellpadding="0" cellspacing="0" width="100%;" class=".tbody">
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">爬&nbsp;&nbsp;虫&nbsp;&nbsp;名&nbsp;&nbsp;称: <a href="javascript:void(0)" onclick="toolTip(this,'定义爬虫名称，给爬虫取一个名称')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<input id="name" type="text" name="name" id="crawlerName" value="${crawler.name }" style="width: 250px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">访&nbsp;&nbsp;问&nbsp;&nbsp;路&nbsp;&nbsp;径: <a href="javascript:void(0)" onclick="toolTip(this,'定义爬虫访问路径，爬虫在网络上的访问路径')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<input id="crawlerAddress" type="text" name="crawlerAddress" value="${crawler.crawlerAddress}" style="width: 250px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">爬&nbsp;&nbsp;虫&nbsp;&nbsp;代&nbsp;&nbsp;码: <a href="javascript:void(0)" onclick="toolTip(this,'定义爬虫编码，爬虫在部署到服务器的时候设置的编码')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<input id="code" type="text" name="code" value="${crawler.code }" style="width: 250px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">采&nbsp;&nbsp;集&nbsp;&nbsp;线&nbsp;&nbsp;程: <a href="javascript:void(0)" onclick="toolTip(this,'定义爬虫采集线程，爬虫在采集数据的过程中，每一个爬虫可以同时开启的线程数')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<input id="crawlerThread" type="text" name="crawlerThread" value="${crawler.crawlerThread }" style="width: 250px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">下&nbsp;&nbsp;载&nbsp;&nbsp;线&nbsp;&nbsp;程: <a href="javascript:void(0)" onclick="toolTip(this,'定义下载线程，爬虫在下载图片的过程中，每一个爬虫可以同时开启的线程数')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<input id="downloadThread" type="text" name="downloadThread" value="${crawler.downloadThread}" style="width: 250px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">单&nbsp;&nbsp;站点&nbsp;线程: <a href="javascript:void(0)" onclick="toolTip(this,'定义单站点线程，每一个站点中，采集数据和下载图片的总线程个数')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<input id="siteThread" type="text" name="siteThread" value="${crawler.siteThread}" style="width: 250px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" valign="top" >
			<span class="label">是否存储快照: <a href="javascript:void(0)" onclick="toolTip(this,'定义爬虫是否存储快照，指该爬虫在下载页面时是否保存页面的快照信息')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<select id="saveSnap" class="inputTxt1" style="width: 185px;"  name="saveSnap" >
				<option value="1" <c:if test="${crawler.saveSnap=='1'}">selected="selected"</c:if>>是</option>
				<option value="0" <c:if test="${crawler.saveSnap=='0'}">selected="selected"</c:if>>否</option>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">使&nbsp;&nbsp;用&nbsp;&nbsp;状&nbsp;&nbsp;态: <a href="javascript:void(0)" onclick="toolTip(this,'定义爬虫状态，若为启动，则可以执行采集；否则，不执行采集')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<select id="status" class="inputTxt1" style="width: 185px;"  name="status" >
				<option value="1" <c:if test="${crawler.status==1 }">selected="selected"</c:if>>启用</option>
				<option value="0" <c:if test="${crawler.status==0 }">selected="selected"</c:if>>禁用</option>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">爬&nbsp;&nbsp;虫&nbsp;&nbsp;类&nbsp;&nbsp;型: <a href="javascript:void(0)" onclick="toolTip(this,'选择爬虫类型，是指该爬虫被使用在哪些数据采集中')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<input type="checkbox" id="siteCrawlercrawlerType" name="crawlerType"  <c:if test="${fn:indexOf(crawler.crawlerType,'网页爬虫')>-1}">checked="checked"</c:if> value="网页爬虫"/>网页爬虫&nbsp;&nbsp;&nbsp;
			<input type="checkbox" id="webCrawlercrawlerType" name="crawlerType" <c:if test="${fn:indexOf(crawler.crawlerType,'全网爬虫')>-1}">checked="checked"</c:if> value="全网爬虫"/>全网爬虫&nbsp;&nbsp;&nbsp;
			<!--<input type="checkbox" id="weiboCrawlercrawlerType" name="crawlerType" <c:if test="${fn:indexOf(crawler.crawlerType,'微博爬虫')>-1}">checked="checked"</c:if> value="微博爬虫"/>微博爬虫
		--></td>
	</tr>
</table>
