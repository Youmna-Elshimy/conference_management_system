package CSCI334.ConferencePro.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.Comment;
import CSCI334.ConferencePro.entities.Review;
import CSCI334.ConferencePro.persistence.repos.CommentRepository;
import CSCI334.ConferencePro.persistence.repos.ReviewRepository;

@Component
public class CommentPersistence {
    private CommentRepository commentRepository;
    private ReviewRepository reviewRepository;

    public CommentPersistence(CommentRepository commentRepository, ReviewRepository reviewRepository) {
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
    }

    // CREATE
    public Optional<Comment> createComment(Comment comment) {
        // save comment
        Comment newComment = commentRepository.save(
                new Comment(
                        LocalDateTime.now(),
                        comment.getReplyCommentId(),
                        comment.getCommenterEmail(),
                        comment.getComment()));

        return Optional.of(newComment);
    }

    public Optional<Comment> createCommentOnReview(Comment comment, String reviewId) {
        // find review that comment was created on
        Optional<Review> review = reviewRepository.findById(reviewId);

        // if review doesn't exist then return an empty
        if (!review.isPresent()) {
            return Optional.empty();
        }

        // save comment
        Comment newComment = commentRepository.save(
                new Comment(
                        LocalDateTime.now(),
                        comment.getReplyCommentId(),
                        comment.getCommenterEmail(),
                        comment.getComment()));

        // review must exist, so update the review with the new comment id
        review.get().addComment(newComment.getId());
        reviewRepository.save(review.get());

        return Optional.of(newComment);
    }

    // READ
    // All comments
    public List<Comment> getAllComments() {
        // load all comments in DB to new list
        List<Comment> allComments = new ArrayList<Comment>();
        commentRepository.findAll().forEach(allComments::add);

        return allComments;
    }

    // One comment
    public Optional<Comment> getComment(Comment comment) {
        // try to find comment in DB with given id
        Optional<Comment> commentData = commentRepository.findById(comment.getId());

        // no comment with id provided exists in the DB, return empty optional
        if (!commentData.isPresent()) {
            return Optional.empty();
        } else { // else comment exists, so return it
            return commentData;
        }
    }

    // UPDATE
    public Optional<Comment> updateComment(Comment newCommentData) {
        // try to find comment in DB with given id
        Optional<Comment> foundComment = commentRepository.findById(newCommentData.getId());

        // if comment is found then update comment info and return newly saved data
        if (foundComment.isPresent()) {
            Comment newComment = foundComment.get();
            if (newCommentData.getSubmittedDateTime() != null)
                newComment.setSubmittedDateTime(newCommentData.getSubmittedDateTime());
            if (newCommentData.getReplyCommentId() != null)
                newComment.setReplyCommentId(newCommentData.getReplyCommentId());
            if (newCommentData.getCommenterEmail() != null)
                newComment.setCommenterEmail(newCommentData.getCommenterEmail());
            if (newCommentData.getComment() != null)
                newComment.setComment(newCommentData.getComment());
            Comment updatedComment = commentRepository.save(newComment);
            return Optional.of(updatedComment);
        } else { // else return empty
            return Optional.empty();
        }
    }

    // DELETE
    public Optional<String> deleteComment(Comment comment) {
        // try to find comment in DB with given id
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());
        if (!foundComment.isPresent()) {
            System.out.println("Comment with id:" + comment.getId() + " was not found.");
            return Optional.empty();
        }

        // find research review that comment was created on
        Optional<Review> review = reviewRepository.findByCommentId(foundComment.get().getId());

        // if review exists then update the review to remove the comment id
        if (review.isPresent()) {
            review.get().removeComment(foundComment.get().getId());
            reviewRepository.save(review.get());
        }

        // delete it if found, return message
        if (foundComment.isPresent()) {
            commentRepository.deleteById(foundComment.get().getId());
            return Optional.of("Comment with id:" + foundComment.get().getId() + " deleted successfully");
        } else { // else return empty
            return Optional.empty();
        }
    }
}
