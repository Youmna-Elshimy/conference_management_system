package CSCI334.ConferencePro.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.ResearchPaper;
import CSCI334.ConferencePro.entities.Review;
import CSCI334.ConferencePro.persistence.repos.ResearchPaperRepository;
import CSCI334.ConferencePro.persistence.repos.ReviewRepository;

@Component
public class ReviewPersistence {
    private ReviewRepository reviewRepository;
    private ResearchPaperRepository paperRepository;

    public ReviewPersistence(ReviewRepository reviewRepository, ResearchPaperRepository paperRepository) {
        this.reviewRepository = reviewRepository;
        this.paperRepository = paperRepository;
    }

    public Optional<Review> createReviewOnPaper(Review review, String paperId) {
        // find research paper that review was created for
        Optional<ResearchPaper> paper = paperRepository.findById(paperId);

        // if paper doesn't exist then return an empty
        if (!paper.isPresent()) {
            return Optional.empty();
        }

        // save review
        Review newReview = reviewRepository.save(
                new Review(
                        review.getReviewerEmail(),
                        review.getScore(),
                        review.getTextReview()));

        // paper must exist, so update the paper with the new review id
        paper.get().addReview(newReview.getId());
        paperRepository.save(paper.get());

        return Optional.of(newReview);
    }

    // READ
    // All reviews
    public List<Review> getAllReviews() {
        // load all comments in DB to new list
        List<Review> allReviews = new ArrayList<Review>();
        reviewRepository.findAll().forEach(allReviews::add);

        return allReviews;
    }

    // One review
    public Optional<Review> getReview(String id) {
        // try to find review in DB with given id
        Optional<Review> reviewData = reviewRepository.findById(id);

        // no review with id provided exists in the DB, return empty optional
        if (!reviewData.isPresent()) {
            return Optional.empty();
        } else { // else review exists, so return it
            return reviewData;
        }
    }

    // UPDATE
    public Optional<Review> updateReview(Review newReviewData) {
        // try to find review in DB with given id
        Optional<Review> foundReview = reviewRepository.findById(newReviewData.getId());

        // if review is found then update review info and return newly saved data
        if (foundReview.isPresent()) {
            Review newReview = foundReview.get();
            if (newReviewData.getReviewerEmail() != null)
                newReview.setReviewerEmail(newReviewData.getReviewerEmail());
            //if (newReviewData.getScore() > -5 && newReviewData.getScore() < 5)
                newReview.setScore(newReviewData.getScore());
            if (newReviewData.getTextReview() != null)
                newReview.setTextReview(newReviewData.getTextReview());
            if (newReviewData.getRatingIds() != null)
                newReview.setRatingIds(newReviewData.getRatingIds());
            Review updatedReview = reviewRepository.save(newReview);
            return Optional.of(updatedReview);
        } else { // else return empty
            return Optional.empty();
        }
    }

    // DELETE
    public Optional<String> deleteReview(Review review) {
        // try to find review in DB with given id
        Optional<Review> foundReview = reviewRepository.findById(review.getId());
        if (!foundReview.isPresent()) {
            System.out.println("Review with id:" + review.getId() + " was not found.");
            return Optional.empty();
        }

        // find research paper that review was created on
        Optional<ResearchPaper> paper = paperRepository.findByReviewId(foundReview.get().getId());

        // if paper exists then update the paper to remove the review id
        if (paper.isPresent()) {
            paper.get().removeReview(foundReview.get().getId());
            paperRepository.save(paper.get());
        }

        // delete it if found, return message
        if (foundReview.isPresent()) {
            reviewRepository.deleteById(foundReview.get().getId());
            return Optional.of("Review with id:" + foundReview.get().getId() + " deleted successfully");
        } else { // else return empty
            return Optional.empty();
        }
    }
}
