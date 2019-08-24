package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Data;


/**
 * <p>Java class for com.anz.Req_MsgHdr_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Req_MsgHdr_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientDt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ClientName" type="{http://schema.cmo.anz/PmtAdd}string10"/>
 *         &lt;element name="PartyId" type="{http://schema.cmo.anz/PmtAdd}string20"/>
 *         &lt;element name="Version" type="{http://schema.cmo.anz/PmtAdd}string10"/>
 *         &lt;element name="OrigMethod" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://schema.cmo.anz/PmtAdd}string20">
 *               &lt;enumeration value="Attended"/>
 *               &lt;enumeration value="Unattended"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OutcomeQueue" type="{http://schema.cmo.anz/PmtAdd}string80" minOccurs="0"/>
 *         &lt;element name="ValidationToken" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Req_MsgHdr_Type", propOrder = {
    "clientDt",
    "clientName",
    "partyId",
    "version",
    "origMethod",
    "outcomeQueue",
    "validationToken"
})
@Data
public class ComAnzReqMsgHdrType {

  @XmlElement(name = "ClientDt", required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar clientDt;

  @XmlElement(name = "ClientName", required = true)
  protected String clientName;

  @XmlElement(name = "PartyId", required = true)
  protected String partyId;

  @XmlElement(name = "Version", required = true)
  protected String version;

  @XmlElement(name = "OrigMethod")
  protected String origMethod;

  @XmlElement(name = "OutcomeQueue")
  protected String outcomeQueue;

  @XmlElement(name = "ValidationToken")
  protected String validationToken;

}
