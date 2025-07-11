package CSCI334.ConferencePro.persistence.repos;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import CSCI334.ConferencePro.entities.ConferenceChair;

public interface ConfChairRepository extends MongoRepository<ConferenceChair, String> {
  public Optional<ConferenceChair> findByEmail(String email);
}
