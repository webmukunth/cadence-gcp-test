package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.From_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.From_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcctId" type="{http://schema.cmo.anz/PmtAdd}string34" minOccurs="0"/>
 *         &lt;element name="AcctSys" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="AcctGrp" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Branch" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="CostCentre" type="{http://schema.cmo.anz/PmtAdd}string10" minOccurs="0"/>
 *         &lt;element name="Ccy" type="{http://schema.cmo.anz/PmtAdd}string3" minOccurs="0"/>
 *         &lt;element name="Amt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Narrative" type="{http://schema.cmo.anz/PmtAdd}string255" minOccurs="0"/>
 *         &lt;element name="PmtAuthMethod" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://schema.cmo.anz/PmtAdd}string20">
 *               &lt;enumeration value="AFPONLY"/>
 *               &lt;enumeration value="FORCEDEBIT"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.From_Type", propOrder = {
    "acctId",
    "acctSys",
    "acctGrp",
    "branch",
    "costCentre",
    "ccy",
    "amt",
    "narrative",
    "pmtAuthMethod"
})
@Data
public class ComAnzFromType {

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

  @XmlElement(name = "Ccy")
  protected String ccy;

  @XmlElement(name = "Amt")
  protected BigDecimal amt;

  @XmlElement(name = "Narrative")
  protected String narrative;

  @XmlElement(name = "PmtAuthMethod")
  protected String pmtAuthMethod;

}
