package CSCI334.ConferencePro.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;

    private String reviewerEmail;
    private int score;
    private String textReview;
    private List<String> ratingIds;
    private List<String> commentIds;    // for the reviewers to discuss the reviews after completing their review.

    public Review() {
    }

    // constructor without id, one will be generated in DB
    public Review(String reviewerEmail, int score, String textReview) {
        this.reviewerEmail = reviewerEmail;
        this.score = score;
        this.textReview = textReview;
        ratingIds = new ArrayList<>();
    }

    public Review(String id, String reviewerEmail, int score, String textReview) {
        this.id = id;
        this.reviewerEmail = reviewerEmail;
        this.score = score;
        this.textReview = textReview;
        ratingIds = new ArrayList<>();
        commentIds = new ArrayList<>();
    }

    public Review(String id, String reviewerEmail, int score, String textReview, List<String> ratingIds, List<String> commentIds) {
        this.id = id;
        this.reviewerEmail = reviewerEmail;
        this.score = score;
        this.textReview = textReview;
        this.ratingIds = ratingIds;
        this.commentIds = commentIds;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewerEmail() {
        return this.reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTextReview() {
        return this.textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public List<String> getRatingIds() {
        return this.ratingIds;
    }

    public void setRatingIds(List<String> ratingIds) {
        this.ratingIds = ratingIds;
    }

    public List<String> getCommentIds() {
        return this.commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }

    public Review id(String id) {
        setId(id);
        return this;
    }

    public Review reviewerEmail(String reviewerEmail) {
        setReviewerEmail(reviewerEmail);
        return this;
    }

    public Review score(int score) {
        setScore(score);
        return this;
    }

    public Review textReview(String textReview) {
        setTextReview(textReview);
        return this;
    }

    public Review ratingIds(List<String> ratingIds) {
        setRatingIds(ratingIds);
        return this;
    }

    public Review commentIds(List<String> commentIds) {
        setCommentIds(commentIds);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Review)) {
            return false;
        }
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(reviewerEmail, review.reviewerEmail) && score == review.score && Objects.equals(textReview, review.textReview) && Objects.equals(ratingIds, review.ratingIds) && Objects.equals(commentIds, review.commentIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reviewerEmail, score, textReview, ratingIds, commentIds);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", reviewerEmail='" + getReviewerEmail() + "'" +
            ", score='" + getScore() + "'" +
            ", textReview='" + getTextReview() + "'" +
            ", ratingIds='" + getRatingIds() + "'" +
            ", commentIds='" + getCommentIds() + "'" +
            "}";
    }
    


    // adders/removers
    // ratings
    public void addRating(String ratingId) {
        ratingIds.add(ratingId);
    }

    public void removeRating(String ratingId) {
        // iterates through ratingsIds list and remove the given ratingId if found
        Iterator<String> iterator = ratingIds.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals(ratingId)) {
                iterator.remove();
            }
        }
    }

    // comments
    public void addComment(String commentId) {
        commentIds.add(commentId);
    }

    public void removeComment(String commentId) {
        // iterates through commentIds list and remove the given comment id if found
        Iterator<String> iterator = commentIds.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals(commentId)) {
                iterator.remove();
            }
        }
    }

}
