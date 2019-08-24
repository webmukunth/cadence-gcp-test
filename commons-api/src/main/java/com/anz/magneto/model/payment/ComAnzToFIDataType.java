
package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.ToFIData_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.ToFIData_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{http://schema.cmo.anz/PmtAdd}string2"/>
 *         &lt;element name="BIC" type="{http://schema.cmo.anz/PmtAdd}string11" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr1" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr2" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr3" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec1" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec2" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec3" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec4" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec5" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec6" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.ToFIData_Type", propOrder = {
    "country",
    "bic",
    "branch",
    "name",
    "addr1",
    "addr2",
    "addr3",
    "senderRec1",
    "senderRec2",
    "senderRec3",
    "senderRec4",
    "senderRec5",
    "senderRec6"
})
public class ComAnzToFIDataType {

    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "BIC")
    protected String bic;
    @XmlElement(name = "Branch")
    protected String branch;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Addr1")
    protected String addr1;
    @XmlElement(name = "Addr2")
    protected String addr2;
    @XmlElement(name = "Addr3")
    protected String addr3;
    @XmlElement(name = "SenderRec1")
    protected String senderRec1;
    @XmlElement(name = "SenderRec2")
    protected String senderRec2;
    @XmlElement(name = "SenderRec3")
    protected String senderRec3;
    @XmlElement(name = "SenderRec4")
    protected String senderRec4;
    @XmlElement(name = "SenderRec5")
    protected String senderRec5;
    @XmlElement(name = "SenderRec6")
    protected String senderRec6;

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the bic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBIC() {
        return bic;
    }

    /**
     * Sets the value of the bic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBIC(String value) {
        this.bic = value;
    }

    /**
     * Gets the value of the branch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets the value of the branch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranch(String value) {
        this.branch = value;
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
     * Gets the value of the addr1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * Sets the value of the addr1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr1(String value) {
        this.addr1 = value;
    }

    /**
     * Gets the value of the addr2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * Sets the value of the addr2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr2(String value) {
        this.addr2 = value;
    }

    /**
     * Gets the value of the addr3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr3() {
        return addr3;
    }

    /**
     * Sets the value of the addr3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr3(String value) {
        this.addr3 = value;
    }

    /**
     * Gets the value of the senderRec1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderRec1() {
        return senderRec1;
    }

    /**
     * Sets the value of the senderRec1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderRec1(String value) {
        this.senderRec1 = value;
    }

    /**
     * Gets the value of the senderRec2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderRec2() {
        return senderRec2;
    }

    /**
     * Sets the value of the senderRec2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderRec2(String value) {
        this.senderRec2 = value;
    }

    /**
     * Gets the value of the senderRec3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderRec3() {
        return senderRec3;
    }

    /**
     * Sets the value of the senderRec3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderRec3(String value) {
        this.senderRec3 = value;
    }

    /**
     * Gets the value of the senderRec4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderRec4() {
        return senderRec4;
    }

    /**
     * Sets the value of the senderRec4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderRec4(String value) {
        this.senderRec4 = value;
    }

    /**
     * Gets the value of the senderRec5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderRec5() {
        return senderRec5;
    }

    /**
     * Sets the value of the senderRec5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderRec5(String value) {
        this.senderRec5 = value;
    }

    /**
     * Gets the value of the senderRec6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderRec6() {
        return senderRec6;
    }

    /**
     * Sets the value of the senderRec6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderRec6(String value) {
        this.senderRec6 = value;
    }

}
