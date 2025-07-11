import React from "react";
import ReviewTableRow from "./ReviewTableRow";
import styles from "../../author/ViewSubmissionReviews.module.scss";

const ReviewTable = ({ researchPaper }) => {
  const reviews = researchPaper.reviews;

  return (
    <table className={styles.reviewTable}>
      <colgroup>
        <col span="1" style={{ width: "18%" }} />
        <col span="1" style={{ width: "14%" }} />
        <col span="1" style={{ width: "50%" }} />
      </colgroup>
      <tbody>
        <tr className={styles.reviewTableHeaderRow}>
          <th>Reviewer's Name</th>
          <th>Rating Given</th>
          <th>Review Provided</th>
        </tr>
        {reviews.map((item, index) => (
          <ReviewTableRow key={index} reviewId={item} />
        ))}
      </tbody>
    </table>
  );
};

export default ReviewTable;
