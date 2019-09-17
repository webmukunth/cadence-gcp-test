package com.anz.cadence.commons.model.payment;

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
 * <p>Java class for com.anz.Req_MsgHdr_Pac_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Req_MsgHdr_Pac_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientName" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://schema.cmo.anz/PmtAdd}string2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Req_MsgHdr_Pac_Type", propOrder = {
    "clientName",
    "country"
})
@Data
@Wither
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class ComAnzReqMsgHdrPacType {

  @XmlElement(name = "ClientName")
  String clientName;

  @XmlElement(name = "Country", required = true)
  String country;

}
