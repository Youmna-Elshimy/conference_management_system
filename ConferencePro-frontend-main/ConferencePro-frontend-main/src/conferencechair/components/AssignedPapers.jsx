import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Status from "./Status";
import { getAllAssignedPapers } from "../../services/reviewService";

const AssignedPapers = () => {
  const [assignedPapers, setAssignedPapers] = useState([]);

  useEffect(() => {
    fetchAssignedPapers();
  }, []);

  const fetchAssignedPapers = async () => {
    try {
      const papers = await getAllAssignedPapers();
      setAssignedPapers([...papers]);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <ul>
      {assignedPapers.map((item) => (
        <li className="assigned_papers_row" key={item.id}>
          <Link
            to={`/chair/paper/${item.id}`}
            className="assigned_papers_row-link"
          >
            {item.title}
          </Link>
          <p>
            {item.status && (
              <Status
                value={item.status}
                additionalText={item.status === "pending" ? "Decision" : ""}
              />
            )}
          </p>
        </li>
      ))}
    </ul>
  );
};

export default AssignedPapers;
