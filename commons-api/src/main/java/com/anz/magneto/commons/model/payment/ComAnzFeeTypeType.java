package com.anz.magneto.commons.model.payment;

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
 * <p>Java class for com.anz.FeeType_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.FeeType_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="Code" type="{http://schema.cmo.anz/PmtAdd}string10"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CurCode" type="{http://schema.cmo.anz/PmtAdd}string3"/>
 *         &lt;element name="Narrative" type="{http://schema.cmo.anz/PmtAdd}string160" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FeeType_Type", propOrder = {
    "itemId",
    "code",
    "amount",
    "curCode",
    "narrative"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzFeeTypeType {

  @XmlElement(name = "ItemId")
  String itemId;

  @XmlElement(name = "Code", required = true)
  String code;

  @XmlElement(name = "Amount", required = true)
  BigDecimal amount;

  @XmlElement(name = "CurCode", required = true)
  String curCode;

  @XmlElement(name = "Narrative")
  String narrative;

}
