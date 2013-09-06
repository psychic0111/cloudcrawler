
package com.xdtech.platform.webcrawler.ws.servercrawler;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
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
@WebService(name = "IServerCrawlerService", targetNamespace = "http://www.xd-tech.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IServerCrawlerService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "initCrawlerInfo", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.InitCrawlerInfo")
    @ResponseWrapper(localName = "initCrawlerInfoResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.InitCrawlerInfoResponse")
    public void initCrawlerInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.xdtech.platform.webcrawler.ws.servercrawler.Keyword>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getKeywords", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetKeywords")
    @ResponseWrapper(localName = "getKeywordsResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetKeywordsResponse")
    public List<Keyword> getKeywords();

    /**
     * 
     * @return
     *     returns java.util.List<com.xdtech.platform.webcrawler.ws.servercrawler.Engine>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getEngines", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetEngines")
    @ResponseWrapper(localName = "getEnginesResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetEnginesResponse")
    public List<Engine> getEngines();

    /**
     * 
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCrawlerConnectionStatusManage", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetCrawlerConnectionStatusManage")
    @ResponseWrapper(localName = "getCrawlerConnectionStatusManageResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetCrawlerConnectionStatusManageResponse")
    public Integer getCrawlerConnectionStatusManage();

    /**
     * 
     * @return
     *     returns java.util.List<com.xdtech.platform.webcrawler.ws.servercrawler.TimeRange>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getWaitTimeRange", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetWaitTimeRange")
    @ResponseWrapper(localName = "getWaitTimeRangeResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetWaitTimeRangeResponse")
    public List<TimeRange> getWaitTimeRange();

    /**
     * 
     * @param arg0
     * @return
     *     returns com.xdtech.platform.webcrawler.ws.servercrawler.Crawler
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCrawlerByCode", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetCrawlerByCode")
    @ResponseWrapper(localName = "getCrawlerByCodeResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetCrawlerByCodeResponse")
    public Crawler getCrawlerByCode(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.xdtech.platform.webcrawler.ws.servercrawler.Crawler
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCrawlerSite", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetCrawlerSite")
    @ResponseWrapper(localName = "getCrawlerSiteResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.servercrawler.GetCrawlerSiteResponse")
    public Crawler getCrawlerSite(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}