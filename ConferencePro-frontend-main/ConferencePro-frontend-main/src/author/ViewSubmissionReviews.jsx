import { useState, useEffect } from "react";
import { useParams } from "react-router";
import secureLocalStorage from "react-secure-storage";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFaceSmile, faFaceFrown } from '@fortawesome/free-solid-svg-icons'

import { getAllReviewers } from "../services/userService";
import { getResearchPaper } from "../services/researchpaperService";
import { getReview } from "../services/reviewService";
import { createRatingOnReview, getRating, updateRating } from "../services/ratingService";

import AuthorLinks from "./AuthorLinks";

import styles from "./ViewSubmissionReviews.module.scss";

const ReviewTableRow = ({ reviewId }) => {
  const [reviewDetails, setReviewDetails] = useState({})
  const [reviewerFullName, setReviewerFullName] = useState("")
  const [reviewRating, setReviewRating] = useState({});

  useEffect(() => {  // Get review details
    getReview(reviewId)
      .then(response => {
        setReviewDetails(response);
      });
  // eslint-disable-next-line
  },[]);

  useEffect(() => {  // Get reviewer firstName and lastName
    if (Object.keys(reviewDetails).length !== 0) {
      getAllReviewers()
      .then(response => {
        const reviewer = response.find(element => element.email === reviewDetails.reviewerEmail);
        setReviewerFullName(`${reviewer.firstName} ${reviewer.lastName}`)
      });
    }
  },[reviewDetails])

  useEffect(() => {  // Get review rating (if it exists)
    if (Object.keys(reviewDetails).length !== 0) {
      if (!reviewDetails.ratingIds.length) { return; }
      reviewDetails.ratingIds.forEach(function (ratingId) {
        getRating({id: ratingId})
          .then(response => {
            if (response.authorEmail === JSON.parse(secureLocalStorage.getItem("user")).email) {
              setReviewRating(response)
            }
          })
      })
    }
  },[reviewDetails])

  const changeRatingToPositive = (event) => {
    if (Object.keys(reviewRating).length !== 0) {
      const updatedRating = {...reviewRating, rating: 1}
      updateRating(updatedRating)
        .then(response => {setReviewRating(response)});
    } else {
      const newRating = {
        rating: 1,
        authorEmail: JSON.parse(secureLocalStorage.getItem("user")).email
      }
      createRatingOnReview(newRating, reviewId)
        .then(response => {setReviewRating(response)});
    }
  }

  const changeRatingToNegative = (event) => {
    if (Object.keys(reviewRating).length !== 0) {
      const updatedRating = {...reviewRating, rating: -1}
      updateRating(updatedRating)
        .then(response => {setReviewRating(response)});
    } else {
      const newRating = {
        rating: -1,
        authorEmail: JSON.parse(secureLocalStorage.getItem("user")).email
      }
      createRatingOnReview(newRating, reviewId)
        .then(response => {setReviewRating(response)});
    }
  }

  return (
    <tr>
      <td> {reviewerFullName} </td>
      <td> {reviewDetails.score} </td>
      <td> {reviewDetails.textReview} </td>
      <td className={styles.reviewTableLastColumnCell}>
        {
          Object.keys(reviewRating || {}).length === 0
          ? <FontAwesomeIcon icon={faFaceSmile} size="2xl" style={{color: "#e7ddd7",}} onClick={changeRatingToPositive} />
          : reviewRating.rating === 1
          ? <FontAwesomeIcon icon={faFaceSmile} size="2xl" style={{color: "#F9D77F",}} />
          : <FontAwesomeIcon icon={faFaceSmile} size="2xl" style={{color: "#e7ddd7",}} onClick={changeRatingToPositive} />
        }
        &nbsp;
        {
          Object.keys(reviewRating || {}).length === 0
          ? <FontAwesomeIcon icon={faFaceFrown} size="2xl" style={{color: "#e7ddd7",}} onClick={changeRatingToNegative} />
          : reviewRating.rating === -1
          ? <FontAwesomeIcon icon={faFaceFrown} size="2xl" style={{color: "#F9D77F",}} />
          : <FontAwesomeIcon icon={faFaceFrown} size="2xl" style={{color: "#e7ddd7",}} onClick={changeRatingToNegative} />
        }
      </td>
    </tr>
  )
}

const ReviewTable = ({ researchPaper }) => {
  const reviews = researchPaper.reviews

  return (
    <table className={styles.reviewTable}>
      <colgroup>
        <col span="1" style={{width: '18%'}} />
        <col span="1" style={{width: '14%'}} />
        <col span="1" style={{width: '50%'}} />
        <col span="1" className={styles.reviewTableLastColumn} style={{width: '18%'}} />
      </colgroup>
      <tbody>
        <tr className={styles.reviewTableHeaderRow}>
          <th>Reviewer's Name</th>
          <th>Rating Given</th>
          <th>Review Provided</th>
          <th className={styles.reviewTableLastColumnCell}>Rate the Review</th>
        </tr>
      {reviews.map((item, index) => (
        <ReviewTableRow key={index} reviewId={item}/>
      ))}
      </tbody>
    </table>
  )
}

const ViewSubmissionReviews = () => {
  const { paperId } = useParams();

  const [researchPaper, setResearchPaper] = useState({})

  const hasBeenReviewed = (researchPaper.reviews != null && researchPaper.reviews !== undefined && researchPaper.reviews.length);

  useEffect(() => {
    getResearchPaper(paperId)
      .then(response => {
        setResearchPaper(response.data)
      });
  },[paperId]);

  return ( 
    <div className="page__container">
      <AuthorLinks />

      <div className="page-content__container">
        <div className={styles.headerContainer}>
            <span className={styles.headerText}>
            {hasBeenReviewed ? "Your paper has been reviewed!" : "Your paper is still awaiting review!"}
            </span>
        </div>
        <div className={styles.bodyTextContainer}>
          <div className={styles.bodyText}>

            <p className={styles.paperTitle}>{researchPaper.title}</p>

            <p className={styles.reviewStatus}>
            { 
              hasBeenReviewed 
              ? "Our reviewers have left their ratings and feedback. You can rate the review(s) accordingly."
              : "Our reviewers are still evaluating your paper. Please check this page again soon."
            }
            </p>
            
            { hasBeenReviewed
              ? <ReviewTable researchPaper={researchPaper}/>
              : "" }
            
          </div>
        </div>
      </div>
    </div>
  );
}
 
export default ViewSubmissionReviews;