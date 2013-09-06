<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<table border=0 cellpadding="0"  cellspacing="0" width="100%;" class=".tbody">
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">是否下载图片: <a href="javascript:void(0)" onclick="toolTip(this,'网页中的图片是否下载')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="70%" >
			<select id="downImage" class="inputTxt1" style="width: 185px;"  name="downImage" >
				<option value="1" <c:if test="${crawler.downImage==1 }">selected="selected"</c:if>>是</option>
				<option value="0" <c:if test="${crawler.downImage==0 && crawler.id!=null}">selected="selected"</c:if>>否</option>
			</select>
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">最小图片大小: <a href="javascript:void(0)" onclick="toolTip(this,'所要下载的图片的最小尺寸')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="imageSize" type="text" name="imageSize" value="${crawler.imageSize }" style="width: 300px;" class="inputTxt1"  />(K)
		</td>
	</tr>
	<tr  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">图片存储路径: <a href="javascript:void(0)" onclick="toolTip(this,'下载的图片的保存路径')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="imagePath" type="text" name="imagePath" value="${crawler.imagePath }" style="width: 300px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">数据存储方式: <a href="javascript:void(0)" onclick="toolTip(this,'采集的数据的保存方式')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="saveMode" class="inputTxt1" onchange="changeBlock(this);" style="width: 185px;"  name="saveMode" >
				<option value="1" <c:if test="${crawler.saveMode==1 }">selected="selected"</c:if>>数据库存储</option>
			</select>
		</td>
	</tr>
	<tr  class="crawlerHide" height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">数据存储路径: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是文件存储时，您要保存的数据文件的路径')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="dataPath" type="text" name="dataPath" value="${crawler.dataPath }" style="width: 300px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr class="crawlerShow" height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">数据库&nbsp;&nbsp;&nbsp;类型: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，您要存放的数据库类型')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="dbtype" onchange="getDataBaseString();" class="inputTxt1" style="width: 185px;"  name="dbtype" >
				<option value="com.mysql.jdbc.Driver" <c:if test="${dataBase.dbtype=='mysql' }">selected="selected"</c:if>>mysql</option>
				<option value="com.microsoft.sqlserver.jdbc.SQLServerDriver" <c:if test="${dataBase.dbtype=='sqlserver' }">selected="selected"</c:if>>sqlserver</option>
			</select>
		</td>
	</tr>
	<tr class="crawlerShow"  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">服务器&nbsp;&nbsp;&nbsp;地址: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，您要使用的数据库所在的服务器的IP')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="dbip" onkeyup="getDataBaseString();" type="text" name="dbip" value="${dataBase.dbip }" style="width: 300px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr class="crawlerShow"  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">端&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;口&nbsp;&nbsp;&nbsp;&nbsp;号: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，您要使用的数据库的访问端口')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="dbport" onkeyup="getDataBaseString();" type="text" name="dbport" value="${dataBase.dbport }" style="width: 300px;" class="inputTxt1"  />
		</td>
	</tr >
	<tr class="crawlerShow"  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;名: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，您要使用的数据库的用户名')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="dbuser" type="text" name="dbuser" value="${dataBase.dbuser }" style="width: 300px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr class="crawlerShow"  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，您要使用的数据库的密码')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="dbpassword" type="text" name="dbpassword" value="${dataBase.dbpassword }" style="width: 300px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr class="crawlerShow"  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label" >数&nbsp;&nbsp;据&nbsp;&nbsp;库&nbsp;&nbsp;名: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，您要使用的数据库的名称')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="dbname" onkeyup="getDataBaseString();" type="text" name="dbname" value="${dataBase.dbname }" style="width: 300px;" class="inputTxt1"  />
		</td>
	</tr>
	<tr class="crawlerShow"  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label" >数&nbsp;&nbsp;据&nbsp;&nbsp;库&nbsp;&nbsp;表: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，您要使用的数据库的名称')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<select id="tableName"  class="inputTxt1" style="width: 185px;"  name="tableName" >
				<option value="" >请选择</option>
			</select>
		</td>
	</tr>
	<tr class="crawlerShow"  height="30px;" >
		<td class="TD_Bgcolor3" width="25%" >
			<span class="label">连&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接&nbsp;&nbsp;&nbsp;&nbsp;串: <a href="javascript:void(0)" onclick="toolTip(this,'当数据存储方式是数据库存储时，根据您输入的数据库信息，组成的数据库连接字符串')">[?]</a></span>
		</td>
		<td class="TD_Bgcolor4" width="80%" >
			<input id="dburl" type="text" name="dburl" value="${dataBase.dburl }" style="width: 300px;" class="inputTxt1"  onchange="linkOnChange();"/><input name="" type="button" class="botton_style3" value="测试" onclick="testConnection();" />
		</td>
	</tr>
</table>
<script type="text/javascript">
var saveMode = '${crawler.saveMode}';
if (saveMode == ""){
	$(".crawlerHide").hide();
}else if(saveMode == "2"){
	$(".crawlerShow").hide();
}else{
	$(".crawlerHide").hide();
}

function changeBlock(sel){
	var sel = $(sel).val();
	if(sel == 1){
		$(".crawlerShow").show();
		$(".crawlerHide").hide();
	}else{
		$(".crawlerShow").hide();
		$(".crawlerHide").show();
	}
}
function getDataBaseString(){
	var ip = $("#dbip").val();
	var port = $("#dbport").val();
	var type = $("#dbtype").find("option:selected").text();
	var dataBaseName = $("#dbname").val();
	var url = "jdbc:"+type+"://"+ip+":"+port+"/"+dataBaseName;
	$("#dburl").val(url);
	
	linkOnChange();
}
getDataBaseString();
function testConnection(){
	var dataParam = "dbuser:"+$("#dbuser").val();
	AT.get("${pageContext.request.contextPath}/crawler/testConnection.do?dbuser="+$("#dbuser").val()+"&dbpassword="+$("#dbpassword").val()+"&dburl="+$("#dburl").val()+"&dbtype="+$("#dbtype").val(),function(data){
		MSG.alert(data);
	});
}
function linkOnChange(){
	var curTableName = '${dataBase.tableName}';
	var dataParam = "dbuser:"+$("#dbuser").val();
	AT.get("${pageContext.request.contextPath}/crawler/getTableNames.do?dbuser="+$("#dbuser").val()+"&dbpassword="+$("#dbpassword").val()+"&dburl="+$("#dburl").val()+"&dbtype="+$("#dbtype").val(),function(data){
		if (data != null && data != "" &&  data != undefined)   
         {  
	         $("#tableName").empty();   
	         // 转换成json对象   
	         var option = "";
             // 循环组装下拉框选项   
             var array = eval(data);
	         $.each(data, function(k, v)   
	         {   
	         	if(curTableName == v.tableCode){
	         		option += "<option selected='selected' value=\"" + v.tableCode + "\">" + v.tableName + "</option>";
	         	}else{
	         		option += "<option value=\"" + v.tableCode + "\">" + v.tableName + "</option>";
	         	}
	         }); 
	         $("#tableName").append(option); 
         }else{
	         $("#tableName").empty(); 
         }
	});
}
</script>
