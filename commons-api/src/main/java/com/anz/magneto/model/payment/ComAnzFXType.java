package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Data;


/**
 * <p>Java class for com.anz.FX_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.FX_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemNo" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="FXType" type="{http://schema.cmo.anz/PmtAdd}string16"/>
 *         &lt;element name="FXOwner" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="DealSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="DealGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="FXAcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="QuoteId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="DealId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="ExchRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TranDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="FromCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="ToCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="FromAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ToAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BaseAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BaseCurCode" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.FX_Type", propOrder = {
    "itemNo",
    "fxType",
    "fxOwner",
    "dealSys",
    "dealGrp",
    "fxAcctId",
    "quoteId",
    "dealId",
    "exchRate",
    "tranDate",
    "fromCurCode",
    "toCurCode",
    "fromAmount",
    "toAmount",
    "baseAmount",
    "baseCurCode",
    "type"
})
@Data
public class ComAnzFXType {

  @XmlElement(name = "ItemNo")
  String itemNo;

  @XmlElement(name = "FXType", required = true)
  String fxType;

  @XmlElement(name = "FXOwner")
  String fxOwner;

  @XmlElement(name = "DealSys")
  String dealSys;

  @XmlElement(name = "DealGrp")
  String dealGrp;

  @XmlElement(name = "FXAcctId")
  String fxAcctId;

  @XmlElement(name = "QuoteId")
  String quoteId;

  @XmlElement(name = "DealId")
  String dealId;

  @XmlElement(name = "ExchRate")
  BigDecimal exchRate;

  @XmlElement(name = "TranDate")
  @XmlSchemaType(name = "dateTime")
  XMLGregorianCalendar tranDate;

  @XmlElement(name = "FromCurCode")
  String fromCurCode;

  @XmlElement(name = "ToCurCode")
  String toCurCode;

  @XmlElement(name = "FromAmount")
  BigDecimal fromAmount;

  @XmlElement(name = "ToAmount")
  BigDecimal toAmount;

  @XmlElement(name = "BaseAmount")
  BigDecimal baseAmount;

  @XmlElement(name = "BaseCurCode")
  String baseCurCode;

  @XmlElement(name = "Type")
  String type;
}
