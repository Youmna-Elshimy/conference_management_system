package CSCI334.ConferencePro.controllers;

import CSCI334.ConferencePro.entities.ResearchPaper;
import CSCI334.ConferencePro.entities.Review;
import CSCI334.ConferencePro.entities.Reviewer;
import CSCI334.ConferencePro.persistence.ResearchPaperPersistence;
import CSCI334.ConferencePro.persistence.ReviewPersistence;
import CSCI334.ConferencePro.persistence.ReviewerPersistence;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {

  private final ReviewPersistence persistence;
  private final ReviewerPersistence reviewerPersistence;
  private final ResearchPaperPersistence paperPersistence;

  public ReviewController(ReviewPersistence persistance, ResearchPaperPersistence paperPersistence,
      ReviewerPersistence reviewerPersistence) {
    this.persistence = persistance;
    this.paperPersistence = paperPersistence;
    this.reviewerPersistence = reviewerPersistence;
  }

  // Create review on a given research paper id (e.g. url =
  // .../reviews/abd7137f8ds7123bf9fg9)
  @PostMapping("/{id}")
  public ResponseEntity<Review> createReviewOnPaper(@RequestBody Review review, @PathVariable String id) {
    Optional<Review> newReview = persistence.createReviewOnPaper(review, id);
    // if review is returned then review was saved properly
    if (newReview.isPresent()) {
      return new ResponseEntity<>(newReview.get(), HttpStatus.CREATED);
    } else { // else the paper with given id was not found
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
  }

  // Get all reviews
  @GetMapping("/all")
  public ResponseEntity<List<Review>> getAllReviews() {
    try {
      List<Review> reviewList = persistence.getAllReviews();

      if (reviewList.size() > 0) {
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
      } else if (reviewList.size() == 0) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // shouldn't make it to here, but if it does
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Get review
  @GetMapping("{id}")
  public ResponseEntity<Review> getReview(@RequestParam String id) {
    // try to find review
    Optional<Review> reviewData = persistence.getReview(id);

    // no review with id provided exists in the DB, return not found
    if (!reviewData.isPresent()) {
      System.out.println("Couldn't find review id:" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else { // else review must exist, return comment
      return new ResponseEntity<>(reviewData.get(), HttpStatus.OK);
    }
  }

  // get all papers assigned to a reviewer
  @PostMapping("/papersReviewedBy")
  public ResponseEntity<List<ResearchPaper>> getPapersReviewedBy(@RequestBody Reviewer reviewer) {
    // try to find review
    List<ResearchPaper> papers = paperPersistence.getAllPapersReviewedBy(reviewer);

    if (papers.size() > 0) {
      return new ResponseEntity<>(papers, HttpStatus.OK);
    } else if (papers.size() == 0) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // shouldn't make it to here, but if it does
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // get all assigned papers (3/3 reviewers)
  @GetMapping("/getAllAssignedPapers")
  public ResponseEntity<List<ResearchPaper>> getAllAssignedPapers() {
    // try to find review
    List<ResearchPaper> papers = paperPersistence.getAllAssignedPapers();

    if (papers.size() > 0) {
      return new ResponseEntity<>(papers, HttpStatus.OK);
    } else if (papers.size() == 0) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // shouldn't make it to here, but if it does
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // get all unassigned papers (<3 reviewers)
  @GetMapping("/getAllUnassignedPapers")
  public ResponseEntity<List<ResearchPaper>> getAllUnassignedPapers() {
    // try to find review
    List<ResearchPaper> papers = paperPersistence.getAllUnassignedPapers();

    if (papers.size() > 0) {
      return new ResponseEntity<>(papers, HttpStatus.OK);
    } else if (papers.size() == 0) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // shouldn't make it to here, but if it does
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Update review data
  @PutMapping("")
  public ResponseEntity<Review> updateReview(@RequestBody Review newReviewData) {
    // update review with provided data
    Optional<Review> updatedReview = persistence.updateReview(newReviewData);

    // review will be present if successfully updated, empty if not
    if (updatedReview.isPresent()) {
      return new ResponseEntity<>(updatedReview.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Assign paper (by id) to a given reviewer email
  @PutMapping("/assignPaper/{id}")
  public ResponseEntity<String> assignPaper(@RequestBody Reviewer reviewer, @PathVariable String id) {
    // update reviewer and research paper with provided data
    Optional<Reviewer> assignedReviewer = reviewerPersistence.assignPaper(reviewer.getEmail(), id);

    // review will be present if successfully updated, empty if not
    if (assignedReviewer.isPresent()) {
      return new ResponseEntity<>("Paper assigned to reviewer.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete review
  @DeleteMapping("")
  public ResponseEntity<String> deleteReview(@RequestBody Review review) {
    // call delete review and save return message
    Optional<String> returnMessage = persistence.deleteReview(review);

    // if message is present then review was deleted, otherwise review wasn't found
    if (returnMessage.isPresent()) {
      return new ResponseEntity<>(returnMessage.get(), HttpStatus.OK);
    } else {
      Optional<String> notFoundMessage = Optional.of("Review not found in database");
      return new ResponseEntity<>(notFoundMessage.get(), HttpStatus.NOT_FOUND);
    }
  }
}
