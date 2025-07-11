package CSCI334.ConferencePro.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "comments")
public class Comment {
  @Id
  private String id;

  private LocalDateTime submittedDateTime;
  private String replyCommentId;  // the id of the comment this comment may be replying to
  private String commenterEmail;
  private String comment;
  

  public Comment() {
  }

  // constructor without id, one will be generated in DB
  public Comment(LocalDateTime submittedDateTime, String replyCommentId, String commenterEmail, String comment) {
    this.submittedDateTime = submittedDateTime;
    this.replyCommentId = replyCommentId;
    this.commenterEmail = commenterEmail;
    this.comment = comment;
  }

  public Comment(String id, LocalDateTime submittedDateTime, String replyCommentId, String commenterEmail, String comment) {
    this.id = id;
    this.submittedDateTime = submittedDateTime;
    this.replyCommentId = replyCommentId;
    this.commenterEmail = commenterEmail;
    this.comment = comment;
  }


  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getSubmittedDateTime() {
    return this.submittedDateTime;
  }

  public void setSubmittedDateTime(LocalDateTime submittedDateTime) {
    this.submittedDateTime = submittedDateTime;
  }

  public String getReplyCommentId() {
    return this.replyCommentId;
  }

  public void setReplyCommentId(String replyCommentId) {
    this.replyCommentId = replyCommentId;
  }

  public String getCommenterEmail() {
    return this.commenterEmail;
  }

  public void setCommenterEmail(String commenterEmail) {
    this.commenterEmail = commenterEmail;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Comment id(String id) {
    setId(id);
    return this;
  }

  public Comment submittedDateTime(LocalDateTime submittedDateTime) {
    setSubmittedDateTime(submittedDateTime);
    return this;
  }

  public Comment replyCommentId(String replyCommentId) {
    setReplyCommentId(replyCommentId);
    return this;
  }

  public Comment commenterEmail(String commenterEmail) {
    setCommenterEmail(commenterEmail);
    return this;
  }

  public Comment comment(String comment) {
    setComment(comment);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(submittedDateTime, comment.submittedDateTime) && Objects.equals(replyCommentId, comment.replyCommentId) && Objects.equals(commenterEmail, comment.commenterEmail) && Objects.equals(comment, comment.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, submittedDateTime, replyCommentId, commenterEmail, comment);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", submittedDateTime='" + getSubmittedDateTime() + "'" +
      ", replyCommentId='" + getReplyCommentId() + "'" +
      ", commenterEmail='" + getCommenterEmail() + "'" +
      ", comment='" + getComment() + "'" +
      "}";
  }
  

}
