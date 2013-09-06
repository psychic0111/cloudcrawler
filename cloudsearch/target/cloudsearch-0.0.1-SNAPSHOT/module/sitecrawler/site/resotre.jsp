<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" language="javascript">
$().ready(function() {
	$("#restoreSiteForm").validate({
			rules:{
				name:{required:true}
			},
			messages:{
				name:{remote:'文件不能为空!'}
			}
		}
	);
});
</script>
<form id="restoreSiteForm" method="post" 	action="${pageContext.request.contextPath}/site/restore.do"  enctype="multipart/form-data">
	<input type="hidden" name="_token" value="${token}">
	<table border=0 cellpadding="0" cellspacing="0" width="100%;"  class=".tbody">
		<tr height="30px;" >
			<td class="TD_Bgcolor3" width="25%" >
				<span class="txt_color2"><img src="${pageContext.request.contextPath}/images/star2.png" width="15" height="16" /></span><span class="label">分类名称: <a href="javascript:void(0)" onclick="toolTip(this,'定义站点分类名称')">[?]</a></span>
			</td>
			<td class="TD_Bgcolor4" width="80%" >
				<input id="sitesZip" type="file" name="sitesZip" style="width: 200px;" class="inputTxt1" />
			</td>
		</tr>
	</table>
	<div class="function2">
		<input name="" type="button" class="botton_style3"  onclick="checkFileType();" id="submitBtn" value="保存" />
		<input name="" type="button" class="botton_style2"	onclick="closeDg()" value="取消" />
	</div >
</form>

<script>
	function checkFileType(){
		var v = document.getElementById("sitesZip").value;
		var i = v.lastIndexOf(".");
		var var1 = v.substring(i+1).toLowerCase();
		if(var1 != "zip" ){	
			alert("请选择zip文件");
			return ;
		}
		document.getElementById("restoreSiteForm").target="uploadIframe";
		document.getElementById("restoreSiteForm").submit();
		closeDg();
	}
</script>