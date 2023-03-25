package com.anz.temporal.commons.model.payment;

import java.math.BigDecimal;
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
 * <p>Java class for com.anz.FX_Response_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.FX_Response_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemNo" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ItemId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="DealId" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ExchRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="FromAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="FromCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="ToAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ToCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FX_Response_Type", propOrder = {
    "itemNo",
    "itemId",
    "dealId",
    "exchRate",
    "rate",
    "fromAmount",
    "fromCurCode",
    "toAmount",
    "toCurCode"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzFXResponseType {

  @XmlElement(name = "ItemNo")
  String itemNo;

  @XmlElement(name = "ItemId")
  String itemId;

  @XmlElement(name = "DealId")
  String dealId;

  @XmlElement(name = "ExchRate")
  BigDecimal exchRate;

  @XmlElement(name = "Rate")
  BigDecimal rate;

  @XmlElement(name = "FromAmount")
  BigDecimal fromAmount;

  @XmlElement(name = "FromCurCode")
  String fromCurCode;

  @XmlElement(name = "ToAmount")
  BigDecimal toAmount;

  @XmlElement(name = "ToCurCode")
  String toCurCode;

}
