//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.04 at 02:21:35 PM AEST
//


package com.anz.magneto.model.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.Service_Action complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Service_Action">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActionCode" type="{http://schema.cmo.anz/PSPWrapperObject}string20" minOccurs="0"/>
 *         &lt;element name="ActionDesc" type="{http://schema.cmo.anz/PSPWrapperObject}string255" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Service_Action", namespace = "http://schema.cmo.anz/PSPWrapperObject", propOrder = {
        "actionCode",
        "actionDesc"
})
public class ComAnzServiceAction {

    @XmlElement(name = "ActionCode")
    protected String actionCode;
    @XmlElement(name = "ActionDesc")
    protected String actionDesc;

    /**
     * Gets the value of the actionCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Sets the value of the actionCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

    /**
     * Gets the value of the actionDesc property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActionDesc() {
        return actionDesc;
    }

    /**
     * Sets the value of the actionDesc property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActionDesc(String value) {
        this.actionDesc = value;
    }

}
