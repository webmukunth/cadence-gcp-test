
package com.anz.magneto.model.payment;

import com.anz.psp.commons.model.format.DateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com.anz.FX_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.FX_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemNo" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="FXType" type="{http://schema.cmo.anz/PmtAdd}string16"/>
 *         &lt;element name="FXOwner" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="DealSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="DealGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="FXAcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="QuoteId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="DealId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="ExchRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TranDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="FromCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="ToCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="FromAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ToAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BaseAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BaseCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FX_Type", propOrder = {
    "itemNo",
    "fxType",
    "fxOwner",
    "dealSys",
    "dealGrp",
    "fxAcctId",
    "quoteId",
    "dealId",
    "exchRate",
    "tranDate",
    "fromCurCode",
    "toCurCode",
    "fromAmount",
    "toAmount",
    "baseAmount",
    "baseCurCode",
    "type"
})
public class ComAnzFXType {

    @XmlElement(name = "ItemNo")
    protected String itemNo;
    @XmlElement(name = "FXType", required = true)
    protected String fxType;
    @XmlElement(name = "FXOwner")
    protected String fxOwner;
    @XmlElement(name = "DealSys")
    protected String dealSys;
    @XmlElement(name = "DealGrp")
    protected String dealGrp;
    @XmlElement(name = "FXAcctId")
    protected String fxAcctId;
    @XmlElement(name = "QuoteId")
    protected String quoteId;
    @XmlElement(name = "DealId")
    protected String dealId;
    @XmlElement(name = "ExchRate")
    protected BigDecimal exchRate;
    @XmlElement(name = "TranDate")
    @XmlSchemaType(name = "dateTime")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_FORMAT)
    protected XMLGregorianCalendar tranDate;
    @XmlElement(name = "FromCurCode")
    protected String fromCurCode;
    @XmlElement(name = "ToCurCode")
    protected String toCurCode;
    @XmlElement(name = "FromAmount")
    protected BigDecimal fromAmount;
    @XmlElement(name = "ToAmount")
    protected BigDecimal toAmount;
    @XmlElement(name = "BaseAmount")
    protected BigDecimal baseAmount;
    @XmlElement(name = "BaseCurCode")
    protected String baseCurCode;
    @XmlElement(name = "Type")
    protected String type;

    /**
     * Gets the value of the itemNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the value of the itemNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemNo(String value) {
        this.itemNo = value;
    }

    /**
     * Gets the value of the fxType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFXType() {
        return fxType;
    }

    /**
     * Sets the value of the fxType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFXType(String value) {
        this.fxType = value;
    }

    /**
     * Gets the value of the fxOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFXOwner() {
        return fxOwner;
    }

    /**
     * Sets the value of the fxOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFXOwner(String value) {
        this.fxOwner = value;
    }

    /**
     * Gets the value of the dealSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealSys() {
        return dealSys;
    }

    /**
     * Sets the value of the dealSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealSys(String value) {
        this.dealSys = value;
    }

    /**
     * Gets the value of the dealGrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealGrp() {
        return dealGrp;
    }

    /**
     * Sets the value of the dealGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealGrp(String value) {
        this.dealGrp = value;
    }

    /**
     * Gets the value of the fxAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFXAcctId() {
        return fxAcctId;
    }

    /**
     * Sets the value of the fxAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFXAcctId(String value) {
        this.fxAcctId = value;
    }

    /**
     * Gets the value of the quoteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * Sets the value of the quoteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteId(String value) {
        this.quoteId = value;
    }

    /**
     * Gets the value of the dealId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealId() {
        return dealId;
    }

    /**
     * Sets the value of the dealId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealId(String value) {
        this.dealId = value;
    }

    /**
     * Gets the value of the exchRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExchRate() {
        return exchRate;
    }

    /**
     * Sets the value of the exchRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExchRate(BigDecimal value) {
        this.exchRate = value;
    }

    /**
     * Gets the value of the tranDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTranDate() {
        return tranDate;
    }

    /**
     * Sets the value of the tranDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTranDate(XMLGregorianCalendar value) {
        this.tranDate = value;
    }

    /**
     * Gets the value of the fromCurCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromCurCode() {
        return fromCurCode;
    }

    /**
     * Sets the value of the fromCurCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromCurCode(String value) {
        this.fromCurCode = value;
    }

    /**
     * Gets the value of the toCurCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToCurCode() {
        return toCurCode;
    }

    /**
     * Sets the value of the toCurCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToCurCode(String value) {
        this.toCurCode = value;
    }

    /**
     * Gets the value of the fromAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    /**
     * Sets the value of the fromAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFromAmount(BigDecimal value) {
        this.fromAmount = value;
    }

    /**
     * Gets the value of the toAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getToAmount() {
        return toAmount;
    }

    /**
     * Sets the value of the toAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setToAmount(BigDecimal value) {
        this.toAmount = value;
    }

    /**
     * Gets the value of the baseAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    /**
     * Sets the value of the baseAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBaseAmount(BigDecimal value) {
        this.baseAmount = value;
    }

    /**
     * Gets the value of the baseCurCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseCurCode() {
        return baseCurCode;
    }

    /**
     * Sets the value of the baseCurCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseCurCode(String value) {
        this.baseCurCode = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
