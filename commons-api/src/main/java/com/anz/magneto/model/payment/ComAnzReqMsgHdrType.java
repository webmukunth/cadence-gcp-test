
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
 * 
 * 
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
public class ComAnzReqMsgHdrType {

    @XmlElement(name = "ClientDt", required = true)
    @XmlSchemaType(name = "dateTime")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DateFormat.DATE_TIME_FORMAT)
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
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyId(String value) {
        this.partyId = value;
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
     * Gets the value of the origMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigMethod() {
        return origMethod;
    }

    /**
     * Sets the value of the origMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigMethod(String value) {
        this.origMethod = value;
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
     * Gets the value of the validationToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationToken() {
        return validationToken;
    }

    /**
     * Sets the value of the validationToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationToken(String value) {
        this.validationToken = value;
    }

}
