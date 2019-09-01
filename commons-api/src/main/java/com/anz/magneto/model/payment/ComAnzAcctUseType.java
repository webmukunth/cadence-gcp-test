package com.anz.magneto.model.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import lombok.ToString;


/**
 * <p>Java class for com.anz.AcctUse_Type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="com.anz.AcctUse_Type">
 *   &lt;restriction base="{http://schema.cmo.anz/PmtAdd}string10">
 *     &lt;enumeration value="BUSINESS"/>
 *     &lt;enumeration value="SUSPENSE"/>
 *     &lt;enumeration value="PERSONAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "com.anz.AcctUse_Type")
@XmlEnum
@ToString
public enum ComAnzAcctUseType {

  BUSINESS,
  SUSPENSE,
  PERSONAL;

  public static ComAnzAcctUseType fromValue(String v) {
    return valueOf(v);
  }

  public String value() {
    return name();
  }

}