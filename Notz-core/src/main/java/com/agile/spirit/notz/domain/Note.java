package com.agile.spirit.notz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "note")
@Entity
@Table(name = "NTZ_NOTES")
public class Note extends BaseEntity {

  private static final long serialVersionUID = 1L;

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

  public static Note create(Note note) {
    if (note == null) {
      return create();
    }
    Note newNote = create(note.getTitle(), note.getDescription());
    newNote.setCreationDate(note.getCreationDate());
    newNote.setModificationDate(note.getModificationDate());
    return newNote;
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

  @Column
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column
  @Lob
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
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (object instanceof Note) {
      Note note = (Note) object;
      return this.getId().equals(note.getId());
    }
    return false;
  }

  @Override
  public String toString() {
    return "{\"id\":\"" + getId() + "\", \"title\":\"" + title + "\", \"description\":\"" + description + "\"}";
  }

}
