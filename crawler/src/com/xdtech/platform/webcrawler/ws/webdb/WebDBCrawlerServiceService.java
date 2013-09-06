
package com.xdtech.platform.webcrawler.ws.webdb;

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
@WebServiceClient(name = "WebDBCrawlerServiceService", targetNamespace = "http://www.xd-tech.com", wsdlLocation = "http://127.0.0.1:8000/webdb/service/webdbcrawlerservice?wsdl")
public class WebDBCrawlerServiceService
    extends Service
{

    private final static URL WEBDBCRAWLERSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.xdtech.platform.webcrawler.ws.webdb.WebDBCrawlerServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.xdtech.platform.webcrawler.ws.webdb.WebDBCrawlerServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://127.0.0.1:8000/webdb/service/webdbcrawlerservice?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://127.0.0.1:8000/webdb/service/webdbcrawlerservice?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WEBDBCRAWLERSERVICESERVICE_WSDL_LOCATION = url;
    }

    public WebDBCrawlerServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebDBCrawlerServiceService() {
        super(WEBDBCRAWLERSERVICESERVICE_WSDL_LOCATION, new QName("http://www.xd-tech.com", "WebDBCrawlerServiceService"));
    }

    /**
     * 
     * @return
     *     returns WebDBCrawlerServiceInterface
     */
    @WebEndpoint(name = "WebDBCrawlerServicePort")
    public WebDBCrawlerServiceInterface getWebDBCrawlerServicePort() {
        return super.getPort(new QName("http://www.xd-tech.com", "WebDBCrawlerServicePort"), WebDBCrawlerServiceInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebDBCrawlerServiceInterface
     */
    @WebEndpoint(name = "WebDBCrawlerServicePort")
    public WebDBCrawlerServiceInterface getWebDBCrawlerServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.xd-tech.com", "WebDBCrawlerServicePort"), WebDBCrawlerServiceInterface.class, features);
    }

}
