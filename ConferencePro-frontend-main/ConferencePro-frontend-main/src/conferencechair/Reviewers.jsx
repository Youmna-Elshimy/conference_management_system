import React, { useEffect, useState } from "react";
import PageHeader from "../components/PageHeader";
import ReviewersTable from "./components/ReviewersTable";
import "./styles/common.scss";
import "./styles/reviewers.scss";
import ConfLinks from "./ConfLinks";
import { getAllReviewers } from "../services/userService";

export default function Reviewers() {
  const [reviewers, setReviewers] = useState([]);

  useEffect(() => {
    fetchReviewers();
  }, []);

  async function fetchReviewers() {
    try {
      const response = await getAllReviewers();
      setReviewers(response);
    } catch (error) {
      console.log(error);
      alert("Failed to retrieve reviewers. Please try again.");
    }
  }

  return (
    <div className="page__container">
      <ConfLinks />

      <div className="page-content__container">
        <PageHeader pageTitle="Reviewers" />
        <div className="page-content-item__wrapper">
          <ReviewersTable data={reviewers} />
        </div>
      </div>
    </div>
  );
}
