
package com.xdtech.cloudsearch.ws.sitecrawler;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xdtech.cloudsearch.ws.sitecrawler package. 
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

    private final static QName _Start_QNAME = new QName("http://www.xd-tech.com", "start");
    private final static QName _SetCrawlerConfigResponse_QNAME = new QName("http://www.xd-tech.com", "setCrawlerConfigResponse");
    private final static QName _SetCrawlerConfig_QNAME = new QName("http://www.xd-tech.com", "setCrawlerConfig");
    private final static QName _FindRunStatus_QNAME = new QName("http://www.xd-tech.com", "findRunStatus");
    private final static QName _FindRunStatusResponse_QNAME = new QName("http://www.xd-tech.com", "findRunStatusResponse");
    private final static QName _Parser_QNAME = new QName("http://www.xd-tech.com", "parser");
    private final static QName _ParserResponse_QNAME = new QName("http://www.xd-tech.com", "parserResponse");
    private final static QName _FindAllCrawlerStatusResponse_QNAME = new QName("http://www.xd-tech.com", "findAllCrawlerStatusResponse");
    private final static QName _GetCrawlerConnectionStatusResponse_QNAME = new QName("http://www.xd-tech.com", "getCrawlerConnectionStatusResponse");
    private final static QName _Stop_QNAME = new QName("http://www.xd-tech.com", "stop");
    private final static QName _StopResponse_QNAME = new QName("http://www.xd-tech.com", "stopResponse");
    private final static QName _GetCrawlerConnectionStatus_QNAME = new QName("http://www.xd-tech.com", "getCrawlerConnectionStatus");
    private final static QName _FindAllCrawlerStatus_QNAME = new QName("http://www.xd-tech.com", "findAllCrawlerStatus");
    private final static QName _StartResponse_QNAME = new QName("http://www.xd-tech.com", "startResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xdtech.cloudsearch.ws.sitecrawler
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindAllCrawlerStatusResponse }
     * 
     */
    public FindAllCrawlerStatusResponse createFindAllCrawlerStatusResponse() {
        return new FindAllCrawlerStatusResponse();
    }

    /**
     * Create an instance of {@link ParserResponse }
     * 
     */
    public ParserResponse createParserResponse() {
        return new ParserResponse();
    }

    /**
     * Create an instance of {@link FetchTemplate }
     * 
     */
    public FetchTemplate createFetchTemplate() {
        return new FetchTemplate();
    }

    /**
     * Create an instance of {@link ParseResult }
     * 
     */
    public ParseResult createParseResult() {
        return new ParseResult();
    }

    /**
     * Create an instance of {@link SetCrawlerConfig }
     * 
     */
    public SetCrawlerConfig createSetCrawlerConfig() {
        return new SetCrawlerConfig();
    }

    /**
     * Create an instance of {@link StartResponse }
     * 
     */
    public StartResponse createStartResponse() {
        return new StartResponse();
    }

    /**
     * Create an instance of {@link Start }
     * 
     */
    public Start createStart() {
        return new Start();
    }

    /**
     * Create an instance of {@link SetCrawlerConfigResponse }
     * 
     */
    public SetCrawlerConfigResponse createSetCrawlerConfigResponse() {
        return new SetCrawlerConfigResponse();
    }

    /**
     * Create an instance of {@link GetCrawlerConnectionStatus }
     * 
     */
    public GetCrawlerConnectionStatus createGetCrawlerConnectionStatus() {
        return new GetCrawlerConnectionStatus();
    }

    /**
     * Create an instance of {@link WebServiceProperties }
     * 
     */
    public WebServiceProperties createWebServiceProperties() {
        return new WebServiceProperties();
    }

    /**
     * Create an instance of {@link GetCrawlerConnectionStatusResponse }
     * 
     */
    public GetCrawlerConnectionStatusResponse createGetCrawlerConnectionStatusResponse() {
        return new GetCrawlerConnectionStatusResponse();
    }

    /**
     * Create an instance of {@link FindRunStatus }
     * 
     */
    public FindRunStatus createFindRunStatus() {
        return new FindRunStatus();
    }

    /**
     * Create an instance of {@link Stop }
     * 
     */
    public Stop createStop() {
        return new Stop();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link StopResponse }
     * 
     */
    public StopResponse createStopResponse() {
        return new StopResponse();
    }

    /**
     * Create an instance of {@link Parser }
     * 
     */
    public Parser createParser() {
        return new Parser();
    }

    /**
     * Create an instance of {@link FindAllCrawlerStatus }
     * 
     */
    public FindAllCrawlerStatus createFindAllCrawlerStatus() {
        return new FindAllCrawlerStatus();
    }

    /**
     * Create an instance of {@link FindRunStatusResponse }
     * 
     */
    public FindRunStatusResponse createFindRunStatusResponse() {
        return new FindRunStatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Start }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "start")
    public JAXBElement<Start> createStart(Start value) {
        return new JAXBElement<Start>(_Start_QNAME, Start.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetCrawlerConfigResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "setCrawlerConfigResponse")
    public JAXBElement<SetCrawlerConfigResponse> createSetCrawlerConfigResponse(SetCrawlerConfigResponse value) {
        return new JAXBElement<SetCrawlerConfigResponse>(_SetCrawlerConfigResponse_QNAME, SetCrawlerConfigResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetCrawlerConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "setCrawlerConfig")
    public JAXBElement<SetCrawlerConfig> createSetCrawlerConfig(SetCrawlerConfig value) {
        return new JAXBElement<SetCrawlerConfig>(_SetCrawlerConfig_QNAME, SetCrawlerConfig.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRunStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findRunStatus")
    public JAXBElement<FindRunStatus> createFindRunStatus(FindRunStatus value) {
        return new JAXBElement<FindRunStatus>(_FindRunStatus_QNAME, FindRunStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRunStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findRunStatusResponse")
    public JAXBElement<FindRunStatusResponse> createFindRunStatusResponse(FindRunStatusResponse value) {
        return new JAXBElement<FindRunStatusResponse>(_FindRunStatusResponse_QNAME, FindRunStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Parser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "parser")
    public JAXBElement<Parser> createParser(Parser value) {
        return new JAXBElement<Parser>(_Parser_QNAME, Parser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "parserResponse")
    public JAXBElement<ParserResponse> createParserResponse(ParserResponse value) {
        return new JAXBElement<ParserResponse>(_ParserResponse_QNAME, ParserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllCrawlerStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findAllCrawlerStatusResponse")
    public JAXBElement<FindAllCrawlerStatusResponse> createFindAllCrawlerStatusResponse(FindAllCrawlerStatusResponse value) {
        return new JAXBElement<FindAllCrawlerStatusResponse>(_FindAllCrawlerStatusResponse_QNAME, FindAllCrawlerStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerConnectionStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerConnectionStatusResponse")
    public JAXBElement<GetCrawlerConnectionStatusResponse> createGetCrawlerConnectionStatusResponse(GetCrawlerConnectionStatusResponse value) {
        return new JAXBElement<GetCrawlerConnectionStatusResponse>(_GetCrawlerConnectionStatusResponse_QNAME, GetCrawlerConnectionStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "stop")
    public JAXBElement<Stop> createStop(Stop value) {
        return new JAXBElement<Stop>(_Stop_QNAME, Stop.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "stopResponse")
    public JAXBElement<StopResponse> createStopResponse(StopResponse value) {
        return new JAXBElement<StopResponse>(_StopResponse_QNAME, StopResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCrawlerConnectionStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "getCrawlerConnectionStatus")
    public JAXBElement<GetCrawlerConnectionStatus> createGetCrawlerConnectionStatus(GetCrawlerConnectionStatus value) {
        return new JAXBElement<GetCrawlerConnectionStatus>(_GetCrawlerConnectionStatus_QNAME, GetCrawlerConnectionStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllCrawlerStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "findAllCrawlerStatus")
    public JAXBElement<FindAllCrawlerStatus> createFindAllCrawlerStatus(FindAllCrawlerStatus value) {
        return new JAXBElement<FindAllCrawlerStatus>(_FindAllCrawlerStatus_QNAME, FindAllCrawlerStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "startResponse")
    public JAXBElement<StartResponse> createStartResponse(StartResponse value) {
        return new JAXBElement<StartResponse>(_StartResponse_QNAME, StartResponse.class, null, value);
    }

}
