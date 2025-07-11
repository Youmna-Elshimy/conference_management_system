package CSCI334.ConferencePro.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.Author;
import CSCI334.ConferencePro.entities.ResearchPaper;
import CSCI334.ConferencePro.entities.Reviewer;
import CSCI334.ConferencePro.persistence.repos.ResearchPaperRepository;

@Component
public class ResearchPaperPersistence {
    private ResearchPaperRepository researchPaperRepository;

    public ResearchPaperPersistence(ResearchPaperRepository researchPaperRepository) {
        this.researchPaperRepository = researchPaperRepository;
    }

    // CREATE
    public Optional<ResearchPaper> createResearchPaper(ResearchPaper researchPaper) {
        ResearchPaper newResearchPaper = researchPaperRepository.save(
                new ResearchPaper(
                        researchPaper.getTitle(),
                        researchPaper.getPaperAbstract(),
                        researchPaper.getAuthors(),
                        researchPaper.getKeywords(),
                        researchPaper.getPdfLink()));

        return Optional.of(newResearchPaper);
    }

    // READ
    // All research papers
    public List<ResearchPaper> getAllResearchPapers() {
        //List<ResearchPaper> allReseachPapers = new ArrayList<ResearchPaper>();

        //researchPaperRepository.findAll().forEach(allReseachPapers::add);

        return researchPaperRepository.findAll();
    }

    // One research paper by id
    public Optional<ResearchPaper> findById(String id) {
        Optional<ResearchPaper> researchPaper = researchPaperRepository.findById(id);

        return researchPaper;
    }

    // All papers by given author
    public List<ResearchPaper> getAllResearchPapersByAuthor(Author user) {
        if (user.getEmail() == null) {
            List<ResearchPaper> empty = new ArrayList<ResearchPaper>();
            return empty;
        }

        String emailAddress = user.getEmail();

        List<ResearchPaper> researchPapersByAuthor = researchPaperRepository.findByPaperAuthor(emailAddress);

        return researchPapersByAuthor;
    }

    public List<ResearchPaper> getAllPapersReviewedBy(Reviewer reviewer) {
        if (reviewer.getEmail() == null) {
            List<ResearchPaper> empty = new ArrayList<ResearchPaper>();
            return empty;
        }

        String emailAddress = reviewer.getEmail();

        List<ResearchPaper> researchPapersReviewedBy = researchPaperRepository.findByReviewers(emailAddress);

        return researchPapersReviewedBy;
    }

    public List<ResearchPaper> getAllUnassignedPapers() {
        List<ResearchPaper> allPapers = researchPaperRepository.findAll();

        List<ResearchPaper> unassignedPapers = new ArrayList<>();

        for (ResearchPaper researchPaper : allPapers) {
            if(researchPaper.getReviewers().size() < 3)
                unassignedPapers.add(researchPaper);
        }

        return unassignedPapers;
    }

    public List<ResearchPaper> getAllAssignedPapers() {
        List<ResearchPaper> allPapers = researchPaperRepository.findAll();

        List<ResearchPaper> assignedPapers = new ArrayList<>();

        for (ResearchPaper researchPaper : allPapers) {
            if(researchPaper.getReviewers().size() >= 3)
                assignedPapers.add(researchPaper);
        }

        return assignedPapers;
    }

    // UPDATE
    public Optional<ResearchPaper> updateResearchPaper(ResearchPaper researchPaperDetails) {
        Optional<ResearchPaper> optionalResearchPaper = researchPaperRepository.findById(researchPaperDetails.getId());
        if (optionalResearchPaper.isPresent()) {
            ResearchPaper researchPaper = optionalResearchPaper.get();
            if (researchPaperDetails.getTitle() != null)
                researchPaper.setTitle(researchPaperDetails.getTitle());
            if (researchPaperDetails.getAuthors() != null)
                researchPaper.setAuthors(researchPaperDetails.getAuthors());
            if (researchPaperDetails.getReviewers() != null)
                researchPaper.setReviewers(researchPaperDetails.getReviewers());
            if (researchPaperDetails.getPaperAbstract() != null)
                researchPaper.setPaperAbstract(researchPaperDetails.getPaperAbstract());
            if (researchPaperDetails.getKeywords() != null)
                researchPaper.setKeywords(researchPaperDetails.getKeywords());
            if (researchPaperDetails.getPaperBidders() != null)
                researchPaper.setPaperBidders(researchPaperDetails.getPaperBidders());
            if (researchPaperDetails.getStatus() != null)
                researchPaper.setStatus(researchPaperDetails.getStatus());
            ResearchPaper updatedResearchPaper = researchPaperRepository.save(researchPaper);
            return Optional.of(updatedResearchPaper);
        } else {
            return Optional.empty();
        }
    }

    // DELETE
    public Optional<String> deleteById(String researchPaperId) {
        Optional<ResearchPaper> researchPaper = researchPaperRepository.findById(researchPaperId);
        if (researchPaper.isPresent()) {
            researchPaperRepository.deleteById(researchPaperId);
            return Optional.of("Research paper with ID " + researchPaperId + " deleted successfully");
        } else {
            return Optional.empty();
        }
    }

    // Find by keyword
    public List<ResearchPaper> getAllByKeywords(List<String> reviewerInterests) {
        List<ResearchPaper> matchingSubjectPapers = new ArrayList<ResearchPaper>();

        researchPaperRepository.findByKeywordsIn(reviewerInterests).forEach(matchingSubjectPapers::add);

        return matchingSubjectPapers;
    }

    // Bid on paper
    public Optional<ResearchPaper> bidOnResearchPaper(ResearchPaper researchPaperDetails, String reviewerEmail) {
        Optional<ResearchPaper> optionalResearchPaper = researchPaperRepository.findById(researchPaperDetails.getId());
        if (optionalResearchPaper.isPresent()) {
            ResearchPaper researchPaper = optionalResearchPaper.get();
            // add bidder only if it isn't already present
            if (!researchPaper.getPaperBidders().contains(reviewerEmail))
                researchPaper.addBidder(reviewerEmail);
            ResearchPaper updatedResearchPaper = researchPaperRepository.save(researchPaper);
            return Optional.of(updatedResearchPaper);
        } else {
            return Optional.empty();
        }
    }
}
