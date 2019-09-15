package com.anz.magneto.commons.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Wither;


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
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzFeesType {

  @XmlElement(name = "ItemId")
  String itemId;

  @XmlElement(name = "Consolidate")
  String consolidate;

  @XmlElement(name = "From")
  ComAnzFromType from;

  @XmlElement(name = "To")
  ComAnzToType to;

  @XmlElement(name = "FX")
  ComAnzFXType fx;

  @XmlElement(name = "AcctId")
  String acctId;

  @XmlElement(name = "AcctSys")
  String acctSys;

  @XmlElement(name = "AcctGrp")
  String acctGrp;

  @XmlElement(name = "Branch")
  String branch;

  @XmlElement(name = "CostCentre")
  String costCentre;

  @XmlElement(name = "Ccy")
  String ccy;

  @XmlElement(name = "Amt")
  String amt;

  @XmlElement(name = "Narrative")
  String narrative;

  @XmlElement(name = "Commission")
  ComAnzCommissionType commission;

  @XmlElement(name = "ChargeBearer")
  String chargeBearer;

  @XmlElement(name = "CurCode")
  String curCode;

  @XmlElement(name = "FeeType")
  List<ComAnzFeeTypeType> feeType;
}
