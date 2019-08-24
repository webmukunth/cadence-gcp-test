package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Data;


/**
 * <p>Java class for com.anz.PmtEnqRq_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.PmtEnqRq_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RqUID" type="{http://schema.cmo.anz/PmtAdd}string36"/>
 *         &lt;element name="ClientInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClientDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ClientName" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="Version" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="OutcomeQueue" type="{http://schema.cmo.anz/PmtAdd}string80" minOccurs="0"/>
 *         &lt;element name="MsgHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.Req_MsgHdr_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PmtEnqRq_Type", propOrder = {
    "rqUID",
    "clientInfo",
    "clientDt",
    "clientName",
    "version",
    "outcomeQueue",
    "msgHdr"
})
@Data
public class ComAnzPmtEnqRqType {

  @XmlElement(name = "RqUID", required = true)
  protected String rqUID;

  @XmlElement(name = "ClientInfo")
  protected String clientInfo;

  @XmlElement(name = "ClientDt")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar clientDt;

  @XmlElement(name = "ClientName")
  protected String clientName;

  @XmlElement(name = "Version")
  protected String version;

  @XmlElement(name = "OutcomeQueue")
  protected String outcomeQueue;

  @XmlElement(name = "MsgHdr", required = true)
  protected ComAnzReqMsgHdrType msgHdr;

}
