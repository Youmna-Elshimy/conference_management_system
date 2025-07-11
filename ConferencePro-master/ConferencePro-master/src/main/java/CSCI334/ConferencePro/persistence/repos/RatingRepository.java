package CSCI334.ConferencePro.persistence.repos;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import CSCI334.ConferencePro.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
  public Optional<Rating> findByAuthorEmail(String authorEmail);
}
