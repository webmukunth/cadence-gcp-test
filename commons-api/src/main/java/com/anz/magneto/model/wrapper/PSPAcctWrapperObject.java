//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.23 at 04:42:54 PM AEDT 
//


package com.anz.magneto.model.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PSPAcctWrapperObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PSPAcctWrapperObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.cmo.anz/PSPWrapperObject}PSPWrapperObject">
 *       &lt;sequence>
 *         &lt;element name="AccountingEntryMap" type="{http://schema.cmo.anz/PSPWrapperObject}com.anz.Accounting_Entry_Map"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PSPAcctWrapperObject", propOrder = {
    "accountingEntryMap"
})
public class PSPAcctWrapperObject
    extends PSPWrapperObject
{

    @XmlElement(name = "AccountingEntryMap", required = true)
    protected ComAnzAccountingEntryMap accountingEntryMap;

    /**
     * Gets the value of the accountingEntryMap property.
     * 
     * @return
     *     possible object is
     *     {@link ComAnzAccountingEntryMap }
     *     
     */
    public ComAnzAccountingEntryMap getAccountingEntryMap() {
        return accountingEntryMap;
    }

    /**
     * Sets the value of the accountingEntryMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComAnzAccountingEntryMap }
     *     
     */
    public void setAccountingEntryMap(ComAnzAccountingEntryMap value) {
        this.accountingEntryMap = value;
    }

}
