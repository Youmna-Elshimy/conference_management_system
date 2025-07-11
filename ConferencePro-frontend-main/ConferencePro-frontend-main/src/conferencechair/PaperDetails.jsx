import React, { useEffect, useState } from "react";
import Status from "./components/Status";
import ReviewTable from "./components/ReviewTable";
import AssignedPaperAction from "./components/AssignedPaperAction";
import UnassignedPaperAction from "./components/UnassignedPaperAction";
import TopicAreas from "./components/TopicAreas";
import PaperDetail from "./components/PaperDetail";
import AssignReviewers from "./components/AssignReviewers";
import { useNavigate, useParams } from "react-router-dom";
import ConfLinks from "./ConfLinks";
import PageHeader from "../components/PageHeader";
import "./styles/papers.scss";
import { getResearchPaper } from "../services/researchpaperService";

const PaperDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [viewReviewer, setViewReviewer] = useState(false);
  const [paperData, setPaperData] = useState(null);
  const [status, setStatus] = useState(null);
  const [isAssigned, setIsAssigned] = useState(false);

  useEffect(() => {
    fetchPaperData();
  }, []);

  const fetchPaperData = async () => {
    try {
      const response = await getResearchPaper(id);
      const paper = response.data;
      if (!paper) {
        navigate("/papers");
        return;
      }

      setPaperData(paper);
      setStatus(paper.status);
      setIsAssigned(paper.reviewers.length === 3);
    } catch (error) {
      console.log(error);
    }
  };

  if (!paperData) {
    return <p style={{ padding: "2rem 0", textAlign: "center" }}>Loading...</p>;
  }

  return (
    <div className="page__container">
      <ConfLinks />

      <div className="page-content__container">
        <PageHeader
          pageTitle={
            viewReviewer
              ? "Assign Reviewers to Paper"
              : isAssigned
              ? "Assigned Paper"
              : "Unassigned Paper"
          }
        />

        {viewReviewer && (
          <p className="back_btn" onClick={() => setViewReviewer(false)}>
            &#8592; Back
          </p>
        )}

        <hr />

        {!viewReviewer && (
          <div className="chair_page_content">
            <Status value={status || "Unassigned"} additionalText={""} />

            <PaperDetail data={paperData} />

            {isAssigned ? (
              <ReviewTable researchPaper={paperData} />
            ) : (
              <TopicAreas
                topics={paperData.keywords}
                submission={paperData.submissionDate}
              />
            )}

            {isAssigned && status === "Pending" ? (
              <AssignedPaperAction
                statusChangeHandler={(st) => setStatus(st)}
                paperData={paperData}
              />
            ) : null}

            {!isAssigned && (
              <UnassignedPaperAction
                viewReviewers={() => setViewReviewer(true)}
              />
            )}
          </div>
        )}

        {viewReviewer && (
          <div className="chair_page_content">
            <AssignReviewers data={paperData} />
          </div>
        )}
      </div>
    </div>
  );
};

export default PaperDetails;
