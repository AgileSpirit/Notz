package com.agile.spirit.notz.domain;

import static com.agile.spirit.notz.domain.Note.FIND_NOTES_BY_USER;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="note")
@Entity
@Table(name="NTZ_NOTES")
@NamedQueries({@NamedQuery(name=FIND_NOTES_BY_USER, query="FROM Note n WHERE n.email=:email")})
public class Note extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /*
   * NAMED QUERIES
   */
  public static final String FIND_NOTES_BY_USER = "findNotesByUser";
  
  /*
   * ATTRIBUTES
   */

  private String title;
  private String description;

  /*
   * CONSTRUCTORS
   */

  public static Note create() {
    return create("", "");
  }

  public static Note create(String title, String description) {
    Note note = new Note();
    note.title = title;
    note.description = description;
    return note;
  }
  
  
  /*
   * ACCESSORS
   */
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  
  /*
   * BEHAVIORS
   */

  @Override
  public String toString() {
    return "Note [title=" + title + ", description=" + description + "]";
  }
  
}
