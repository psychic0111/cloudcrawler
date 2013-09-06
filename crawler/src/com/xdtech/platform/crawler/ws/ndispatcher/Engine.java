
package com.xdtech.platform.crawler.ws.ndispatcher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for engine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="engine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crawlerLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="durationRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imagePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isUse" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isVisiteUrl" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="itemBlock" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listBlock" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operateUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pages" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="perPageCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proxyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publishTimeRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="timeIntervalType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="titleRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "engine", propOrder = {
    "authorRule",
    "contentType",
    "contentTypeDesc",
    "crawlerLevel",
    "durationRule",
    "enginCode",
    "enginName",
    "hostUrl",
    "id",
    "imagePath",
    "isUse",
    "isVisiteUrl",
    "itemBlock",
    "listBlock",
    "mediaType",
    "operateTime",
    "operateUser",
    "pageUrl",
    "pages",
    "perPageCount",
    "proxyId",
    "publishTimeRule",
    "summary",
    "timeInterval",
    "timeIntervalType",
    "titleRule",
    "urlRule"
})
public class Engine {

    protected String authorRule;
    protected String contentType;
    protected String contentTypeDesc;
    protected String crawlerLevel;
    protected String durationRule;
    protected String enginCode;
    protected String enginName;
    protected String hostUrl;
    protected String id;
    protected String imagePath;
    protected Integer isUse;
    protected Integer isVisiteUrl;
    protected String itemBlock;
    protected String listBlock;
    protected String mediaType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operateTime;
    protected String operateUser;
    protected String pageUrl;
    protected Integer pages;
    protected Integer perPageCount;
    protected String proxyId;
    protected String publishTimeRule;
    protected String summary;
    protected Integer timeInterval;
    protected Integer timeIntervalType;
    protected String titleRule;
    protected String urlRule;

    /**
     * Gets the value of the authorRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorRule() {
        return authorRule;
    }

    /**
     * Sets the value of the authorRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorRule(String value) {
        this.authorRule = value;
    }

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the contentTypeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentTypeDesc() {
        return contentTypeDesc;
    }

    /**
     * Sets the value of the contentTypeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentTypeDesc(String value) {
        this.contentTypeDesc = value;
    }

    /**
     * Gets the value of the crawlerLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrawlerLevel() {
        return crawlerLevel;
    }

    /**
     * Sets the value of the crawlerLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrawlerLevel(String value) {
        this.crawlerLevel = value;
    }

    /**
     * Gets the value of the durationRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDurationRule() {
        return durationRule;
    }

    /**
     * Sets the value of the durationRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDurationRule(String value) {
        this.durationRule = value;
    }

    /**
     * Gets the value of the enginCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnginCode() {
        return enginCode;
    }

    /**
     * Sets the value of the enginCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnginCode(String value) {
        this.enginCode = value;
    }

    /**
     * Gets the value of the enginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnginName() {
        return enginName;
    }

    /**
     * Sets the value of the enginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnginName(String value) {
        this.enginName = value;
    }

    /**
     * Gets the value of the hostUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostUrl() {
        return hostUrl;
    }

    /**
     * Sets the value of the hostUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostUrl(String value) {
        this.hostUrl = value;
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
     * Gets the value of the imagePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the value of the imagePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagePath(String value) {
        this.imagePath = value;
    }

    /**
     * Gets the value of the isUse property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsUse() {
        return isUse;
    }

    /**
     * Sets the value of the isUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsUse(Integer value) {
        this.isUse = value;
    }

    /**
     * Gets the value of the isVisiteUrl property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsVisiteUrl() {
        return isVisiteUrl;
    }

    /**
     * Sets the value of the isVisiteUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsVisiteUrl(Integer value) {
        this.isVisiteUrl = value;
    }

    /**
     * Gets the value of the itemBlock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemBlock() {
        return itemBlock;
    }

    /**
     * Sets the value of the itemBlock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemBlock(String value) {
        this.itemBlock = value;
    }

    /**
     * Gets the value of the listBlock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListBlock() {
        return listBlock;
    }

    /**
     * Sets the value of the listBlock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListBlock(String value) {
        this.listBlock = value;
    }

    /**
     * Gets the value of the mediaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Sets the value of the mediaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaType(String value) {
        this.mediaType = value;
    }

    /**
     * Gets the value of the operateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperateTime() {
        return operateTime;
    }

    /**
     * Sets the value of the operateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperateTime(XMLGregorianCalendar value) {
        this.operateTime = value;
    }

    /**
     * Gets the value of the operateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateUser() {
        return operateUser;
    }

    /**
     * Sets the value of the operateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateUser(String value) {
        this.operateUser = value;
    }

    /**
     * Gets the value of the pageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Sets the value of the pageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageUrl(String value) {
        this.pageUrl = value;
    }

    /**
     * Gets the value of the pages property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * Sets the value of the pages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPages(Integer value) {
        this.pages = value;
    }

    /**
     * Gets the value of the perPageCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPerPageCount() {
        return perPageCount;
    }

    /**
     * Sets the value of the perPageCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPerPageCount(Integer value) {
        this.perPageCount = value;
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
     * Gets the value of the publishTimeRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishTimeRule() {
        return publishTimeRule;
    }

    /**
     * Sets the value of the publishTimeRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishTimeRule(String value) {
        this.publishTimeRule = value;
    }

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the timeInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimeInterval() {
        return timeInterval;
    }

    /**
     * Sets the value of the timeInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimeInterval(Integer value) {
        this.timeInterval = value;
    }

    /**
     * Gets the value of the timeIntervalType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimeIntervalType() {
        return timeIntervalType;
    }

    /**
     * Sets the value of the timeIntervalType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimeIntervalType(Integer value) {
        this.timeIntervalType = value;
    }

    /**
     * Gets the value of the titleRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleRule() {
        return titleRule;
    }

    /**
     * Sets the value of the titleRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleRule(String value) {
        this.titleRule = value;
    }

    /**
     * Gets the value of the urlRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlRule() {
        return urlRule;
    }

    /**
     * Sets the value of the urlRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlRule(String value) {
        this.urlRule = value;
    }

}
