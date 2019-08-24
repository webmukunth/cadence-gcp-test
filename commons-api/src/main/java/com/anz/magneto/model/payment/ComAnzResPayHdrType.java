
package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.Res_PayHdr_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.Res_PayHdr_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentID" type="{http://schema.cmo.anz/PmtAdd}string35"/>
 *         &lt;element name="PaymentBatchID" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ItemId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="ClearPref" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ClearSubPref" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="MOP" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Res_PayHdr_Type", propOrder = {
    "paymentID",
    "paymentBatchID",
    "itemId",
    "clearPref",
    "clearSubPref",
    "mop"
})
public class ComAnzResPayHdrType {

    @XmlElement(name = "PaymentID", required = true)
    protected String paymentID;
    @XmlElement(name = "PaymentBatchID")
    protected String paymentBatchID;
    @XmlElement(name = "ItemId")
    protected String itemId;
    @XmlElement(name = "ClearPref")
    protected String clearPref;
    @XmlElement(name = "ClearSubPref")
    protected String clearSubPref;
    @XmlElement(name = "MOP")
    protected String mop;

    /**
     * Gets the value of the paymentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentID() {
        return paymentID;
    }

    /**
     * Sets the value of the paymentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentID(String value) {
        this.paymentID = value;
    }

    /**
     * Gets the value of the paymentBatchID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentBatchID() {
        return paymentBatchID;
    }

    /**
     * Sets the value of the paymentBatchID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentBatchID(String value) {
        this.paymentBatchID = value;
    }

    /**
     * Gets the value of the itemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the value of the itemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemId(String value) {
        this.itemId = value;
    }

    /**
     * Gets the value of the clearPref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearPref() {
        return clearPref;
    }

    /**
     * Sets the value of the clearPref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearPref(String value) {
        this.clearPref = value;
    }

    /**
     * Gets the value of the clearSubPref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearSubPref() {
        return clearSubPref;
    }

    /**
     * Sets the value of the clearSubPref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearSubPref(String value) {
        this.clearSubPref = value;
    }

    /**
     * Gets the value of the mop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOP() {
        return mop;
    }

    /**
     * Sets the value of the mop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOP(String value) {
        this.mop = value;
    }

}
