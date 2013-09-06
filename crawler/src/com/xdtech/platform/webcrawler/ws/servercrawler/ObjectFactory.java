
package com.xdtech.platform.webcrawler.ws.servercrawler;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xdtech.platform.webcrawler.ws.servercrawler package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCrawlerConnectionStatusManage_QNAME = new QName("http://www.xd-tech.com", "getCrawlerConnectionStatusManage");
    private final static QName _GetCrawlerSiteResponse_QNAME = new QName("http://www.xd-tech.com", "getCrawlerSiteResponse");
    private final static QName _GetKeywords_QNAME = new QName("http://www.xd-tech.com", "getKeywords");
    private final static QName _GetCrawlerByCode_QNAME = new QName("http://www.xd-tech.com", "getCrawlerByCode");
    private final static QName _GetEngines_QNAME = new QName("http://www.xd-tech.com", "getEngines");
    private final static QName _GetCrawlerByCodeResponse_QNAME = new QName("http://www.xd-tech.com", "getCrawlerByCodeResponse");
    private final static QName _InitCrawlerInfo_QNAME = new QName("http://www.xd-tech.com", "initCrawlerInfo");
    private final static QName _GetCrawlerSite_QNAME = new QName("http://www.xd-tech.com", "getCrawlerSite");
    private final static QName _InitCrawlerInfoResponse_QNAME = new QName("http://www.xd-tech.com", "initCrawlerInfoResponse");
    private final static QName _GetKeywordsResponse_QNAME = new QName("http://www.xd-tech.com", "getKeywordsResponse");
    private final static QName _GetWaitTimeRange_QNAME = new QName("http://www.xd-tech.com", "getWaitTimeRange");
    private final static QName _GetEnginesResponse_QNAME = new QName("http://www.xd-tech.com", "getEnginesResponse");
    private final static QName _GetWaitTimeRangeResponse_QNAME = new QName("http://www.xd-tech.com", "getWaitTimeRangeResponse");
    private final static QName _GetCrawlerConnectionStatusManageResponse_QNAME = new QName("http://www.xd-tech.com", "getCrawlerConnectionStatusManageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xdtech.platform.webcrawler.ws.servercrawler
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SiteProxy }
     * 
     */
    public SiteProxy createSiteProxy() {
        return new SiteProxy();
    }

    /**
     * Create an instance of {@link DataBase }
     * 
     */
    public DataBase createDataBase() {
        return new DataBase();
    }

    /**
     * Create an instance of {@link InitCrawlerInfoResponse }
     * 
     */
    public InitCrawlerInfoResponse createInitCrawlerInfoResponse() {
        return new InitCrawlerInfoResponse();
    }

    /**
     * Create an instance of {@link GetWaitTimeRangeResponse }
     * 
     */
    public GetWaitTimeRangeResponse createGetWaitTimeRangeResponse() {
        return new GetWaitTimeRangeResponse();
    }

    /**
     * Create an instance of {@link Crawler }
     * 
     */
    public Crawler createCrawler() {
        return new Crawler();
    }

    /**
     * Create an instance of {@link Template }
     * 
     */
    public Template createTemplate() {
        return new Template();
    }

    /**
     * Create an instance of {@link GetKeywordsResponse }
     * 
     */
    public GetKeywordsResponse createGetKeywordsResponse() {
        return new GetKeywordsResponse();
    }

    /**
     * Create an instance of {@link GetCrawlerConnectionStatusManageResponse }
     * 
     */
    public GetCrawlerConnectionStatusManageResponse createGetCrawlerConnectionStatusManageResponse() {
        return new GetCrawlerConnectionStatusManageResponse();
    }

    /**
     * Create an instance of {@link GetCrawlerByCode }
     * 
     */
    public GetCrawlerByCode createGetCrawlerByCode() {
        return new GetCrawlerByCode();
    }

    /**
     * Create an instance of {@link InitCrawlerInfo }
     * 
     */
    public InitCrawlerInfo createInitCrawlerInfo() {
        return new InitCrawlerInfo();
    }

    /**
     * Create an instance of {@link GetCrawlerSiteResponse }
     * 
     */
    public GetCrawlerSiteResponse createGetCrawlerSiteResponse() {
        return new GetCrawlerSiteResponse();
    }

    /**
     * Create an instance of {@link GetEnginesResponse }
     * 
     */
    public GetEnginesResponse createGetEnginesResponse() {
        return new GetEnginesResponse();
    }

    /**
     * Create an instance of {@link Keyword }
     * 
     */
    public Keyword createKeyword() {
        return new Keyword();
    }

    /**
     * Create an instance of {@link GetKeywords }
     * 
     */
    public GetKeywords createGetKeywords() {
        return new GetKeywords();
    }

    /**
     * Create an instance of {@link Engine }
     * 
     */
    public Engine createEngine() {
        return new Engine();
    }

    /**
     * Create an instance of {@link GetCrawlerByCodeResponse }
     * 
     */
    public GetCrawlerByCodeResponse createGetCrawlerByCodeResponse() {
        return new GetCrawlerByCodeResponse();
    }

    /**
     * Create an instance of {@link GetCrawlerConnectionStatusManage }
     * 
     */
    public GetCrawlerConnectionStatusManage createGetCrawlerConnectionStatusManage() {
        return new GetCrawlerConnectionStatusManage();
    }

    /**
     * Create an instance of {@link GetWaitTimeRange }
     * 
     */
    public GetWaitTimeRange createGetWaitTimeRange() {
        return new GetWaitTimeRange();
    }

    /**
     * Create an instance of {@link TimeRange }
     * 
     */
    public TimeRange createTimeRange() {
        return new TimeRange();
    }

    /**
     * Create an instance of {@link GetCrawlerSite }
     * 
     */
    public GetCrawlerSite createGetCrawlerSite() {
        return new GetCrawlerSite();
    }

    /**
     * Create an instance of {@link GetEngines }
     * 
     */
    public GetEngines createGetEngines() {
        return new GetEngines();
    }

    /**
     * Create an instance of {@link Site }
     * 
     */
    public Site createSite() {
        return new Site();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerConnectionStatusManage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerConnectionStatusManage")
    public JAXBElement<GetCrawlerConnectionStatusManage> createGetCrawlerConnectionStatusManage(GetCrawlerConnectionStatusManage value) {
        return new JAXBElement<GetCrawlerConnectionStatusManage>(_GetCrawlerConnectionStatusManage_QNAME, GetCrawlerConnectionStatusManage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerSiteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerSiteResponse")
    public JAXBElement<GetCrawlerSiteResponse> createGetCrawlerSiteResponse(GetCrawlerSiteResponse value) {
        return new JAXBElement<GetCrawlerSiteResponse>(_GetCrawlerSiteResponse_QNAME, GetCrawlerSiteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getKeywords")
    public JAXBElement<GetKeywords> createGetKeywords(GetKeywords value) {
        return new JAXBElement<GetKeywords>(_GetKeywords_QNAME, GetKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerByCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerByCode")
    public JAXBElement<GetCrawlerByCode> createGetCrawlerByCode(GetCrawlerByCode value) {
        return new JAXBElement<GetCrawlerByCode>(_GetCrawlerByCode_QNAME, GetCrawlerByCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEngines }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getEngines")
    public JAXBElement<GetEngines> createGetEngines(GetEngines value) {
        return new JAXBElement<GetEngines>(_GetEngines_QNAME, GetEngines.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerByCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerByCodeResponse")
    public JAXBElement<GetCrawlerByCodeResponse> createGetCrawlerByCodeResponse(GetCrawlerByCodeResponse value) {
        return new JAXBElement<GetCrawlerByCodeResponse>(_GetCrawlerByCodeResponse_QNAME, GetCrawlerByCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitCrawlerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "initCrawlerInfo")
    public JAXBElement<InitCrawlerInfo> createInitCrawlerInfo(InitCrawlerInfo value) {
        return new JAXBElement<InitCrawlerInfo>(_InitCrawlerInfo_QNAME, InitCrawlerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerSite")
    public JAXBElement<GetCrawlerSite> createGetCrawlerSite(GetCrawlerSite value) {
        return new JAXBElement<GetCrawlerSite>(_GetCrawlerSite_QNAME, GetCrawlerSite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitCrawlerInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "initCrawlerInfoResponse")
    public JAXBElement<InitCrawlerInfoResponse> createInitCrawlerInfoResponse(InitCrawlerInfoResponse value) {
        return new JAXBElement<InitCrawlerInfoResponse>(_InitCrawlerInfoResponse_QNAME, InitCrawlerInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getKeywordsResponse")
    public JAXBElement<GetKeywordsResponse> createGetKeywordsResponse(GetKeywordsResponse value) {
        return new JAXBElement<GetKeywordsResponse>(_GetKeywordsResponse_QNAME, GetKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitTimeRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getWaitTimeRange")
    public JAXBElement<GetWaitTimeRange> createGetWaitTimeRange(GetWaitTimeRange value) {
        return new JAXBElement<GetWaitTimeRange>(_GetWaitTimeRange_QNAME, GetWaitTimeRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEnginesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getEnginesResponse")
    public JAXBElement<GetEnginesResponse> createGetEnginesResponse(GetEnginesResponse value) {
        return new JAXBElement<GetEnginesResponse>(_GetEnginesResponse_QNAME, GetEnginesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitTimeRangeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getWaitTimeRangeResponse")
    public JAXBElement<GetWaitTimeRangeResponse> createGetWaitTimeRangeResponse(GetWaitTimeRangeResponse value) {
        return new JAXBElement<GetWaitTimeRangeResponse>(_GetWaitTimeRangeResponse_QNAME, GetWaitTimeRangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerConnectionStatusManageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerConnectionStatusManageResponse")
    public JAXBElement<GetCrawlerConnectionStatusManageResponse> createGetCrawlerConnectionStatusManageResponse(GetCrawlerConnectionStatusManageResponse value) {
        return new JAXBElement<GetCrawlerConnectionStatusManageResponse>(_GetCrawlerConnectionStatusManageResponse_QNAME, GetCrawlerConnectionStatusManageResponse.class, null, value);
    }

}
