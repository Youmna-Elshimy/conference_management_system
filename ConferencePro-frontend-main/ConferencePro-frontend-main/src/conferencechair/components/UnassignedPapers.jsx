import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Status from "./Status";
import { getAllUnassignedPapers } from "../../services/reviewService";

const UnassignedPapers = () => {
  const [unassignedPapers, setUnassignedPapers] = useState([]);

  useEffect(() => {
    fetchUnassignedPapers();
  }, []);

  const fetchUnassignedPapers = async () => {
    try {
      const papers = await getAllUnassignedPapers();
      setUnassignedPapers([...papers]);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <ul>
      {unassignedPapers.map((item) => (
        <li className="unassigned_papers_row" key={item.id}>
          <Link
            to={`/chair/paper/${item.id}`}
            className="assigned_papers_row-link"
          >
            {item.title}
          </Link>
          <p>
            <Status value="Unassigned" additionalText="" />
          </p>
        </li>
      ))}
    </ul>
  );
};

export default UnassignedPapers;
