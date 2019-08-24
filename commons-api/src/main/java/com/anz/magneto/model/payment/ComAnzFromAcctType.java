
package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.FromAcct_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.FromAcct_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcctId" type="{http://schema.cmo.anz/PmtAdd}string34"/>
 *         &lt;element name="AcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="AcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="CostCentre" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://schema.cmo.anz/PmtAdd}string40" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string150" minOccurs="0"/>
 *         &lt;element name="Linkage" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="PmtAuthMethod" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://schema.cmo.anz/PmtAdd}string20">
 *               &lt;enumeration value="AFPONLY"/>
 *               &lt;enumeration value="FORCEDEBIT"/>
 *               &lt;enumeration value="LIMITONLY"/>
 *               &lt;enumeration value="AFPTHENLIMIT"/>
 *               &lt;enumeration value="FORCEDEBITLIMIT"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AutoException" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="Narrative" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="CurCode" type="{http://schema.cmo.anz/PmtAdd}string3"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="AcctUse" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="RestrictMeth" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Reference" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="OrigAcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="OrigAcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="OrigAcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="OrigAcctCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FromAcct_Type", propOrder = {
    "acctId",
    "acctSys",
    "acctGrp",
    "branch",
    "costCentre",
    "location",
    "name",
    "linkage",
    "pmtAuthMethod",
    "autoException",
    "narrative",
    "curCode",
    "amount",
    "acctUse",
    "restrictMeth",
    "reference",
    "origAcctId",
    "origAcctSys",
    "origAcctGrp",
    "origAcctCurCode"
})
public class ComAnzFromAcctType {

    @XmlElement(name = "AcctId", required = true)
    protected String acctId;
    @XmlElement(name = "AcctSys")
    protected String acctSys;
    @XmlElement(name = "AcctGrp")
    protected String acctGrp;
    @XmlElement(name = "Branch")
    protected String branch;
    @XmlElement(name = "CostCentre")
    protected String costCentre;
    @XmlElement(name = "Location")
    protected String location;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Linkage")
    protected String linkage;
    @XmlElement(name = "PmtAuthMethod")
    protected String pmtAuthMethod;
    @XmlElement(name = "AutoException")
    protected String autoException;
    @XmlElement(name = "Narrative")
    protected String narrative;
    @XmlElement(name = "CurCode", required = true)
    protected String curCode;
    @XmlElement(name = "Amount")
    protected BigDecimal amount;
    @XmlElement(name = "AcctUse")
    protected String acctUse;
    @XmlElement(name = "RestrictMeth")
    protected String restrictMeth;
    @XmlElement(name = "Reference")
    protected String reference;
    @XmlElement(name = "OrigAcctId")
    protected String origAcctId;
    @XmlElement(name = "OrigAcctSys")
    protected String origAcctSys;
    @XmlElement(name = "OrigAcctGrp")
    protected String origAcctGrp;
    @XmlElement(name = "OrigAcctCurCode")
    protected String origAcctCurCode;

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
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
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
     * Gets the value of the linkage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkage() {
        return linkage;
    }

    /**
     * Sets the value of the linkage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkage(String value) {
        this.linkage = value;
    }

    /**
     * Gets the value of the pmtAuthMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmtAuthMethod() {
        return pmtAuthMethod;
    }

    /**
     * Sets the value of the pmtAuthMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmtAuthMethod(String value) {
        this.pmtAuthMethod = value;
    }

    /**
     * Gets the value of the autoException property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoException() {
        return autoException;
    }

    /**
     * Sets the value of the autoException property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoException(String value) {
        this.autoException = value;
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
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the acctUse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctUse() {
        return acctUse;
    }

    /**
     * Sets the value of the acctUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctUse(String value) {
        this.acctUse = value;
    }

    /**
     * Gets the value of the restrictMeth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestrictMeth() {
        return restrictMeth;
    }

    /**
     * Sets the value of the restrictMeth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestrictMeth(String value) {
        this.restrictMeth = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the origAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigAcctId() {
        return origAcctId;
    }

    /**
     * Sets the value of the origAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigAcctId(String value) {
        this.origAcctId = value;
    }

    /**
     * Gets the value of the origAcctSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigAcctSys() {
        return origAcctSys;
    }

    /**
     * Sets the value of the origAcctSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigAcctSys(String value) {
        this.origAcctSys = value;
    }

    /**
     * Gets the value of the origAcctGrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigAcctGrp() {
        return origAcctGrp;
    }

    /**
     * Sets the value of the origAcctGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigAcctGrp(String value) {
        this.origAcctGrp = value;
    }

    /**
     * Gets the value of the origAcctCurCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigAcctCurCode() {
        return origAcctCurCode;
    }

    /**
     * Sets the value of the origAcctCurCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigAcctCurCode(String value) {
        this.origAcctCurCode = value;
    }

}
