package com.anz.magneto.model.payment;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Java class for com.anz.Returned_Type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="com.anz.Returned_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DRBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CRBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DRLedgerBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CRLedgerBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PaymentReference" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="PODSId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="ClearMethod" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="ClearSubMethod" type="{http://schema.cmo.anz/PmtAdd}string16" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://schema.cmo.anz/PmtAdd}com.anz.Msg_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LienId" type="{http://schema.cmo.anz/PmtAdd}string36" minOccurs="0"/>
 *         &lt;element name="Timestamps" type="{http://schema.cmo.anz/PmtAdd}com.anz.Timestamps_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FX" type="{http://schema.cmo.anz/PmtAdd}com.anz.FX_Response_Type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com.anz.Returned_Type", propOrder = {
    "drBalance",
    "crBalance",
    "drLedgerBalance",
    "crLedgerBalance",
    "paymentReference",
    "podsId",
    "clearMethod",
    "clearSubMethod",
    "message",
    "lienId",
    "timestamps",
    "fx"
})
@Data
public class ComAnzReturnedType {

  @XmlElement(name = "DRBalance")
  BigDecimal drBalance;

  @XmlElement(name = "CRBalance")
  BigDecimal crBalance;

  @XmlElement(name = "DRLedgerBalance")
  BigDecimal drLedgerBalance;

  @XmlElement(name = "CRLedgerBalance")
  BigDecimal crLedgerBalance;

  @XmlElement(name = "PaymentReference")
  String paymentReference;

  @XmlElement(name = "PODSId")
  String podsId;

  @XmlElement(name = "ClearMethod")
  String clearMethod;

  @XmlElement(name = "ClearSubMethod")
  String clearSubMethod;

  @XmlElement(name = "Message")
  List<ComAnzMsgType> message;

  @XmlElement(name = "LienId")
  String lienId;

  @XmlElement(name = "Timestamps")
  List<ComAnzTimestampsType> timestamps;

  @XmlElement(name = "FX")
  List<ComAnzFXResponseType> fx;
}
