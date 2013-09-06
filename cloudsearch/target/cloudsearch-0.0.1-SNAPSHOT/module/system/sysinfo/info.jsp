<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.io.File"%>
<%
	Runtime r = Runtime.getRuntime();
	long totalMemory = r.totalMemory() ;
	long freeMemory = r.freeMemory() ;
	long userMemory = r.totalMemory() - r.freeMemory();
	long freeM = (freeMemory + (1 << ((freeMemory > 1 << 20) ? 20 : 10) - 1) >> ((freeMemory > 1 << 20) ? 20 : 10)) ;
	long freeSpace=0L; //空闲空间
	long totalSpace=0L; //总空间
	File[] roots = File.listRoots();//获取磁盘分区列表
    for (File file : roots) {
       freeSpace+=(file.getFreeSpace()/1024/1024/1024);
       totalSpace+=(file.getTotalSpace()/1024/1024/1024);
    }
%>
<div class="title" >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td class="TD1" width="35%">分配内存</td>
       <td class="TD2">
       	 <span><%=(totalMemory + (1 << ((totalMemory > 1 << 20) ? 20 : 10) - 1) >> ((totalMemory > 1 << 20) ? 20 : 10)) + (totalMemory > 1 << 20 ? " mb" : " kb")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">剩余内存</td>
       <td class="TD2">
       	 <span><% if(freeM<10){ %><font color='red'><%}%><%= freeM + (freeMemory > 1 << 20 ? " mb" : " kb")%><% if(freeM<10){ %> &nbsp;&nbsp;&nbsp;剩余内存小于10M，请适当增加可用内存总量</font><%} %></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">使用内存</td>
       <td class="TD2">
       	 <span><%=(userMemory + (1 << ((userMemory > 1 << 20) ? 20 : 10) - 1) >> ((userMemory > 1 << 20) ? 20 : 10)) + (userMemory > 1 << 20 ? " mb" : " kb")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">用户路径</td>
       <td class="TD2">
       	 <span><%=System.getProperty("user.home")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">虚拟机安装路径</td>
       <td class="TD2">
       	 <span><%=System.getProperty("java.home")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">JDK安装目录</td>
       <td class="TD2">
       	 <span><%=System.getProperty("java.ext.dirs")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">服务器端口号</td>
       <td class="TD2">
       	 <span><%=request.getServerPort()%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">操作系统名称</td>
       <td class="TD2">
       	 <span><%=System.getProperty("os.name")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">操作系统用户</td>
       <td class="TD2">
       	 <span><%=System.getProperty("user.name")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">操作系统版本</td>
       <td class="TD2">
       	 <span><%=System.getProperty("os.version")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">应用系统目录</td>
       <td class="TD2">
       	 <span><%=System.getProperty("user.dir")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">文件系统分隔符</td>
       <td class="TD2">
       	 <span><%=System.getProperty("file.separator")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">JRE版本</td>
       <td class="TD2">
       	 <span ><%=System.getProperty("java.version")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">VM名称</td>
       <td class="TD2">
       	 <span><%=System.getProperty("java.vm.name")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">VM版本</td>
       <td class="TD2">
       	 <span ><%=System.getProperty("java.vm.version")%></span>
        </td>
     </tr>
     <tr>
       <td class="TD1" width="15%">磁盘空间/剩余空间</td>
       <td class="TD2">
       	 <span><%=totalSpace+"G  / "+freeSpace+"G" %></span>
        </td>
     </tr>
   </table>
   </div>
<br/>
