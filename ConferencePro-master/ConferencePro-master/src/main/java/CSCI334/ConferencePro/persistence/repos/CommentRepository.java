package CSCI334.ConferencePro.persistence.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import CSCI334.ConferencePro.entities.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
  
}
