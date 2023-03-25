package com.anz.temporal.commons.model.payment;

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
 * <p>Java class for com.anz.ContactInfo_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.ContactInfo_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContactType" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ContactName" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ContactAddr" type="{http://schema.cmo.anz/PmtAdd}string128" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.ContactInfo_Type", propOrder = {
    "contactType",
    "contactName",
    "contactAddr"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzContactInfoType {

  @XmlElement(name = "ContactType")
  String contactType;

  @XmlElement(name = "ContactName")
  String contactName;

  @XmlElement(name = "ContactAddr")
  String contactAddr;

}
