import { useEffect, useState } from "react";
import PageHeader from "../components/PageHeader";
import RowItem from "../components/RowItem";
import ReviewerLinks from "./components/ReviewerLinks";
import { getPapersMatchingInterests } from "../services/researchpaperService";
import secureLocalStorage from "react-secure-storage";

function BidForPapers() {
  const [papers, setPapers] = useState([]);

  useEffect(() => {
    const user = JSON.parse(secureLocalStorage.getItem("user"));

    getPapersMatchingInterests(user)
      .then((response) => {
        setPapers(response);
      })
      .catch((error) => {
        console.error("Failed to retrieve papers: ", error);
      });
  }, []);

  return (
    <div className="page__container">
      <ReviewerLinks />
      <div className="page-content__container">
        <PageHeader pageTitle="All Papers" />
        {papers.map((paper, index) => (
          <RowItem key={paper.paperId} paper={paper} index={index} />
        ))}
      </div>
    </div>
  );
}

export default BidForPapers;
