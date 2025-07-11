package CSCI334.ConferencePro.controllers;

import CSCI334.ConferencePro.entities.Comment;
import CSCI334.ConferencePro.persistence.CommentPersistence;

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
@RequestMapping("/comments")
public class CommentController {

  private final CommentPersistence persistence;

  public CommentController(CommentPersistence persistance) {
    this.persistence = persistance;
  }


  // Create comment on a given review id (e.g. url = .../reviews/abd7137f8ds7123bf9fg9)
  @PostMapping("/{id}")
  public ResponseEntity<Comment> createCommentOnReview(@RequestBody Comment comment, @PathVariable String id) {
    Optional<Comment> newComment = persistence.createCommentOnReview(comment, id);
    if (newComment.isPresent()) {
      return new ResponseEntity<>(newComment.get(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
  }

  // Get all comments
  @GetMapping("/all")
  public ResponseEntity<List<Comment>> getAllComments() {
    try {
      List<Comment> commentList = persistence.getAllComments();

      if (commentList.size() > 0) {
        return new ResponseEntity<>(commentList, HttpStatus.OK);
      } else if (commentList.size() == 0) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // shouldn't make it to here, but if it does
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Get comment
  @PostMapping("/find")
  public ResponseEntity<Comment> getComment(@RequestBody Comment comment) {
    // try to find comment
    Optional<Comment> commentData = persistence.getComment(comment);

    // no comment with id provided exists in the DB, return not found
    if (!commentData.isPresent()) {
      System.out.println("Couldn't find comment id:" + comment.getId());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else { // else comment must exist, return comment
      return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
    }
  }

  // Update comment data
  @PutMapping("")
  public ResponseEntity<Comment> updateComment(@RequestBody Comment newCommentData) {
    // update comment with provided data
    Optional<Comment> updatedComment = persistence.updateComment(newCommentData);

    // comment will be present if successfully updated, empty if not
    if (updatedComment.isPresent()) {
      return new ResponseEntity<>(updatedComment.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete comment
  @DeleteMapping("")
  public ResponseEntity<String> deleteComment(@RequestBody Comment comment) {
    // call delete comment and save return message
    Optional<String> returnMessage = persistence.deleteComment(comment);

    // if message is present then comment was deleted, otherwise comment wasn't found
    if (returnMessage.isPresent()) {
      return new ResponseEntity<>(returnMessage.get(), HttpStatus.OK);
    } else {
      Optional<String> notFoundMessage = Optional.of("Comment not found in database");
      return new ResponseEntity<>(notFoundMessage.get(), HttpStatus.NOT_FOUND);
    }
  }
}
