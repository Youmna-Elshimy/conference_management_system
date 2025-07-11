import { Link } from "react-router-dom";
import PageHeader from "../../components/PageHeader";
import ReviewerWorkflowLinks from "./ReviewerWorkflowLinks";

function AssignedPaperDetails({ paper }) {
  return (
    <div className="summary__container">
      <div id="paper-summary__status">{paper.status}</div>
      <div className="paper__content-two-columns">
        <div id="paper-summary__title">
          <h3>Title</h3>
          <b>
            <p>{paper.title}</p>
          </b>
        </div>
        <div id="paper-summary__author">
          <h3>Author(s)</h3>
          {paper.authors.map((author, index) => (
            <p key={index}>{author}</p>
          ))}
        </div>
      </div>
      <div className="paper__content-single">
        <h3>Abstract</h3>
        <p>{paper.paperAbstract}</p>
      </div>
      <div className="paper__content-single">
        <h3>Topic Areas</h3>
        <div id="paper__topic-areas">
          {paper.keywords.map((keyword, index) => (
            <div className="paper__topic-area" key={index}>
              {keyword}
            </div>
          ))}
        </div>
      </div>
      <div className="paper__content-two-columns">
        <div id="paper__submission-date">
          <h3>Submission Date</h3>
          <p>{paper.submissionDate}</p>
        </div>
      </div>
      <div id="review__confirmation">
        <p>
          <span role="img" aria-label="celebration-emoji">
            &#x1F389;
          </span>{" "}
          You've been assigned to review this paper! <br></br>Have a read at the
          abstract and click to leave your rating & review.
        </p>
        <button id="review__accept-btn">
          <Link
            to={`/reviewer/rating-and-review/${paper.id}`}
            style={{ backgroundColor: "inherit", color: "inherit" }}
          >
            Leave Review
          </Link>
        </button>
      </div>
    </div>
  );
}

export default AssignedPaperDetails;
