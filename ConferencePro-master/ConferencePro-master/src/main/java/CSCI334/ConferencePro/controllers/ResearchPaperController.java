package CSCI334.ConferencePro.controllers;

import CSCI334.ConferencePro.entities.Author;
import CSCI334.ConferencePro.entities.ResearchPaper;
import CSCI334.ConferencePro.entities.Reviewer;
import CSCI334.ConferencePro.persistence.ResearchPaperPersistence;

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
@RequestMapping("/papers")
public class ResearchPaperController {

  private final ResearchPaperPersistence persistence;

  public ResearchPaperController(ResearchPaperPersistence persistence) {
    this.persistence = persistence;
  }

  // CREATE
  @PostMapping("")
  public ResponseEntity<ResearchPaper> createResearchPaper(@RequestBody ResearchPaper researchPaper) {
    // save new research paper
    Optional<ResearchPaper> createdPaper = persistence.createResearchPaper(researchPaper);

    // if paper saved properly then return the paper as verification
    if (createdPaper.isPresent()) {
      return new ResponseEntity<>(createdPaper.get(), HttpStatus.CREATED);
    } else { // else return an error because creation shouldn't fail
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // All research papers
  @GetMapping("/all")
  public ResponseEntity<List<ResearchPaper>> getAllResearchPapers() {
    try {
      // get all papers
      List<ResearchPaper> allReseachPapers = persistence.getAllResearchPapers();

      // if list is empty then return NO CONTENT
      if (allReseachPapers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      // return list of research papers
      return new ResponseEntity<>(allReseachPapers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // One research paper by id (just send a json with attribute "id":"enter id
  // here")
  @GetMapping("/{id}")
  public ResponseEntity<ResearchPaper> getResearchPaper(@PathVariable String id) {
    // find research paper by id
    Optional<ResearchPaper> researchPaper = persistence.findById(id);

    if (researchPaper.isPresent()) {
      return new ResponseEntity<>(researchPaper.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // All research papers by given author
  @PostMapping("/by")
  public ResponseEntity<List<ResearchPaper>> getAllPapersByAuthor(@RequestBody Author user) {

    // find research paper by id
    List<ResearchPaper> researchPapers = persistence.getAllResearchPapersByAuthor(user);

    if (researchPapers.size() > 0) {
      return new ResponseEntity<>(researchPapers, HttpStatus.OK);
    } else if (researchPapers.size() == 0) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // if it makes it here then there was a problem generating the list
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Update paper details (paper identified by id, all other details overwritten)
  @PutMapping("")
  public ResponseEntity<ResearchPaper> updateResearchPaper(@RequestBody ResearchPaper newPaperDetails) {
    // update paper and save the result
    Optional<ResearchPaper> updatedPaper = persistence.updateResearchPaper(newPaperDetails);

    // if paper is present then it was updated
    if (updatedPaper.isPresent()) {
      return new ResponseEntity<>(updatedPaper.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
  }

  // update status of a research paper (paper identified by id, body containing
  // "status":)
  @PutMapping("/status/{id}")
  public ResponseEntity<ResearchPaper> updateResearchPaperStatus(@RequestBody String status,
      @PathVariable String id) {
    // find the paper
    Optional<ResearchPaper> paper = persistence.findById(id);

    // return not found if paper wasn't found
    if (!paper.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // change status
    paper.get().setStatus(status);

    // save paper
    Optional<ResearchPaper> retPaper = persistence.updateResearchPaper(paper.get());

    // return newly altered paper
    return new ResponseEntity<>(retPaper.get(), HttpStatus.OK);
  }

  // DELETE
  @DeleteMapping("")
  public ResponseEntity<String> deleteResearchPaper(@RequestBody ResearchPaper paperToDelete) {
    // delete research paper by id
    Optional<String> confirmationMessage = persistence.deleteById(paperToDelete.getId());

    // if returned String is not empty then the delete was successful, so return
    // message
    if (confirmationMessage.isPresent()) {
      return new ResponseEntity<>(confirmationMessage.get(), HttpStatus.OK);

    } else { // else delete was not successful
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Find research papers by reviewer interests
  @PostMapping("/matching")
  public ResponseEntity<List<ResearchPaper>> getPapersMatchingInterests(@RequestBody Reviewer reviewer) {
    try {
      // get all papers
      List<ResearchPaper> matchingPapers = persistence.getAllByKeywords(reviewer.getSubjectInterests());

      // if list is empty then return NO CONTENT
      if (matchingPapers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      // return list of research papers
      return new ResponseEntity<>(matchingPapers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Bid on research paper (paper identified by id, reviewer identified by email)
  @PutMapping("/bid/{id}")
  public ResponseEntity<ResearchPaper> bidOnResearchPaper(@RequestBody Reviewer reviewerBidding,
      @PathVariable String id) {
    // find existing paper
    Optional<ResearchPaper> foundPaper = persistence.findById(id);
    // if paper is empty then return not found
    if (!foundPaper.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // add reviewer email to paper and save it
    Optional<ResearchPaper> updatedPaper = persistence.bidOnResearchPaper(foundPaper.get(), reviewerBidding.getEmail());

    // if paper is present then it was updated
    if (updatedPaper.isPresent()) {
      return new ResponseEntity<>(updatedPaper.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
