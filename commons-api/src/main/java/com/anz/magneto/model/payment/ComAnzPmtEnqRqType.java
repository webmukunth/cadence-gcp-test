
package com.anz.magneto.model.payment;

import com.anz.psp.commons.model.format.DateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 * 
 * 
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
public class ComAnzPmtEnqRqType {

    @XmlElement(name = "RqUID", required = true)
    protected String rqUID;
    @XmlElement(name = "ClientInfo")
    protected String clientInfo;
    @XmlElement(name = "ClientDt")
    @XmlSchemaType(name = "dateTime")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_TIME_FORMAT)
    protected XMLGregorianCalendar clientDt;
    @XmlElement(name = "ClientName")
    protected String clientName;
    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "OutcomeQueue")
    protected String outcomeQueue;
    @XmlElement(name = "MsgHdr", required = true)
    protected ComAnzReqMsgHdrType msgHdr;

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
     * Gets the value of the clientDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClientDt() {
        return clientDt;
    }

    /**
     * Sets the value of the clientDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClientDt(XMLGregorianCalendar value) {
        this.clientDt = value;
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
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     *     {@link ComAnzReqMsgHdrType }
     *     
     */
    public ComAnzReqMsgHdrType getMsgHdr() {
        return msgHdr;
    }

    /**
     * Sets the value of the msgHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzReqMsgHdrType }
     *     
     */
    public void setMsgHdr(ComAnzReqMsgHdrType value) {
        this.msgHdr = value;
    }

}
