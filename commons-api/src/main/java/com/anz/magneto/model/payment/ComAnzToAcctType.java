package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Data;


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
  protected XMLGregorianCalendar aliasCreationDate;

  @XmlElement(name = "AliasMaintDate")
  @XmlSchemaType(name = "date")
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

}
