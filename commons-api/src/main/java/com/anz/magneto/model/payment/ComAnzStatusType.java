package com.anz.magneto.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.Status_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Status_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatusCode" type="{http://schema.cmo.anz/PmtAdd}string4"/>
 *         &lt;element name="StatusDesc" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="Duplicate" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="AddStatusCode" type="{http://schema.cmo.anz/PmtAdd}string4" minOccurs="0"/>
 *         &lt;element name="AddStatusDesc" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="AdditionalStatus" type="{http://schema.cmo.anz/PmtAdd}com.anz.AdditionalStatus_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Status_Type", propOrder = {
    "statusCode",
    "statusDesc",
    "duplicate",
    "addStatusCode",
    "addStatusDesc",
    "additionalStatus"
})
@Data
public class ComAnzStatusType {

  @XmlElement(name = "StatusCode", required = true)
  String statusCode;

  @XmlElement(name = "StatusDesc")
  String statusDesc;

  @XmlElement(name = "Duplicate")
  String duplicate;

  @XmlElement(name = "AddStatusCode")
  String addStatusCode;

  @XmlElement(name = "AddStatusDesc")
  String addStatusDesc;

  @XmlElement(name = "AdditionalStatus")
  List<ComAnzAdditionalStatusType> additionalStatus;

}
