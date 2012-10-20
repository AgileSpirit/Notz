package com.agile.spirit.notz.domain;

import static com.agile.spirit.notz.domain.User.FIND_USERS_BY_LOGIN;
import static com.agile.spirit.notz.domain.User.LIST_USERS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "user")
@Entity
@Table(name = "NTZ_USERS")
@NamedQueries({ @NamedQuery(name = FIND_USERS_BY_LOGIN, query = "FROM User u WHERE u.username=:login OR u.email=:login"),
    @NamedQuery(name = LIST_USERS, query = "FROM User u") })
public class User extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /*
   * NAMED QUERIES
   */
  public static final String FIND_USERS_BY_LOGIN = "findUsersByLogin";
  public static final String LIST_USERS = "listUsers";

  /*
   * ATTRIBUTES
   */

  private String username;
  private String completeName;
  private String email;
  private String password;
  private List<Note> notes;

  /*
   * CONSTRUCTORS
   */

  public static User create() {
    return create("", "", "", "");
  }

  public static User create(String username, String email, String password) {
    return create(username, "", email, password);
  }

  public static User create(String username, String completeName, String email, String password) {
    User user = new User();
    user.username = username;
    user.completeName = completeName;
    user.email = email;
    user.password = password;
    user.notes = new ArrayList<Note>();
    return user;
  }

  /*
   * ACCESSORS
   */

  public String getCompleteName() {
    return completeName;
  }

  public void setCompleteName(String completeName) {
    this.completeName = completeName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(nullable = false, unique = true)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @OneToMany(cascade = { CascadeType.ALL }, targetEntity = Note.class, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
  @OrderBy("modificationDate DESC, creationDate DESC")
  @XmlTransient
  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  /*
   * BEHAVIORS
   */

  public void merge(User other) {
    if (other != null) {
      this.completeName = other.getCompleteName();
      this.username = other.getUsername();
      this.email = other.getEmail();
      this.password = other.getPassword();
    }
  }

  @Override
  public String toString() {
    return "User [username=" + username + ", completeName=" + completeName + ", email=" + email + ", password=" + password + ", notes="
        + (notes != null ? notes.size() : "null") + "]";
  }

}
