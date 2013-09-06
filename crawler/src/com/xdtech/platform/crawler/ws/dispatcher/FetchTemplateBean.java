
package com.xdtech.platform.crawler.ws.dispatcher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fetchTemplateBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fetchTemplateBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categories" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commonLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentRules" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datatime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageRules" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relatedLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siteIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srcSites" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleFilterChars" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleRules" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlRule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="views" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetchTemplateBean", propOrder = {
    "author",
    "categories",
    "comments",
    "commonLink",
    "contentRules",
    "datatime",
    "id",
    "name",
    "pageRules",
    "relatedLink",
    "relationRule",
    "siteIds",
    "srcSites",
    "titleFilterChars",
    "titleRules",
    "urlRule",
    "views"
})
public class FetchTemplateBean {

    protected String author;
    protected String categories;
    protected String comments;
    protected String commonLink;
    protected String contentRules;
    protected String datatime;
    protected String id;
    protected String name;
    protected String pageRules;
    protected String relatedLink;
    protected String relationRule;
    protected String siteIds;
    protected String srcSites;
    protected String titleFilterChars;
    protected String titleRules;
    protected String urlRule;
    protected String views;

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the categories property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategories() {
        return categories;
    }

    /**
     * Sets the value of the categories property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategories(String value) {
        this.categories = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Gets the value of the commonLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommonLink() {
        return commonLink;
    }

    /**
     * Sets the value of the commonLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommonLink(String value) {
        this.commonLink = value;
    }

    /**
     * Gets the value of the contentRules property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentRules() {
        return contentRules;
    }

    /**
     * Sets the value of the contentRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentRules(String value) {
        this.contentRules = value;
    }

    /**
     * Gets the value of the datatime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatatime() {
        return datatime;
    }

    /**
     * Sets the value of the datatime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatatime(String value) {
        this.datatime = value;
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
     * Gets the value of the pageRules property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageRules() {
        return pageRules;
    }

    /**
     * Sets the value of the pageRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageRules(String value) {
        this.pageRules = value;
    }

    /**
     * Gets the value of the relatedLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedLink() {
        return relatedLink;
    }

    /**
     * Sets the value of the relatedLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedLink(String value) {
        this.relatedLink = value;
    }

    /**
     * Gets the value of the relationRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationRule() {
        return relationRule;
    }

    /**
     * Sets the value of the relationRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationRule(String value) {
        this.relationRule = value;
    }

    /**
     * Gets the value of the siteIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteIds() {
        return siteIds;
    }

    /**
     * Sets the value of the siteIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteIds(String value) {
        this.siteIds = value;
    }

    /**
     * Gets the value of the srcSites property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcSites() {
        return srcSites;
    }

    /**
     * Sets the value of the srcSites property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcSites(String value) {
        this.srcSites = value;
    }

    /**
     * Gets the value of the titleFilterChars property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleFilterChars() {
        return titleFilterChars;
    }

    /**
     * Sets the value of the titleFilterChars property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleFilterChars(String value) {
        this.titleFilterChars = value;
    }

    /**
     * Gets the value of the titleRules property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleRules() {
        return titleRules;
    }

    /**
     * Sets the value of the titleRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleRules(String value) {
        this.titleRules = value;
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

    /**
     * Gets the value of the views property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViews() {
        return views;
    }

    /**
     * Sets the value of the views property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViews(String value) {
        this.views = value;
    }

}
