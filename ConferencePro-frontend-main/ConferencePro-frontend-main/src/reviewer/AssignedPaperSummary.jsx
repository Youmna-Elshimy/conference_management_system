import React, { useEffect, useState } from "react";
import PageHeader from "../components/PageHeader";
import ReviewerWorkflowLinks from "./components/ReviewerWorkflowLinks";

import "./AssignedPaperSummary.scss";
import { Link, useNavigate, useParams } from "react-router-dom";
import { getResearchPaper } from "../services/researchpaperService";
import AssignedPaperDetails from "./components/AssignedPaperDetails";

function AssignedPaperSummary() {
  const { id } = useParams();
  const navigate = useNavigate;
  const [paperData, setPaperData] = useState(null);

  useEffect(() => {
    getResearchPaper(id)
      .then((response) => {
        setPaperData(response.data);
      })
      .catch((error) => {
        navigate("/reviewer/my-reviews");
        console.error("Error displaying paper data: ", error);
      });
  }, []);

  if (!paperData) {
    return <p style={{ padding: "2rem 0", textAlign: "center" }}>Loading...</p>;
  }

  return (
    <div className="page__container">
      <ReviewerWorkflowLinks />
      <div className="page-content__container">
        <PageHeader pageTitle="Assigned Paper" />
        <AssignedPaperDetails paper={paperData} />
      </div>
    </div>
  );
}

export default AssignedPaperSummary;
