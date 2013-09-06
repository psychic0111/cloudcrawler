
package com.xdtech.platform.crawler.ws.dispatcher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xdtech.platform.crawler.ws.dispatcher package. 
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

    private final static QName _GetFilterRule_QNAME = new QName("http://www.xd-tech.com", "getFilterRule");
    private final static QName _GetAllFetchTemplateResponse_QNAME = new QName("http://www.xd-tech.com", "getAllFetchTemplateResponse");
    private final static QName _GetAllSite_QNAME = new QName("http://www.xd-tech.com", "getAllSite");
    private final static QName _GetCrawlerConfigResponse_QNAME = new QName("http://www.xd-tech.com", "getCrawlerConfigResponse");
    private final static QName _GetWaitTimeRange_QNAME = new QName("http://www.xd-tech.com", "getWaitTimeRange");
    private final static QName _GetCrawlerConfig_QNAME = new QName("http://www.xd-tech.com", "getCrawlerConfig");
    private final static QName _GetWaitTimeRangeResponse_QNAME = new QName("http://www.xd-tech.com", "getWaitTimeRangeResponse");
    private final static QName _GetAllFetchTemplate_QNAME = new QName("http://www.xd-tech.com", "getAllFetchTemplate");
    private final static QName _GetAllProxyConfig_QNAME = new QName("http://www.xd-tech.com", "getAllProxyConfig");
    private final static QName _GetAllProxyConfigResponse_QNAME = new QName("http://www.xd-tech.com", "getAllProxyConfigResponse");
    private final static QName _GetAllSiteResponse_QNAME = new QName("http://www.xd-tech.com", "getAllSiteResponse");
    private final static QName _GetFilterRuleResponse_QNAME = new QName("http://www.xd-tech.com", "getFilterRuleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xdtech.platform.crawler.ws.dispatcher
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TimeRange }
     * 
     */
    public TimeRange createTimeRange() {
        return new TimeRange();
    }

    /**
     * Create an instance of {@link GetFilterRuleResponse }
     * 
     */
    public GetFilterRuleResponse createGetFilterRuleResponse() {
        return new GetFilterRuleResponse();
    }

    /**
     * Create an instance of {@link GetAllSite }
     * 
     */
    public GetAllSite createGetAllSite() {
        return new GetAllSite();
    }

    /**
     * Create an instance of {@link GetWaitTimeRangeResponse }
     * 
     */
    public GetWaitTimeRangeResponse createGetWaitTimeRangeResponse() {
        return new GetWaitTimeRangeResponse();
    }

    /**
     * Create an instance of {@link GetAllProxyConfig }
     * 
     */
    public GetAllProxyConfig createGetAllProxyConfig() {
        return new GetAllProxyConfig();
    }

    /**
     * Create an instance of {@link FetchTemplateBean }
     * 
     */
    public FetchTemplateBean createFetchTemplateBean() {
        return new FetchTemplateBean();
    }

    /**
     * Create an instance of {@link FilterRule }
     * 
     */
    public FilterRule createFilterRule() {
        return new FilterRule();
    }

    /**
     * Create an instance of {@link ProxyConfigBean }
     * 
     */
    public ProxyConfigBean createProxyConfigBean() {
        return new ProxyConfigBean();
    }

    /**
     * Create an instance of {@link GetAllSiteResponse }
     * 
     */
    public GetAllSiteResponse createGetAllSiteResponse() {
        return new GetAllSiteResponse();
    }

    /**
     * Create an instance of {@link GetWaitTimeRange }
     * 
     */
    public GetWaitTimeRange createGetWaitTimeRange() {
        return new GetWaitTimeRange();
    }

    /**
     * Create an instance of {@link CrawlerConfig }
     * 
     */
    public CrawlerConfig createCrawlerConfig() {
        return new CrawlerConfig();
    }

    /**
     * Create an instance of {@link GetCrawlerConfig }
     * 
     */
    public GetCrawlerConfig createGetCrawlerConfig() {
        return new GetCrawlerConfig();
    }

    /**
     * Create an instance of {@link SiteBean }
     * 
     */
    public SiteBean createSiteBean() {
        return new SiteBean();
    }

    /**
     * Create an instance of {@link GetCrawlerConfigResponse }
     * 
     */
    public GetCrawlerConfigResponse createGetCrawlerConfigResponse() {
        return new GetCrawlerConfigResponse();
    }

    /**
     * Create an instance of {@link GetAllFetchTemplate }
     * 
     */
    public GetAllFetchTemplate createGetAllFetchTemplate() {
        return new GetAllFetchTemplate();
    }

    /**
     * Create an instance of {@link GetFilterRule }
     * 
     */
    public GetFilterRule createGetFilterRule() {
        return new GetFilterRule();
    }

    /**
     * Create an instance of {@link GetAllFetchTemplateResponse }
     * 
     */
    public GetAllFetchTemplateResponse createGetAllFetchTemplateResponse() {
        return new GetAllFetchTemplateResponse();
    }

    /**
     * Create an instance of {@link GetAllProxyConfigResponse }
     * 
     */
    public GetAllProxyConfigResponse createGetAllProxyConfigResponse() {
        return new GetAllProxyConfigResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilterRule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getFilterRule")
    public JAXBElement<GetFilterRule> createGetFilterRule(GetFilterRule value) {
        return new JAXBElement<GetFilterRule>(_GetFilterRule_QNAME, GetFilterRule.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllFetchTemplateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getAllFetchTemplateResponse")
    public JAXBElement<GetAllFetchTemplateResponse> createGetAllFetchTemplateResponse(GetAllFetchTemplateResponse value) {
        return new JAXBElement<GetAllFetchTemplateResponse>(_GetAllFetchTemplateResponse_QNAME, GetAllFetchTemplateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getAllSite")
    public JAXBElement<GetAllSite> createGetAllSite(GetAllSite value) {
        return new JAXBElement<GetAllSite>(_GetAllSite_QNAME, GetAllSite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerConfigResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerConfigResponse")
    public JAXBElement<GetCrawlerConfigResponse> createGetCrawlerConfigResponse(GetCrawlerConfigResponse value) {
        return new JAXBElement<GetCrawlerConfigResponse>(_GetCrawlerConfigResponse_QNAME, GetCrawlerConfigResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerConfig")
    public JAXBElement<GetCrawlerConfig> createGetCrawlerConfig(GetCrawlerConfig value) {
        return new JAXBElement<GetCrawlerConfig>(_GetCrawlerConfig_QNAME, GetCrawlerConfig.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllFetchTemplate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getAllFetchTemplate")
    public JAXBElement<GetAllFetchTemplate> createGetAllFetchTemplate(GetAllFetchTemplate value) {
        return new JAXBElement<GetAllFetchTemplate>(_GetAllFetchTemplate_QNAME, GetAllFetchTemplate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProxyConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getAllProxyConfig")
    public JAXBElement<GetAllProxyConfig> createGetAllProxyConfig(GetAllProxyConfig value) {
        return new JAXBElement<GetAllProxyConfig>(_GetAllProxyConfig_QNAME, GetAllProxyConfig.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProxyConfigResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getAllProxyConfigResponse")
    public JAXBElement<GetAllProxyConfigResponse> createGetAllProxyConfigResponse(GetAllProxyConfigResponse value) {
        return new JAXBElement<GetAllProxyConfigResponse>(_GetAllProxyConfigResponse_QNAME, GetAllProxyConfigResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSiteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getAllSiteResponse")
    public JAXBElement<GetAllSiteResponse> createGetAllSiteResponse(GetAllSiteResponse value) {
        return new JAXBElement<GetAllSiteResponse>(_GetAllSiteResponse_QNAME, GetAllSiteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilterRuleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getFilterRuleResponse")
    public JAXBElement<GetFilterRuleResponse> createGetFilterRuleResponse(GetFilterRuleResponse value) {
        return new JAXBElement<GetFilterRuleResponse>(_GetFilterRuleResponse_QNAME, GetFilterRuleResponse.class, null, value);
    }

}
