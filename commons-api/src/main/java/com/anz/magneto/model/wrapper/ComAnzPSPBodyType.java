//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.04 at 04:24:16 PM AEST
//


package com.anz.magneto.model.wrapper;

import com.anz.psp.commons.model.batch.BatchAddRq;
import com.anz.psp.commons.model.batch.BatchAddRsType;
import com.anz.psp.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.psp.commons.model.payment.ComAnzPmtAddRsType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com.anz.PSPBody_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.PSPBody_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://schema.cmo.anz/PmtAdd}PmtAddRq" maxOccurs="255"/>
 *         &lt;element ref="{http://schema.cmo.anz/PmtAdd}PmtAddRs" maxOccurs="255"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.PSPBody_Type", namespace = "http://schema.cmo.anz/PSPWrapperObject", propOrder = {
        "pmtAddRq",
        "pmtAddRs",
        "batchAddRq",
        "batchAddRs"
})
public class ComAnzPSPBodyType {

    @XmlElement(name = "PmtAddRq", namespace = "http://schema.cmo.anz/PmtAdd", required = true)
    protected List<ComAnzPmtAddRqType> pmtAddRq;
    @XmlElement(name = "PmtAddRs", namespace = "http://schema.cmo.anz/PmtAdd", required = true)
    protected List<ComAnzPmtAddRsType> pmtAddRs;

    @XmlElement(name = "BatchAddRq", namespace = "http://schema.cmo.anz/PmtAdd", required = true)
    protected BatchAddRq batchAddRq;
    @XmlElement(name = "BatchAddRs", namespace = "http://schema.cmo.anz/PmtAdd", required = true)
    protected BatchAddRsType batchAddRs;
    
    /**
     * Gets the value of the pmtAddRq property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pmtAddRq property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPmtAddRq().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzPmtAddRqType }
     *
     *
     */
    public List<ComAnzPmtAddRqType> getPmtAddRq() {
        if (pmtAddRq == null) {
            pmtAddRq = new ArrayList<ComAnzPmtAddRqType>();
        }
        return this.pmtAddRq;
    }

    /**
     * Gets the value of the pmtAddRs property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pmtAddRs property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPmtAddRs().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComAnzPmtAddRsType }
     *
     *
     */
    public List<ComAnzPmtAddRsType> getPmtAddRs() {
        if (pmtAddRs == null) {
            pmtAddRs = new ArrayList<ComAnzPmtAddRsType>();
        }
        return this.pmtAddRs;
    }

	public BatchAddRq getBatchAddRq() {
		return batchAddRq;
	}

	public void setBatchAddRq(BatchAddRq batchAddRq) {
		this.batchAddRq = batchAddRq;
	}

	public BatchAddRsType getBatchAddRs() {
		return batchAddRs;
	}

	public void setBatchAddRs(BatchAddRsType batchAddRs) {
		this.batchAddRs = batchAddRs;
	}

}