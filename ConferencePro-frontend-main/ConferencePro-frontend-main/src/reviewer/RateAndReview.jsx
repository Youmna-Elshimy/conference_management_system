import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { getResearchPaper } from "../services/researchpaperService";
import { createReviewOnPaper, updateReview } from "../services/reviewService";
import PageHeader from "../components/PageHeader";
import ReviewerWorkflowLinks from "./components/ReviewerWorkflowLinks";

import "./RateAndReview.scss";
import secureLocalStorage from "react-secure-storage";

function RateAndReview() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [paperData, setPaperData] = useState(null);
  const [score, setScore] = useState(0);
  const [textReview, setTextReview] = useState("");
  const [user, setUser] = useState(null);

  useEffect(() => {
    setUser(JSON.parse(secureLocalStorage.getItem("user")));

    getResearchPaper(id)
      .then((response) => {
        setPaperData(response.data);
      })
      .catch((error) => {
        navigate("/reviewer/my-reviews");
        console.error("Error displaying paper data: ", error);
      });

    const storedReview = JSON.parse(localStorage.getItem(id));
    if (storedReview) {
      setScore(storedReview.score);
      setTextReview(storedReview.textReview);
    }
  }, []);

  const handleReviewSubmission = () => {
    if (textReview.trim() === "") {
      // If the review is empty, show an error message or perform appropriate validation
      alert("Review is empty!");
      return;
    }

    const reviewData = {
      reviewerEmail: user.email,
      score: score,
      textReview: textReview,
      ratingIds: [],
      commentIds: null,
    };

    const storedReview = JSON.parse(localStorage.getItem(id));
    if (storedReview != null) {
      // Update existing review in localStorage
      localStorage.setItem(id, JSON.stringify(reviewData));

      updateReview(reviewData)
        .then((updatedReview) => {
          // Handle the successful update of the review
          console.log("Review updated:", updatedReview);
          navigate(`/reviewer/past-reviews/${id}`);
        })
        .catch((error) => {
          // Handle the error case
          console.error("Error updating review:", error);
        });
    } else {
      // Create new review in localStorage
      localStorage.setItem(id, JSON.stringify(reviewData));

      createReviewOnPaper(reviewData, paperData.id)
        .then((createdReview) => {
          // Handle the successful creation of the review
          console.log("Review created:", createdReview);
          navigate(`/reviewer/past-reviews/${id}`);
        })
        .catch((error) => {
          // Handle the error case
          console.error("Error creating review:", error);
        });
    }
  };

  return (
    <div className="page__container">
      <ReviewerWorkflowLinks />
      <div className="page-content__container">
        <PageHeader pageTitle="Rating & Review" />
        <div className="page-content-item__wrapper">
          <div className="rating-review__rating-container">
            <h2>
              Overall Merit <span style={{ color: "#e05526" }}>*</span>
            </h2>
            <p>
              How solid is the presented work? Is the evaluation methodology
              appropriate? Does the data seem accurate? How appropriate is this
              submission for this conference? How easy is it to understand the
              topics presented?
            </p>
            <div className="rating-review__desc">
              <ul className="ratings__desc">
                <li>3 : Strongly accept</li>
                <li>2 : Accept</li>
                <li>1 : Weakly accept</li>
              </ul>
              <ul className="ratings__desc">
                <li>-1 : Weakly reject</li>
                <li>-2 : Reject</li>
                <li>-3 : Strongly reject</li>
              </ul>
            </div>
            <div className="rating-review__buttons">
              {[3, 2, 1, 0, -1, -2, -3].map((value) => (
                <button
                  key={value}
                  className={`rating-review__button ${
                    score === value ? "selected" : ""
                  }`}
                  onClick={() => setScore(value)}
                >
                  {value}
                </button>
              ))}
            </div>
          </div>
          <div className="rating-review__review-container">
            <h2>
              Detailed Comments for the Author(s){" "}
              <span style={{ color: "#e05526" }}>*</span>
            </h2>
            <textarea
              id="reviewers-review__textarea"
              name="reviewers-review"
              value={textReview}
              onChange={(e) => setTextReview(e.target.value)}
            />
            <p>
              <i>
                (You can edit your review until the Chair makes a decision.)
              </i>
            </p>
          </div>
          <div className="rating-review__action-buttons">
            <button
              id="complete-review__button"
              onClick={handleReviewSubmission}
            >
              <Link
                to={`/reviewer/past-reviews/${id}`}
                style={{ backgroundColor: "inherit", fontWeight: "700" }}
              >
                Complete Review
              </Link>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RateAndReview;
