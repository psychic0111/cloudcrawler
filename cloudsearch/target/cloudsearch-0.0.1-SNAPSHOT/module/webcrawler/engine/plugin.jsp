<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<table border=0 cellpadding="0"  cellspacing="0" width="100%;" class=".tbody">
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">列&nbsp;&nbsp;表&nbsp;&nbsp;区&nbsp;&nbsp;域: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎内容所在的列表区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="listBlock" type="text" name="listBlock" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#listBlock").val('${engine.listBlock}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">块&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;规&nbsp;&nbsp;&nbsp;&nbsp;则: <a href="javascript:void(0)" onclick="toolTip(this,'定义每一条内容所在的快区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="itemBlock" type="text" name="itemBlock" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#itemBlock").val('${engine.itemBlock}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">原&nbsp;&nbsp;文&nbsp;&nbsp;链&nbsp;&nbsp;接: <a href="javascript:void(0)" onclick="toolTip(this,'定义每一条内容所在的原文链接')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="urlRule" type="text" name="urlRule" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#urlRule").val('${engine.urlRule}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">原文相对地址: <a href="javascript:void(0)" onclick="toolTip(this,'若该搜索引擎采集相对地址时，需要定义原文相对地址，即域名')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="hostUrl" type="text" name="hostUrl" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#hostUrl").val('${engine.hostUrl}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">发&nbsp;&nbsp;布&nbsp;&nbsp;时&nbsp;&nbsp;间: <a href="javascript:void(0)" onclick="toolTip(this,'定义么一条内容所在的的发布时间所在的区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="publishTimeRule" type="text" name="publishTimeRule" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#publishTimeRule").val('${engine.publishTimeRule}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题: <a href="javascript:void(0)" onclick="toolTip(this,'定义么一条内容所在的的标题所在的区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="titleRule" type="text" name="titleRule" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#titleRule").val('${engine.titleRule}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者: <a href="javascript:void(0)" onclick="toolTip(this,'定义么一条内容所在的的发布时间所在的区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="authorRule" type="text" name="authorRule" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#authorRule").val('${engine.authorRule}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长: <a href="javascript:void(0)" onclick="toolTip(this,'定义么一条内容所在的的发布时间所在的区域')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="durationRule" type="text" name="durationRule" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#durationRule").val('${engine.durationRule}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">图&nbsp;&nbsp;片&nbsp;&nbsp;地&nbsp;&nbsp;址: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎爬去的图片的存放位置')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="imagePath" type="text" name="imagePath" value="" style="width: 450px;" class="inputTxt1"  />
			<script>
				$("#imagePath").val('${engine.imagePath}');
			</script>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="22%" >
			<span class="label">每&nbsp;&nbsp;页&nbsp;&nbsp;条&nbsp;&nbsp;数: <a href="javascript:void(0)" onclick="toolTip(this,'定义该搜索引擎每一页采集数据多少条')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="78%" >
			<input id="perPageCount" type="text" name="perPageCount" value="${engine.perPageCount}" style="width: 450px;" class="inputTxt1"  />
		</td>
	</tr>
</table>
