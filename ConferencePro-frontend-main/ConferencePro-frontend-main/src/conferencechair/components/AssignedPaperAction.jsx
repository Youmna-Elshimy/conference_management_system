import React, { useState } from "react";
import StatusModal from "./StatusModal";
import { updateResearchPaperStatus } from "../../services/researchpaperService";

const AssignedPaperAction = ({ statusChangeHandler, paperData }) => {
  const [openModal, setOpenModal] = useState({
    open: false,
    message: "",
    title: "",
    color: "",
    status: "",
  });

  const acceptHandler = () => {
    const recipients = paperData.authors.join(",");
    const subject = "Paper Accepted";
    const body = "Your paper has been accepted.";
    const decision = "Accepted";

    const mailtoLink = `mailto:${recipients}?subject=${encodeURIComponent(
      subject
    )}&body=${encodeURIComponent(body)}`;

    updateResearchPaperStatus(paperData.id, decision)
      .then((response) => {
        console.log("Paper accepted! ", response);
        window.open(mailtoLink);

        setOpenModal({
          open: true,
          message: "Author will be automatically notified through email",
          title: "Paper Has Been Accepted",
          color: "green",
          status: "accepted",
        });
      })
      .catch((error) => {
        console.error("Error accepting paper: ", error);
      });
  };

  const declineHandler = () => {
    const recipients = paperData.authors.join(",");
    const subject = "Paper Declined";
    const body = "Your paper has been declined.";
    const decision = "Rejected";

    const mailtoLink = `mailto:${recipients}?subject=${encodeURIComponent(
      subject
    )}&body=${encodeURIComponent(body)}`;

    updateResearchPaperStatus(paperData.id, decision)
      .then((response) => {
        console.log("Paper declined! ", response);
        window.open(mailtoLink);

        setOpenModal({
          open: true,
          message: "Author will be automatically notified through email",
          title: "Paper Has Been Declined",
          color: "red",
          status: "rejected",
        });
      })
      .catch((error) => {
        console.error("Error declining paper: ", error);
      });
  };

  const closeHandler = () => {
    setOpenModal({ open: false });
    statusChangeHandler(openModal.status);
  };

  return (
    <div className="assigned_paper_actions_container">
      <h2>Do you wish to accept or decline this paper?</h2>

      <div className="buttons_container">
        <button className="btn accept_btn" onClick={acceptHandler}>
          Accept
        </button>
        <button className="btn decline_btn" onClick={declineHandler}>
          Decline
        </button>
      </div>

      {openModal.open && (
        <StatusModal
          message={openModal.message}
          title={openModal.title}
          color={openModal.color}
          closeModal={closeHandler}
        />
      )}
    </div>
  );
};

export default AssignedPaperAction;
