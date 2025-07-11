package CSCI334.ConferencePro.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.ResearchPaper;
import CSCI334.ConferencePro.entities.Reviewer;
import CSCI334.ConferencePro.interfaces.UserPersistenceInterface;
import CSCI334.ConferencePro.persistence.repos.ResearchPaperRepository;
import CSCI334.ConferencePro.persistence.repos.ReviewerRepository;

@Component
public class ReviewerPersistence implements UserPersistenceInterface<Reviewer> {
    @Autowired
    private ReviewerRepository repository;
    private ResearchPaperRepository paperRepository;

    public ReviewerPersistence(ReviewerRepository repository, ResearchPaperRepository paperRepository) {
        this.repository = repository;
        this.paperRepository = paperRepository;
    }

    // CREATE
    public Optional<Reviewer> create(Reviewer reviewer) {
        // if email already exists then return an empty optional
        Optional<Reviewer> existingUser = repository.findByEmail(reviewer.getEmail());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }

        // save reviewer information
        Reviewer newReviewer = repository.save(
                new Reviewer(
                        reviewer.getEmail(),
                        reviewer.getPassword(),
                        reviewer.getFirstName(),
                        reviewer.getLastName(),
                        reviewer.getUserType(),
                        reviewer.getPaperLimit(),
                        reviewer.getAssignedPapers(),
                        reviewer.getSubjectInterests()));

        return Optional.of(newReviewer);
    }

    // READ
    // All reviewers
    public List<Reviewer> getAll() {
        // load all reviewers in DB to new list
        List<Reviewer> allReviewers = new ArrayList<Reviewer>();
        repository.findAll().forEach(allReviewers::add);

        return allReviewers;
    }

    // One reviewer
    public Optional<Reviewer> get(Reviewer reviewer) {
        // try to find reviewer in DB with given email
        Optional<Reviewer> reviewerData = repository.findByEmail(reviewer.getEmail());

        // no reviewer with email provided exists in the DB, return empty optional
        if (!reviewerData.isPresent()) {
            return Optional.empty();
        }

        // reviewer with email and password provided is found, return reviewer info
        if (reviewerData.get().getPassword().equals(reviewer.getPassword())) {
            return reviewerData;
        }

        // if no return yet then assume authorisation information is incorrect
        // return instance of reviewer with id = 0;
        return Optional.of(new Reviewer("0", "", "", "", "", "", 0, 0, new ArrayList<>()));
    }

    // Find by email
    public Optional<Reviewer> findByEmail(String reviewerEmail) {
        // try to find reviewer in DB with given email
        Optional<Reviewer> reviewerData = repository.findByEmail(reviewerEmail);
        return reviewerData;
    }

    // Find all with interest
    public List<Reviewer> findByInterests(String interest) {
        // find all reviewers with given interest, return list
        return repository.findBySubjectInterests(interest);
    }

    // UPDATE
    public Optional<Reviewer> update(Reviewer newReviewerData) {
        // try to find reviewer in DB with given email
        Optional<Reviewer> foundReviewer = repository.findByEmail(newReviewerData.getEmail());

        // if reviewer is found then update reviewer info and return newly saved data
        if (foundReviewer.isPresent()) {
            Reviewer newReviewer = foundReviewer.get();
            if (newReviewerData.getEmail() != null)
                newReviewer.setEmail(newReviewerData.getEmail());
            if (newReviewerData.getPassword() != null)
                newReviewer.setPassword(newReviewerData.getPassword());
            if (newReviewerData.getFirstName() != null)
                newReviewer.setFirstName(newReviewerData.getFirstName());
            if (newReviewerData.getLastName() != null)
                newReviewer.setLastName(newReviewerData.getLastName());
            if (newReviewerData.getUserType() != null)
                newReviewer.setUserType(newReviewerData.getUserType());
            // if (newReviewerData.getPaperLimit() > -1 && newReviewerData.getPaperLimit() < 20)
                newReviewer.setPaperLimit(newReviewerData.getPaperLimit());
            if (newReviewerData.getSubjectInterests() != null)
                newReviewer.setSubjectInterests(newReviewerData.getSubjectInterests());
            Reviewer updatedAuthor = repository.save(newReviewer);
            return Optional.of(updatedAuthor);
        } else { // else return empty
            return Optional.empty();
        }
    }

    // assign paper to reviewer
    public Optional<Reviewer> assignPaper(String reviewerEmail, String paperId) {
        Optional<Reviewer> reviewer = repository.findByEmail(reviewerEmail);
        Optional<ResearchPaper> paper = paperRepository.findById(paperId);

        if(reviewer.isEmpty() || paper.isEmpty())
            return Optional.empty();

        // update paper
        paper.get().addReviewer(reviewer.get().getEmail());
        paperRepository.save(paper.get());

        // update reviewer
        reviewer.get().setAssignedPapers(reviewer.get().getAssignedPapers() + 1);
        return Optional.of(repository.save(reviewer.get()));
    }

    // DELETE
    public Optional<String> delete(Reviewer reviewer) {
        // try to find reviewer in DB with given email
        Optional<Reviewer> foundUser = repository.findByEmail(reviewer.getEmail());

        // delete it if found, return message
        if (foundUser.isPresent()) {
            repository.deleteById(foundUser.get().getId());
            return Optional.of("Reviewer with email:" + foundUser.get().getEmail() + " deleted successfully");
        } else { // else return empty
            return Optional.empty();
        }
    }
}
