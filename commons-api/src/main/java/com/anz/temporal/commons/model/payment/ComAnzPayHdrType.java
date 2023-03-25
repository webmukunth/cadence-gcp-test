package com.anz.temporal.commons.model.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Wither;


/**
 * <p>Java class for com.anz.PayHdr_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.PayHdr_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PODsID" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="PaymentID" type="{http://schema.cmo.anz/PmtAdd}string35"/>
 *         &lt;element name="ThirdPartyPayID" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="PaymentBatchID" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ItemId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="NbrofItems" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="ValueofItems" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PaymentTRN" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="PaymentRelRef" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ProcDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="AcctTranType" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="BankOriginated" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="CustomerContact" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PayHdr_Type", propOrder = {
    "poDsID",
    "paymentID",
    "thirdPartyPayID",
    "paymentBatchID",
    "itemId",
    "nbrofItems",
    "valueofItems",
    "paymentTRN",
    "paymentRelRef",
    "procDate",
    "acctTranType",
    "bankOriginated",
    "customerContact"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzPayHdrType {

  @XmlElement(name = "PODsID")
  String poDsID;

  @XmlElement(name = "PaymentID", required = true)
  String paymentID;

  @XmlElement(name = "ThirdPartyPayID")
  String thirdPartyPayID;

  @XmlElement(name = "PaymentBatchID")
  String paymentBatchID;

  @XmlElement(name = "ItemId")
  String itemId;

  @XmlElement(name = "NbrofItems")
  BigInteger nbrofItems;

  @XmlElement(name = "ValueofItems")
  BigDecimal valueofItems;

  @XmlElement(name = "PaymentTRN")
  String paymentTRN;

  @XmlElement(name = "PaymentRelRef")
  String paymentRelRef;

  @XmlElement(name = "ProcDate", required = true)
  @XmlSchemaType(name = "date")
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
  XMLGregorianCalendar procDate;

  @XmlElement(name = "AcctTranType")
  String acctTranType;

  @XmlElement(name = "BankOriginated")
  String bankOriginated;

  @XmlElement(name = "CustomerContact")
  String customerContact;
}
