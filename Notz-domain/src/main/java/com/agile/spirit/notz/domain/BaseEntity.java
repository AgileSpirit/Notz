package com.agile.spirit.notz.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseEntity implements Serializable, Comparable<BaseEntity> {

  private static final long serialVersionUID = 1L;

  private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public int compareTo(BaseEntity compared) {
    if (compared == null) return 1;
    
    Integer id1 = this.getId();
    Integer id2 = ((BaseEntity) compared).getId(); 
    
    if (id1 == null) {
      if (id2 == null) return 0;
      return -1;
    }
    if (id2 == null) return 1;
    
    if (id2 > id1)  return -1; 
    else if (id2 == id1) return 0; 
    else return 1; 
  }

}
