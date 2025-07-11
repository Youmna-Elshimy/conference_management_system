import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function PaperInfoRow({ paper }) {
  const [isComplete, setStatus] = useState(false);

  useEffect(() => {
    const storedReview = JSON.parse(localStorage.getItem(paper.id));
    if (storedReview) {
      setStatus(true);
    }
  }, []);

  const successStatus = () => {
    setStatus(true);
  };

  const warningStyle = {
    color: "#e05526",
    backgroundColor: "#fbece6",
    padding: "5px",
    borderRadius: "8px",
  };

  const successStyle = {
    color: "#00a651",
    backgroundColor: "#dbf4e7",
    padding: "5px",
    borderRadius: "8px",
  };

  return (
    <div className="row-item__container">
      <div className="row-item">
        <div
          className="paper__status"
          style={isComplete ? successStyle : warningStyle}
        >
          {isComplete ? "Reviewed" : "Incomplete"}
        </div>
        <p className="paper-row__title">{paper.title}</p>
        <Link to={`/reviewer/assigned-paper/${paper.id}`}>
          <button className="paper-abstract__view-link">
            {isComplete ? "Edit Review" : "Review Paper"}
          </button>
        </Link>
      </div>
      <div className="row-item__divider"></div>
    </div>
  );
}

export default PaperInfoRow;
