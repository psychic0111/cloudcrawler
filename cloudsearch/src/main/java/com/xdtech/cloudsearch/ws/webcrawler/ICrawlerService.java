
package com.xdtech.cloudsearch.ws.webcrawler;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ICrawlerService", targetNamespace = "http://www.xd-tech.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ICrawlerService {


    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "start", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.cloudsearch.ws.webcrawler.Start")
    @ResponseWrapper(localName = "startResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.cloudsearch.ws.webcrawler.StartResponse")
    public void start();

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "stop", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.cloudsearch.ws.webcrawler.Stop")
    @ResponseWrapper(localName = "stopResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.cloudsearch.ws.webcrawler.StopResponse")
    public void stop();

    /**
     * 
     * @return 0.爬虫出现故障,1.爬虫正在运行，全网地址库加载线程正在运行，全网地址库生成线程正在运行，返回信息：全网正在抓取,2.爬虫未启动，返回信息：爬虫出现故障,3.全网监控地址库加载线程停止运行，返回信息：全网地址库加载线程出现故障, 4.全网监控地址库生成线程停止运行，返回信息：全网监控地址库生成线程出现故障
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findRunStatus", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.cloudsearch.ws.webcrawler.FindRunStatus")
    @ResponseWrapper(localName = "findRunStatusResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.cloudsearch.ws.webcrawler.FindRunStatusResponse")
    public String findRunStatus();

}
