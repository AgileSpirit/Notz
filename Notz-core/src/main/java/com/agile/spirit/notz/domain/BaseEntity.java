package com.agile.spirit.notz.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseEntity implements Serializable, Comparable<BaseEntity> {

  private static final long serialVersionUID = 1L;

  /*
   * ATTRIBUTES
   */

  private String id;
  protected Integer version;
  private Date creationDate;
  private Date modificationDate;

  /*
   * ACCESSORS
   */

  /**
   * In order to avoid Cycle during JAXB XML (un-)marshaling, one must have totally unique ID for entities. First type of IdS was Integer
   * but during research in order to fix cycle problems, one solution was to use XmlID and XmlIDRef annotation. The fact was that these
   * annotations only accept String IdS. Another fact was that JPA does not provide an implementation for the
   * "auto strategy of IdS generation". It is why the annotation <code>@GenericGenerator(name = "system-uuid", strategy = "uuid")</code>
   * using the vendor implementation (Hibernate) is set.
   */
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  public Date getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(Date modificationDate) {
    this.modificationDate = modificationDate;
  }

  @Version
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public int compareTo(BaseEntity compared) {
    if (compared == null)
      return 1;

    String id1 = this.getId();
    String id2 = compared.getId();

    if (id1 == null) {
      if (id2 == null)
        return 0;
      return -1;
    }
    if (id2 == null)
      return 1;

    return id1.compareTo(id2);
  }

}
