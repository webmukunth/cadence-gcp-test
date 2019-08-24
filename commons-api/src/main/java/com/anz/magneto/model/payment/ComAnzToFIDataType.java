package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.ToFIData_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.ToFIData_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{http://schema.cmo.anz/PmtAdd}string2"/>
 *         &lt;element name="BIC" type="{http://schema.cmo.anz/PmtAdd}string11" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr1" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr2" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr3" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec1" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec2" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec3" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec4" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec5" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="SenderRec6" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.ToFIData_Type", propOrder = {
    "country",
    "bic",
    "branch",
    "name",
    "addr1",
    "addr2",
    "addr3",
    "senderRec1",
    "senderRec2",
    "senderRec3",
    "senderRec4",
    "senderRec5",
    "senderRec6"
})
@Data
public class ComAnzToFIDataType {

  @XmlElement(name = "Country", required = true)
  protected String country;

  @XmlElement(name = "BIC")
  protected String bic;

  @XmlElement(name = "Branch")
  protected String branch;

  @XmlElement(name = "Name")
  protected String name;

  @XmlElement(name = "Addr1")
  protected String addr1;

  @XmlElement(name = "Addr2")
  protected String addr2;

  @XmlElement(name = "Addr3")
  protected String addr3;

  @XmlElement(name = "SenderRec1")
  protected String senderRec1;

  @XmlElement(name = "SenderRec2")
  protected String senderRec2;

  @XmlElement(name = "SenderRec3")
  protected String senderRec3;

  @XmlElement(name = "SenderRec4")
  protected String senderRec4;

  @XmlElement(name = "SenderRec5")
  protected String senderRec5;

  @XmlElement(name = "SenderRec6")
  protected String senderRec6;

  /**
   * Gets the value of the bic property.
   *
   * @return possible object is {@link String }
   */
  public String getBIC() {
    return bic;
  }

  /**
   * Sets the value of the bic property.
   *
   * @param value allowed object is {@link String }
   */
  public void setBIC(String value) {
    this.bic = value;
  }

}
