package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.ToBene_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.ToBene_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string140" minOccurs="0"/>
 *         &lt;element name="ResidentStatus" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr1" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr2" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Addr3" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://schema.cmo.anz/PmtAdd}string2" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://schema.cmo.anz/PmtAdd}string1000" minOccurs="0"/>
 *         &lt;element name="ContactType" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ContactName" type="{http://schema.cmo.anz/PmtAdd}string140" minOccurs="0"/>
 *         &lt;element name="ContactAddr" type="{http://schema.cmo.anz/PmtAdd}string128" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.ToBene_Type", propOrder = {
    "id",
    "name",
    "residentStatus",
    "addr1",
    "addr2",
    "addr3",
    "country",
    "message",
    "contactType",
    "contactName",
    "contactAddr"
})
@Data
public class ComAnzToBeneType {

  @XmlElement(name = "Id")
  protected String id;

  @XmlElement(name = "Name")
  protected String name;

  @XmlElement(name = "ResidentStatus")
  protected String residentStatus;

  @XmlElement(name = "Addr1")
  protected String addr1;

  @XmlElement(name = "Addr2")
  protected String addr2;

  @XmlElement(name = "Addr3")
  protected String addr3;

  @XmlElement(name = "Country")
  protected String country;

  @XmlElement(name = "Message")
  protected String message;

  @XmlElement(name = "ContactType")
  protected String contactType;

  @XmlElement(name = "ContactName")
  protected String contactName;

  @XmlElement(name = "ContactAddr")
  protected String contactAddr;

}
