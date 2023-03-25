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
 * <p>Java class for com.anz.FromFIData_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.FromFIData_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{http://schema.cmo.anz/PmtAdd}string2"/>
 *         &lt;element name="BIC" type="{http://schema.cmo.anz/PmtAdd}string11" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FromFIData_Type", propOrder = {
    "country",
    "bic",
    "name",
    "branch"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzFromFIDataType {

  @XmlElement(name = "Country", required = true)
  String country;

  @XmlElement(name = "BIC")
  String bic;

  @XmlElement(name = "Name")
  String name;

  @XmlElement(name = "Branch")
  String branch;
}
