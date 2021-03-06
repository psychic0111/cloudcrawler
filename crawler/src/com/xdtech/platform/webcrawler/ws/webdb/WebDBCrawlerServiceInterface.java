
package com.xdtech.platform.webcrawler.ws.webdb;

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
@WebService(name = "WebDBCrawlerServiceInterface", targetNamespace = "http://www.xd-tech.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebDBCrawlerServiceInterface {


    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateByMD5", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.UpdateByMD5")
    @ResponseWrapper(localName = "updateByMD5Response", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.UpdateByMD5Response")
    public void updateByMD5(
        @WebParam(name = "arg0", targetNamespace = "")
        List<String> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addOrUpdateURLs", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.AddOrUpdateURLs")
    @ResponseWrapper(localName = "addOrUpdateURLsResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.AddOrUpdateURLsResponse")
    public void addOrUpdateURLs(
        @WebParam(name = "arg0", targetNamespace = "")
        List<UrlInfoCrawler> arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "update", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.UpdateResponse")
    public void update(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.DeleteResponse")
    public void delete(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<com.xdtech.platform.webcrawler.ws.webdb.WebDbCrawler>
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findWebDbByQueryFromTable", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.FindWebDbByQueryFromTable")
    @ResponseWrapper(localName = "findWebDbByQueryFromTableResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.FindWebDbByQueryFromTableResponse")
    public List<WebDbCrawler> findWebDbByQueryFromTable(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "addUrl", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.AddUrl")
    @ResponseWrapper(localName = "addUrlResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.AddUrlResponse")
    public void addUrl(
        @WebParam(name = "arg0", targetNamespace = "")
        List<UrlInfoCrawler> arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns com.xdtech.platform.webcrawler.ws.webdb.WebDBCrawlerResult
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findWebDbByQuery", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.FindWebDbByQuery")
    @ResponseWrapper(localName = "findWebDbByQueryResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.FindWebDbByQueryResponse")
    public WebDBCrawlerResult findWebDbByQuery(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3)
        throws Exception_Exception
    ;

    /**
     * 
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "clearDB", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.ClearDB")
    @ResponseWrapper(localName = "clearDBResponse", targetNamespace = "http://www.xd-tech.com", className = "com.xdtech.platform.webcrawler.ws.webdb.ClearDBResponse")
    public void clearDB()
        throws Exception_Exception
    ;

}
