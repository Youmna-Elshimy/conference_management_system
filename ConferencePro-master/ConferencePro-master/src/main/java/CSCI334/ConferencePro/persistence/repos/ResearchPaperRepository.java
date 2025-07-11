package CSCI334.ConferencePro.persistence.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import CSCI334.ConferencePro.entities.ResearchPaper;

public interface ResearchPaperRepository extends MongoRepository<ResearchPaper, String> {
  @Query("{ 'authors' : ?0 }")
  public List<ResearchPaper> findByPaperAuthor(String authorEmail);

  @Query("{ 'reviewIds' : ?0 }")
  public Optional<ResearchPaper> findByReviewId(String reviewId);

  //@Query("{ 'keywords' : ?0 }")
  public List<ResearchPaper> findByKeywordsIn(List<String> keywords);

  public List<ResearchPaper> findByReviewers(String reviewerEmail);
}
