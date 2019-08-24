
package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.FromCust_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.FromCust_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string150"/>
 *         &lt;element name="ResidentStatus" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr1" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr2" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr3" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="CustClass" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="BRNorCRN" type="{http://schema.cmo.anz/PmtAdd}string25" minOccurs="0"/>
 *         &lt;element name="CRN" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="LastAuthoriser" type="{http://schema.cmo.anz/PmtAdd}string60" minOccurs="0"/>
 *         &lt;element name="ARN" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="CRNCustClass" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="ClearingId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="Reference" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ContactType" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ContactName" type="{http://schema.cmo.anz/PmtAdd}string140" minOccurs="0"/>
 *         &lt;element name="ContactAddr" type="{http://schema.cmo.anz/PmtAdd}string128" minOccurs="0"/>
 *         &lt;element name="InstructingParty" type="{http://schema.cmo.anz/PmtAdd}string140" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="LimitId" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ControlCentre" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FromCust_Type", propOrder = {
    "name",
    "residentStatus",
    "addr1",
    "addr2",
    "addr3",
    "custClass",
    "brNorCRN",
    "crn",
    "lastAuthoriser",
    "arn",
    "crnCustClass",
    "clearingId",
    "reference",
    "contactType",
    "contactName",
    "contactAddr",
    "instructingParty",
    "id",
    "limitId",
    "controlCentre"
})
public class ComAnzFromCustType {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ResidentStatus")
    protected String residentStatus;
    @XmlElement(name = "Addr1")
    protected String addr1;
    @XmlElement(name = "Addr2")
    protected String addr2;
    @XmlElement(name = "Addr3")
    protected String addr3;
    @XmlElement(name = "CustClass")
    protected String custClass;
    @XmlElement(name = "BRNorCRN")
    protected String brNorCRN;
    @XmlElement(name = "CRN")
    protected String crn;
    @XmlElement(name = "LastAuthoriser")
    protected String lastAuthoriser;
    @XmlElement(name = "ARN")
    protected String arn;
    @XmlElement(name = "CRNCustClass")
    protected String crnCustClass;
    @XmlElement(name = "ClearingId")
    protected String clearingId;
    @XmlElement(name = "Reference")
    protected String reference;
    @XmlElement(name = "ContactType")
    protected String contactType;
    @XmlElement(name = "ContactName")
    protected String contactName;
    @XmlElement(name = "ContactAddr")
    protected String contactAddr;
    @XmlElement(name = "InstructingParty")
    protected String instructingParty;
    @XmlElement(name = "Id")
    protected String id;
    @XmlElement(name = "LimitId")
    protected String limitId;
    @XmlElement(name = "ControlCentre")
    protected String controlCentre;

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
     * Gets the value of the residentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidentStatus() {
        return residentStatus;
    }

    /**
     * Sets the value of the residentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidentStatus(String value) {
        this.residentStatus = value;
    }

    /**
     * Gets the value of the addr1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * Sets the value of the addr1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr1(String value) {
        this.addr1 = value;
    }

    /**
     * Gets the value of the addr2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * Sets the value of the addr2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr2(String value) {
        this.addr2 = value;
    }

    /**
     * Gets the value of the addr3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr3() {
        return addr3;
    }

    /**
     * Sets the value of the addr3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr3(String value) {
        this.addr3 = value;
    }

    /**
     * Gets the value of the custClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustClass() {
        return custClass;
    }

    /**
     * Sets the value of the custClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustClass(String value) {
        this.custClass = value;
    }

    /**
     * Gets the value of the brNorCRN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBRNorCRN() {
        return brNorCRN;
    }

    /**
     * Sets the value of the brNorCRN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBRNorCRN(String value) {
        this.brNorCRN = value;
    }

    /**
     * Gets the value of the crn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRN() {
        return crn;
    }

    /**
     * Sets the value of the crn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRN(String value) {
        this.crn = value;
    }

    /**
     * Gets the value of the lastAuthoriser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastAuthoriser() {
        return lastAuthoriser;
    }

    /**
     * Sets the value of the lastAuthoriser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastAuthoriser(String value) {
        this.lastAuthoriser = value;
    }

    /**
     * Gets the value of the arn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getARN() {
        return arn;
    }

    /**
     * Sets the value of the arn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setARN(String value) {
        this.arn = value;
    }

    /**
     * Gets the value of the crnCustClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRNCustClass() {
        return crnCustClass;
    }

    /**
     * Sets the value of the crnCustClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRNCustClass(String value) {
        this.crnCustClass = value;
    }

    /**
     * Gets the value of the clearingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearingId() {
        return clearingId;
    }

    /**
     * Sets the value of the clearingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearingId(String value) {
        this.clearingId = value;
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
     * Gets the value of the contactType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * Sets the value of the contactType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactType(String value) {
        this.contactType = value;
    }

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactAddr() {
        return contactAddr;
    }

    /**
     * Sets the value of the contactAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactAddr(String value) {
        this.contactAddr = value;
    }

    /**
     * Gets the value of the instructingParty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstructingParty() {
        return instructingParty;
    }

    /**
     * Sets the value of the instructingParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstructingParty(String value) {
        this.instructingParty = value;
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

    /**
     * Gets the value of the limitId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitId() {
        return limitId;
    }

    /**
     * Sets the value of the limitId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitId(String value) {
        this.limitId = value;
    }

    /**
     * Gets the value of the controlCentre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlCentre() {
        return controlCentre;
    }

    /**
     * Sets the value of the controlCentre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlCentre(String value) {
        this.controlCentre = value;
    }

}
