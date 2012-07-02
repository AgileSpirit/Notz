package com.agile.spirit.notz.domain;

import java.util.ArrayList;
import java.util.List;


public class User extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  /*
   * ATTRIBUTES
   */
  
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private List<Note> notes;

  /*
   * CONSTRUCTORS
   */

  public static User create() {
    return create("", "", "", "");
  }

  public static User create(String firstName, String lastName, String email, String password) {
    User user = new User();
    user.firstName = firstName;
    user.lastName = lastName;
    user.email = email;
    user.password = password;
    user.notes = new ArrayList<Note>();
    return user;
  }

  /*
   * ACCESSORS
   */
  
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  /*
   * BEHAVIORS
   */

  @Override
  public String toString() {
    return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
        + ", notes=" + notes.size() + "]";
  }

}
