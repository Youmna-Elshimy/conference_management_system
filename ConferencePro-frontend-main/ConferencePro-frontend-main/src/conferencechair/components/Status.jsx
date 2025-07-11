import classNames from "classnames";
import React from "react";

const Status = ({ value, additionalText }) => {
  const classes = classNames({
    status_common: true,
    pending: value === "Pending" || "Unassigned",
    rejected: value === "Rejected",
    accepted: value === "Accepted",
  });

  let statusText = value;
  if (value === "Pending") {
    statusText = "Pending Decision";
  }

  if (value === "Unassigned") {
    statusText = "Pending Assignment";
  }

  return (
    <span className={classes}>
      {statusText} {additionalText}
    </span>
  );
};

export default Status;
