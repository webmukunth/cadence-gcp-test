//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.14 at 03:36:19 PM AEDT 
//


package com.anz.magneto.model.wrapper;

import com.anz.psp.commons.model.format.DateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com.anz.Accounting_Entry_Map_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.Accounting_Entry_Map_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjId" type="{http://schema.cmo.anz/PSPWrapperObject}string55" minOccurs="0"/>
 *         &lt;element name="LegNum" type="{http://schema.cmo.anz/PSPWrapperObject}string4" minOccurs="0"/>
 *         &lt;element name="SeqNum" type="{http://schema.cmo.anz/PSPWrapperObject}string4" minOccurs="0"/>
 *         &lt;element name="BulkCount" type="{http://schema.cmo.anz/PSPWrapperObject}string4" minOccurs="0"/>
 *         &lt;element name="PodsId" type="{http://schema.cmo.anz/PSPWrapperObject}string40" minOccurs="0"/>
 *         &lt;element name="Source" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="ProcDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://schema.cmo.anz/PSPWrapperObject}string2" minOccurs="0"/>
 *         &lt;element name="DebitCountry" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="DebitCurrency" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="DebitAccountId" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="DebitAccountGroup" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="DebitAccountBSB" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="DebitCostCentre" type="{http://schema.cmo.anz/PSPWrapperObject}string20" minOccurs="0"/>
 *         &lt;element name="DebitAccountSys" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="DebitAmount" type="{http://schema.cmo.anz/PSPWrapperObject}string20" minOccurs="0"/>
 *         &lt;element name="DebitNarrative" type="{http://schema.cmo.anz/PSPWrapperObject}string400" minOccurs="0"/>
 *         &lt;element name="DebitASTM" type="{http://schema.cmo.anz/PSPWrapperObject}string55" minOccurs="0"/>
 *         &lt;element name="DebitAggregateInd" type="{http://schema.cmo.anz/PSPWrapperObject}string4" minOccurs="0"/>
 *         &lt;element name="CreditCountry" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="CreditCurrency" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="CreditAccountId" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="CreditAccountGroup" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="CreditAccountBSB" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="CreditCostCentre" type="{http://schema.cmo.anz/PSPWrapperObject}string20" minOccurs="0"/>
 *         &lt;element name="CreditAccountSys" type="{http://schema.cmo.anz/PSPWrapperObject}string35" minOccurs="0"/>
 *         &lt;element name="CreditAmount" type="{http://schema.cmo.anz/PSPWrapperObject}string20" minOccurs="0"/>
 *         &lt;element name="CreditNarrative" type="{http://schema.cmo.anz/PSPWrapperObject}string400" minOccurs="0"/>
 *         &lt;element name="CreditASTM" type="{http://schema.cmo.anz/PSPWrapperObject}string55" minOccurs="0"/>
 *         &lt;element name="CreditAggregateInd" type="{http://schema.cmo.anz/PSPWrapperObject}string4" minOccurs="0"/>
 *         &lt;element name="DestinationASTM" type="{http://schema.cmo.anz/PSPWrapperObject}string55" minOccurs="0"/>
 *         &lt;element name="MiscAttribute1" type="{http://schema.cmo.anz/PSPWrapperObject}string400" minOccurs="0"/>
 *         &lt;element name="MiscAttribute2" type="{http://schema.cmo.anz/PSPWrapperObject}string400" minOccurs="0"/>
 *         &lt;element name="MiscAttribute3" type="{http://schema.cmo.anz/PSPWrapperObject}string400" minOccurs="0"/>
 *         &lt;element name="MiscAttribute4" type="{http://schema.cmo.anz/PSPWrapperObject}string400" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Accounting_Entry_Map_Type", namespace = "http://schema.cmo.anz/PSPWrapperObject", propOrder = {
    "objId",
    "legNum",
    "seqNum",
    "bulkCount",
    "podsId",
    "source",
    "procDate",
    "country",
    "debitCountry",
    "debitCurrency",
    "debitAccountId",
    "debitAccountGroup",
    "debitAccountBSB",
    "debitCostCentre",
    "debitAccountSys",
    "debitAmount",
    "debitNarrative",
    "debitASTM",
    "debitAggregateInd",
    "creditCountry",
    "creditCurrency",
    "creditAccountId",
    "creditAccountGroup",
    "creditAccountBSB",
    "creditCostCentre",
    "creditAccountSys",
    "creditAmount",
    "creditNarrative",
    "creditASTM",
    "creditAggregateInd",    
    "destinationASTM",
    "miscAttribute1",
    "miscAttribute2",
    "miscAttribute3",
    "miscAttribute4"
})
public class ComAnzAccountingEntryMapType {

    @XmlElement(name = "ObjId")
    protected String objId;
    @XmlElement(name = "LegNum")
    protected String legNum;
    @XmlElement(name = "SeqNum")
    protected String seqNum;
    @XmlElement(name = "BulkCount")
    protected String bulkCount;
    @XmlElement(name = "PodsId")
    protected String podsId;
    @XmlElement(name = "Source")
    protected String source;
    @XmlElement(name = "ProcDate")
    @XmlSchemaType(name = "date")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_FORMAT)
    protected XMLGregorianCalendar procDate;
    @XmlElement(name = "Country")
    protected String country;
    @XmlElement(name = "DebitCountry")
    protected String debitCountry;
    @XmlElement(name = "DebitCurrency")
    protected String debitCurrency;
    @XmlElement(name = "DebitAccountId")
    protected String debitAccountId;
    @XmlElement(name = "DebitAccountGroup")
    protected String debitAccountGroup;
    @XmlElement(name = "DebitAccountBSB")
    protected String debitAccountBSB;
    @XmlElement(name = "DebitCostCentre")
    protected String debitCostCentre;
    @XmlElement(name = "DebitAccountSys")
    protected String debitAccountSys;
    @XmlElement(name = "DebitAmount")
    protected String debitAmount;
    @XmlElement(name = "DebitNarrative")
    protected String debitNarrative;
    @XmlElement(name = "DebitASTM")
    protected String debitASTM;
    @XmlElement(name = "DebitAggregateInd")
    protected String debitAggregateInd;
    @XmlElement(name = "CreditCountry")
    protected String creditCountry;
    @XmlElement(name = "CreditCurrency")
    protected String creditCurrency;
    @XmlElement(name = "CreditAccountId")
    protected String creditAccountId;
    @XmlElement(name = "CreditAccountGroup")
    protected String creditAccountGroup;
    @XmlElement(name = "CreditAccountBSB")
    protected String creditAccountBSB;
    @XmlElement(name = "CreditCostCentre")
    protected String creditCostCentre;
    @XmlElement(name = "CreditAccountSys")
    protected String creditAccountSys;
    @XmlElement(name = "CreditAmount")
    protected String creditAmount;
    @XmlElement(name = "CreditNarrative")
    protected String creditNarrative;
    @XmlElement(name = "CreditASTM")
    protected String creditASTM;
    @XmlElement(name = "CreditAggregateInd")
    protected String creditAggregateInd;
    @XmlElement(name = "DestinationASTM")
    protected String destinationASTM;
    @XmlElement(name = "MiscAttribute1")
    protected String miscAttribute1;
    @XmlElement(name = "MiscAttribute2")
    protected String miscAttribute2;
    @XmlElement(name = "MiscAttribute3")
    protected String miscAttribute3;
    @XmlElement(name = "MiscAttribute4")
    protected String miscAttribute4;

    /**
     * Gets the value of the objId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjId() {
        return objId;
    }

    /**
     * Sets the value of the objId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjId(String value) {
        this.objId = value;
    }

    /**
     * Gets the value of the legNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegNum() {
        return legNum;
    }

    /**
     * Sets the value of the legNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegNum(String value) {
        this.legNum = value;
    }

    /**
     * Gets the value of the seqNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqNum() {
        return seqNum;
    }

    /**
     * Sets the value of the seqNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqNum(String value) {
        this.seqNum = value;
    }

    /**
     * Gets the value of the bulkCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBulkCount() {
        return bulkCount;
    }

    /**
     * Sets the value of the bulkCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBulkCount(String value) {
        this.bulkCount = value;
    }

    /**
     * Gets the value of the podsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodsId() {
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
    public void setPodsId(String value) {
        this.podsId = value;
    }
    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
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
     * Gets the value of the debitCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitCountry() {
        return debitCountry;
    }

    /**
     * Sets the value of the debitCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitCountry(String value) {
        this.debitCountry = value;
    }

    /**
     * Gets the value of the debitCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitCurrency() {
        return debitCurrency;
    }

    /**
     * Sets the value of the debitCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitCurrency(String value) {
        this.debitCurrency = value;
    }

    /**
     * Gets the value of the debitAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitAccountId() {
        return debitAccountId;
    }

    /**
     * Sets the value of the debitAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitAccountId(String value) {
        this.debitAccountId = value;
    }

    /**
     * Gets the value of the debitAccountGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitAccountGroup() {
        return debitAccountGroup;
    }

    /**
     * Sets the value of the debitAccountGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitAccountGroup(String value) {
        this.debitAccountGroup = value;
    }

    /**
     * Gets the value of the debitAccountBSB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitAccountBSB() {
        return debitAccountBSB;
    }
    
    /**
     * Sets the value of the debitAccountBSB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitAccountBSB(String value) {
        this.debitAccountBSB = value;
    }
    
    /**
     * Gets the value of the debitCostCentre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitCostCentre() {
        return debitCostCentre;
    }

    /**
     * Sets the value of the debitCostCentre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitCostCentre(String value) {
        this.debitCostCentre = value;
    }

    /**
     * Gets the value of the debitAccountSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitAccountSys() {
        return debitAccountSys;
    }

    /**
     * Sets the value of the debitAccountSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitAccountSys(String value) {
        this.debitAccountSys = value;
    }

    /**
     * Gets the value of the debitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitAmount() {
        return debitAmount;
    }

    /**
     * Sets the value of the debitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitAmount(String value) {
        this.debitAmount = value;
    }

    /**
     * Gets the value of the debitNarrative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitNarrative() {
        return debitNarrative;
    }

    /**
     * Sets the value of the debitNarrative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitNarrative(String value) {
        this.debitNarrative = value;
    }

    /**
     * Gets the value of the debitASTM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitASTM() {
        return debitASTM;
    }

    /**
     * Sets the value of the debitASTM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitASTM(String value) {
        this.debitASTM = value;
    }

    /**
     * Gets the value of the debitAggregateInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitAggregateInd() {
        return debitAggregateInd;
    }

    /**
     * Sets the value of the debitAggregateInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitAggregateInd(String value) {
        this.debitAggregateInd = value;
    }

    /**
     * Gets the value of the creditCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCountry() {
        return creditCountry;
    }

    /**
     * Sets the value of the creditCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCountry(String value) {
        this.creditCountry = value;
    }

    /**
     * Gets the value of the creditCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCurrency() {
        return creditCurrency;
    }

    /**
     * Sets the value of the creditCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCurrency(String value) {
        this.creditCurrency = value;
    }

    /**
     * Gets the value of the creditAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAccountId() {
        return creditAccountId;
    }

    /**
     * Sets the value of the creditAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAccountId(String value) {
        this.creditAccountId = value;
    }

    /**
     * Gets the value of the creditAccountGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAccountGroup() {
        return creditAccountGroup;
    }

    /**
     * Sets the value of the creditAccountGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAccountGroup(String value) {
        this.creditAccountGroup = value;
    }

    /**
     * Gets the value of the creditAccountBSB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAccountBSB() {
        return creditAccountBSB;
    }

    /**
     * Sets the value of the creditAccountBSB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAccountBSB(String value) {
        this.creditAccountBSB = value;
    }

    /**
     * Gets the value of the creditCostCentre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCostCentre() {
        return creditCostCentre;
    }

    /**
     * Sets the value of the creditCostCentre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCostCentre(String value) {
        this.creditCostCentre = value;
    }

    /**
     * Gets the value of the creditAccountSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAccountSys() {
        return creditAccountSys;
    }

    /**
     * Sets the value of the creditAccountSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAccountSys(String value) {
        this.creditAccountSys = value;
    }

    /**
     * Gets the value of the creditAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAmount() {
        return creditAmount;
    }

    /**
     * Sets the value of the creditAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAmount(String value) {
        this.creditAmount = value;
    }

    /**
     * Gets the value of the creditNarrative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditNarrative() {
        return creditNarrative;
    }

    /**
     * Sets the value of the creditNarrative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditNarrative(String value) {
        this.creditNarrative = value;
    }

    /**
     * Gets the value of the creditASTM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditASTM() {
        return creditASTM;
    }

    /**
     * Sets the value of the creditASTM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditASTM(String value) {
        this.creditASTM = value;
    }

    /**
     * Gets the value of the creditAggregateInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAggregateInd() {
        return creditAggregateInd;
    }

    /**
     * Sets the value of the creditAggregateInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAggregateInd(String value) {
        this.creditAggregateInd = value;
    }

   /**
     * Gets the value of the destinationASTM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationASTM() {
        return destinationASTM;
    }

    /**
     * Sets the value of the destinationASTM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationASTM(String value) {
        this.destinationASTM = value;
    }

    /**
     * Gets the value of the miscAttribute1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscAttribute1() {
        return miscAttribute1;
    }

    /**
     * Sets the value of the miscAttribute1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscAttribute1(String value) {
        this.miscAttribute1 = value;
    }

    /**
     * Gets the value of the miscAttribute2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscAttribute2() {
        return miscAttribute2;
    }

    /**
     * Sets the value of the miscAttribute2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscAttribute2(String value) {
        this.miscAttribute2 = value;
    }

    /**
     * Gets the value of the miscAttribute3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscAttribute3() {
        return miscAttribute3;
    }

    /**
     * Sets the value of the miscAttribute3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscAttribute3(String value) {
        this.miscAttribute3 = value;
    }

    /**
     * Gets the value of the miscAttribute4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiscAttribute4() {
        return miscAttribute4;
    }

    /**
     * Sets the value of the miscAttribute4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiscAttribute4(String value) {
        this.miscAttribute4 = value;
    }

}