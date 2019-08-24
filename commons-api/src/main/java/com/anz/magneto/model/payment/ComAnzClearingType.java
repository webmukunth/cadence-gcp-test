
package com.anz.magneto.model.payment;

import com.anz.psp.commons.model.format.DateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com.anz.Clearing_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com.anz.Clearing_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClearPref" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ClearSubPref" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ClearVersion" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="PurposeCode" type="{http://schema.cmo.anz/PmtAdd}string30" minOccurs="0"/>
 *         &lt;element name="PurposeText" type="{http://schema.cmo.anz/PmtAdd}string350" minOccurs="0"/>
 *         &lt;element name="InstructionCode" type="{http://schema.cmo.anz/PmtAdd}string30" minOccurs="0"/>
 *         &lt;element name="Instruction" type="{http://schema.cmo.anz/PmtAdd}com.anz.Instruction_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReturnsAcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="ReturnsBranch" type="{http://schema.cmo.anz/PmtAdd}string30" minOccurs="0"/>
 *         &lt;element name="ReturnsAcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="ReturnsAcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="ReturnsCostCentre" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="Reference" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ClearingId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="ClearingName" type="{http://schema.cmo.anz/PmtAdd}string40" minOccurs="0"/>
 *         &lt;element name="TaxType" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="TaxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ChqDeliveryMeth" type="{http://schema.cmo.anz/PmtAdd}string2" minOccurs="0"/>
 *         &lt;element name="ChqPayLocation" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="ChqPrintLocation" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="ChargeBearer" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="BENFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BENAcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="BENAcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="BENAcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="BENBranch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="BENCostCentre" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="BENNarrative" type="{http://schema.cmo.anz/PmtAdd}string200" minOccurs="0"/>
 *         &lt;element name="OtherBank" type="{http://schema.cmo.anz/PmtAdd}com.anz.OtherBank_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://schema.cmo.anz/PmtAdd}com.anz.Msg_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RegReporting" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="RegReporting-1" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="RegReporting-2" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="RegReporting-3" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Clearing_Type", propOrder = {
    "clearPref",
    "clearSubPref",
    "clearVersion",
    "date",
    "purposeCode",
    "purposeText",
    "instructionCode",
    "instruction",
    "returnsAcctId",
    "returnsBranch",
    "returnsAcctSys",
    "returnsAcctGrp",
    "returnsCostCentre",
    "reference",
    "name",
    "clearingId",
    "clearingName",
    "taxType",
    "taxAmount",
    "chqDeliveryMeth",
    "chqPayLocation",
    "chqPrintLocation",
    "chargeBearer",
    "benFee",
    "benAcctId",
    "benAcctSys",
    "benAcctGrp",
    "benBranch",
    "benCostCentre",
    "benNarrative",
    "otherBank",
    "message",
    "regReporting",
    "regReporting1",
    "regReporting2",
    "regReporting3"
})
public class ComAnzClearingType {

    @XmlElement(name = "ClearPref")
    protected String clearPref;
    @XmlElement(name = "ClearSubPref")
    protected String clearSubPref;
    @XmlElement(name = "ClearVersion")
    protected String clearVersion;
    @XmlElement(name = "Date")
    @XmlSchemaType(name = "date")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_FORMAT)
    protected XMLGregorianCalendar date;
    @XmlElement(name = "PurposeCode")
    protected String purposeCode;
    @XmlElement(name = "PurposeText")
    protected String purposeText;
    @XmlElement(name = "InstructionCode")
    protected String instructionCode;
    @XmlElement(name = "Instruction")
    protected List<ComAnzInstructionType> instruction;
    @XmlElement(name = "ReturnsAcctId")
    protected String returnsAcctId;
    @XmlElement(name = "ReturnsBranch")
    protected String returnsBranch;
    @XmlElement(name = "ReturnsAcctSys")
    protected String returnsAcctSys;
    @XmlElement(name = "ReturnsAcctGrp")
    protected String returnsAcctGrp;
    @XmlElement(name = "ReturnsCostCentre")
    protected String returnsCostCentre;
    @XmlElement(name = "Reference")
    protected String reference;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "ClearingId")
    protected String clearingId;
    @XmlElement(name = "ClearingName")
    protected String clearingName;
    @XmlElement(name = "TaxType")
    protected String taxType;
    @XmlElement(name = "TaxAmount")
    protected BigDecimal taxAmount;
    @XmlElement(name = "ChqDeliveryMeth")
    protected String chqDeliveryMeth;
    @XmlElement(name = "ChqPayLocation")
    protected String chqPayLocation;
    @XmlElement(name = "ChqPrintLocation")
    protected String chqPrintLocation;
    @XmlElement(name = "ChargeBearer")
    protected String chargeBearer;
    @XmlElement(name = "BENFee")
    protected BigDecimal benFee;
    @XmlElement(name = "BENAcctId")
    protected String benAcctId;
    @XmlElement(name = "BENAcctSys")
    protected String benAcctSys;
    @XmlElement(name = "BENAcctGrp")
    protected String benAcctGrp;
    @XmlElement(name = "BENBranch")
    protected String benBranch;
    @XmlElement(name = "BENCostCentre")
    protected String benCostCentre;
    @XmlElement(name = "BENNarrative")
    protected String benNarrative;
    @XmlElement(name = "OtherBank")
    protected List<ComAnzOtherBankType> otherBank;
    @XmlElement(name = "Message")
    protected List<ComAnzMsgType> message;
    @XmlElement(name = "RegReporting")
    protected String regReporting;
    @XmlElement(name = "RegReporting-1")
    protected String regReporting1;
    @XmlElement(name = "RegReporting-2")
    protected String regReporting2;
    @XmlElement(name = "RegReporting-3")
    protected String regReporting3;

    /**
     * Gets the value of the clearPref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearPref() {
        return clearPref;
    }

    /**
     * Sets the value of the clearPref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearPref(String value) {
        this.clearPref = value;
    }

    /**
     * Gets the value of the clearSubPref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearSubPref() {
        return clearSubPref;
    }

    /**
     * Sets the value of the clearSubPref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearSubPref(String value) {
        this.clearSubPref = value;
    }

    /**
     * Gets the value of the clearVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearVersion() {
        return clearVersion;
    }

    /**
     * Sets the value of the clearVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearVersion(String value) {
        this.clearVersion = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the purposeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeCode() {
        return purposeCode;
    }

    /**
     * Sets the value of the purposeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeCode(String value) {
        this.purposeCode = value;
    }

    /**
     * Gets the value of the purposeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeText() {
        return purposeText;
    }

    /**
     * Sets the value of the purposeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeText(String value) {
        this.purposeText = value;
    }

    /**
     * Gets the value of the instructionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstructionCode() {
        return instructionCode;
    }

    /**
     * Sets the value of the instructionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstructionCode(String value) {
        this.instructionCode = value;
    }

    /**
     * Gets the value of the instruction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instruction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstruction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzInstructionType }
     * 
     * 
     */
    public List<ComAnzInstructionType> getInstruction() {
        if (instruction == null) {
            instruction = new ArrayList<ComAnzInstructionType>();
        }
        return this.instruction;
    }

    /**
     * Gets the value of the returnsAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnsAcctId() {
        return returnsAcctId;
    }

    /**
     * Sets the value of the returnsAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnsAcctId(String value) {
        this.returnsAcctId = value;
    }

    /**
     * Gets the value of the returnsBranch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnsBranch() {
        return returnsBranch;
    }

    /**
     * Sets the value of the returnsBranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnsBranch(String value) {
        this.returnsBranch = value;
    }

    /**
     * Gets the value of the returnsAcctSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnsAcctSys() {
        return returnsAcctSys;
    }

    /**
     * Sets the value of the returnsAcctSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnsAcctSys(String value) {
        this.returnsAcctSys = value;
    }

    /**
     * Gets the value of the returnsAcctGrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnsAcctGrp() {
        return returnsAcctGrp;
    }

    /**
     * Sets the value of the returnsAcctGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnsAcctGrp(String value) {
        this.returnsAcctGrp = value;
    }

    /**
     * Gets the value of the returnsCostCentre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnsCostCentre() {
        return returnsCostCentre;
    }

    /**
     * Sets the value of the returnsCostCentre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnsCostCentre(String value) {
        this.returnsCostCentre = value;
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
     * Gets the value of the clearingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearingName() {
        return clearingName;
    }

    /**
     * Sets the value of the clearingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearingName(String value) {
        this.clearingName = value;
    }

    /**
     * Gets the value of the taxType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxType() {
        return taxType;
    }

    /**
     * Sets the value of the taxType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxType(String value) {
        this.taxType = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxAmount(BigDecimal value) {
        this.taxAmount = value;
    }

    /**
     * Gets the value of the chqDeliveryMeth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChqDeliveryMeth() {
        return chqDeliveryMeth;
    }

    /**
     * Sets the value of the chqDeliveryMeth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChqDeliveryMeth(String value) {
        this.chqDeliveryMeth = value;
    }

    /**
     * Gets the value of the chqPayLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChqPayLocation() {
        return chqPayLocation;
    }

    /**
     * Sets the value of the chqPayLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChqPayLocation(String value) {
        this.chqPayLocation = value;
    }

    /**
     * Gets the value of the chqPrintLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChqPrintLocation() {
        return chqPrintLocation;
    }

    /**
     * Sets the value of the chqPrintLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChqPrintLocation(String value) {
        this.chqPrintLocation = value;
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
     * Gets the value of the benFee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBENFee() {
        return benFee;
    }

    /**
     * Sets the value of the benFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBENFee(BigDecimal value) {
        this.benFee = value;
    }

    /**
     * Gets the value of the benAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBENAcctId() {
        return benAcctId;
    }

    /**
     * Sets the value of the benAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBENAcctId(String value) {
        this.benAcctId = value;
    }

    /**
     * Gets the value of the benAcctSys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBENAcctSys() {
        return benAcctSys;
    }

    /**
     * Sets the value of the benAcctSys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBENAcctSys(String value) {
        this.benAcctSys = value;
    }

    /**
     * Gets the value of the benAcctGrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBENAcctGrp() {
        return benAcctGrp;
    }

    /**
     * Sets the value of the benAcctGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBENAcctGrp(String value) {
        this.benAcctGrp = value;
    }

    /**
     * Gets the value of the benBranch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBENBranch() {
        return benBranch;
    }

    /**
     * Sets the value of the benBranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBENBranch(String value) {
        this.benBranch = value;
    }

    /**
     * Gets the value of the benCostCentre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBENCostCentre() {
        return benCostCentre;
    }

    /**
     * Sets the value of the benCostCentre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBENCostCentre(String value) {
        this.benCostCentre = value;
    }

    /**
     * Gets the value of the benNarrative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBENNarrative() {
        return benNarrative;
    }

    /**
     * Sets the value of the benNarrative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBENNarrative(String value) {
        this.benNarrative = value;
    }

    /**
     * Gets the value of the otherBank property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherBank property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherBank().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzOtherBankType }
     * 
     * 
     */
    public List<ComAnzOtherBankType> getOtherBank() {
        if (otherBank == null) {
            otherBank = new ArrayList<ComAnzOtherBankType>();
        }
        return this.otherBank;
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
     * Gets the value of the regReporting property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegReporting() {
        return regReporting;
    }

    /**
     * Sets the value of the regReporting property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegReporting(String value) {
        this.regReporting = value;
    }

    /**
     * Gets the value of the regReporting1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegReporting1() {
        return regReporting1;
    }

    /**
     * Sets the value of the regReporting1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegReporting1(String value) {
        this.regReporting1 = value;
    }

    /**
     * Gets the value of the regReporting2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegReporting2() {
        return regReporting2;
    }

    /**
     * Sets the value of the regReporting2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegReporting2(String value) {
        this.regReporting2 = value;
    }

    /**
     * Gets the value of the regReporting3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegReporting3() {
        return regReporting3;
    }

    /**
     * Sets the value of the regReporting3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegReporting3(String value) {
        this.regReporting3 = value;
    }

}
