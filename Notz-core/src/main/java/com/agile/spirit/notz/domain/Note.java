package com.agile.spirit.notz.domain;

import static com.agile.spirit.notz.domain.Note.FIND_NOTES_BY_USER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "note")
@Entity
@Table(name = "NTZ_NOTES")
@NamedQueries({ @NamedQuery(name = FIND_NOTES_BY_USER, query = "FROM Note n WHERE n.user.id=:userId ORDER BY n.modificationDate DESC, n.creationDate DESC") })
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
  private User user;

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

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "userId", updatable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  /*
   * BEHAVIORS
   */

  @Override
  public String toString() {
    return "Note [title=" + title + ", description=" + description + "]";
  }

}
