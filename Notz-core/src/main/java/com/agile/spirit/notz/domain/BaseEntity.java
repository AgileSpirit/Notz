package com.agile.spirit.notz.domain;

import java.io.Serializable;
import java.util.Date;

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

  /**
   * In order to avoid Cycle during JAXB XML (un-)marshalling, one must have totaly unique ID for entities. First type of IdS was Integer
   * but during research in order to fix cycle problems, one solution was to use XmlID and XmlIDRef annotation. The fact was that these
   * annotations only accept String IdS. Another fact was that JPA does not provide an implementation for the
   * "auto strategy of IdS generation". It is why the annotation <code>@GenericGenerator(name = "system-uuid", strategy = "uuid")</code>
   * using the vendor implementation (Hibernate) is set.
   */
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Version
  protected Integer version;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
