import React, { useEffect, useState } from "react";
import StatusModal from "./StatusModal";

import { getAllReviewers } from "../../services/userService";
import { assignPaper } from "../../services/reviewService";
import { useNavigate } from "react-router-dom";

const AssignReviewers = ({ data }) => {
  const [openModal, setOpenModal] = useState(false);
  const [paperBidders, setPaperBidders] = useState([]);
  const [checkedAuthors, setCheckedAuthors] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    // Get Reviewer objects by getting all authors and comparing emails
    getAllReviewers()
      .then(response => {
        let _paperBidders = [];
        data.paperBidders.forEach(function (bidderEmail) {  
          const paperBidder = response.find(bidder => bidder.email === bidderEmail);
          if (paperBidder !== undefined) {
            _paperBidders = [..._paperBidders, paperBidder];
          }
        })
        setPaperBidders([..._paperBidders])
      })
  // eslint-disable-next-line
  },[])

  const confirmHandler = () => {
    console.log(checkedAuthors)
    checkedAuthors.forEach(function (reviewer) {
      assignPaper(reviewer, data.id)
    })
    setOpenModal(true);
  };

  const handleCheck = (event) => {
    let updatedList = [...checkedAuthors];

    if (event.target.checked) {
      updatedList = [...checkedAuthors, paperBidders[event.target.value]];  // If checkbox is checked add to list
    } else {
      updatedList.splice(checkedAuthors.indexOf(paperBidders[event.target.value]), 1);  // Otherwise remove from list
    }

    setCheckedAuthors(updatedList);
  };

  return (
    <>
      <table className="assign_reviewers_table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Specialties</th>
            <th>Workload</th>
            <th>Status</th>
            <th>Assign</th>
          </tr>
        </thead>

        <tbody>
          {React.Children.toArray(
            paperBidders?.map((item, index) => (
              <tr>
                <td>{`${item.firstName} ${item.lastName}`}</td>
                <td>
                  <ul style={{ paddingInlineStart: 0 }}>
                    {React.Children.toArray(
                      item.subjectInterests?.map((sp) => <li>{sp}</li>)
                    )}
                  </ul>
                </td>
                <td>{item.assignedPapers} / {item.paperLimit}</td>
                <td>
                  {item.assignedPapers === item.paperLimit
                  ? "Full Workload"
                  : "Available for Assignment"}
                </td>
                <td>
                    { item.assignedPapers < item.paperLimit
                    ? <input value={index} type="checkbox" onChange={handleCheck} />
                    : "" }
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>

      <div className="flex justify-center" style={{ marginTop: "2rem" }}>
        <button className="btn confirm_btn" onClick={confirmHandler}>
          Confirm
        </button>
      </div>

      {openModal && (
        <StatusModal
          message="Reviewer(s) will be notified"
          title="Paper Has Been Assigned"
          color="green"
          closeModal={() => {setOpenModal(false); navigate("/chair/all-papers")}}
        />
      )}
    </>
  );
};

export default AssignReviewers;
