
package com.xdtech.platform.crawler.ws.ndispatcher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dataBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dbip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbpassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbport" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dbtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dburl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbuser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataBase", propOrder = {
    "dbip",
    "dbname",
    "dbpassword",
    "dbport",
    "dbtype",
    "dburl",
    "dbuser",
    "id",
    "operateTime",
    "tableName"
})
public class DataBase {

    protected String dbip;
    protected String dbname;
    protected String dbpassword;
    protected Integer dbport;
    protected String dbtype;
    protected String dburl;
    protected String dbuser;
    protected String id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operateTime;
    protected String tableName;

    /**
     * Gets the value of the dbip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbip() {
        return dbip;
    }

    /**
     * Sets the value of the dbip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbip(String value) {
        this.dbip = value;
    }

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
     * Gets the value of the dbpassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpassword() {
        return dbpassword;
    }

    /**
     * Sets the value of the dbpassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpassword(String value) {
        this.dbpassword = value;
    }

    /**
     * Gets the value of the dbport property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDbport() {
        return dbport;
    }

    /**
     * Sets the value of the dbport property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDbport(Integer value) {
        this.dbport = value;
    }

    /**
     * Gets the value of the dbtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbtype() {
        return dbtype;
    }

    /**
     * Sets the value of the dbtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbtype(String value) {
        this.dbtype = value;
    }

    /**
     * Gets the value of the dburl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDburl() {
        return dburl;
    }

    /**
     * Sets the value of the dburl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDburl(String value) {
        this.dburl = value;
    }

    /**
     * Gets the value of the dbuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbuser() {
        return dbuser;
    }

    /**
     * Sets the value of the dbuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbuser(String value) {
        this.dbuser = value;
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
     * Gets the value of the tableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

}
