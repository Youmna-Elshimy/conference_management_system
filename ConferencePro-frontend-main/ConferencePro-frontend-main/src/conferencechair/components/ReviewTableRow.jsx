import React, { useEffect, useState } from "react";
import { getReview } from "../../services/reviewService";
import { getAllReviewers } from "../../services/userService";

const ReviewTableRow = ({ reviewId }) => {
  const [reviewDetails, setReviewDetails] = useState({});
  const [reviewerFullName, setReviewerFullName] = useState("");

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
      } catch (error) {
        console.log(error);
      }
    };

    fetchReviewerFullName();
  }, [reviewDetails]);

  return (
    <tr>
      <td> {reviewerFullName} </td>
      <td> {reviewDetails.score} </td>
      <td> {reviewDetails.textReview} </td>
    </tr>
  );
};

export default ReviewTableRow;
