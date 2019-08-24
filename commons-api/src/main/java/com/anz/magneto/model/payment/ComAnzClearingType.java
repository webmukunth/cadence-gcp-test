package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;


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
@Data
public class ComAnzClearingType {

  @XmlElement(name = "ClearPref")
  protected String clearPref;

  @XmlElement(name = "ClearSubPref")
  protected String clearSubPref;

  @XmlElement(name = "ClearVersion")
  protected String clearVersion;

  @XmlElement(name = "Date")
  @XmlSchemaType(name = "date")
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
  @Getter(AccessLevel.NONE)
  protected List<ComAnzMsgType> message;

  @XmlElement(name = "RegReporting")
  protected String regReporting;

  @XmlElement(name = "RegReporting-1")
  protected String regReporting1;

  @XmlElement(name = "RegReporting-2")
  protected String regReporting2;

  @XmlElement(name = "RegReporting-3")
  protected String regReporting3;
}
