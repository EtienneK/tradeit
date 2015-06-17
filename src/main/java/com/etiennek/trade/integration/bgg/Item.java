package com.etiennek.trade.integration.bgg;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

  @XmlAttribute(name = "id")
  private long externalId;
  @XmlAttribute
  private String type;
  @XmlElement(name = "name")
  private List<Name> names;
  @XmlElement(name = "yearpublished")
  private ValueObject yearPublished;

  public long getExternalId() {
    return externalId;
  }

  public String getType() {
    return type;
  }

  public List<Name> getNames() {
    return names;
  }

  public Long getYearPublished() {
    if (yearPublished == null) {
      return null;
    }
    return yearPublished.getValueAsLong();
  }

  @Override
  public String toString() {
    return "Item [externalId=" + externalId + ", type=" + type + ", names=" + names + ", yearPublished="
        + yearPublished + "]";
  }

}
