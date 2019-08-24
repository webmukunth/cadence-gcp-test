package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


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
@Data
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

}
