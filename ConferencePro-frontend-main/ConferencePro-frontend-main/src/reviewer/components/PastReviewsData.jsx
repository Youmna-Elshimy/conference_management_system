import { useEffect, useState } from "react";
import FeedbackModal from "./FeedbackModal";
import { createCommentOnReview } from "../../services/commentService";
import "./PastReviewsTable.scss";
import { getReview } from "../../services/reviewService";
import { getAllReviewers } from "../../services/userService";

function PastReviewsData({ reviewId, researchPaper }) {
  const [showModal, setShowModal] = useState(false);
  const [reviewDetails, setReviewDetails] = useState({});
  const [reviewerFullName, setReviewerFullName] = useState("");
  const [reviewerEmail, setReviewerEmail] = useState("");

  useEffect(() => {
    const fetchReviewDetails = async () => {
      try {
        const response = await getReview(reviewId);
        setReviewDetails(response);
      } catch (error) {
        console.log(error);
      }
    };

    fetchReviewDetails();
  }, [reviewId]);

  useEffect(() => {
    const fetchReviewerFullName = async () => {
      try {
        const response = await getAllReviewers();
        const reviewer = response.find(
          (reviewer) => reviewer.email === reviewDetails.reviewerEmail
        );
        setReviewerFullName(`${reviewer.firstName} ${reviewer.lastName}`);
        setReviewerEmail(reviewer.email);
      } catch (error) {
        console.log(error);
      }
    };

    fetchReviewerFullName();
  }, [reviewDetails]);

  function openModal() {
    setShowModal(true);
  }

  function closeModal() {
    setShowModal(false);
  }

  async function handleSaveFeedback(comment, paperId) {
    try {
      // Make the API call to create comment using axios or fetch
      const response = await createCommentOnReview(comment, paperId);
      // Handle the response or perform any necessary actions
      console.log(response);
      // Close the modal after saving feedback
      closeModal();
    } catch (error) {
      console.log(error);
      // Handle the error
    }
  }

  return (
    <div className="past-reviews__container-row">
      <p className="past-reviewers__name">{reviewerFullName}</p>
      <p className="past-reviewers__rating-given">{reviewDetails.score}</p>
      <p className="past-reviewers__review-given">{reviewDetails.textReview}</p>
      <div className="add-feedback__action-btn" onClick={openModal}>
        Add Feedback
      </div>

      {showModal && (
        <FeedbackModal
          reviewerName={reviewerFullName}
          reviewerEmail={reviewerEmail}
          paper={researchPaper} // Use the research paper's ID
          onSave={handleSaveFeedback} // Update prop name to 'onSave'
          onCancel={closeModal}
        />
      )}
    </div>
  );
}

export default PastReviewsData;
