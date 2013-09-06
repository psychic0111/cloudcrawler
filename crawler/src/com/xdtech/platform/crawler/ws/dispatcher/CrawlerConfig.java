
package com.xdtech.platform.crawler.ws.dispatcher;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crawlerConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crawlerConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crawlerThread" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="downloadThread" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="filePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="siteThread" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="urlLevel" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="useState" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="webdbs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crawlerConfig", propOrder = {
    "code",
    "crawlerThread",
    "downloadThread",
    "filePath",
    "ip",
    "name",
    "port",
    "siteThread",
    "urlLevel",
    "useState",
    "webdbs"
})
public class CrawlerConfig {

    protected String code;
    protected int crawlerThread;
    protected int downloadThread;
    protected String filePath;
    protected String ip;
    protected String name;
    protected int port;
    protected int siteThread;
    @XmlElement(nillable = true)
    protected List<Integer> urlLevel;
    protected int useState;
    @XmlElement(nillable = true)
    protected List<String> webdbs;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the crawlerThread property.
     * 
     */
    public int getCrawlerThread() {
        return crawlerThread;
    }

    /**
     * Sets the value of the crawlerThread property.
     * 
     */
    public void setCrawlerThread(int value) {
        this.crawlerThread = value;
    }

    /**
     * Gets the value of the downloadThread property.
     * 
     */
    public int getDownloadThread() {
        return downloadThread;
    }

    /**
     * Sets the value of the downloadThread property.
     * 
     */
    public void setDownloadThread(int value) {
        this.downloadThread = value;
    }

    /**
     * Gets the value of the filePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the value of the filePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePath(String value) {
        this.filePath = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the port property.
     * 
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the value of the port property.
     * 
     */
    public void setPort(int value) {
        this.port = value;
    }

    /**
     * Gets the value of the siteThread property.
     * 
     */
    public int getSiteThread() {
        return siteThread;
    }

    /**
     * Sets the value of the siteThread property.
     * 
     */
    public void setSiteThread(int value) {
        this.siteThread = value;
    }

    /**
     * Gets the value of the urlLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the urlLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrlLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getUrlLevel() {
        if (urlLevel == null) {
            urlLevel = new ArrayList<Integer>();
        }
        return this.urlLevel;
    }

    /**
     * Gets the value of the useState property.
     * 
     */
    public int getUseState() {
        return useState;
    }

    /**
     * Sets the value of the useState property.
     * 
     */
    public void setUseState(int value) {
        this.useState = value;
    }

    /**
     * Gets the value of the webdbs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the webdbs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWebdbs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getWebdbs() {
        if (webdbs == null) {
            webdbs = new ArrayList<String>();
        }
        return this.webdbs;
    }

}
