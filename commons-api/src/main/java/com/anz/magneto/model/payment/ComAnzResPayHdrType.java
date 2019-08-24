package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.Res_PayHdr_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Res_PayHdr_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentID" type="{http://schema.cmo.anz/PmtAdd}string35"/>
 *         &lt;element name="PaymentBatchID" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *         &lt;element name="ItemId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="ClearPref" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ClearSubPref" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="MOP" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Res_PayHdr_Type", propOrder = {
    "paymentID",
    "paymentBatchID",
    "itemId",
    "clearPref",
    "clearSubPref",
    "mop"
})
@Data
public class ComAnzResPayHdrType {

  @XmlElement(name = "PaymentID", required = true)
  protected String paymentID;

  @XmlElement(name = "PaymentBatchID")
  protected String paymentBatchID;

  @XmlElement(name = "ItemId")
  protected String itemId;

  @XmlElement(name = "ClearPref")
  protected String clearPref;

  @XmlElement(name = "ClearSubPref")
  protected String clearSubPref;

  @XmlElement(name = "MOP")
  protected String mop;
}
