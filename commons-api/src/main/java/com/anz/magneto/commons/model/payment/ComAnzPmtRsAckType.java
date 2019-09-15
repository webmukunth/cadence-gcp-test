package com.anz.magneto.commons.model.payment;

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
 * <p>Java class for com.anz.PmtRsAck_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.PmtRsAck_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RqUID" type="{http://schema.cmo.anz/PmtAdd}string36"/>
 *         &lt;element name="StatusCode" type="{http://schema.cmo.anz/PmtAdd}string4"/>
 *         &lt;element name="StatusDesc" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="ClientInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationName" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="ClientName" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="OutcomeQueue" type="{http://schema.cmo.anz/PmtAdd}string80" minOccurs="0"/>
 *         &lt;element name="MsgHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.Req_MsgHdr_Pac_Type" minOccurs="0"/>
 *         &lt;element name="PayHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.Res_PayHdr_Type" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://schema.cmo.anz/PmtAdd}com.anz.Status_Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PmtRsAck_Type", propOrder = {
    "rqUID",
    "statusCode",
    "statusDesc",
    "clientInfo",
    "destinationName",
    "clientName",
    "outcomeQueue",
    "msgHdr",
    "payHdr",
    "status"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzPmtRsAckType {

  @XmlElement(name = "RqUID", required = true)
  String rqUID;

  @XmlElement(name = "StatusCode", required = true)
  String statusCode;

  @XmlElement(name = "StatusDesc")
  String statusDesc;

  @XmlElement(name = "ClientInfo")
  String clientInfo;

  @XmlElement(name = "DestinationName")
  String destinationName;

  @XmlElement(name = "ClientName")
  String clientName;

  @XmlElement(name = "OutcomeQueue")
  String outcomeQueue;

  @XmlElement(name = "MsgHdr")
  ComAnzReqMsgHdrPacType msgHdr;

  @XmlElement(name = "PayHdr")
  ComAnzResPayHdrType payHdr;

  @XmlElement(name = "Status")
  ComAnzStatusType status;

}
