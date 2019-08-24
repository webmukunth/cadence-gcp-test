
package com.anz.magneto.model.payment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.Status_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.Status_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatusCode" type="{http://schema.cmo.anz/PmtAdd}string4"/>
 *         &lt;element name="StatusDesc" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="Duplicate" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="AddStatusCode" type="{http://schema.cmo.anz/PmtAdd}string4" minOccurs="0"/>
 *         &lt;element name="AddStatusDesc" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="AdditionalStatus" type="{http://schema.cmo.anz/PmtAdd}com.anz.AdditionalStatus_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Status_Type", propOrder = {
    "statusCode",
    "statusDesc",
    "duplicate",
    "addStatusCode",
    "addStatusDesc",
    "additionalStatus"
})
public class ComAnzStatusType {

    @XmlElement(name = "StatusCode", required = true)
    protected String statusCode;
    @XmlElement(name = "StatusDesc")
    protected String statusDesc;
    @XmlElement(name = "Duplicate")
    protected String duplicate;
    @XmlElement(name = "AddStatusCode")
    protected String addStatusCode;
    @XmlElement(name = "AddStatusDesc")
    protected String addStatusDesc;
    @XmlElement(name = "AdditionalStatus")
    protected List<ComAnzAdditionalStatusType> additionalStatus;

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Sets the value of the statusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDesc(String value) {
        this.statusDesc = value;
    }

    /**
     * Gets the value of the duplicate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuplicate() {
        return duplicate;
    }

    /**
     * Sets the value of the duplicate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuplicate(String value) {
        this.duplicate = value;
    }

    /**
     * Gets the value of the addStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddStatusCode() {
        return addStatusCode;
    }

    /**
     * Sets the value of the addStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddStatusCode(String value) {
        this.addStatusCode = value;
    }

    /**
     * Gets the value of the addStatusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddStatusDesc() {
        return addStatusDesc;
    }

    /**
     * Sets the value of the addStatusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddStatusDesc(String value) {
        this.addStatusDesc = value;
    }

    /**
     * Gets the value of the additionalStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzAdditionalStatusType }
     * 
     * 
     */
    public List<ComAnzAdditionalStatusType> getAdditionalStatus() {
        if (additionalStatus == null) {
            additionalStatus = new ArrayList<ComAnzAdditionalStatusType>();
        }
        return this.additionalStatus;
    }

}
