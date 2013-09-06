<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="title">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
              <td class="c2">服务器当前状态</td>
              <td class="c3"><img src="${pageContext.request.contextPath}/images/title_c_right.png" width="3" height="34" /></td>
            </tr>
          </table>
        </div>
        <!--lists Begin-->
        <div class="lists"> 
          <!--table_list3 Begin-->
          <div class="table_list3">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <th>
                	<img src="${pageContext.request.contextPath}/images/arrow2.png"/>全网监控运行状态：
                	<span class="txt_color2" id="webcrawler_status">Not Running</span>
                </th>
              </tr>
              <tr>
                <td class="TD_Bgcolor2"><img src="${pageContext.request.contextPath}/images/arrow2.png" />爬虫运行状态：<span class="txt_color1">Running</span>，共接受请求数量：<span class="txt_color1">0</span>次，
                  平均响应时间：<span class="txt_color1">0</span>秒， 
                  共有 <span class="txt_color1">1 </span>台服务器提供搜索服务，
                  索引记录数量：<span class="txt_color1">8021</span>条，
                  数据文件：<span class="txt_color1"> 214M</span>，开始运行时间：<span class="txt_color1">2012-02-18 10:19:44</span></td>
              </tr>
              <tr>
                <td class="TD_Bgcolor1">
                	<img src="${pageContext.request.contextPath}/images/arrow2.png"/>任务运行状态： 
                	<span class="txt_color2">Not Running</span>
                </td>
              </tr>
              <%-- <tr>
                <td class="TD_Bgcolor2"><img src="${pageContext.request.contextPath}/images/arrow2.png" />集群系统状态：<span class="txt_color2">Not Running</span></td>
              </tr> --%>
            </table>
          </div>
          <!--table_list3 End--> 
          <!--function_lists Begin-->
          <div class="function_lists"> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon1.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">索引管理</li>
                  <li><a href="#">设置索引参数</a></li>
                  <li><a href="#">定义索引字段结构</a></li>
                  <li><a href="#">索引数据维护</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon2.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">数据采集</li>
                  <li><a href="#">采集数据库内容</a></li>
                  <li><a href="#">采集文件目录内容</a></li>
                  <li><a href="#">设置定时采集任务</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon3.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">数据处理（ETL）</li>
                  <li><a href="#">数据清洗</a></li>
                  <li><a href="#">数据解析</a></li>
                  <li><a href="#">数据转换</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon4.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">检索设置</li>
                  <li><a href="#">检索设置向导</a></li>
                  <li><a href="#">检索模式设置</a></li>
                  <li><a href="#">检索结果的排序干预</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon5.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">统计分析</li>
                  <li><a href="#">索引的数据统计</a></li>
                  <li><a href="#">对搜索词的统计</a></li>
                  <li><a href="#">用户搜索行为分析</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon6.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">云搜索</li>
                  <li><a href="#">云视图</a></li>
                  <li><a href="#">索引分发与优化</a></li>
                  <li><a href="#">集群和分布式设置</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon7.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">接口管理</li>
                  <li><a href="#">查看接口类型及设置</a></li>
                  <li><a href="#">发放接口开发令牌</a></li>
                  <li><a href="#">检索参数设置</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <!--lists Begin-->
            <div class="list">
              <div class="left"><img src="${pageContext.request.contextPath}/images/icon8.png" /></div>
              <div class="right">
                <ul>
                  <li class="title">系统设置</li>
                  <li><a href="#">查看系统日志</a></li>
                  <li><a href="#">访问者控制</a></li>
                  <li><a href="#">数据备份与恢复</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End-->
            <div class="clear"></div>
          </div>
          <!--function_lists End--> 
        </div>
<script>
	//refresh();
	function refresh(){
		var url="${pageContext.request.contextPath}/engineControl/ajaxList.do";
		AT.get(url,function(data){
			var html = "";
			if(data == "1"){
				html = "全网监控正常运行！";
			}else if(data == "2"){
				html = "搜索引擎未启动或者爬虫出现故障，请您启动爬虫！";
			}else if(data == "3"){
				html = "全网监控地址加载模块出现故障，请您重启系统！";
			}else if(data == "4"){
				html = "全网监控地址生成模块出现故障，请您重启系统！";
			}
			$("#webcrawler_status").html(html);
		});
	}
	//intervalid  = window.setInterval(refresh,1000);

</script>