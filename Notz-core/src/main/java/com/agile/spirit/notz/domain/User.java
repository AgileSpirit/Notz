package com.agile.spirit.notz.domain;

import static com.agile.spirit.notz.domain.User.FIND_USERS_BY_EMAIL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "NTZ_USERS")
@NamedQueries({ @NamedQuery(name = FIND_USERS_BY_EMAIL, query = "FROM User u WHERE u.email=:email") })
public class User extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /*
   * NAMED QUERIES
   */
  public static final String FIND_USERS_BY_EMAIL = "findUsersByEmail";

  /*
   * ATTRIBUTES
   */

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Note.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  @OrderBy("modificationDate DESC, creationDate DESC")
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
    return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", notes="
        + notes.size() + "]";
  }

}
