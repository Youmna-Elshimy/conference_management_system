package CSCI334.ConferencePro.persistence.repos;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import CSCI334.ConferencePro.entities.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
  public Optional<Author> findByEmail(String email);
}
