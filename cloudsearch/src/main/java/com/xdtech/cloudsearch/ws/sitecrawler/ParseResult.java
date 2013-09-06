
package com.xdtech.cloudsearch.ws.sitecrawler;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parseResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parseResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="base" type="{http://www.xd-tech.com}webServiceProperties" minOccurs="0"/>
 *         &lt;element name="blocks" type="{http://www.xd-tech.com}webServiceProperties" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parseResult", propOrder = {
    "base",
    "blocks"
})
public class ParseResult {

    protected WebServiceProperties base;
    @XmlElement(nillable = true)
    protected List<WebServiceProperties> blocks;

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link WebServiceProperties }
     *     
     */
    public WebServiceProperties getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebServiceProperties }
     *     
     */
    public void setBase(WebServiceProperties value) {
        this.base = value;
    }

    /**
     * Gets the value of the blocks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blocks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlocks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WebServiceProperties }
     * 
     * 
     */
    public List<WebServiceProperties> getBlocks() {
        if (blocks == null) {
            blocks = new ArrayList<WebServiceProperties>();
        }
        return this.blocks;
    }

}
