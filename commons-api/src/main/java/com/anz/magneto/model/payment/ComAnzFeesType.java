
package com.anz.magneto.model.payment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.Fees_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.Fees_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="Consolidate" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="From" type="{http://schema.cmo.anz/PmtAdd}com.anz.From_Type" minOccurs="0"/>
 *         &lt;element name="To" type="{http://schema.cmo.anz/PmtAdd}com.anz.To_Type" minOccurs="0"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Type" minOccurs="0"/>
 *         &lt;element name="AcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="AcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="AcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="CostCentre" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Ccy" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="Amt" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="Narrative" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="Commission" type="{http://schema.cmo.anz/PmtAdd}com.anz.Commission_Type" minOccurs="0"/>
 *         &lt;element name="ChargeBearer" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://schema.cmo.anz/PmtAdd}string3">
 *               &lt;enumeration value="OUR"/>
 *               &lt;enumeration value="BEN"/>
 *               &lt;enumeration value="SHA"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="FeeType" type="{http://schema.cmo.anz/PmtAdd}com.anz.FeeType_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Fees_Type", propOrder = {
    "itemId",
    "consolidate",
    "from",
    "to",
    "fx",
    "acctId",
    "acctSys",
    "acctGrp",
    "branch",
    "costCentre",
    "ccy",
    "amt",
    "narrative",
    "commission",
    "chargeBearer",
    "curCode",
    "feeType"
})
public class ComAnzFeesType {

    @XmlElement(name = "ItemId")
    protected String itemId;
    @XmlElement(name = "Consolidate")
    protected String consolidate;
    @XmlElement(name = "From")
    protected ComAnzFromType from;
    @XmlElement(name = "To")
    protected ComAnzToType to;
    @XmlElement(name = "FX")
    protected ComAnzFXType fx;
    @XmlElement(name = "AcctId")
    protected String acctId;
    @XmlElement(name = "AcctSys")
    protected String acctSys;
    @XmlElement(name = "AcctGrp")
    protected String acctGrp;
    @XmlElement(name = "Branch")
    protected String branch;
    @XmlElement(name = "CostCentre")
    protected String costCentre;
    @XmlElement(name = "Ccy")
    protected String ccy;
    @XmlElement(name = "Amt")
    protected String amt;
    @XmlElement(name = "Narrative")
    protected String narrative;
    @XmlElement(name = "Commission")
    protected ComAnzCommissionType commission;
    @XmlElement(name = "ChargeBearer")
    protected String chargeBearer;
    @XmlElement(name = "CurCode")
    protected String curCode;
    @XmlElement(name = "FeeType")
    protected List<ComAnzFeeTypeType> feeType;

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
     * Gets the value of the consolidate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsolidate() {
        return consolidate;
    }

    /**
     * Sets the value of the consolidate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsolidate(String value) {
        this.consolidate = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzFromType }
     *     
     */
    public ComAnzFromType getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzFromType }
     *     
     */
    public void setFrom(ComAnzFromType value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzToType }
     *     
     */
    public ComAnzToType getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzToType }
     *     
     */
    public void setTo(ComAnzToType value) {
        this.to = value;
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
     * Gets the value of the acctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * Sets the value of the acctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctId(String value) {
        this.acctId = value;
    }

    /**
     * Gets the value of the acctSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSys() {
        return acctSys;
    }

    /**
     * Sets the value of the acctSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSys(String value) {
        this.acctSys = value;
    }

    /**
     * Gets the value of the acctGrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctGrp() {
        return acctGrp;
    }

    /**
     * Sets the value of the acctGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctGrp(String value) {
        this.acctGrp = value;
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
     * Gets the value of the costCentre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostCentre() {
        return costCentre;
    }

    /**
     * Sets the value of the costCentre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostCentre(String value) {
        this.costCentre = value;
    }

    /**
     * Gets the value of the ccy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * Sets the value of the ccy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcy(String value) {
        this.ccy = value;
    }

    /**
     * Gets the value of the amt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmt() {
        return amt;
    }

    /**
     * Sets the value of the amt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmt(String value) {
        this.amt = value;
    }

    /**
     * Gets the value of the narrative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNarrative() {
        return narrative;
    }

    /**
     * Sets the value of the narrative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNarrative(String value) {
        this.narrative = value;
    }

    /**
     * Gets the value of the commission property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzCommissionType }
     *     
     */
    public ComAnzCommissionType getCommission() {
        return commission;
    }

    /**
     * Sets the value of the commission property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzCommissionType }
     *     
     */
    public void setCommission(ComAnzCommissionType value) {
        this.commission = value;
    }

    /**
     * Gets the value of the chargeBearer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeBearer() {
        return chargeBearer;
    }

    /**
     * Sets the value of the chargeBearer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeBearer(String value) {
        this.chargeBearer = value;
    }

    /**
     * Gets the value of the curCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurCode() {
        return curCode;
    }

    /**
     * Sets the value of the curCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurCode(String value) {
        this.curCode = value;
    }

    /**
     * Gets the value of the feeType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the feeType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeeType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzFeeTypeType }
     * 
     * 
     */
    public List<ComAnzFeeTypeType> getFeeType() {
        if (feeType == null) {
            feeType = new ArrayList<ComAnzFeeTypeType>();
        }
        return this.feeType;
    }

}
