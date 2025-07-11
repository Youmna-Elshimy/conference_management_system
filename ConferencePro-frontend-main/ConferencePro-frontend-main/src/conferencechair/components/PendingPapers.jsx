import React from "react";
import { Link } from "react-router-dom";
import Status from "./Status";
import allPapers from "../sample-data/papers.json";

const items = allPapers.filter((item) => !item.isAssigned);

export default function PendingPapers() {
  return (
    <div className="pending_papers">
      <div className="flex items-center">
        <h2 className="font-bold grow">Pending Papers</h2>
        <Link to="/chair/all-papers">See All</Link>
      </div>

      <ul className="py-4">
        {React.Children.toArray(
          items.map((item) => (
            <li>
              <Link to={`/chair/paper/${item.id}`}>{item.title}</Link>
              <p>Reviewed {item.reviewDate}</p>
              <p>
                <Status value="pending" additionalText="Decision" />
              </p>
            </li>
          ))
        )}
      </ul>
    </div>
  );
}
