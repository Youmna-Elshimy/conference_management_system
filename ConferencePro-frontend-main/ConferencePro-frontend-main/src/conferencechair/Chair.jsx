import React from "react";
import secureLocalStorage from "react-secure-storage";
import Dashboard from "../components/Dashboard";
import ConfLinks from "./ConfLinks";

export default function Chair() {
  const user = JSON.parse(secureLocalStorage.getItem("user"));

  return (
    <div className="page__container">
      <ConfLinks />

      <Dashboard
        userName={user.name}
        dashboardItems={[
          [
            "Reviewers",
            "View the full list of reviewers in the system here.",
            "/chair/all-reviewers",
          ],
          [
            "Papers",
            "View the full list of submissions for the International STEM Conference 2023.",
            "/chair/all-papers",
          ],
          [
            "Inbox",
            "Any communication within the system can be viewed here.",
            "/chair/inbox",
          ],
        ]}
      />
    </div>
  );
}
