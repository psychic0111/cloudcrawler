<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="xd" uri="http://www.xd-tech.com.cn/" %>
	<script type="text/javascript" language="javascript">

	//初始化
	function clearLog(){
			MSG.confirm('您确认要清空日志？',null,function(){
				AT.post("${pageContext.request.contextPath}/log/clearLog.do",{actionUser : $("#actionUser").val(),startTime : $("#startTime").val(),endTime : $("#endTime").val()},function(data){
					$("#actionUser").attr('value','');
					$("#startTime").attr('value','');
					$("#endTime").attr('value','');
					AT.load("listDiv","${pageContext.request.contextPath}/log/list.do",iframeHeight);
				});
			},null);
	}

	function doPage(ths,pageNo,pageSize){
		AT.post("${pageContext.request.contextPath}/log/list.do",{actionUser : $("#actionUser").val(),startTime : $("#startTime").val(),endTime : $("#endTime").val(),pageNo:pageNo,pageSize:pageSize},function(data){
			$("#listDiv").empty();
			$("#listDiv").html(data);
		});
	}
	function searchLog(){
    	AT.post("${pageContext.request.contextPath}/log/list.do",{actionUser : $("#actionUser").val(),startTime : $("#startTime").val(),endTime : $("#endTime").val()},function(data){
			$("#listDiv").empty();
			$("#listDiv").html(data);
		});
       }
	</script>
  <div class="title">
           <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
              <td class="c2">
				<img src="${pageContext.request.contextPath}/images/sys.png"  />系统日志
 	 		  </td>
 	 		  <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
              <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
            </tr>
          </table>
    </div>
   <div class="lists">
    <!--tab Begin-->
	<!--tab End-->
	<div class="float_frame" id="dialog">
  	</div>     
       <!--function Begin-->
       <div class="function">
         <div class="left">
           <input  type="button" class="botton_style2" value="清空日志" onclick="clearLog()"  />
         </div>
	         <div class="right">
	              <table  border="0" cellspacing="0" cellpadding="0">
	                <tr> 	
	                	<td>操作人:<input type="text" id="actionUser" name="actionUser" style="width:100px;" class="inputTxt1"/></td>							
	                 	<td> 
	                 		从:<input id="startTime" name="" type="text"  readonly="readonly" onclick="WdatePicker();"  style="width:100px;" class="inputTxt1"/>
	                 	    <img onclick="WdatePicker({el:'startTime'})" src="${pageContext.request.contextPath}/js/My97DatePicker/skin/datePicker.gif" width="22" height="30" >
	                 	</td>
	                 	<td> 
		                 	到:<input id="endTime" name="" type="text"  readonly="readonly" onclick="WdatePicker();"  style="width:100px;" class="inputTxt1"/>
		                 	<img onclick="WdatePicker({el:'endTime'})" src="${pageContext.request.contextPath}/js/My97DatePicker/skin/datePicker.gif" width="22" height="30" >
	                 	</td>
	                 	 <td><input name="" type="button" class="botton_style3" value="查询" onclick="searchLog()" /></td>
	                </tr>
	              </table>
	            </div>
         <div class="clear"></div>
       </div>
       	<div id="listDiv">
        	<jsp:include page="/module/system/log/list.jsp"/>
    	</div>
       </div>
