import React, { useEffect, useState } from "react";
import "./PastReviewsTable.scss";
import PastReviewsData from "./PastReviewsData.jsx";

function PastReviewsTable({ researchPaper, reviews }) {
  return (
    <div className="past-reviewers-table__container">
      <div className="past-reviews__container-row">
        <h2 className="past-reviewers__name-title">Reviewer's Info</h2>
        <h2 className="past-reviewers__rating-given-title">Rating Given</h2>
        <h2 className="past-reviewers__review-given-title">Review Provided</h2>
      </div>
      {reviews.map((review) => (
        <PastReviewsData reviewId={review} researchPaper={researchPaper} />
      ))}
    </div>
  );
}

export default PastReviewsTable;
