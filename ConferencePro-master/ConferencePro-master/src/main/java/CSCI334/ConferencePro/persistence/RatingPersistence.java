package CSCI334.ConferencePro.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.Rating;
import CSCI334.ConferencePro.entities.Review;
import CSCI334.ConferencePro.persistence.repos.RatingRepository;
import CSCI334.ConferencePro.persistence.repos.ReviewRepository;

@Component
public class RatingPersistence {
    private RatingRepository ratingRepository;
    private ReviewRepository reviewRepository;

    public RatingPersistence(RatingRepository ratingRepository, ReviewRepository reviewRepository) {
        this.ratingRepository = ratingRepository;
        this.reviewRepository = reviewRepository;
    }

    // Create rating on review with given id
    public Optional<Rating> createRatingOnReview(Rating rating, String reviewId) {
        // find research review that rating was created on
        Optional<Review> review = reviewRepository.findById(reviewId);

        // if review doesn't exist then return an empty
        if (!review.isPresent()) {
            return Optional.empty();
        }

        // save rating
        Rating newRating = ratingRepository.save(
                new Rating(rating.getRating(), rating.getAuthorEmail()));

        // review must exist, so update the review with the new rating id
        review.get().addRating(newRating.getId());
        reviewRepository.save(review.get());

        return Optional.of(newRating);
    }

    // All ratings
    public List<Rating> getAllRatings() {
        // load all ratings in DB to new list
        List<Rating> allRatings = new ArrayList<Rating>();
        ratingRepository.findAll().forEach(allRatings::add);

        return allRatings;
    }

    // One rating
    public Optional<Rating> getRating(Rating rating) {
        // try to find rating in DB with given id
        Optional<Rating> ratingData = ratingRepository.findById(rating.getId());

        // no rating with id provided exists in the DB, return empty optional
        if (!ratingData.isPresent()) {
            return Optional.empty();
        } else { // else rating exists, so return it
            return ratingData;
        }
    }

    // UPDATE
    public Optional<Rating> updateRating(Rating newRatingData) {
        // try to find rating in DB with given id
        Optional<Rating> foundRating = ratingRepository.findById(newRatingData.getId());

        // if rating is found then update rating info and return newly saved data
        if (foundRating.isPresent()) {
            Rating newRating = foundRating.get();
            //if (newRatingData.getRating() > -5 && newRatingData.getRating() < 5)
                newRating.setRating(newRatingData.getRating());
            if (newRatingData.getAuthorEmail() != null)
                newRating.setAuthorEmail(newRatingData.getAuthorEmail());
            Rating updatedRating = ratingRepository.save(newRating);
            return Optional.of(updatedRating);
        } else { // else return empty
            return Optional.empty();
        }
    }

    // DELETE
    public Optional<String> deleteRating(Rating rating) {
        // try to find rating in DB with given id
        Optional<Rating> foundRating = ratingRepository.findById(rating.getId());
        if (!foundRating.isPresent()) {
            System.out.println("Rating with id:" + rating.getId() + " was not found.");
            return Optional.empty();
        }

        // find research review that rating was created on
        Optional<Review> review = reviewRepository.findByRatingIds(foundRating.get().getId());

        // if review exists then update the review to remove the rating id
        if (review.isPresent()) {
            review.get().removeRating(foundRating.get().getId());
            reviewRepository.save(review.get());
        }

        // delete it if found, return message
        if (foundRating.isPresent()) {
            ratingRepository.deleteById(foundRating.get().getId());
            return Optional.of("Rating with id:" + foundRating.get().getId() + " deleted successfully");
        } else { // else return empty
            return Optional.empty();
        }
    }
}
