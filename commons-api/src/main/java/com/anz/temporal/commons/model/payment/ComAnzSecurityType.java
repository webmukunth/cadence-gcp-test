package com.anz.temporal.commons.model.payment;

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
 * <p>Java class for com.anz.Security_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Security_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TerminalId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="DeviceId" type="{http://schema.cmo.anz/PmtAdd}string40" minOccurs="0"/>
 *         &lt;element name="Authentication" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="RiskAssessmentOutcome" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="CustRespToRiskAssessment" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="AuthorisersCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SessionId" type="{http://schema.cmo.anz/PmtAdd}string40" minOccurs="0"/>
 *         &lt;element name="AuthenticationMethd" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="ServiceRepresentativeId" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="MalwareRiskScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MalwareID" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="CustomerThreatIndicator" type="{http://schema.cmo.anz/PmtAdd}string1" minOccurs="0"/>
 *         &lt;element name="ClientName" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="ShieldStatus" type="{http://schema.cmo.anz/PmtAdd}string2" minOccurs="0"/>
 *         &lt;element name="SMSStatus" type="{http://schema.cmo.anz/PmtAdd}string2" minOccurs="0"/>
 *         &lt;element name="TGO-TMH-Indicators" type="{http://schema.cmo.anz/PmtAdd}string20" minOccurs="0"/>
 *         &lt;element name="ModeofStepUpAuth" type="{http://schema.cmo.anz/PmtAdd}string2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Security_Type", propOrder = {
    "terminalId",
    "deviceId",
    "authentication",
    "riskAssessmentOutcome",
    "custRespToRiskAssessment",
    "authorisersCnt",
    "sessionId",
    "authenticationMethd",
    "serviceRepresentativeId",
    "malwareRiskScore",
    "malwareID",
    "customerThreatIndicator",
    "clientName",
    "shieldStatus",
    "smsStatus",
    "tgotmhIndicators",
    "modeofStepUpAuth"
})
@Data
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ComAnzSecurityType {

  @XmlElement(name = "TerminalId")
  String terminalId;

  @XmlElement(name = "DeviceId")
  String deviceId;

  @XmlElement(name = "Authentication")
  String authentication;

  @XmlElement(name = "RiskAssessmentOutcome")
  String riskAssessmentOutcome;

  @XmlElement(name = "CustRespToRiskAssessment")
  String custRespToRiskAssessment;

  @XmlElement(name = "AuthorisersCnt")
  Integer authorisersCnt;

  @XmlElement(name = "SessionId")
  String sessionId;

  @XmlElement(name = "AuthenticationMethd")
  String authenticationMethd;

  @XmlElement(name = "ServiceRepresentativeId")
  String serviceRepresentativeId;

  @XmlElement(name = "MalwareRiskScore")
  Integer malwareRiskScore;

  @XmlElement(name = "MalwareID")
  String malwareID;

  @XmlElement(name = "CustomerThreatIndicator")
  String customerThreatIndicator;

  @XmlElement(name = "ClientName")
  String clientName;

  @XmlElement(name = "ShieldStatus")
  String shieldStatus;

  @XmlElement(name = "SMSStatus")
  String smsStatus;

  @XmlElement(name = "TGO-TMH-Indicators")
  String tgotmhIndicators;

  @XmlElement(name = "ModeofStepUpAuth")
  String modeofStepUpAuth;
}
