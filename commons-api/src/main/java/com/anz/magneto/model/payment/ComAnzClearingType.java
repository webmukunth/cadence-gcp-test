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
  String clearPref;

  @XmlElement(name = "ClearSubPref")
  String clearSubPref;

  @XmlElement(name = "ClearVersion")
  String clearVersion;

  @XmlElement(name = "Date")
  @XmlSchemaType(name = "date")
  XMLGregorianCalendar date;

  @XmlElement(name = "PurposeCode")
  String purposeCode;

  @XmlElement(name = "PurposeText")
  String purposeText;

  @XmlElement(name = "InstructionCode")
  String instructionCode;

  @XmlElement(name = "Instruction")
  List<ComAnzInstructionType> instruction;

  @XmlElement(name = "ReturnsAcctId")
  String returnsAcctId;

  @XmlElement(name = "ReturnsBranch")
  String returnsBranch;

  @XmlElement(name = "ReturnsAcctSys")
  String returnsAcctSys;

  @XmlElement(name = "ReturnsAcctGrp")
  String returnsAcctGrp;

  @XmlElement(name = "ReturnsCostCentre")
  String returnsCostCentre;

  @XmlElement(name = "Reference")
  String reference;

  @XmlElement(name = "Name")
  String name;

  @XmlElement(name = "ClearingId")
  String clearingId;

  @XmlElement(name = "ClearingName")
  String clearingName;

  @XmlElement(name = "TaxType")
  String taxType;

  @XmlElement(name = "TaxAmount")
  BigDecimal taxAmount;

  @XmlElement(name = "ChqDeliveryMeth")
  String chqDeliveryMeth;

  @XmlElement(name = "ChqPayLocation")
  String chqPayLocation;

  @XmlElement(name = "ChqPrintLocation")
  String chqPrintLocation;

  @XmlElement(name = "ChargeBearer")
  String chargeBearer;

  @XmlElement(name = "BENFee")
  BigDecimal benFee;

  @XmlElement(name = "BENAcctId")
  String benAcctId;

  @XmlElement(name = "BENAcctSys")
  String benAcctSys;

  @XmlElement(name = "BENAcctGrp")
  String benAcctGrp;

  @XmlElement(name = "BENBranch")
  String benBranch;

  @XmlElement(name = "BENCostCentre")
  String benCostCentre;

  @XmlElement(name = "BENNarrative")
  String benNarrative;

  @XmlElement(name = "OtherBank")
  List<ComAnzOtherBankType> otherBank;

  @XmlElement(name = "Message")
  @Getter(AccessLevel.NONE)
  List<ComAnzMsgType> message;

  @XmlElement(name = "RegReporting")
  String regReporting;

  @XmlElement(name = "RegReporting-1")
  String regReporting1;

  @XmlElement(name = "RegReporting-2")
  String regReporting2;

  @XmlElement(name = "RegReporting-3")
  String regReporting3;
}
