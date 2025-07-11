package CSCI334.ConferencePro.persistence.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import CSCI334.ConferencePro.entities.Reviewer;

public interface ReviewerRepository extends MongoRepository<Reviewer, String> {
  public Optional<Reviewer> findByEmail(String email);
  public List<Reviewer> findBySubjectInterests(String interest);
}
