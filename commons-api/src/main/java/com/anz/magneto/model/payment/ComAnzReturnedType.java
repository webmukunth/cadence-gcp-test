
package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.Returned_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.Returned_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DRBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CRBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DRLedgerBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CRLedgerBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PaymentReference" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="PODSId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="ClearMethod" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ClearSubMethod" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://schema.cmo.anz/PmtAdd}com.anz.Msg_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LienId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="Timestamps" type="{http://schema.cmo.anz/PmtAdd}com.anz.Timestamps_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Response_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Returned_Type", propOrder = {
    "drBalance",
    "crBalance",
    "drLedgerBalance",
    "crLedgerBalance",
    "paymentReference",
    "podsId",
    "clearMethod",
    "clearSubMethod",
    "message",
    "lienId",
    "timestamps",
    "fx"
})
public class ComAnzReturnedType {

    @XmlElement(name = "DRBalance")
    protected BigDecimal drBalance;
    @XmlElement(name = "CRBalance")
    protected BigDecimal crBalance;
    @XmlElement(name = "DRLedgerBalance")
    protected BigDecimal drLedgerBalance;
    @XmlElement(name = "CRLedgerBalance")
    protected BigDecimal crLedgerBalance;
    @XmlElement(name = "PaymentReference")
    protected String paymentReference;
    @XmlElement(name = "PODSId")
    protected String podsId;
    @XmlElement(name = "ClearMethod")
    protected String clearMethod;
    @XmlElement(name = "ClearSubMethod")
    protected String clearSubMethod;
    @XmlElement(name = "Message")
    protected List<ComAnzMsgType> message;
    @XmlElement(name = "LienId")
    protected String lienId;
    @XmlElement(name = "Timestamps")
    protected List<ComAnzTimestampsType> timestamps;
    @XmlElement(name = "FX")
    protected List<ComAnzFXResponseType> fx;

    /**
     * Gets the value of the drBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDRBalance() {
        return drBalance;
    }

    /**
     * Sets the value of the drBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDRBalance(BigDecimal value) {
        this.drBalance = value;
    }

    /**
     * Gets the value of the crBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCRBalance() {
        return crBalance;
    }

    /**
     * Sets the value of the crBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCRBalance(BigDecimal value) {
        this.crBalance = value;
    }

    /**
     * Gets the value of the drLedgerBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDRLedgerBalance() {
        return drLedgerBalance;
    }

    /**
     * Sets the value of the drLedgerBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDRLedgerBalance(BigDecimal value) {
        this.drLedgerBalance = value;
    }

    /**
     * Gets the value of the crLedgerBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCRLedgerBalance() {
        return crLedgerBalance;
    }

    /**
     * Sets the value of the crLedgerBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCRLedgerBalance(BigDecimal value) {
        this.crLedgerBalance = value;
    }

    /**
     * Gets the value of the paymentReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * Sets the value of the paymentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentReference(String value) {
        this.paymentReference = value;
    }

    /**
     * Gets the value of the podsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPODSId() {
        return podsId;
    }

    /**
     * Sets the value of the podsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPODSId(String value) {
        this.podsId = value;
    }

    /**
     * Gets the value of the clearMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearMethod() {
        return clearMethod;
    }

    /**
     * Sets the value of the clearMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearMethod(String value) {
        this.clearMethod = value;
    }

    /**
     * Gets the value of the clearSubMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearSubMethod() {
        return clearSubMethod;
    }

    /**
     * Sets the value of the clearSubMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearSubMethod(String value) {
        this.clearSubMethod = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzMsgType }
     * 
     * 
     */
    public List<ComAnzMsgType> getMessage() {
        if (message == null) {
            message = new ArrayList<ComAnzMsgType>();
        }
        return this.message;
    }

    /**
     * Gets the value of the lienId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLienId() {
        return lienId;
    }

    /**
     * Sets the value of the lienId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLienId(String value) {
        this.lienId = value;
    }

    /**
     * Gets the value of the timestamps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timestamps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimestamps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzTimestampsType }
     * 
     * 
     */
    public List<ComAnzTimestampsType> getTimestamps() {
        if (timestamps == null) {
            timestamps = new ArrayList<ComAnzTimestampsType>();
        }
        return this.timestamps;
    }

    /**
     * Gets the value of the fx property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fx property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFX().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzFXResponseType }
     * 
     * 
     */
    public List<ComAnzFXResponseType> getFX() {
        if (fx == null) {
            fx = new ArrayList<ComAnzFXResponseType>();
        }
        return this.fx;
    }

}
