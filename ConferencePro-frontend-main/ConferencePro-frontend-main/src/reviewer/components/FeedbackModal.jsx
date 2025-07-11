import React, { useState } from "react";
import "./BidPaperModal.scss";

function FeedbackModal({
  reviewerEmail,
  reviewerName,
  paper,
  onSave,
  onCancel,
}) {
  const [comment, setComment] = useState("");

  const handleInputChange = (event) => {
    setComment(event.target.value);
  };

  const handleSave = () => {
    // Send email to the reviewer
    sendEmailToReviewer(comment);

    // Call the onSave callback to save the feedback
    onSave({ text: comment }, paper.id);
  };

  const handleCancel = () => {
    onCancel();
  };

  const sendEmailToReviewer = (comment) => {
    const recipients = `${reviewerEmail}`;
    const subject = `Discussion about paper: ${paper.title}`;
    const body = `Hey ${reviewerName}! ${comment}`;

    const mailtoLink = `mailto:${recipients}?subject=${encodeURIComponent(
      subject
    )}&body=${encodeURIComponent(body)}`;

    window.open(mailtoLink);
  };

  return (
    <>
      <div className="modal">
        <h2 className="modal__paper-title">Add feedback</h2>
        <h4 className="modal__abstract">To {reviewerName}'s review:</h4>
        <div className="modal__abstract-content">
          <textarea
            id="review-feedback__textarea"
            name="review-feedback"
            rows={5}
            cols={5}
            value={comment}
            onChange={handleInputChange}
          />
        </div>
        <button className="modal__close-btn" onClick={handleSave}>
          Discuss
        </button>
        <button className="modal__cancel-btn" onClick={handleCancel}>
          Cancel
        </button>
      </div>
      <div className="backdrop" />
    </>
  );
}

export default FeedbackModal;
