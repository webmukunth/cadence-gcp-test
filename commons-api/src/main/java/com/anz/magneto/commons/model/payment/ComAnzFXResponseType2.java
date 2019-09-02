package com.anz.magneto.commons.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.FX_Response_Type2 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.FX_Response_Type2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemNo" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="StatusCode" type="{http://schema.cmo.anz/PmtAdd}string4" minOccurs="0"/>
 *         &lt;element name="StatusDesc" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FX_Response_Type2", propOrder = {
    "itemNo",
    "statusCode",
    "statusDesc"
})
@Data
class ComAnzFXResponseType2 {

  @XmlElement(name = "ItemNo")
  String itemNo;

  @XmlElement(name = "StatusCode")
  String statusCode;

  @XmlElement(name = "StatusDesc")
  String statusDesc;

}
