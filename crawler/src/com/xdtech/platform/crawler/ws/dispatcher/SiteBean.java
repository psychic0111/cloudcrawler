
package com.xdtech.platform.crawler.ws.dispatcher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for siteBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="siteBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentEncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crawler" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="protocol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proxyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regrule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siteStatue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sortId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templateids" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updaeInterval" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="urlGenerateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="webType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "siteBean", propOrder = {
    "areaType",
    "category",
    "cityId",
    "cityName",
    "contain",
    "contentEncode",
    "contryId",
    "contryName",
    "countyId",
    "countyName",
    "crawler",
    "createTime",
    "depth",
    "id",
    "inUrl",
    "loginId",
    "protocol",
    "provinceId",
    "provinceName",
    "proxyId",
    "regrule",
    "siteName",
    "siteStatue",
    "sortId",
    "templateids",
    "updaeInterval",
    "urlGenerateDate",
    "webType"
})
public class SiteBean {

    protected String areaType;
    protected String category;
    protected String cityId;
    protected String cityName;
    protected String contain;
    protected String contentEncode;
    protected String contryId;
    protected String contryName;
    protected String countyId;
    protected String countyName;
    protected String crawler;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected short depth;
    protected String id;
    protected String inUrl;
    protected String loginId;
    protected String protocol;
    protected String provinceId;
    protected String provinceName;
    protected String proxyId;
    protected String regrule;
    protected String siteName;
    protected int siteStatue;
    protected String sortId;
    protected String templateids;
    protected int updaeInterval;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar urlGenerateDate;
    protected int webType;

    /**
     * Gets the value of the areaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaType() {
        return areaType;
    }

    /**
     * Sets the value of the areaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaType(String value) {
        this.areaType = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the cityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * Sets the value of the cityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityId(String value) {
        this.cityId = value;
    }

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the contain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContain() {
        return contain;
    }

    /**
     * Sets the value of the contain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContain(String value) {
        this.contain = value;
    }

    /**
     * Gets the value of the contentEncode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentEncode() {
        return contentEncode;
    }

    /**
     * Sets the value of the contentEncode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentEncode(String value) {
        this.contentEncode = value;
    }

    /**
     * Gets the value of the contryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContryId() {
        return contryId;
    }

    /**
     * Sets the value of the contryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContryId(String value) {
        this.contryId = value;
    }

    /**
     * Gets the value of the contryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContryName() {
        return contryName;
    }

    /**
     * Sets the value of the contryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContryName(String value) {
        this.contryName = value;
    }

    /**
     * Gets the value of the countyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyId() {
        return countyId;
    }

    /**
     * Sets the value of the countyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyId(String value) {
        this.countyId = value;
    }

    /**
     * Gets the value of the countyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * Sets the value of the countyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyName(String value) {
        this.countyName = value;
    }

    /**
     * Gets the value of the crawler property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrawler() {
        return crawler;
    }

    /**
     * Sets the value of the crawler property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrawler(String value) {
        this.crawler = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the depth property.
     * 
     */
    public short getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     */
    public void setDepth(short value) {
        this.depth = value;
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
     * Gets the value of the inUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInUrl() {
        return inUrl;
    }

    /**
     * Sets the value of the inUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInUrl(String value) {
        this.inUrl = value;
    }

    /**
     * Gets the value of the loginId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginId(String value) {
        this.loginId = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

    /**
     * Gets the value of the provinceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * Sets the value of the provinceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceId(String value) {
        this.provinceId = value;
    }

    /**
     * Gets the value of the provinceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * Sets the value of the provinceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceName(String value) {
        this.provinceName = value;
    }

    /**
     * Gets the value of the proxyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxyId() {
        return proxyId;
    }

    /**
     * Sets the value of the proxyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyId(String value) {
        this.proxyId = value;
    }

    /**
     * Gets the value of the regrule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegrule() {
        return regrule;
    }

    /**
     * Sets the value of the regrule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegrule(String value) {
        this.regrule = value;
    }

    /**
     * Gets the value of the siteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * Sets the value of the siteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteName(String value) {
        this.siteName = value;
    }

    /**
     * Gets the value of the siteStatue property.
     * 
     */
    public int getSiteStatue() {
        return siteStatue;
    }

    /**
     * Sets the value of the siteStatue property.
     * 
     */
    public void setSiteStatue(int value) {
        this.siteStatue = value;
    }

    /**
     * Gets the value of the sortId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortId() {
        return sortId;
    }

    /**
     * Sets the value of the sortId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortId(String value) {
        this.sortId = value;
    }

    /**
     * Gets the value of the templateids property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateids() {
        return templateids;
    }

    /**
     * Sets the value of the templateids property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateids(String value) {
        this.templateids = value;
    }

    /**
     * Gets the value of the updaeInterval property.
     * 
     */
    public int getUpdaeInterval() {
        return updaeInterval;
    }

    /**
     * Sets the value of the updaeInterval property.
     * 
     */
    public void setUpdaeInterval(int value) {
        this.updaeInterval = value;
    }

    /**
     * Gets the value of the urlGenerateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUrlGenerateDate() {
        return urlGenerateDate;
    }

    /**
     * Sets the value of the urlGenerateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUrlGenerateDate(XMLGregorianCalendar value) {
        this.urlGenerateDate = value;
    }

    /**
     * Gets the value of the webType property.
     * 
     */
    public int getWebType() {
        return webType;
    }

    /**
     * Sets the value of the webType property.
     * 
     */
    public void setWebType(int value) {
        this.webType = value;
    }

}
