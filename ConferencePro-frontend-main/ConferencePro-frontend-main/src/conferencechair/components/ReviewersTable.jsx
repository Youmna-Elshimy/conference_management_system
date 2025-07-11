import React from "react";

const ReviewersTable = ({ data }) => {
  return (
    <table className="chair_reviewers_table">
      <thead className="chair_reviewers_table_header">
        <tr>
          <th>Name</th>
          <th>Specialties</th>
          <th>Workload</th>
          <th>Status</th>
        </tr>
      </thead>

      <tbody>
        {data.map((item) => (
          <tr key={item.id} className="chair_reviewers_table_row">
            <td>
              {item.firstName} {item.lastName}
            </td>
            <td>
              <ul>
                {item.subjectInterests.map((sp) => (
                  <li key={sp}>{sp}</li>
                ))}
              </ul>
            </td>
            <td>
              {item.assignedPapers} / {item.paperLimit}
            </td>
            <td>
              {item.assignedPapers === item.paperLimit
                ? "Full Workload"
                : "Available for Assignment"}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ReviewersTable;
