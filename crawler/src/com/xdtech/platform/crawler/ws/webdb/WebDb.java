
package com.xdtech.platform.crawler.ws.webdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for webDb complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webDb">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fcount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ftime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="md5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nftime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="refurl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siteid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="urllevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webDb", propOrder = {
    "dbname",
    "fcount",
    "ftime",
    "id",
    "intime",
    "md5",
    "nftime",
    "refurl",
    "siteid",
    "status",
    "url",
    "urlType",
    "urllevel"
})
public class WebDb {

    protected String dbname;
    protected int fcount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftime;
    protected String id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar intime;
    protected String md5;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nftime;
    protected String refurl;
    protected String siteid;
    protected int status;
    protected String url;
    protected int urlType;
    protected int urllevel;

    /**
     * Gets the value of the dbname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbname() {
        return dbname;
    }

    /**
     * Sets the value of the dbname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbname(String value) {
        this.dbname = value;
    }

    /**
     * Gets the value of the fcount property.
     * 
     */
    public int getFcount() {
        return fcount;
    }

    /**
     * Sets the value of the fcount property.
     * 
     */
    public void setFcount(int value) {
        this.fcount = value;
    }

    /**
     * Gets the value of the ftime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtime() {
        return ftime;
    }

    /**
     * Sets the value of the ftime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtime(XMLGregorianCalendar value) {
        this.ftime = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the intime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIntime() {
        return intime;
    }

    /**
     * Sets the value of the intime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIntime(XMLGregorianCalendar value) {
        this.intime = value;
    }

    /**
     * Gets the value of the md5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Sets the value of the md5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMd5(String value) {
        this.md5 = value;
    }

    /**
     * Gets the value of the nftime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNftime() {
        return nftime;
    }

    /**
     * Sets the value of the nftime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNftime(XMLGregorianCalendar value) {
        this.nftime = value;
    }

    /**
     * Gets the value of the refurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefurl() {
        return refurl;
    }

    /**
     * Sets the value of the refurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefurl(String value) {
        this.refurl = value;
    }

    /**
     * Gets the value of the siteid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteid() {
        return siteid;
    }

    /**
     * Sets the value of the siteid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteid(String value) {
        this.siteid = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the urlType property.
     * 
     */
    public int getUrlType() {
        return urlType;
    }

    /**
     * Sets the value of the urlType property.
     * 
     */
    public void setUrlType(int value) {
        this.urlType = value;
    }

    /**
     * Gets the value of the urllevel property.
     * 
     */
    public int getUrllevel() {
        return urllevel;
    }

    /**
     * Sets the value of the urllevel property.
     * 
     */
    public void setUrllevel(int value) {
        this.urllevel = value;
    }

}