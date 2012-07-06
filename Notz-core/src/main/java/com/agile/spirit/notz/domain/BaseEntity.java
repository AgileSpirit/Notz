package com.agile.spirit.notz.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@MappedSuperclass
public class BaseEntity implements Serializable, Comparable<BaseEntity> {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Integer id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(Date modificationDate) {
    this.modificationDate = modificationDate;
  }

  @Override
  public int compareTo(BaseEntity compared) {
    if (compared == null)
      return 1;

    Integer id1 = this.getId();
    Integer id2 = ((BaseEntity) compared).getId();

    if (id1 == null) {
      if (id2 == null)
        return 0;
      return -1;
    }
    if (id2 == null)
      return 1;

    if (id2 > id1)
      return -1;
    else if (id2 == id1)
      return 0;
    else
      return 1;
  }

}
