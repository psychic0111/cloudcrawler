<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<table border=0 cellpadding="0"  cellspacing="0" width="100%;" class=".tbody">
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">列&nbsp;&nbsp;表&nbsp;&nbsp;区&nbsp;&nbsp;域: <a href="javascript:void(0)" onclick="toolTip(this,'定义搜索引擎列表区域，列表区域是为了精确从目标网站上采集数据而设置的属性，使用时需要指定采集的数据在目标网站所在的区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="listBlock" type="text" name="listBlock" value="<c:out value="${ engine.listBlock}" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">块&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;规&nbsp;&nbsp;&nbsp;&nbsp;则: <a href="javascript:void(0)" onclick="toolTip(this,'定义搜索引擎块规则，块规则是为了精确从搜索结果页面解析到每一条搜索结果的块区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="itemBlock" type="text" name="itemBlock"  value="<c:out value="${ engine.itemBlock}" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">原文相对地址: <a href="javascript:void(0)" onclick="toolTip(this,'定义抓取页面时相对地址的解析规则，由于部分采集路径为相对路径，需要加上网站域名组成一个完整的网络地址，爬虫才可以正确爬取')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="hostUrl" type="text" name="hostUrl" value="<c:out value="${engine.hostUrl }" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">原&nbsp;&nbsp;文&nbsp;&nbsp;链&nbsp;&nbsp;接: <a href="javascript:void(0)" onclick="toolTip(this,'定义抓取页面原文链接的解析规则，原文链接是指文章的原文链接出现在搜索结果页所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="urlRule" type="text" name="urlRule" value="<c:out value="${engine.urlRule }" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
			
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">发&nbsp;&nbsp;布&nbsp;&nbsp;时&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'定义抓取内容发布时间的解析规则，发布时间是指文章的发布时间出现在搜索结果页所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="publishTimeRule" type="text" name="publishTimeRule" value="<c:out value="${engine.publishTimeRule }" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题: <a href="javascript:void(0)" onclick="toolTip(this,'定义抓取内容标题的解析规则，标题是指文章的标题出现在搜索结果页所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="titleRule" type="text" name="titleRule" value="<c:out value="${engine.titleRule }" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者: <a href="javascript:void(0)" onclick="toolTip(this,'定义抓取内容作者解析规则，作者是指文章的作者出现在搜索结果页所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="authorRule" type="text" name="authorRule" value="<c:out value="${engine.authorRule }" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长: <a href="javascript:void(0)" onclick="toolTip(this,'定义抓取视频时长的解析规则，时长是指视频的时长出现在搜索结果页所在区域，支持正则表达式和选择器两种解析方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="durationRule" type="text" name="durationRule" value="<c:out value="${engine.durationRule }" escapeXml="true"/>" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">图&nbsp;&nbsp;片&nbsp;&nbsp;地&nbsp;&nbsp;址: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎抓取图片的存放位置')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="imagePath" type="text" name="imagePath" value="<c:out value="${engine.imagePath }" escapeXml="true"/>"  style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">每&nbsp;&nbsp;页&nbsp;&nbsp;条&nbsp;&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎搜索结果页包含数据条数')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="perPageCount" type="text" name="perPageCount" value="${engine.perPageCount}" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
</table>
