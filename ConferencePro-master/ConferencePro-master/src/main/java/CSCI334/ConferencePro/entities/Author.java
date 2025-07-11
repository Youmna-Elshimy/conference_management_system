package CSCI334.ConferencePro.entities;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;

import CSCI334.ConferencePro.persistence.AuthorPersistence;

@Document(collection = "authors")
public class Author extends User {  
  private String title;
  private String organisation;


  public Author() {
    super();
    title = "";
    organisation = "";
  }

  // constructor without id, one will be generated in DB
  public Author(String email, String password, String firstName, String lastName, String userType, String title, String organisation) {
    super(email, password, firstName, lastName, userType);
    this.title = title;
    this.organisation = organisation;
  }

  // constructor with id
  public Author(String id, String email, String password, String firstName, String lastName, String userType, String title, String organisation) {
    super(id, email, password, firstName, lastName, userType);
    this.title = title;
    this.organisation = organisation;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOrganisation() {
    return this.organisation;
  }

  public void setOrganisation(String organisation) {
    this.organisation = organisation;
  }

  public Author title(String title) {
    setTitle(title);
    return this;
  }

  public Author organisation(String organisation) {
    setOrganisation(organisation);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Author)) {
            return false;
        }
        Author author = (Author) o;
        return Objects.equals(title, author.title) && Objects.equals(organisation, author.organisation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, organisation);
  }

  @Override
  public String toString() {
    return "{" +
      " title='" + getTitle() + "'" +
      ", organisation='" + getOrganisation() + "'" +
      "}";
  }

  // persistence
  public Optional<Author> create(AuthorPersistence persistence) {
    return persistence.create(this);
  }
  public List<Author> getAll(AuthorPersistence persistence) {
    return persistence.getAll();
  }
  public Optional<Author> get(AuthorPersistence persistence) {
    return persistence.get(this);
  }
  public Optional<Author> update(AuthorPersistence persistence) {
    return persistence.update(this);
  }
  public Optional<String> delete(AuthorPersistence persistence) {
    return persistence.delete(this);
  }
}
