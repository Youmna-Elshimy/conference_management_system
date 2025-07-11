import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import secureLocalStorage from "react-secure-storage";
import { getAllPapersByAuthor } from "../services/researchpaperService";

import AuthorLinks from "./AuthorLinks";

import styles from "./MySubmissions.module.scss";

const SubmissionRow = ({ ResearchPaper }) => {
  const hasBeenReviewed =
    ResearchPaper.reviews != null &&
    ResearchPaper.reviews !== undefined &&
    ResearchPaper.reviews.length;

  return (
    <div className={styles.paperRow}>
      <div>
        <span
          className={
            hasBeenReviewed
              ? styles.statusBoxreviewed
              : styles.statusBoxnotReviewed
          }
        >
          {hasBeenReviewed ? "Reviewed" : "Not Reviewed"}
        </span>
      </div>
      <div className="paper-row__title">{ResearchPaper.title}</div>
      <div className={styles.viewPaper}>
        <Link to={`/author/submissions/${ResearchPaper.id}`}>View Paper</Link>
      </div>
    </div>
  );
};

const MySubmissions = () => {
  const [submissionList, setSubmissionList] = useState([]);

  useEffect(() => {
    getAllPapersByAuthor(secureLocalStorage.getItem("user")).then(
      (response) => {
        setSubmissionList([...response.data]);
      }
    );
  }, []);

  return (
    <div className="page__container">
      <AuthorLinks />

      <div className={"page-content__container"}>
        <div className={styles.headerContainer}>
          <span className={styles.headerText}>My Submissions</span>
        </div>
        <div className={styles.bodyTextContainer}>
          <div className={styles.bodyText}>
            <div className={styles.rowsContainer}>
              {submissionList.map((item, index) => (
                <SubmissionRow key={index} ResearchPaper={item} />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MySubmissions;
