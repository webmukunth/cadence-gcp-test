
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
 * <p>Java class for com.anz.ToAcct_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.ToAcct_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="AcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="AcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="CostCentre" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://schema.cmo.anz/PmtAdd}string40" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string150" minOccurs="0"/>
 *         &lt;element name="CurCode" type="{http://schema.cmo.anz/PmtAdd}string3"/>
 *         &lt;element name="Alias" type="{http://schema.cmo.anz/PmtAdd}string2048" minOccurs="0"/>
 *         &lt;element name="AliasType" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="AliasName" type="{http://schema.cmo.anz/PmtAdd}string140" minOccurs="0"/>
 *         &lt;element name="AliasLegalName" type="{http://schema.cmo.anz/PmtAdd}string140" minOccurs="0"/>
 *         &lt;element name="AliasCreationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="AliasMaintDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Narrative" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="OwnAccount" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="AcctUse" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="RestrictMeth" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="PmtAuthMethod" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://schema.cmo.anz/PmtAdd}string20">
 *               &lt;enumeration value="AFPONLY"/>
 *               &lt;enumeration value="FORCEDEBIT"/>
 *               &lt;enumeration value="LIMITONLY"/>
 *               &lt;enumeration value="AFPTHENLIMIT"/>
 *               &lt;enumeration value="FORCEDEBITLIMIT"/>
 *               &lt;enumeration value="AFPPLUSLIMIT"/>
 *               &lt;enumeration value="GROUPLIMIT"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AutoException" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.ToAcct_Type", propOrder = {
    "acctId",
    "acctSys",
    "acctGrp",
    "branch",
    "costCentre",
    "location",
    "name",
    "curCode",
    "alias",
    "aliasType",
    "aliasName",
    "aliasLegalName",
    "aliasCreationDate",
    "aliasMaintDate",
    "amount",
    "narrative",
    "ownAccount",
    "acctUse",
    "restrictMeth",
    "pmtAuthMethod",
    "autoException"
})
public class ComAnzToAcctType {

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
    @XmlElement(name = "Location")
    protected String location;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "CurCode", required = true)
    protected String curCode;
    @XmlElement(name = "Alias")
    protected String alias;
    @XmlElement(name = "AliasType")
    protected String aliasType;
    @XmlElement(name = "AliasName")
    protected String aliasName;
    @XmlElement(name = "AliasLegalName")
    protected String aliasLegalName;
    @XmlElement(name = "AliasCreationDate")
    @XmlSchemaType(name = "date")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_FORMAT)
    protected XMLGregorianCalendar aliasCreationDate;
    @XmlElement(name = "AliasMaintDate")
    @XmlSchemaType(name = "date")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_FORMAT)
    protected XMLGregorianCalendar aliasMaintDate;
    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "Narrative")
    protected String narrative;
    @XmlElement(name = "OwnAccount")
    protected String ownAccount;
    @XmlElement(name = "AcctUse")
    protected String acctUse;
    @XmlElement(name = "RestrictMeth")
    protected String restrictMeth;
    @XmlElement(name = "PmtAuthMethod")
    protected String pmtAuthMethod;
    @XmlElement(name = "AutoException")
    protected String autoException;

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
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Gets the value of the aliasType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasType() {
        return aliasType;
    }

    /**
     * Sets the value of the aliasType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasType(String value) {
        this.aliasType = value;
    }

    /**
     * Gets the value of the aliasName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * Sets the value of the aliasName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasName(String value) {
        this.aliasName = value;
    }

    /**
     * Gets the value of the aliasLegalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasLegalName() {
        return aliasLegalName;
    }

    /**
     * Sets the value of the aliasLegalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasLegalName(String value) {
        this.aliasLegalName = value;
    }

    /**
     * Gets the value of the aliasCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAliasCreationDate() {
        return aliasCreationDate;
    }

    /**
     * Sets the value of the aliasCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAliasCreationDate(XMLGregorianCalendar value) {
        this.aliasCreationDate = value;
    }

    /**
     * Gets the value of the aliasMaintDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAliasMaintDate() {
        return aliasMaintDate;
    }

    /**
     * Sets the value of the aliasMaintDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAliasMaintDate(XMLGregorianCalendar value) {
        this.aliasMaintDate = value;
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
     * Gets the value of the ownAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnAccount() {
        return ownAccount;
    }

    /**
     * Sets the value of the ownAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnAccount(String value) {
        this.ownAccount = value;
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

}
