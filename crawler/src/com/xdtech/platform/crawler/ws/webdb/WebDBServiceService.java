
package com.xdtech.platform.crawler.ws.webdb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "WebDBServiceService", targetNamespace = "http://www.xd-tech.com", wsdlLocation = "http://127.0.0.1:8000/webdb/service/webdbservice?wsdl")
public class WebDBServiceService
    extends Service
{

    private final static URL WEBDBSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.xdtech.platform.crawler.ws.webdb.WebDBServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.xdtech.platform.crawler.ws.webdb.WebDBServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://127.0.0.1:8000/webdb/service/webdbservice?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://127.0.0.1:8000/webdb/service/webdbservice?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WEBDBSERVICESERVICE_WSDL_LOCATION = url;
    }

    public WebDBServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebDBServiceService() {
        super(WEBDBSERVICESERVICE_WSDL_LOCATION, new QName("http://www.xd-tech.com", "WebDBServiceService"));
    }

    /**
     * 
     * @return
     *     returns WebDBServiceInterface
     */
    @WebEndpoint(name = "WebDBServicePort")
    public WebDBServiceInterface getWebDBServicePort() {
        return super.getPort(new QName("http://www.xd-tech.com", "WebDBServicePort"), WebDBServiceInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebDBServiceInterface
     */
    @WebEndpoint(name = "WebDBServicePort")
    public WebDBServiceInterface getWebDBServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.xd-tech.com", "WebDBServicePort"), WebDBServiceInterface.class, features);
    }

}
