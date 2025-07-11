package CSCI334.ConferencePro.testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.Author;
import CSCI334.ConferencePro.entities.ConferenceChair;
import CSCI334.ConferencePro.entities.Rating;
import CSCI334.ConferencePro.entities.ResearchPaper;
import CSCI334.ConferencePro.entities.Review;
import CSCI334.ConferencePro.entities.Reviewer;
import CSCI334.ConferencePro.persistence.AuthorPersistence;
import CSCI334.ConferencePro.persistence.CommentPersistence;
import CSCI334.ConferencePro.persistence.ConfChairPersistence;
import CSCI334.ConferencePro.persistence.RatingPersistence;
import CSCI334.ConferencePro.persistence.ResearchPaperPersistence;
import CSCI334.ConferencePro.persistence.ReviewPersistence;
import CSCI334.ConferencePro.persistence.ReviewerPersistence;

@Component
public class DataGenerator {
    @Autowired
    AuthorPersistence authPer;

    @Autowired
    CommentPersistence comPer;

    @Autowired
    ConfChairPersistence ccPer;

    @Autowired
    RatingPersistence ratPer;

    @Autowired
    ResearchPaperPersistence rpPer;

    @Autowired
    ReviewerPersistence revPer;

    @Autowired
    ReviewPersistence reviewPer;

    @Autowired
    DataStore dStore;

    Random random = new Random();

    public DataGenerator() {
    }

    public void generateEverything() {
        generateAuthors(100);
        generateReviewers(100);
        generateConfChairs(1);
        generateResearchPapers(100);
        generateBids();
        assignPapers();
        generateReviews();
        generateRatings();
    }

    public void generateAuthors(int amount) {
        // for each author to generate
        for (int i = 0; i < amount; i++) {
            // generate attributes
            String fn = dStore.firstNames[random.nextInt(50)];
            String ln = dStore.lastNames[random.nextInt(50)];
            String pw = "password";
            String uType = "Author";
            String email = fn.substring(0, 1).toLowerCase() + ln.substring(0, 1).toLowerCase() + (i + 1)
                    + "@authormail.com";
            String org = dStore.organizationNames[random.nextInt(20)];
            String title = "The honourable";

            // save it
            authPer.create(new Author(email, pw, fn, ln, uType, title, org));
        }
    }

    public void generateReviewers(int amount) {
        // for each author to generate
        for (int i = 0; i < amount; i++) {
            // generate attributes
            String fn = dStore.firstNames[random.nextInt(50)];
            String ln = dStore.lastNames[random.nextInt(50)];
            String pw = "password";
            String uType = "Reviewer";
            String email = fn.substring(0, 1).toLowerCase() + ln.substring(0, 1).toLowerCase() + (i + 1)
                    + "@reviewermail.com";
            int reviewLimit = random.nextInt(4) + 3;

            // roll up 3 subjects
            List<String> subjects = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                boolean loop = true;
                while (loop) {
                    // choose a random index on the list
                    int chosenSubjectIndex = random.nextInt(10);
                    // if email isn't already in the list then add it and stop looping
                    if (!subjects.contains(dStore.subjects[chosenSubjectIndex])) {
                        subjects.add(dStore.subjects[chosenSubjectIndex]);
                        loop = false;
                    }
                }
            }

            // System.out.println(subjects.get(0) + ", " + subjects.get(1) + ", " +
            // subjects.get(2));

            // save it
            revPer.create(new Reviewer(email, pw, fn, ln, uType, reviewLimit, 0, subjects));
        }
    }

    public void generateConfChairs(int amount) {
        // for each conf chair to generate
        for (int i = 0; i < amount; i++) {
            // generate attributes
            String fn = dStore.firstNames[random.nextInt(50)];
            String ln = dStore.lastNames[random.nextInt(50)];
            String pw = "password";
            String uType = "ConferenceChair";
            String email = fn.substring(0, 1).toLowerCase() + ln.substring(0, 1).toLowerCase() + (i + 1)
                    + "@ccmail.com";

            // save it
            ccPer.create(new ConferenceChair(email, pw, fn, ln, uType));
        }
    }

    public void generateResearchPapers(int amount) {
        for (int i = 0; i < amount; i++) {

            // generate list of author emails
            List<Author> existingAuthors = authPer.getAll();
            List<String> paperAuthorEmails = new ArrayList<>();

            int authorAmount = random.nextInt(5) + 1; // between 1 and 5 authors
            for (int j = 0; j < authorAmount; j++) {
                boolean loop = true;
                while (loop) {
                    // choose a random index on the list
                    int chosenAuthorIndex = random.nextInt(existingAuthors.size());
                    // if email isn't already in the list then add it and stop looping
                    if (!paperAuthorEmails.contains(existingAuthors.get(chosenAuthorIndex).getEmail())) {
                        paperAuthorEmails.add(existingAuthors.get(chosenAuthorIndex).getEmail());
                        loop = false;
                    }
                }
            }

            String pdfLink = "https://www.ConferencePro.com/papers/" + (i + 1);

            // generate subjects
            List<String> subjects = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                boolean loop = true;
                while (loop) {
                    // choose a random index on the list
                    int chosenSubjectIndex = random.nextInt(10);
                    // if email isn't already in the list then add it and stop looping
                    if (!subjects.contains(dStore.subjects[chosenSubjectIndex])) {
                        subjects.add(dStore.subjects[chosenSubjectIndex]);
                        loop = false;
                    }
                }
            }

            String title = "";
            String summary = "";

            if (i < 10) {
                title = dStore.paperTitles[i];
                summary = dStore.paperAbstracts[i];
            } else {
                title = "Legitimate Research Paper " + i;
                summary = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
            }

            // save it
            rpPer.createResearchPaper(new ResearchPaper(title, summary, paperAuthorEmails, subjects, pdfLink));
        }
    }

    public void generateBids() {
        List<Reviewer> existingReviewers = revPer.getAll();

        int[] bidSpread = { 1, 2, 2, 2, 3, 3, 4, 4 };

        // for every reviewer
        for (int i = 0; i < existingReviewers.size(); i++) {
            // roll the amount of reviews
            int bidsToMake = bidSpread[random.nextInt(8)];

            List<ResearchPaper> papersOfInterest = rpPer
                    .getAllByKeywords(existingReviewers.get(i).getSubjectInterests());

            // fbid on the amount of papers decided
            for (int j = 0; j < bidsToMake; j++) {
                // choose a paper to bid on
                int bidIndex = random.nextInt(papersOfInterest.size());

                // bid on the paper
                rpPer.bidOnResearchPaper(papersOfInterest.get(bidIndex), existingReviewers.get(i).getEmail());
            }
        }
    }

    public List<ResearchPaper> assignPapers() {
        List<ResearchPaper> initialPapers = rpPer.getAllResearchPapers();
        List<Reviewer> availableReviewers = revPer.getAll();

        List<ResearchPaper> papersToUpdate = new ArrayList<>();
        List<ResearchPaper> remainingPapers = new ArrayList<>();
        List<ResearchPaper> unassignablePapers = new ArrayList<>();

        int reviewersPerPaper = 3;

        int reviewTotal = 0;
        // initialize hash map
        for (Reviewer reviewer : availableReviewers) {
            // currentlyAssigned.put(reviewer.getEmail(), 0);
            reviewTotal += reviewer.getPaperLimit();
        }

        System.out.println("Total amount of reviews available: " + reviewTotal);

        // assign based on bidders
        for (ResearchPaper paper : initialPapers) {
            // list of reviewers for the current paper
            List<Reviewer> reviewers = new ArrayList<>();

            // populate list of reviewers for current paper
            for (String s : paper.getPaperBidders()) {
                Optional<Reviewer> foundRev = revPer.findByEmail(s);
                if (foundRev.isPresent())
                    reviewers.add(foundRev.get());
            }

            for (Reviewer r : reviewers) {
                // if paper isn't assigned yet
                if (paper.getReviewers().size() < reviewersPerPaper) {
                    // if reviewer isn't in assigned map OR if reviewer hasn't hit their review
                    // limit
                    if ((r.getAssignedPapers() < r.getPaperLimit()) && !paper.getReviewers().contains(r.getEmail())) {
                        // add reviewer to paper and update reviewer assigned papers
                        paper.addReviewer(r.getEmail());
                        revPer.assignPaper(r.getEmail(), paper.getId());

                        // remove reviewer from the reviewer list if they have hit their cap on reviews
                        if (revPer.findByEmail(r.getEmail()).get().getAssignedPapers() >= r.getPaperLimit()) {
                            System.out.println(r.getEmail() + " has bowed out.");
                            if (availableReviewers.contains(r)) {
                                availableReviewers.remove(r);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Finished assigning to bidders");

        // check all papers, put those with enough reviewers in update list, those
        // without in remaining list
        for (ResearchPaper paper : initialPapers) {
            if (paper.getReviewers().size() < reviewersPerPaper) {
                remainingPapers.add(paper);
            } else if (!papersToUpdate.contains(paper)) {
                papersToUpdate.add(paper);
            }
        }

        // assign based on interests, assign randomly if can't find a subject match
        // within threshold limit
        for (ResearchPaper paper : remainingPapers) {
            boolean loop = true;
            int loopLimit = 20;
            int hardLimit = 300;
            int loops = 0;
            while (loop) {
                if (paper.getReviewers().size() >= reviewersPerPaper) {
                    loop = false;
                } else {
                    // roll a random index
                    int randomIndex = random.nextInt(availableReviewers.size());
                    Reviewer selectedReviewer = revPer.findByEmail(availableReviewers.get(randomIndex).getEmail()).get();
                    // for all of the reviewer's subjects
                    for (String subject : selectedReviewer.getSubjectInterests()) {
                        // if the paper has the same subject OR if the loop has already tried too many
                        // times to find a matching reviewer
                        if (paper.getKeywords().contains(subject) || loops > loopLimit) {
                            // and if the reviewer isn't at their paper max
                            if ((selectedReviewer.getAssignedPapers() < selectedReviewer.getPaperLimit()) && !paper.getReviewers().contains(selectedReviewer.getEmail())) {
                                // add reviewer to paper and update reviewer assigned papers
                                paper.addReviewer(selectedReviewer.getEmail());
                                revPer.assignPaper(selectedReviewer.getEmail(), paper.getId());

                                // remove reviewer from the reviewer list if they have hit their cap on reviews
                                if (revPer.findByEmail(selectedReviewer.getEmail()).get().getAssignedPapers() >= selectedReviewer.getPaperLimit()) {
                                    System.out.println(selectedReviewer.getEmail() + " has bowed out in overtime.");
                                    if (availableReviewers.contains(selectedReviewer)) {
                                        availableReviewers.remove(selectedReviewer);
                                    }
                                }
                            }
                        }
                    }
                }
                loops++;

                // stop if too many attempts to assign have been made
                if (loops > hardLimit) {
                    loop = false;
                    System.out.println("Hit hard limit");
                }
            }

            // add paper to unassignable list if there are too few reviewers
            if (paper.getReviewers().size() < reviewersPerPaper) {
                unassignablePapers.add(paper);
            } else if (!papersToUpdate.contains(paper)) {
                papersToUpdate.add(paper);
            }
        }

        System.out.println("Finished assigning papers to everyone");

        // return anything that couldn't be assigned
        return unassignablePapers;
    }

    public void removeAssignedReviewers() {
        // get papers
        List<ResearchPaper> allPapers = rpPer.getAllResearchPapers();

        // reset reviewers and update papers
        for (ResearchPaper paper : allPapers) {
            paper.setReviewers(new ArrayList<>());
            rpPer.updateResearchPaper(paper);
        }
    }

    // generate reviews
    public void generateReviews() {
        // get papers
        List<ResearchPaper> allPapers = rpPer.getAllResearchPapers();
        List<Reviewer> allReviewers = revPer.getAll();

        HashMap<String, Integer> reviewerIndex = new HashMap<>();

        // assign every reviewer to hash table along with their associated index
        // this is to easily find a Reviewer object with a given email
        for (int i = 0; i < allReviewers.size(); i++) {
            reviewerIndex.put(allReviewers.get(i).getEmail(), i);
        }

        // for every paper
        for (ResearchPaper paper : allPapers) {
            // for every reviewer
            for (String reviewerEmail : paper.getReviewers()) {
                // generate attributes for the new review
                int randomIndex = random.nextInt(dStore.textReviews.length);
                String textReview = dStore.textReviews[randomIndex];
                int score = dStore.scoreReviews[randomIndex];

                // create a review, persist it and associate it with the paper
                reviewPer.createReviewOnPaper(new Review(reviewerEmail, score, textReview), paper.getId());
            }
        }
    }

    public void generateRatings() {
        List<ResearchPaper> allPapers = rpPer.getAllResearchPapers();

        for (ResearchPaper rp : allPapers) {
            // get all reviews on a given paper
            List<Review> paperReviews = new ArrayList<>();
            for (String reviewId : rp.getReviews()) {
                paperReviews.add(reviewPer.getReview(reviewId).get());
            }

            // for every review on a paper
            for (Review review : paperReviews) {
                // each author will generate a rating and associate it with the review
                for (String authorEmail : rp.getAuthors()) {
                    // roll up a rating that the author will give
                    int score = 0;
                    int rndVal = random.nextInt(2);
                    if (rndVal == 0) {
                        score = -1;
                    } else {
                        score = 1;
                    }

                    // create and save rating
                    ratPer.createRatingOnReview(new Rating(score, authorEmail), review.getId());
                }
            }
        }
    }
}
