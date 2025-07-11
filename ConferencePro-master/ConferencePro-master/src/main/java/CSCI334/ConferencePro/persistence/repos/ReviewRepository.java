package CSCI334.ConferencePro.persistence.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import CSCI334.ConferencePro.entities.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
  public Optional<List<Review>> findByReviewerEmail(String email);
  public Optional<Review> findByRatingIds(String ratingId);

  @Query("{ 'commentIds' : ?0 }")
  public Optional<Review> findByCommentId(String commentId);
}
