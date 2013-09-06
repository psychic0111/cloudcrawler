
package com.xdtech.cloudsearch.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xdtech.cloudsearch.webservice.client package. 
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

    private final static QName _GetCrawlerSiteResponse_QNAME = new QName("http://www.xd-tech.com", "getCrawlerSiteResponse");
    private final static QName _GetKeywords_QNAME = new QName("http://www.xd-tech.com", "getKeywords");
    private final static QName _GetKeywordsResponse_QNAME = new QName("http://www.xd-tech.com", "getKeywordsResponse");
    private final static QName _GetCrawlerByCode_QNAME = new QName("http://www.xd-tech.com", "getCrawlerByCode");
    private final static QName _GetEngines_QNAME = new QName("http://www.xd-tech.com", "getEngines");
    private final static QName _GetCrawlerByCodeResponse_QNAME = new QName("http://www.xd-tech.com", "getCrawlerByCodeResponse");
    private final static QName _GetEnginesResponse_QNAME = new QName("http://www.xd-tech.com", "getEnginesResponse");
    private final static QName _GetCrawlerSite_QNAME = new QName("http://www.xd-tech.com", "getCrawlerSite");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xdtech.cloudsearch.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEnginesResponse }
     * 
     */
    public GetEnginesResponse createGetEnginesResponse() {
        return new GetEnginesResponse();
    }

    /**
     * Create an instance of {@link GetCrawlerByCodeResponse }
     * 
     */
    public GetCrawlerByCodeResponse createGetCrawlerByCodeResponse() {
        return new GetCrawlerByCodeResponse();
    }

    /**
     * Create an instance of {@link GetCrawlerSite }
     * 
     */
    public GetCrawlerSite createGetCrawlerSite() {
        return new GetCrawlerSite();
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
     * Create an instance of {@link GetCrawlerSiteResponse }
     * 
     */
    public GetCrawlerSiteResponse createGetCrawlerSiteResponse() {
        return new GetCrawlerSiteResponse();
    }

    /**
     * Create an instance of {@link GetCrawlerByCode }
     * 
     */
    public GetCrawlerByCode createGetCrawlerByCode() {
        return new GetCrawlerByCode();
    }

    /**
     * Create an instance of {@link Site }
     * 
     */
    public Site createSite() {
        return new Site();
    }

    /**
     * Create an instance of {@link Engine }
     * 
     */
    public Engine createEngine() {
        return new Engine();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Keyword }
     * 
     */
    public Keyword createKeyword() {
        return new Keyword();
    }

    /**
     * Create an instance of {@link GetEngines }
     * 
     */
    public GetEngines createGetEngines() {
        return new GetEngines();
    }

    /**
     * Create an instance of {@link GetKeywordsResponse }
     * 
     */
    public GetKeywordsResponse createGetKeywordsResponse() {
        return new GetKeywordsResponse();
    }

    /**
     * Create an instance of {@link GetKeywords }
     * 
     */
    public GetKeywords createGetKeywords() {
        return new GetKeywords();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getKeywordsResponse")
    public JAXBElement<GetKeywordsResponse> createGetKeywordsResponse(GetKeywordsResponse value) {
        return new JAXBElement<GetKeywordsResponse>(_GetKeywordsResponse_QNAME, GetKeywordsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEnginesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getEnginesResponse")
    public JAXBElement<GetEnginesResponse> createGetEnginesResponse(GetEnginesResponse value) {
        return new JAXBElement<GetEnginesResponse>(_GetEnginesResponse_QNAME, GetEnginesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerSite")
    public JAXBElement<GetCrawlerSite> createGetCrawlerSite(GetCrawlerSite value) {
        return new JAXBElement<GetCrawlerSite>(_GetCrawlerSite_QNAME, GetCrawlerSite.class, null, value);
    }

}
