package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Data;


/**
 * <p>Java class for com.anz.Lien_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Lien_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Action">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://schema.cmo.anz/PmtAdd}string20">
 *               &lt;enumeration value="ADD"/>
 *               &lt;enumeration value="RELEASE"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LienId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ReasonCode" type="{http://schema.cmo.anz/PmtAdd}string35" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Lien_Type", propOrder = {
    "action",
    "lienId",
    "expiryDate",
    "reasonCode"
})
@Data
public class ComAnzLienType {

  @XmlElement(name = "Action", required = true)
  protected String action;

  @XmlElement(name = "LienId")
  protected String lienId;

  @XmlElement(name = "ExpiryDate")
  @XmlSchemaType(name = "date")
  protected XMLGregorianCalendar expiryDate;

  @XmlElement(name = "ReasonCode")
  protected String reasonCode;

}
