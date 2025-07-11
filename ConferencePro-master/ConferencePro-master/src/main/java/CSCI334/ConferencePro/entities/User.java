package CSCI334.ConferencePro.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public abstract class User {
  @Id
  private String id;

  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String userType;

  public User() {
    this("0", "", "", "", "", "");
  }

  // constructor without id. One will be generated.

  public User(String email, String password, String firstName, String lastName, String userType) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userType = userType;
  }

  // constructor with id
  public User(String id, String email, String password, String firstName, String lastName, String userType) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userType = userType;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserType() {
    return this.userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public User id(String id) {
    setId(id);
    return this;
  }

  public User email(String email) {
    setEmail(email);
    return this;
  }

  public User password(String password) {
    setPassword(password);
    return this;
  }

  public User firstName(String firstName) {
    setFirstName(firstName);
    return this;
  }

  public User lastName(String lastName) {
    setLastName(lastName);
    return this;
  }

  public User userType(String userType) {
    setUserType(userType);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userType, user.userType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, firstName, lastName, userType);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", firstName='" + getFirstName() + "'" +
      ", lastName='" + getLastName() + "'" +
      ", userType='" + getUserType() + "'" +
      "}";
  }
}
