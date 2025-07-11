package CSCI334.ConferencePro.controllers;

import CSCI334.ConferencePro.entities.Rating;
import CSCI334.ConferencePro.persistence.RatingPersistence;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/ratings")
public class RatingController {
  private final RatingPersistence persistence;

  public RatingController(RatingPersistence persistence) {
    this.persistence = persistence;
  }


  // Create rating on a given research paper id (e.g. url = .../reviews/abd7137f8ds7123bf9fg9)
  @PostMapping("/{id}")
  public ResponseEntity<Rating> createRatingOnReview(@RequestBody Rating rating, @PathVariable String id) {
    Optional<Rating> newRating = persistence.createRatingOnReview(rating, id);
    if (newRating.isPresent()) {
      return new ResponseEntity<>(newRating.get(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
  }

  // Get all ratings
  @GetMapping("/all")
  public ResponseEntity<List<Rating>> getAllRatings() {
    try {
      List<Rating> ratingList = persistence.getAllRatings();

      if (ratingList.size() > 0) {
        return new ResponseEntity<>(ratingList, HttpStatus.OK);
      } else if (ratingList.size() == 0) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // shouldn't make it to here, but if it does
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Get rating
  @PostMapping("/find")
  public ResponseEntity<Rating> getRating(@RequestBody Rating rating) {
    // try to find rating
    Optional<Rating> ratingData = persistence.getRating(rating);

    // no rating with id provided exists in the DB, return not found
    if (!ratingData.isPresent()) {
      System.out.println("Couldn't find rating id:" + rating.getId());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else { // else rating must exist, return rating
      return new ResponseEntity<>(ratingData.get(), HttpStatus.OK);
    }
  }

  // Update rating data
  @PutMapping("")
  public ResponseEntity<Rating> updateRating(@RequestBody Rating newRatingData) {
    // update rating with provided data
    Optional<Rating> updatedRating = persistence.updateRating(newRatingData);

    // rating will be present if successfully updated, empty if not
    if (updatedRating.isPresent()) {
      return new ResponseEntity<>(updatedRating.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete rating
  @DeleteMapping("")
  public ResponseEntity<String> deleteRating(@RequestBody Rating rating) {
    // call delete rating and save return message
    Optional<String> returnMessage = persistence.deleteRating(rating);

    // if message is present then rating was deleted, otherwise rating wasn't found
    if (returnMessage.isPresent()) {
      return new ResponseEntity<>(returnMessage.get(), HttpStatus.OK);
    } else {
      Optional<String> notFoundMessage = Optional.of("Rating not found in database");
      return new ResponseEntity<>(notFoundMessage.get(), HttpStatus.NOT_FOUND);
    }
  }
}
