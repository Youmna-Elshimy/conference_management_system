import React from "react";

export default function MyProgress() {
  return (
    <div className="my_progress">
      <h2>My Progress</h2>

      <div>
        <div>
          <h2>
            <span>2</span>
            <span>/</span>
            <span>2</span>
          </h2>
          <h4>Pending Decisions</h4>
        </div>

        <img src="/images/notes.png" alt="" width={90} />

        <div>
          <h2>
            <span>10</span>
            <span>%</span>
          </h2>
          <h4>Complete Overall</h4>
        </div>
      </div>
    </div>
  );
}
