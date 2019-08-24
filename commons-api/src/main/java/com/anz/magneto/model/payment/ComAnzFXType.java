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
  protected String itemNo;

  @XmlElement(name = "FXType", required = true)
  protected String fxType;

  @XmlElement(name = "FXOwner")
  protected String fxOwner;

  @XmlElement(name = "DealSys")
  protected String dealSys;

  @XmlElement(name = "DealGrp")
  protected String dealGrp;

  @XmlElement(name = "FXAcctId")
  protected String fxAcctId;

  @XmlElement(name = "QuoteId")
  protected String quoteId;

  @XmlElement(name = "DealId")
  protected String dealId;

  @XmlElement(name = "ExchRate")
  protected BigDecimal exchRate;

  @XmlElement(name = "TranDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar tranDate;

  @XmlElement(name = "FromCurCode")
  protected String fromCurCode;

  @XmlElement(name = "ToCurCode")
  protected String toCurCode;

  @XmlElement(name = "FromAmount")
  protected BigDecimal fromAmount;

  @XmlElement(name = "ToAmount")
  protected BigDecimal toAmount;

  @XmlElement(name = "BaseAmount")
  protected BigDecimal baseAmount;

  @XmlElement(name = "BaseCurCode")
  protected String baseCurCode;

  @XmlElement(name = "Type")
  protected String type;
}
