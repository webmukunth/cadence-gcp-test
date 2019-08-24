
package com.anz.magneto.model.payment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.PmtAddRq_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.PmtAddRq_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RqUID" type="{http://schema.cmo.anz/PmtAdd}string36"/>
 *         &lt;element name="ClientInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MsgHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.Req_MsgHdr_Type"/>
 *         &lt;element name="Security" type="{http://schema.cmo.anz/PmtAdd}com.anz.Security_Type" minOccurs="0"/>
 *         &lt;element name="PayHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.PayHdr_Type"/>
 *         &lt;element name="FromFIData" type="{http://schema.cmo.anz/PmtAdd}com.anz.FromFIData_Type"/>
 *         &lt;element name="FromCust" type="{http://schema.cmo.anz/PmtAdd}com.anz.FromCust_Type" minOccurs="0"/>
 *         &lt;element name="FromAcct" type="{http://schema.cmo.anz/PmtAdd}com.anz.FromAcct_Type"/>
 *         &lt;element name="Lien" type="{http://schema.cmo.anz/PmtAdd}com.anz.Lien_Type" minOccurs="0"/>
 *         &lt;element name="ToFIData" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToFIData_Type"/>
 *         &lt;element name="Clearing" type="{http://schema.cmo.anz/PmtAdd}com.anz.Clearing_Type" minOccurs="0"/>
 *         &lt;element name="ToBene" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToBene_Type" minOccurs="0"/>
 *         &lt;element name="ToAcct" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToAcct_Type"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Fees" type="{http://schema.cmo.anz/PmtAdd}com.anz.Fees_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PmtAddRq_Type", propOrder = {
    "rqUID",
    "clientInfo",
    "msgHdr",
    "security",
    "payHdr",
    "fromFIData",
    "fromCust",
    "fromAcct",
    "lien",
    "toFIData",
    "clearing",
    "toBene",
    "toAcct",
    "fx",
    "fees"
})
public class ComAnzPmtAddRqType {

    @XmlElement(name = "RqUID", required = true)
    protected String rqUID;
    @XmlElement(name = "ClientInfo")
    protected String clientInfo;
    @XmlElement(name = "MsgHdr", required = true)
    protected ComAnzReqMsgHdrType msgHdr;
    @XmlElement(name = "Security")
    protected ComAnzSecurityType security;
    @XmlElement(name = "PayHdr", required = true)
    protected ComAnzPayHdrType payHdr;
    @XmlElement(name = "FromFIData", required = true)
    protected ComAnzFromFIDataType fromFIData;
    @XmlElement(name = "FromCust")
    protected ComAnzFromCustType fromCust;
    @XmlElement(name = "FromAcct", required = true)
    protected ComAnzFromAcctType fromAcct;
    @XmlElement(name = "Lien")
    protected ComAnzLienType lien;
    @XmlElement(name = "ToFIData", required = true)
    protected ComAnzToFIDataType toFIData;
    @XmlElement(name = "Clearing")
    protected ComAnzClearingType clearing;
    @XmlElement(name = "ToBene")
    protected ComAnzToBeneType toBene;
    @XmlElement(name = "ToAcct", required = true)
    protected ComAnzToAcctType toAcct;
    @XmlElement(name = "FX")
    protected List<ComAnzFXType> fx;
    @XmlElement(name = "Fees")
    protected List<ComAnzFeesType> fees;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the rqUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqUID() {
        return rqUID;
    }

    /**
     * Sets the value of the rqUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqUID(String value) {
        this.rqUID = value;
    }

    /**
     * Gets the value of the clientInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientInfo() {
        return clientInfo;
    }

    /**
     * Sets the value of the clientInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientInfo(String value) {
        this.clientInfo = value;
    }

    /**
     * Gets the value of the msgHdr property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzReqMsgHdrType }
     *     
     */
    public ComAnzReqMsgHdrType getMsgHdr() {
        return msgHdr;
    }

    /**
     * Sets the value of the msgHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzReqMsgHdrType }
     *     
     */
    public void setMsgHdr(ComAnzReqMsgHdrType value) {
        this.msgHdr = value;
    }

    /**
     * Gets the value of the security property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzSecurityType }
     *     
     */
    public ComAnzSecurityType getSecurity() {
        return security;
    }

    /**
     * Sets the value of the security property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzSecurityType }
     *     
     */
    public void setSecurity(ComAnzSecurityType value) {
        this.security = value;
    }

    /**
     * Gets the value of the payHdr property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzPayHdrType }
     *     
     */
    public ComAnzPayHdrType getPayHdr() {
        return payHdr;
    }

    /**
     * Sets the value of the payHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzPayHdrType }
     *     
     */
    public void setPayHdr(ComAnzPayHdrType value) {
        this.payHdr = value;
    }

    /**
     * Gets the value of the fromFIData property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzFromFIDataType }
     *     
     */
    public ComAnzFromFIDataType getFromFIData() {
        return fromFIData;
    }

    /**
     * Sets the value of the fromFIData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzFromFIDataType }
     *     
     */
    public void setFromFIData(ComAnzFromFIDataType value) {
        this.fromFIData = value;
    }

    /**
     * Gets the value of the fromCust property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzFromCustType }
     *     
     */
    public ComAnzFromCustType getFromCust() {
        return fromCust;
    }

    /**
     * Sets the value of the fromCust property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzFromCustType }
     *     
     */
    public void setFromCust(ComAnzFromCustType value) {
        this.fromCust = value;
    }

    /**
     * Gets the value of the fromAcct property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzFromAcctType }
     *     
     */
    public ComAnzFromAcctType getFromAcct() {
        return fromAcct;
    }

    /**
     * Sets the value of the fromAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzFromAcctType }
     *     
     */
    public void setFromAcct(ComAnzFromAcctType value) {
        this.fromAcct = value;
    }

    /**
     * Gets the value of the lien property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzLienType }
     *     
     */
    public ComAnzLienType getLien() {
        return lien;
    }

    /**
     * Sets the value of the lien property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzLienType }
     *     
     */
    public void setLien(ComAnzLienType value) {
        this.lien = value;
    }

    /**
     * Gets the value of the toFIData property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzToFIDataType }
     *     
     */
    public ComAnzToFIDataType getToFIData() {
        return toFIData;
    }

    /**
     * Sets the value of the toFIData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzToFIDataType }
     *     
     */
    public void setToFIData(ComAnzToFIDataType value) {
        this.toFIData = value;
    }

    /**
     * Gets the value of the clearing property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzClearingType }
     *     
     */
    public ComAnzClearingType getClearing() {
        return clearing;
    }

    /**
     * Sets the value of the clearing property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzClearingType }
     *     
     */
    public void setClearing(ComAnzClearingType value) {
        this.clearing = value;
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
     * {@link ComAnzFXType }
     * 
     * 
     */
    public List<ComAnzFXType> getFX() {
        if (fx == null) {
            fx = new ArrayList<ComAnzFXType>();
        }
        return this.fx;
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

}
