import React from "react";
import ConfLinks from "./ConfLinks";
import PageHeader from "../components/PageHeader";
import "./styles/inbox.scss";

export default function Inbox() {
  return (
    <div className="page__container">
      <ConfLinks />

      <div className="page-content__container">
        <PageHeader pageTitle="Inbox" />

        <div className="inbox_content">
          <img src="/images/inbox.png" alt="" width={100} />
          <p>No inbox yet.</p>
        </div>
      </div>
    </div>
  );
}
