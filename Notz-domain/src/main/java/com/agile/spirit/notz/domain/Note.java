package com.agile.spirit.notz.domain;

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
