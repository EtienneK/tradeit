package com.etiennek.trade.integration.bgg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Name extends ValueObject {

  @XmlAttribute
  private String type;

  @Override
  public String toString() {
    return "Name [type=" + type + ", toString()=" + super.toString() + "]";
  }

}
