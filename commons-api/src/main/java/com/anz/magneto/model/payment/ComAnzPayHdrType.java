package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Data;


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
public class ComAnzPayHdrType {

  @XmlElement(name = "PODsID")
  protected String poDsID;

  @XmlElement(name = "PaymentID", required = true)
  protected String paymentID;

  @XmlElement(name = "ThirdPartyPayID")
  protected String thirdPartyPayID;

  @XmlElement(name = "PaymentBatchID")
  protected String paymentBatchID;

  @XmlElement(name = "ItemId")
  protected String itemId;

  @XmlElement(name = "NbrofItems")
  protected BigInteger nbrofItems;

  @XmlElement(name = "ValueofItems")
  protected BigDecimal valueofItems;

  @XmlElement(name = "PaymentTRN")
  protected String paymentTRN;

  @XmlElement(name = "PaymentRelRef")
  protected String paymentRelRef;

  @XmlElement(name = "ProcDate", required = true)
  @XmlSchemaType(name = "date")
  protected XMLGregorianCalendar procDate;

  @XmlElement(name = "AcctTranType")
  protected String acctTranType;

  @XmlElement(name = "BankOriginated")
  protected String bankOriginated;

  @XmlElement(name = "CustomerContact")
  protected String customerContact;
}
