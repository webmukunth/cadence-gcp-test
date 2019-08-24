
package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 * 
 * 
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
public class ComAnzSecurityType {

    @XmlElement(name = "TerminalId")
    protected String terminalId;
    @XmlElement(name = "DeviceId")
    protected String deviceId;
    @XmlElement(name = "Authentication")
    protected String authentication;
    @XmlElement(name = "RiskAssessmentOutcome")
    protected String riskAssessmentOutcome;
    @XmlElement(name = "CustRespToRiskAssessment")
    protected String custRespToRiskAssessment;
    @XmlElement(name = "AuthorisersCnt")
    protected Integer authorisersCnt;
    @XmlElement(name = "SessionId")
    protected String sessionId;
    @XmlElement(name = "AuthenticationMethd")
    protected String authenticationMethd;
    @XmlElement(name = "ServiceRepresentativeId")
    protected String serviceRepresentativeId;
    @XmlElement(name = "MalwareRiskScore")
    protected Integer malwareRiskScore;
    @XmlElement(name = "MalwareID")
    protected String malwareID;
    @XmlElement(name = "CustomerThreatIndicator")
    protected String customerThreatIndicator;
    @XmlElement(name = "ClientName")
    protected String clientName;
    @XmlElement(name = "ShieldStatus")
    protected String shieldStatus;
    @XmlElement(name = "SMSStatus")
    protected String smsStatus;
    @XmlElement(name = "TGO-TMH-Indicators")
    protected String tgotmhIndicators;
    @XmlElement(name = "ModeofStepUpAuth")
    protected String modeofStepUpAuth;

    /**
     * Gets the value of the terminalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Sets the value of the terminalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    /**
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the authentication property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthentication() {
        return authentication;
    }

    /**
     * Sets the value of the authentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthentication(String value) {
        this.authentication = value;
    }

    /**
     * Gets the value of the riskAssessmentOutcome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskAssessmentOutcome() {
        return riskAssessmentOutcome;
    }

    /**
     * Sets the value of the riskAssessmentOutcome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskAssessmentOutcome(String value) {
        this.riskAssessmentOutcome = value;
    }

    /**
     * Gets the value of the custRespToRiskAssessment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustRespToRiskAssessment() {
        return custRespToRiskAssessment;
    }

    /**
     * Sets the value of the custRespToRiskAssessment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustRespToRiskAssessment(String value) {
        this.custRespToRiskAssessment = value;
    }

    /**
     * Gets the value of the authorisersCnt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAuthorisersCnt() {
        return authorisersCnt;
    }

    /**
     * Sets the value of the authorisersCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAuthorisersCnt(Integer value) {
        this.authorisersCnt = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the authenticationMethd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationMethd() {
        return authenticationMethd;
    }

    /**
     * Sets the value of the authenticationMethd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationMethd(String value) {
        this.authenticationMethd = value;
    }

    /**
     * Gets the value of the serviceRepresentativeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRepresentativeId() {
        return serviceRepresentativeId;
    }

    /**
     * Sets the value of the serviceRepresentativeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRepresentativeId(String value) {
        this.serviceRepresentativeId = value;
    }

    /**
     * Gets the value of the malwareRiskScore property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMalwareRiskScore() {
        return malwareRiskScore;
    }

    /**
     * Sets the value of the malwareRiskScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMalwareRiskScore(Integer value) {
        this.malwareRiskScore = value;
    }

    /**
     * Gets the value of the malwareID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMalwareID() {
        return malwareID;
    }

    /**
     * Sets the value of the malwareID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMalwareID(String value) {
        this.malwareID = value;
    }

    /**
     * Gets the value of the customerThreatIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerThreatIndicator() {
        return customerThreatIndicator;
    }

    /**
     * Sets the value of the customerThreatIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerThreatIndicator(String value) {
        this.customerThreatIndicator = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the shieldStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShieldStatus() {
        return shieldStatus;
    }

    /**
     * Sets the value of the shieldStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShieldStatus(String value) {
        this.shieldStatus = value;
    }

    /**
     * Gets the value of the smsStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMSStatus() {
        return smsStatus;
    }

    /**
     * Sets the value of the smsStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMSStatus(String value) {
        this.smsStatus = value;
    }

    /**
     * Gets the value of the tgotmhIndicators property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTGOTMHIndicators() {
        return tgotmhIndicators;
    }

    /**
     * Sets the value of the tgotmhIndicators property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTGOTMHIndicators(String value) {
        this.tgotmhIndicators = value;
    }

    /**
     * Gets the value of the modeofStepUpAuth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeofStepUpAuth() {
        return modeofStepUpAuth;
    }

    /**
     * Sets the value of the modeofStepUpAuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeofStepUpAuth(String value) {
        this.modeofStepUpAuth = value;
    }

}
