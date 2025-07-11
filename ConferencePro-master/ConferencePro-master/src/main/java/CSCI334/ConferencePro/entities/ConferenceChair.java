package CSCI334.ConferencePro.entities;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;

import CSCI334.ConferencePro.persistence.ConfChairPersistence;

@Document(collection = "conferenceChairs")
public class ConferenceChair extends User {

  public ConferenceChair() {
    super();
  }

  // constructor without id, one will be generated in DB
  public ConferenceChair(String email, String password, String firstName, String lastName, String userType) {
    super(email, password, firstName, lastName, userType);
  }

  public ConferenceChair(String id, String email, String password, String firstName, String lastName, String userType) {
    super(id, email, password, firstName, lastName, userType);
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ConferenceChair)) {
            return false;
        }
        ConferenceChair conferenceChair = (ConferenceChair) o;
        return Objects.equals(this, conferenceChair);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "{" +
      "}";
  }

  // persistence
  public Optional<ConferenceChair> create(ConfChairPersistence persistence) {
    return persistence.create(this);
  }
  public List<ConferenceChair> getAll(ConfChairPersistence persistence) {
    return persistence.getAll();
  }
  public Optional<ConferenceChair> get(ConfChairPersistence persistence) {
    return persistence.get(this);
  }
  public Optional<ConferenceChair> update(ConfChairPersistence persistence) {
    return persistence.update(this);
  }
  public Optional<String> delete(ConfChairPersistence persistence) {
    return persistence.delete(this);
  }
}
