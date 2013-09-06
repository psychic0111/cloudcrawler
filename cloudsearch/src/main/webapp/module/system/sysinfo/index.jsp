<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <div class="title">
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
          <td class="c2">
				<img src="${pageContext.request.contextPath}/images/sys.png"  />系统信息
 	 	  </td>
 	 	  <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
          <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
        </tr>
      </table>
  </div>
   
<div class="lists">
	<div class="table_list2" style="overflow-y:scroll;overflow-x:hidden;height:100%;" id="iframepage">
		<jsp:include page="/module/system/sysinfo/info.jsp"></jsp:include>
	</div>
</div>