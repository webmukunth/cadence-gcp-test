package com.anz.temporal.commons.model.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Wither;


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
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzToAcctType {

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

  @XmlElement(name = "Location")
  String location;

  @XmlElement(name = "Name")
  String name;

  @XmlElement(name = "CurCode", required = true)
  String curCode;

  @XmlElement(name = "Alias")
  String alias;

  @XmlElement(name = "AliasType")
  String aliasType;

  @XmlElement(name = "AliasName")
  String aliasName;

  @XmlElement(name = "AliasLegalName")
  String aliasLegalName;

  @XmlElement(name = "AliasCreationDate")
  @XmlSchemaType(name = "date")
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
  XMLGregorianCalendar aliasCreationDate;

  @XmlElement(name = "AliasMaintDate")
  @XmlSchemaType(name = "date")
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
  XMLGregorianCalendar aliasMaintDate;

  @XmlElement(name = "Amount", required = true)
  BigDecimal amount;

  @XmlElement(name = "Narrative")
  String narrative;

  @XmlElement(name = "OwnAccount")
  String ownAccount;

  @XmlElement(name = "AcctUse")
  String acctUse;

  @XmlElement(name = "RestrictMeth")
  String restrictMeth;

  @XmlElement(name = "PmtAuthMethod")
  String pmtAuthMethod;

  @XmlElement(name = "AutoException")
  String autoException;

}
