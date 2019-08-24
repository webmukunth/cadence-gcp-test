
package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 * 
 * 
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
public class ComAnzPmtRsAckType {

    @XmlElement(name = "RqUID", required = true)
    protected String rqUID;
    @XmlElement(name = "StatusCode", required = true)
    protected String statusCode;
    @XmlElement(name = "StatusDesc")
    protected String statusDesc;
    @XmlElement(name = "ClientInfo")
    protected String clientInfo;
    @XmlElement(name = "DestinationName")
    protected String destinationName;
    @XmlElement(name = "ClientName")
    protected String clientName;
    @XmlElement(name = "OutcomeQueue")
    protected String outcomeQueue;
    @XmlElement(name = "MsgHdr")
    protected ComAnzReqMsgHdrPacType msgHdr;
    @XmlElement(name = "PayHdr")
    protected ComAnzResPayHdrType payHdr;
    @XmlElement(name = "Status")
    protected ComAnzStatusType status;

    /**
     * Gets the value of the rqUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqUID() {
        return rqUID;
    }

    /**
     * Sets the value of the rqUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqUID(String value) {
        this.rqUID = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Sets the value of the statusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDesc(String value) {
        this.statusDesc = value;
    }

    /**
     * Gets the value of the clientInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientInfo() {
        return clientInfo;
    }

    /**
     * Sets the value of the clientInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientInfo(String value) {
        this.clientInfo = value;
    }

    /**
     * Gets the value of the destinationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * Sets the value of the destinationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationName(String value) {
        this.destinationName = value;
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
     * Gets the value of the outcomeQueue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutcomeQueue() {
        return outcomeQueue;
    }

    /**
     * Sets the value of the outcomeQueue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutcomeQueue(String value) {
        this.outcomeQueue = value;
    }

    /**
     * Gets the value of the msgHdr property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzReqMsgHdrPacType }
     *     
     */
    public ComAnzReqMsgHdrPacType getMsgHdr() {
        return msgHdr;
    }

    /**
     * Sets the value of the msgHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzReqMsgHdrPacType }
     *     
     */
    public void setMsgHdr(ComAnzReqMsgHdrPacType value) {
        this.msgHdr = value;
    }

    /**
     * Gets the value of the payHdr property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzResPayHdrType }
     *     
     */
    public ComAnzResPayHdrType getPayHdr() {
        return payHdr;
    }

    /**
     * Sets the value of the payHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzResPayHdrType }
     *     
     */
    public void setPayHdr(ComAnzResPayHdrType value) {
        this.payHdr = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzStatusType }
     *     
     */
    public ComAnzStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzStatusType }
     *     
     */
    public void setStatus(ComAnzStatusType value) {
        this.status = value;
    }

}
