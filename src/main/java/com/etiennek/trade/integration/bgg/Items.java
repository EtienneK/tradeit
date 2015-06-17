package com.etiennek.trade.integration.bgg;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Items {
  @XmlElement(name = "item")
  private List<Item> items;

  public List<Item> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "Items [items=" + items + "]";
  }

}
