package CSCI334.ConferencePro.controllers;

import CSCI334.ConferencePro.entities.Author;
import CSCI334.ConferencePro.entities.CompleteUserData;
import CSCI334.ConferencePro.entities.ConferenceChair;
import CSCI334.ConferencePro.entities.Reviewer;
import CSCI334.ConferencePro.entities.User;
import CSCI334.ConferencePro.factories.UsersFactory;
import CSCI334.ConferencePro.persistence.AuthorPersistence;
import CSCI334.ConferencePro.persistence.ConfChairPersistence;
import CSCI334.ConferencePro.persistence.ReviewerPersistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserControllerV2 {
  @Autowired
  UsersFactory usersFactory;

  @Autowired
  private ReviewerPersistence reviewerPersistence;
  @Autowired
  private AuthorPersistence authorPersistence;
  @Autowired
  private ConfChairPersistence confChairPersistence;

  public UserControllerV2() {
  }

  // Sign up user
  @PostMapping("")
  public ResponseEntity<?> createUser(@RequestBody CompleteUserData user) {
    // create user via user factory
    User newUser = usersFactory.createAppropriateUser(user);

    // if user is a reviewer then save it in reviewer repo
    if (newUser instanceof Reviewer) {
      Reviewer newReviewer = (Reviewer) newUser;
      Optional<Reviewer> persistedReviewer = newReviewer.create(reviewerPersistence);
      if (persistedReviewer.isPresent()) {
        return new ResponseEntity<>(persistedReviewer.get(), HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }

    }
    // if user is an author then save it in author repo
    else if (newUser instanceof Author) {
      Author newAuthor = (Author) newUser;
      Optional<Author> persistedAuthor = newAuthor.create(authorPersistence);
      if (persistedAuthor.isPresent()) {
        return new ResponseEntity<>(persistedAuthor.get(), HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
    }
    // if user is a conference chair then save it in conference chair repo
    else if (newUser instanceof ConferenceChair) {
      ConferenceChair newConfChair = (ConferenceChair) newUser;
      Optional<ConferenceChair> persistedConfChair = newConfChair.create(confChairPersistence);
      if (persistedConfChair.isPresent()) {
        return new ResponseEntity<>(persistedConfChair.get(), HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
    }

    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Get all Reviewers
  @GetMapping("/r/all")
  public ResponseEntity<List<Reviewer>> getAllReviewers() {
    // get list of the appropriate user type
    List<Reviewer> userList = reviewerPersistence.getAll();

    // if user list has elements then return list
    if (userList.size() > 0) {
      return new ResponseEntity<>(userList, HttpStatus.OK);
    } else if (userList.size() == 0) { // otherwise if list is empty return a no content status message
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // if niether case is satisfied then return a generic error
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Get all Authors
  @GetMapping("/a/all")
  public ResponseEntity<List<Author>> getAllAuthors() {
    // get list of the appropriate user type
    List<Author> userList = authorPersistence.getAll();

    // if user list has elements then return list
    if (userList.size() > 0) {
      return new ResponseEntity<>(userList, HttpStatus.OK);
    } else if (userList.size() == 0) { // otherwise if list is empty return a no content status message
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // if niether case is satisfied then return a generic error
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Get all Conference Chairs
  @GetMapping("/c/all")
  public ResponseEntity<List<ConferenceChair>> getAllConfChairs() {
    // get list of the appropriate user type
    List<ConferenceChair> userList = confChairPersistence.getAll();

    // if user list has elements then return list
    if (userList.size() > 0) {
      return new ResponseEntity<>(userList, HttpStatus.OK);
    } else if (userList.size() == 0) { // otherwise if list is empty return a no content status message
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // if niether case is satisfied then return a generic error
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Login user
  @PostMapping("/auth")
  public ResponseEntity<?> getUser(@RequestBody CompleteUserData user) {
    // find appropriate type of user
    User typedUser = usersFactory.createAppropriateUser(user);

    // if user is a reviewer then try to match password with existing reviewer
    if (typedUser instanceof Reviewer) {
      Reviewer reviewer = (Reviewer) typedUser;
      Optional<Reviewer> persistedReviewer = reviewer.get(reviewerPersistence);
      if (!persistedReviewer.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else if (persistedReviewer.get().getPassword().equals(typedUser.getPassword())) {
        return new ResponseEntity<>(persistedReviewer.get(), HttpStatus.OK);
      } else if (persistedReviewer.get().getId().equals("0")) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    } // if user is an author then try to match password with existing author
    else if (typedUser instanceof Author) {
      Author author = (Author) typedUser;
      Optional<Author> persistedAuthor = author.get(authorPersistence);
      if (!persistedAuthor.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else if (persistedAuthor.get().getPassword().equals(typedUser.getPassword())) {
        return new ResponseEntity<>(persistedAuthor.get(), HttpStatus.OK);
      } else if (persistedAuthor.get().getId().equals("0")) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    } // if user is a conference chair then try to match password with existing
      // conference chair
    else if (typedUser instanceof ConferenceChair) {
      ConferenceChair confChair = (ConferenceChair) typedUser;
      Optional<ConferenceChair> persistedConfChair = confChair.get(confChairPersistence);
      if (!persistedConfChair.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else if (persistedConfChair.get().getPassword().equals(typedUser.getPassword())) {
        return new ResponseEntity<>(persistedConfChair.get(), HttpStatus.OK);
      } else if (persistedConfChair.get().getId().equals("0")) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }

    // if it makes it to this point then something unforseen has gone wrong
    System.out.println(
        "WARNING: User authentication fell through to last return. Make note of data used for testing and let Brendon know.");
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Update user details (email must match user, all new details overwritten)

  @PutMapping("")
  public ResponseEntity<?> updateUser(@RequestBody CompleteUserData newUserData) {
    // find appropriate type of user
    User typedUser = usersFactory.createAppropriateUser(newUserData);

    // if user is a reviewer then update corresponding reviewer with new information
    if (typedUser instanceof Reviewer) {
      Reviewer reviewer = (Reviewer) typedUser;
      Optional<Reviewer> persistedReviewer = reviewer.update(reviewerPersistence);
      if (persistedReviewer.isPresent())
        return new ResponseEntity<>(persistedReviewer.get(), HttpStatus.OK);
    } // if user is an author then update corresponding author with new information
    else if (typedUser instanceof Author) {
      Author author = (Author) typedUser;
      Optional<Author> persistedAuthor = author.update(authorPersistence);
      if (persistedAuthor.isPresent())
        return new ResponseEntity<>(persistedAuthor.get(), HttpStatus.OK);
    } // if user is a conference chair then update corresponding conference chair with
      // new information
    else if (typedUser instanceof ConferenceChair) {
      ConferenceChair confChair = (ConferenceChair) typedUser;
      Optional<ConferenceChair> persistedConfChair = confChair.update(confChairPersistence);
      if (persistedConfChair.isPresent())
        return new ResponseEntity<>(persistedConfChair.get(), HttpStatus.OK);
    }

    // if user hasn't been updated at this point then user wasn't found
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // Delete user account (by email address)

  @DeleteMapping("")
  public ResponseEntity<String> deleteUser(@RequestBody CompleteUserData user) {
    // find appropriate type of user
    User typedUser = usersFactory.createAppropriateUser(user);

    Optional<String> returnMessage = Optional.empty();

    // if user is a reviewer then delete corresponding reviewer
    if (typedUser instanceof Reviewer) {
      Reviewer reviewer = (Reviewer) typedUser;
      returnMessage = reviewer.delete(reviewerPersistence);
    } // if user is an author then delete corresponding author
    else if (typedUser instanceof Author) {
      Author author = (Author) typedUser;
      returnMessage = author.delete(authorPersistence);
    } // if user is a conference chair then delete corresponding conference chair
    else if (typedUser instanceof ConferenceChair) {
      ConferenceChair confChair = (ConferenceChair) typedUser;
      returnMessage = confChair.delete(confChairPersistence);
    }

    // if message is present then user was deleted, otherwise user wasn't found
    if (returnMessage.isPresent()) {
      return new ResponseEntity<>(returnMessage.get(), HttpStatus.OK);
    } else {
      returnMessage = Optional.of("User not found in database");
      return new ResponseEntity<>(returnMessage.get(), HttpStatus.NOT_FOUND);
    }
  }

}
