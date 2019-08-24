
package com.anz.magneto.model.payment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 * 
 * 
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
public class ComAnzPmtAddRsType {

    @XmlElement(name = "RqUID", required = true)
    protected String rqUID;
    @XmlElement(name = "ClientInfo")
    protected String clientInfo;
    @XmlElement(name = "DestinationName")
    protected String destinationName;
    @XmlElement(name = "ClientName")
    protected String clientName;
    @XmlElement(name = "MsgHdr")
    protected ComAnzReqMsgHdrPacType msgHdr;
    @XmlElement(name = "OutcomeQueue")
    protected String outcomeQueue;
    @XmlElement(name = "PayHdr", required = true)
    protected ComAnzResPayHdrType payHdr;
    @XmlElement(name = "Status", required = true)
    protected ComAnzStatusType status;
    @XmlElement(name = "FX")
    protected List<ComAnzFXResponseType2> fx;
    @XmlElement(name = "Message")
    protected List<ComAnzMessageType2> message;
    @XmlElement(name = "Returned")
    protected ComAnzReturnedType returned;
    @XmlAttribute(name = "id")
    protected String id;

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

    /**
     * Gets the value of the fx property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fx property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFX().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzFXResponseType2 }
     * 
     * 
     */
    public List<ComAnzFXResponseType2> getFX() {
        if (fx == null) {
            fx = new ArrayList<ComAnzFXResponseType2>();
        }
        return this.fx;
    }

    /**
     * Gets the value of the message property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzMessageType2 }
     * 
     * 
     */
    public List<ComAnzMessageType2> getMessage() {
        if (message == null) {
            message = new ArrayList<ComAnzMessageType2>();
        }
        return this.message;
    }

    /**
     * Gets the value of the returned property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzReturnedType }
     *     
     */
    public ComAnzReturnedType getReturned() {
        return returned;
    }

    /**
     * Sets the value of the returned property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzReturnedType }
     *     
     */
    public void setReturned(ComAnzReturnedType value) {
        this.returned = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
