
package com.anz.magneto.model.payment;

import com.anz.psp.commons.model.format.DateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com.anz.PayHdr_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.PayHdr_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PODsID" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="PaymentID" type="{http://schema.cmo.anz/PmtAdd}string35"/>
 *         &lt;element name="ThirdPartyPayID" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="PaymentBatchID" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ItemId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="NbrofItems" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="ValueofItems" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PaymentTRN" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="PaymentRelRef" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ProcDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="AcctTranType" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="BankOriginated" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="CustomerContact" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PayHdr_Type", propOrder = {
    "poDsID",
    "paymentID",
    "thirdPartyPayID",
    "paymentBatchID",
    "itemId",
    "nbrofItems",
    "valueofItems",
    "paymentTRN",
    "paymentRelRef",
    "procDate",
    "acctTranType",
    "bankOriginated",
    "customerContact"
})
public class ComAnzPayHdrType {

    @XmlElement(name = "PODsID")
    protected String poDsID;
    @XmlElement(name = "PaymentID", required = true)
    protected String paymentID;
    @XmlElement(name = "ThirdPartyPayID")
    protected String thirdPartyPayID;
    @XmlElement(name = "PaymentBatchID")
    protected String paymentBatchID;
    @XmlElement(name = "ItemId")
    protected String itemId;
    @XmlElement(name = "NbrofItems")
    protected BigInteger nbrofItems;
    @XmlElement(name = "ValueofItems")
    protected BigDecimal valueofItems;
    @XmlElement(name = "PaymentTRN")
    protected String paymentTRN;
    @XmlElement(name = "PaymentRelRef")
    protected String paymentRelRef;
    @XmlElement(name = "ProcDate", required = true)
    @XmlSchemaType(name = "date")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_FORMAT)
    protected XMLGregorianCalendar procDate;
    @XmlElement(name = "AcctTranType")
    protected String acctTranType;
    @XmlElement(name = "BankOriginated")
    protected String bankOriginated;
    @XmlElement(name = "CustomerContact")
    protected String customerContact;

    /**
     * Gets the value of the poDsID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPODsID() {
        return poDsID;
    }

    /**
     * Sets the value of the poDsID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPODsID(String value) {
        this.poDsID = value;
    }

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
     * Gets the value of the thirdPartyPayID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPartyPayID() {
        return thirdPartyPayID;
    }

    /**
     * Sets the value of the thirdPartyPayID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPartyPayID(String value) {
        this.thirdPartyPayID = value;
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
     * Gets the value of the nbrofItems property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNbrofItems() {
        return nbrofItems;
    }

    /**
     * Sets the value of the nbrofItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNbrofItems(BigInteger value) {
        this.nbrofItems = value;
    }

    /**
     * Gets the value of the valueofItems property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValueofItems() {
        return valueofItems;
    }

    /**
     * Sets the value of the valueofItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValueofItems(BigDecimal value) {
        this.valueofItems = value;
    }

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
     * Gets the value of the paymentRelRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentRelRef() {
        return paymentRelRef;
    }

    /**
     * Sets the value of the paymentRelRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentRelRef(String value) {
        this.paymentRelRef = value;
    }

    /**
     * Gets the value of the procDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProcDate() {
        return procDate;
    }

    /**
     * Sets the value of the procDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProcDate(XMLGregorianCalendar value) {
        this.procDate = value;
    }

    /**
     * Gets the value of the acctTranType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctTranType() {
        return acctTranType;
    }

    /**
     * Sets the value of the acctTranType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctTranType(String value) {
        this.acctTranType = value;
    }

    /**
     * Gets the value of the bankOriginated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankOriginated() {
        return bankOriginated;
    }

    /**
     * Sets the value of the bankOriginated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankOriginated(String value) {
        this.bankOriginated = value;
    }

    /**
     * Gets the value of the customerContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerContact() {
        return customerContact;
    }

    /**
     * Sets the value of the customerContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerContact(String value) {
        this.customerContact = value;
    }

}
