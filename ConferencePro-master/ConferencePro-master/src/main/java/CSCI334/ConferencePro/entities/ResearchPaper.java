package CSCI334.ConferencePro.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "researchPapers")
public class ResearchPaper {
    @Id
    private String id;
    
    private String title;
    private String paperAbstract;
    private List<String> authors;   // email addresses of the authors
    private List<String> keywords;  // keywords showing the technical areas this paper covers
    private String pdfLink; // link to full paper. Papers should not be stored in a database but in a file server like AWS.
    private List<String> paperBidders; // list of email addresses of reviewers that want to review the paper
    private List<String> reviewers; // email addresses of the reviewers
    private List<String> reviews;   // list of review ids
    private String status;  // accepted, rejected, pending
    private LocalDate submissionDate;


    public ResearchPaper() {
    }

    // paper with jsut the information known at submission
    public ResearchPaper(String title, String paperAbstract, List<String> authors, List<String> keywords, String pdfLink) {
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.authors = authors;
        this.keywords = keywords;
        this.pdfLink = pdfLink;
        this.status = "Pending";
        this.submissionDate = LocalDate.now();
        paperBidders = new ArrayList<>();
        reviewers = new ArrayList<>();
        reviews = new ArrayList<>();

    }


    public ResearchPaper(String id, String title, String paperAbstract, List<String> authors, List<String> keywords, String pdfLink, List<String> paperBidders, List<String> reviewers, List<String> reviews, String status, LocalDate submissionDate) {
        this.id = id;
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.authors = authors;
        this.keywords = keywords;
        this.pdfLink = pdfLink;
        this.paperBidders = paperBidders;
        this.reviewers = reviewers;
        this.reviews = reviews;
        this.status = status;
        this.submissionDate = submissionDate;
    }

    public ResearchPaper(String title, String paperAbstract, List<String> authors, List<String> keywords, String pdfLink, List<String> paperBidders, List<String> reviewers, List<String> reviews, String status, LocalDate submissionDate) {
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.authors = authors;
        this.keywords = keywords;
        this.pdfLink = pdfLink;
        this.paperBidders = paperBidders;
        this.reviewers = reviewers;
        this.reviews = reviews;
        this.status = status;
        this.submissionDate = submissionDate;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaperAbstract() {
        return this.paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getPdfLink() {
        return this.pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    public List<String> getPaperBidders() {
        return this.paperBidders;
    }

    public void setPaperBidders(List<String> paperBidders) {
        this.paperBidders = paperBidders;
    }

    public List<String> getReviewers() {
        return this.reviewers;
    }

    public void setReviewers(List<String> reviewers) {
        this.reviewers = reviewers;
    }

    public List<String> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getSubmissionDate() {
        return this.submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public ResearchPaper id(String id) {
        setId(id);
        return this;
    }

    public ResearchPaper title(String title) {
        setTitle(title);
        return this;
    }

    public ResearchPaper paperAbstract(String paperAbstract) {
        setPaperAbstract(paperAbstract);
        return this;
    }

    public ResearchPaper authors(List<String> authors) {
        setAuthors(authors);
        return this;
    }

    public ResearchPaper keywords(List<String> keywords) {
        setKeywords(keywords);
        return this;
    }

    public ResearchPaper pdfLink(String pdfLink) {
        setPdfLink(pdfLink);
        return this;
    }

    public ResearchPaper paperBidders(List<String> paperBidders) {
        setPaperBidders(paperBidders);
        return this;
    }

    public ResearchPaper reviewers(List<String> reviewers) {
        setReviewers(reviewers);
        return this;
    }

    public ResearchPaper reviews(List<String> reviews) {
        setReviews(reviews);
        return this;
    }

    public ResearchPaper status(String status) {
        setStatus(status);
        return this;
    }

    public ResearchPaper submissionDate(LocalDate submissionDate) {
        setSubmissionDate(submissionDate);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ResearchPaper)) {
            return false;
        }
        ResearchPaper researchPaper = (ResearchPaper) o;
        return Objects.equals(id, researchPaper.id) && Objects.equals(title, researchPaper.title) && Objects.equals(paperAbstract, researchPaper.paperAbstract) && Objects.equals(authors, researchPaper.authors) && Objects.equals(keywords, researchPaper.keywords) && Objects.equals(pdfLink, researchPaper.pdfLink) && Objects.equals(paperBidders, researchPaper.paperBidders) && Objects.equals(reviewers, researchPaper.reviewers) && Objects.equals(reviews, researchPaper.reviews) && Objects.equals(status, researchPaper.status) && Objects.equals(submissionDate, researchPaper.submissionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, paperAbstract, authors, keywords, pdfLink, paperBidders, reviewers, reviews, status, submissionDate);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", paperAbstract='" + getPaperAbstract() + "'" +
            ", authors='" + getAuthors() + "'" +
            ", keywords='" + getKeywords() + "'" +
            ", pdfLink='" + getPdfLink() + "'" +
            ", paperBidders='" + getPaperBidders() + "'" +
            ", reviewers='" + getReviewers() + "'" +
            ", reviews='" + getReviews() + "'" +
            ", status='" + getStatus() + "'" +
            ", submissionDate='" + getSubmissionDate() + "'" +
            "}";
    }
    

    // adders/removers
    // authors
    public void addAuthor(String author) {
        authors.add(author);
    }

    public void removeAuthor(String author) {
        // iterates through authors list and remove the given author if found
        Iterator<String> iterator = authors.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals(author)) {
                iterator.remove();
            }
        }
    }

    // reviewers
    public void addReviewer(String reviewer) {
        reviewers.add(reviewer);
    }

    public void removeReviewer(String reviewer) {
        // iterates through reviewers list and remove the given reviewer if found
        Iterator<String> iterator = reviewers.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals(reviewer)) {
                iterator.remove();
            }
        }
    }

    // reviews
    public void addReview(String review) {
        reviews.add(review);
    }

    public void removeReview(String reviewer) {
        // iterates through reviews id list and remove the given reviewer if found
        Iterator<String> iterator = reviews.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals(reviewer)) {
                iterator.remove();
            }
        }
    }

    // keywords
    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }

    public void removeKeyword(String keyword) {
        // iterates through keywords list and remove the given keyword if found
        Iterator<String> iterator = keywords.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals(keyword)) {
                iterator.remove();
            }
        }
    }

    // bidders
    public void addBidder(String bidderEmail) {
        paperBidders.add(bidderEmail);
    }

    public void removeBidder(String bidderEmail) {
        // iterates through paper bidders list and remove the given bidder email if found
        Iterator<String> iterator = paperBidders.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals(bidderEmail)) {
                iterator.remove();
            }
        }
    }

    
}
