package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.Commission_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Commission_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="AcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="AcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="CostCentre" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="AcctCcy" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Commission_Type", propOrder = {
    "acctId",
    "acctSys",
    "acctGrp",
    "branch",
    "costCentre",
    "acctCcy"
})
@Data
public class ComAnzCommissionType {

  @XmlElement(name = "AcctId")
  protected String acctId;

  @XmlElement(name = "AcctSys")
  protected String acctSys;

  @XmlElement(name = "AcctGrp")
  protected String acctGrp;

  @XmlElement(name = "Branch")
  protected String branch;

  @XmlElement(name = "CostCentre")
  protected String costCentre;

  @XmlElement(name = "AcctCcy")
  protected String acctCcy;

}
