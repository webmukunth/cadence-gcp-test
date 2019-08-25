package com.anz.magneto.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.PmtAddRs_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.PmtAddRs_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RqUID" type="{http://schema.cmo.anz/PmtAdd}string36"/>
 *         &lt;element name="ClientInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationName" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="ClientName" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="MsgHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.Req_MsgHdr_Pac_Type" minOccurs="0"/>
 *         &lt;element name="OutcomeQueue" type="{http://schema.cmo.anz/PmtAdd}string80" minOccurs="0"/>
 *         &lt;element name="PayHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.Res_PayHdr_Type"/>
 *         &lt;element name="Status" type="{http://schema.cmo.anz/PmtAdd}com.anz.Status_Type"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Response_Type2" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://schema.cmo.anz/PmtAdd}com.anz.Message_Type2" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Returned" type="{http://schema.cmo.anz/PmtAdd}com.anz.Returned_Type" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PmtAddRs_Type", propOrder = {
    "rqUID",
    "clientInfo",
    "destinationName",
    "clientName",
    "msgHdr",
    "outcomeQueue",
    "payHdr",
    "status",
    "fx",
    "message",
    "returned"
})
@Data
public class ComAnzPmtAddRsType {

  @XmlElement(name = "RqUID", required = true)
  String rqUID;

  @XmlElement(name = "ClientInfo")
  String clientInfo;

  @XmlElement(name = "DestinationName")
  String destinationName;

  @XmlElement(name = "ClientName")
  String clientName;

  @XmlElement(name = "MsgHdr")
  ComAnzReqMsgHdrPacType msgHdr;

  @XmlElement(name = "OutcomeQueue")
  String outcomeQueue;

  @XmlElement(name = "PayHdr", required = true)
  ComAnzResPayHdrType payHdr;

  @XmlElement(name = "Status", required = true)
  ComAnzStatusType status;

  @XmlElement(name = "FX")
  List<ComAnzFXResponseType2> fx;

  @XmlElement(name = "Message")
  List<ComAnzMessageType2> message;

  @XmlElement(name = "Returned")
  ComAnzReturnedType returned;
  @XmlAttribute(name = "id")
  String id;
}
