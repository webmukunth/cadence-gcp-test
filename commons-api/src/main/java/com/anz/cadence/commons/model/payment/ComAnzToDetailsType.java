package com.anz.cadence.commons.model.payment;

import java.util.List;
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
 * <p>Java class for com.anz.ToDetails_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.ToDetails_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentTRN" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://schema.cmo.anz/PmtAdd}com.anz.Status_Type"/>
 *         &lt;element name="ToBene" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToBene_Type" minOccurs="0"/>
 *         &lt;element name="ToAcct" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToAcct_Type"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Type" minOccurs="0"/>
 *         &lt;element name="Fees" type="{http://schema.cmo.anz/PmtAdd}com.anz.Fees_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.ToDetails_Type", propOrder = {
    "paymentTRN",
    "status",
    "toBene",
    "toAcct",
    "fx",
    "fees"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzToDetailsType {

  @XmlElement(name = "PaymentTRN")
  String paymentTRN;

  @XmlElement(name = "Status", required = true)
  ComAnzStatusType status;

  @XmlElement(name = "ToBene")
  ComAnzToBeneType toBene;

  @XmlElement(name = "ToAcct", required = true)
  ComAnzToAcctType toAcct;

  @XmlElement(name = "FX")
  ComAnzFXType fx;

  @XmlElement(name = "Fees")
  List<ComAnzFeesType> fees;
}
