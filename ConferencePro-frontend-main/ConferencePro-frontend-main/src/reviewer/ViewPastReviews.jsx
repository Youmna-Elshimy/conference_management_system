import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import PageHeader from "../components/PageHeader";
import ReviewerWorkflowLinks from "./components/ReviewerWorkflowLinks";
import PastReviewsTable from "./components/PastReviewsTable";
import { getResearchPaper } from "../services/researchpaperService";
import { getReview } from "../services/reviewService";

function ViewPastReviews() {
  const { id } = useParams();
  const [researchPaper, setResearchPaper] = useState(null);
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    getResearchPaper(id)
      .then((response) => {
        setResearchPaper(response.data);
        setReviews(response.data.reviews);
      })
      .catch((error) => {
        console.error("Error displaying paper data: ", error);
      });
  }, []);

  if (!researchPaper) {
    return <p style={{ padding: "2rem 0", textAlign: "center" }}>Loading...</p>;
  }

  return (
    <div className="page__container">
      <ReviewerWorkflowLinks />
      <div className="page-content__container">
        <PageHeader pageTitle="Thank you for submitting!" />
        <div className="page-content-item__wrapper">
          <p>
            Here are some of the other reviews on this paper. Feel free to
            comment on any.
          </p>
          <PastReviewsTable researchPaper={researchPaper} reviews={reviews} />
          <div id="view-past-reviews__action-btn">
            <button>
              <Link to="/reviewer/dashboard" style={{ color: "inherit" }}>
                Back to Dashboard
              </Link>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ViewPastReviews;
