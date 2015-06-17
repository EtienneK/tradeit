package com.etiennek.trade.integration.bgg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ValueObject {
  @XmlAttribute
  private String value;

  public String getValueAsString() {
    return value;
  }

  public Integer getValueAsInteger() {
    return value == null ? null : Integer.parseInt(value);
  }

  public Long getValueAsLong() {
    return value == null ? null : Long.parseLong(value);
  }

  @Override
  public String toString() {
    return "ValueObject [value=" + value + "]";
  }
  
}
