package com.anz.magneto.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.PmtAddRq_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.PmtAddRq_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RqUID" type="{http://schema.cmo.anz/PmtAdd}string36"/>
 *         &lt;element name="ClientInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MsgHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.Req_MsgHdr_Type"/>
 *         &lt;element name="Security" type="{http://schema.cmo.anz/PmtAdd}com.anz.Security_Type" minOccurs="0"/>
 *         &lt;element name="PayHdr" type="{http://schema.cmo.anz/PmtAdd}com.anz.PayHdr_Type"/>
 *         &lt;element name="FromFIData" type="{http://schema.cmo.anz/PmtAdd}com.anz.FromFIData_Type"/>
 *         &lt;element name="FromCust" type="{http://schema.cmo.anz/PmtAdd}com.anz.FromCust_Type" minOccurs="0"/>
 *         &lt;element name="FromAcct" type="{http://schema.cmo.anz/PmtAdd}com.anz.FromAcct_Type"/>
 *         &lt;element name="Lien" type="{http://schema.cmo.anz/PmtAdd}com.anz.Lien_Type" minOccurs="0"/>
 *         &lt;element name="ToFIData" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToFIData_Type"/>
 *         &lt;element name="Clearing" type="{http://schema.cmo.anz/PmtAdd}com.anz.Clearing_Type" minOccurs="0"/>
 *         &lt;element name="ToBene" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToBene_Type" minOccurs="0"/>
 *         &lt;element name="ToAcct" type="{http://schema.cmo.anz/PmtAdd}com.anz.ToAcct_Type"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Fees" type="{http://schema.cmo.anz/PmtAdd}com.anz.Fees_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PmtAddRq_Type", propOrder = {
    "rqUID",
    "clientInfo",
    "msgHdr",
    "security",
    "payHdr",
    "fromFIData",
    "fromCust",
    "fromAcct",
    "lien",
    "toFIData",
    "clearing",
    "toBene",
    "toAcct",
    "fx",
    "fees"
})
@Data
public class ComAnzPmtAddRqType {

  @XmlElement(name = "RqUID", required = true)
  protected String rqUID;

  @XmlElement(name = "ClientInfo")
  protected String clientInfo;

  @XmlElement(name = "MsgHdr", required = true)
  protected ComAnzReqMsgHdrType msgHdr;

  @XmlElement(name = "Security")
  protected ComAnzSecurityType security;

  @XmlElement(name = "PayHdr", required = true)
  protected ComAnzPayHdrType payHdr;

  @XmlElement(name = "FromFIData", required = true)
  protected ComAnzFromFIDataType fromFIData;

  @XmlElement(name = "FromCust")
  protected ComAnzFromCustType fromCust;

  @XmlElement(name = "FromAcct", required = true)
  protected ComAnzFromAcctType fromAcct;

  @XmlElement(name = "Lien")
  protected ComAnzLienType lien;

  @XmlElement(name = "ToFIData", required = true)
  protected ComAnzToFIDataType toFIData;

  @XmlElement(name = "Clearing")
  protected ComAnzClearingType clearing;

  @XmlElement(name = "ToBene")
  protected ComAnzToBeneType toBene;

  @XmlElement(name = "ToAcct", required = true)
  protected ComAnzToAcctType toAcct;

  @XmlElement(name = "FX")
  protected List<ComAnzFXType> fx;

  @XmlElement(name = "Fees")
  protected List<ComAnzFeesType> fees;
  @XmlAttribute(name = "id")
  protected String id;
}
