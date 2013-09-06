<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="title">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="c1"><img src="${pageContext.request.contextPath}/images/title_c_left.png" width="3" height="34" /></td>
              <td class="c2">服务器当前状态</td>
              <td class="cright"><img src="${pageContext.request.contextPath}/images/page.png" /><a class="link4" href="#">查看当前页操作说明</a></td>
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
                	<span class="txt_color1" id="webcrawler_status"></span>
                </th>
              </tr>
              <tr>
                <td class="TD_Bgcolor2">
	                <img src="${pageContext.request.contextPath}/images/arrow2.png" />爬虫运行状态：
	                <span class="txt_color1">运行中的爬虫：</span>
	                <span class="txt_color1" id="running_crawler"></span>
                </td>
              </tr>
              <tr>
                <td class="TD_Bgcolor1">
                	<img src="${pageContext.request.contextPath}/images/arrow2.png"/>任务运行状态： 
                	<span class="txt_color1" id="task_status"></span>
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
                  <li class="title">站点采集</li>
                  <li onclick="redirectTo('站点采集','siteCatalog','${pageContext.request.contextPath}/nav/navTo.do?id=siteMgr','/siteCategory/siteCategory.do');"><a href="#">设置站点分类</a></li>
                  <li onclick="redirectTo('站点采集','siteSetting','${pageContext.request.contextPath}/nav/navTo.do?id=siteMgr','/site/site.do');"><a href="#">设置采集站点</a></li>
                  <li onclick="redirectTo('站点采集','templateManage','${pageContext.request.contextPath}/nav/navTo.do?id=siteMgr','/template/template.do');"><a href="#">设置采集模板</a></li>
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
                  <li class="title">全网采集</li>
                  <li onclick="redirectTo('全网采集','keywordManage','${pageContext.request.contextPath}/nav/navTo.do?id=dataCollect','/keyword/keyword.do');"><a href="#">设置搜索关键词</a></li>
                  <li onclick="redirectTo('全网采集','engineManage','${pageContext.request.contextPath}/nav/navTo.do?id=dataCollect','/engine/engine.do');"><a href="#">设置全网搜索引擎</a></li>
                  <li onclick="redirectTo('全网采集','netMonitor','${pageContext.request.contextPath}/nav/navTo.do?id=dataCollect','/engineControl/engineControl.do');"><a href="#">启动全网采集</a></li>
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
                  <li class="title">爬虫管理</li>
                  <li onclick="redirectTo('爬虫管理','crawlerManage','${pageContext.request.contextPath}/nav/navTo.do?id=index','/crawler/crawler.do');"><a href="#">配置采集爬虫</a></li>
                  <li onclick="redirectTo('爬虫管理','crawlerLogsManager','${pageContext.request.contextPath}/nav/navTo.do?id=index','/crawlerlog/crawlerlog.do');"><a href="#">查看采集日志</a></li>
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
                  <li class="title">任务管理</li>
                  <li onclick="redirectTo('爬虫管理','taskMgr','${pageContext.request.contextPath}/nav/navTo.do?id=taksMagr','/task/task.do');"><a href="#">设置采集任务</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
            <!--lists End--> 
            <%-- <!--lists Begin-->
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
            <!--lists End--> --%>
            <div class="clear"></div>
          </div>
          <!--function_lists End--> 
        </div>  
        <iframe src="${pageContext.request.contextPath}/include/refresh_state.jsp" width="0" height="0"></iframe>      