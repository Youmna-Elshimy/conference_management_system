import { useState } from "react";
import BidPaperModal from "../reviewer/components/BidPaperModal";
import { bidOnResearchPaper } from "../services/researchpaperService";
import "./RowItem.scss";
import secureLocalStorage from "react-secure-storage";

function RowItem({ paper, index }) {
  const [showModal, setShowModal] = useState(false);
  const [bidSuccess, setBidSuccess] = useState(false);
  const [disabled, setDisabled] = useState(false);

  function viewAbstract() {
    setShowModal(true);
  }

  function okBtn() {
    setShowModal(false);
  }

  function bidForPaper() {
    if (bidSuccess) {
      return;
    }

    const user = JSON.parse(secureLocalStorage.getItem("user"));
    const reviewerEmail = user.email;
    if (!reviewerEmail) {
      console.log("Reviewer email not found in localStorage");
      return;
    }

    const paperId = paper.id;

    bidOnResearchPaper(reviewerEmail, paperId)
      .then((response) => {
        console.log("Bid successful:", response);
        setBidSuccess(true);
        setDisabled(true);
        alert(
          "Bid succesful! The Conference Chair will assign this paper to you, based on your current workload."
        );
      })
      .catch((error) => {
        console.log("Bid error:", error);
        alert("Failed to bid on the paper. Please try again.");
      });
  }

  return (
    <div className="row-item__container">
      <div className="row-item">
        <div className="paper__id">#{index + 1}</div>
        <p className="paper__title">{paper.title}</p>
        <button onClick={viewAbstract} className="paper-abstract__view-link">
          View Abstract
        </button>
        <button
          className="paper__action-btn"
          onClick={bidForPaper}
          disabled={disabled}
        >
          {bidSuccess ? "Pending" : "Bid for paper"}
        </button>
      </div>
      <div className="row-item__divider"></div>
      {showModal && <BidPaperModal okBtn={okBtn} paper={paper} />}
    </div>
  );
}

export default RowItem;
