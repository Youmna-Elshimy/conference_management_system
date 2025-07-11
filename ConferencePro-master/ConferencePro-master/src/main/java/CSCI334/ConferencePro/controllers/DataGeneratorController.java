package CSCI334.ConferencePro.controllers;

import CSCI334.ConferencePro.entities.ResearchPaper;
import CSCI334.ConferencePro.testData.DataGenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/dataGen")
public class DataGeneratorController {
  @Autowired
  DataGenerator dataGen;

  @Autowired
  MongoTemplate mongoTemplate;

  public DataGeneratorController() {
  }

  // create a given amount of authors
  @GetMapping("/createEverything")
  public ResponseEntity<String> createEverything() {
    dataGen.generateEverything();
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // create a given amount of authors
  @GetMapping("/createAuthors/{amount}")
  public ResponseEntity<String> createAuthors(@PathVariable int amount) {
    dataGen.generateAuthors(amount);
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // create a given amount of authors
  @GetMapping("/createReviewers/{amount}")
  public ResponseEntity<String> createReviewers(@PathVariable int amount) {
    dataGen.generateReviewers(amount);
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // create a given amount of conference chairs
  @GetMapping("/createConfChairs/{amount}")
  public ResponseEntity<String> createConfChairs(@PathVariable int amount) {
    dataGen.generateConfChairs(amount);
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // create a given amount of research papers
  @GetMapping("/createResearchPapers/{amount}")
  public ResponseEntity<String> createResearchPapers(@PathVariable int amount) {
    dataGen.generateResearchPapers(amount);
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // create bids
  @GetMapping("/createBids")
  public ResponseEntity<String> createBids() {
    dataGen.generateBids();
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  @DeleteMapping("/deleteCollection/{name}")
  public ResponseEntity<String> deleteCollection(@PathVariable String name) {
    if (name.equals("all")) {
      mongoTemplate.dropCollection("authors");
      mongoTemplate.dropCollection("comments");
      mongoTemplate.dropCollection("conferenceChairs");
      mongoTemplate.dropCollection("ratings");
      mongoTemplate.dropCollection("researchPapers");
      mongoTemplate.dropCollection("ratings");
      mongoTemplate.dropCollection("reviewers");
      mongoTemplate.dropCollection("reviews");
    } else {
      mongoTemplate.dropCollection(name);
    }
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // assign research papers and return any papers that can't be assigned
  @GetMapping("/assignPapers")
  public ResponseEntity<List<ResearchPaper>> assignPapers() {
    return new ResponseEntity<>(dataGen.assignPapers(), HttpStatus.OK);
  }

  @DeleteMapping("/removeReviewers")
  public ResponseEntity<String> removeReviewers() {
    dataGen.removeAssignedReviewers();
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // generate reviews by reviewers
  @GetMapping("/generateReviews")
  public ResponseEntity<String> generateReviews() {
    dataGen.generateReviews();
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }

  // generate reviews by reviewers
  @GetMapping("/generateRatings")
  public ResponseEntity<String> generateRatings() {
    dataGen.generateRatings();
    return new ResponseEntity<>("Job done.", HttpStatus.OK);
  }
}
