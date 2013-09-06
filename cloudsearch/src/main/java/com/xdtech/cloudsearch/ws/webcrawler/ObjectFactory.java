
package com.xdtech.cloudsearch.ws.webcrawler;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xdtech.cloudsearch.ws.webcrawler package. 
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
    private final static QName _Stop_QNAME = new QName("http://www.xd-tech.com", "stop");
    private final static QName _FindRunStatus_QNAME = new QName("http://www.xd-tech.com", "findRunStatus");
    private final static QName _FindRunStatusResponse_QNAME = new QName("http://www.xd-tech.com", "findRunStatusResponse");
    private final static QName _StopResponse_QNAME = new QName("http://www.xd-tech.com", "stopResponse");
    private final static QName _StartResponse_QNAME = new QName("http://www.xd-tech.com", "startResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xdtech.cloudsearch.ws.webcrawler
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindRunStatus }
     * 
     */
    public FindRunStatus createFindRunStatus() {
        return new FindRunStatus();
    }

    /**
     * Create an instance of {@link StartResponse }
     * 
     */
    public StartResponse createStartResponse() {
        return new StartResponse();
    }

    /**
     * Create an instance of {@link StopResponse }
     * 
     */
    public StopResponse createStopResponse() {
        return new StopResponse();
    }

    /**
     * Create an instance of {@link FindRunStatusResponse }
     * 
     */
    public FindRunStatusResponse createFindRunStatusResponse() {
        return new FindRunStatusResponse();
    }

    /**
     * Create an instance of {@link Start }
     * 
     */
    public Start createStart() {
        return new Start();
    }

    /**
     * Create an instance of {@link Stop }
     * 
     */
    public Stop createStop() {
        return new Stop();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Stop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "stop")
    public JAXBElement<Stop> createStop(Stop value) {
        return new JAXBElement<Stop>(_Stop_QNAME, Stop.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link StopResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xd-tech.com", name = "stopResponse")
    public JAXBElement<StopResponse> createStopResponse(StopResponse value) {
        return new JAXBElement<StopResponse>(_StopResponse_QNAME, StopResponse.class, null, value);
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
