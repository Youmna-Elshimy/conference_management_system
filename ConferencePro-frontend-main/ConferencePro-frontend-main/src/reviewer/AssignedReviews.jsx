import React, { useEffect, useState } from "react";
import PageHeader from "../components/PageHeader";
import { getPapersReviewedBy } from "../services/reviewService";
import ReviewerLinks from "./components/ReviewerLinks";
import PaperInfoRow from "../components/PaperInfoRow";
import secureLocalStorage from "react-secure-storage";

function AssignedReviews() {
  const [assignedPapers, setAssignedPapers] = useState([]);

  useEffect(() => {
    // Fetch the assigned papers when the component mounts
    fetchAssignedPapers();
  }, []);

  const fetchAssignedPapers = async () => {
    try {
      // Retrieve the current user from local storage
      const currentUser = JSON.parse(secureLocalStorage.getItem("user"));

      if (currentUser) {
        const reviewer = { id: currentUser.id };
        const papers = await getPapersReviewedBy(currentUser);
        setAssignedPapers(papers);
      } else {
        // Handle the case when the current user is not available in local storage
        console.error("Current user not found in local storage");
      }
    } catch (error) {
      console.error("Error fetching assigned papers:", error);
      // Handle the error as needed
    }
  };

  return (
    <div className="page__container">
      <ReviewerLinks />
      <div className="page-content__container">
        <PageHeader pageTitle="My Assigned Reviews" />
        {assignedPapers &&
          assignedPapers.map((paper) => (
            <PaperInfoRow key={paper.id} paper={paper} />
          ))}
      </div>
    </div>
  );
}

export default AssignedReviews;
