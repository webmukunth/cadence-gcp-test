
package com.anz.magneto.model.payment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.ToDetails_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.ToDetails_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentTRN" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://schema.cmo.anz/PmtAdd}com.anz.Status_Type"/>
 *         &lt;element name="ToBene" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToBene_Type" minOccurs="0"/>
 *         &lt;element name="ToAcct" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToAcct_Type"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Type" minOccurs="0"/>
 *         &lt;element name="Fees" type="{http://schema.cmo.anz/PmtAdd}com.anz.Fees_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.ToDetails_Type", propOrder = {
    "paymentTRN",
    "status",
    "toBene",
    "toAcct",
    "fx",
    "fees"
})
public class ComAnzToDetailsType {

    @XmlElement(name = "PaymentTRN")
    protected String paymentTRN;
    @XmlElement(name = "Status", required = true)
    protected ComAnzStatusType status;
    @XmlElement(name = "ToBene")
    protected ComAnzToBeneType toBene;
    @XmlElement(name = "ToAcct", required = true)
    protected ComAnzToAcctType toAcct;
    @XmlElement(name = "FX")
    protected ComAnzFXType fx;
    @XmlElement(name = "Fees")
    protected List<ComAnzFeesType> fees;

    /**
     * Gets the value of the paymentTRN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTRN() {
        return paymentTRN;
    }

    /**
     * Sets the value of the paymentTRN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTRN(String value) {
        this.paymentTRN = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzStatusType }
     *     
     */
    public ComAnzStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzStatusType }
     *     
     */
    public void setStatus(ComAnzStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the toBene property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzToBeneType }
     *     
     */
    public ComAnzToBeneType getToBene() {
        return toBene;
    }

    /**
     * Sets the value of the toBene property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzToBeneType }
     *     
     */
    public void setToBene(ComAnzToBeneType value) {
        this.toBene = value;
    }

    /**
     * Gets the value of the toAcct property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzToAcctType }
     *     
     */
    public ComAnzToAcctType getToAcct() {
        return toAcct;
    }

    /**
     * Sets the value of the toAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzToAcctType }
     *     
     */
    public void setToAcct(ComAnzToAcctType value) {
        this.toAcct = value;
    }

    /**
     * Gets the value of the fx property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzFXType }
     *     
     */
    public ComAnzFXType getFX() {
        return fx;
    }

    /**
     * Sets the value of the fx property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzFXType }
     *     
     */
    public void setFX(ComAnzFXType value) {
        this.fx = value;
    }

    /**
     * Gets the value of the fees property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fees property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFees().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzFeesType }
     * 
     * 
     */
    public List<ComAnzFeesType> getFees() {
        if (fees == null) {
            fees = new ArrayList<ComAnzFeesType>();
        }
        return this.fees;
    }

}
