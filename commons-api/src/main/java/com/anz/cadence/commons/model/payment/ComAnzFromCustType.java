package com.anz.cadence.commons.model.payment;

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
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzFromCustType {

  @XmlElement(name = "Name", required = true)
  String name;

  @XmlElement(name = "ResidentStatus")
  String residentStatus;

  @XmlElement(name = "Addr1")
  String addr1;

  @XmlElement(name = "Addr2")
  String addr2;

  @XmlElement(name = "Addr3")
  String addr3;

  @XmlElement(name = "CustClass")
  String custClass;

  @XmlElement(name = "BRNorCRN")
  String brNorCRN;

  @XmlElement(name = "CRN")
  String crn;

  @XmlElement(name = "LastAuthoriser")
  String lastAuthoriser;

  @XmlElement(name = "ARN")
  String arn;

  @XmlElement(name = "CRNCustClass")
  String crnCustClass;

  @XmlElement(name = "ClearingId")
  String clearingId;

  @XmlElement(name = "Reference")
  String reference;

  @XmlElement(name = "ContactType")
  String contactType;

  @XmlElement(name = "ContactName")
  String contactName;

  @XmlElement(name = "ContactAddr")
  String contactAddr;

  @XmlElement(name = "InstructingParty")
  String instructingParty;

  @XmlElement(name = "Id")
  String id;

  @XmlElement(name = "LimitId")
  String limitId;

  @XmlElement(name = "ControlCentre")
  String controlCentre;

  /**
   * Gets the value of the brNorCRN property.
   *
   * @return possible object is {@link String }
   */
  public String getBRNorCRN() {
    return brNorCRN;
  }

  /**
   * Sets the value of the brNorCRN property.
   *
   * @param value allowed object is {@link String }
   */
  public void setBRNorCRN(String value) {
    this.brNorCRN = value;
  }

  /**
   * Gets the value of the crn property.
   *
   * @return possible object is {@link String }
   */
  public String getCRN() {
    return crn;
  }

  /**
   * Sets the value of the crn property.
   *
   * @param value allowed object is {@link String }
   */
  public void setCRN(String value) {
    this.crn = value;
  }

  /**
   * Gets the value of the arn property.
   *
   * @return possible object is {@link String }
   */
  public String getARN() {
    return arn;
  }

  /**
   * Sets the value of the arn property.
   *
   * @param value allowed object is {@link String }
   */
  public void setARN(String value) {
    this.arn = value;
  }

  /**
   * Gets the value of the crnCustClass property.
   *
   * @return possible object is {@link String }
   */
  public String getCRNCustClass() {
    return crnCustClass;
  }

  /**
   * Sets the value of the crnCustClass property.
   *
   * @param value allowed object is {@link String }
   */
  public void setCRNCustClass(String value) {
    this.crnCustClass = value;
  }

}
