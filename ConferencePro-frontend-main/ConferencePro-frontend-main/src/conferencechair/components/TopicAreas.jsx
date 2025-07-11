import React from "react";

const TopicAreas = ({ topics, submission }) => {
  return (
    <div className="topics_container">
      <div className="grow">
        <h3 className="text-gray">Topic Areas</h3>
        <ul>
          {React.Children.toArray(topics.map((item) => <li>{item}</li>))}
        </ul>
      </div>

      <div style={{ paddingLeft: "0.75rem", textAlign: "right" }}>
        <h3 className="text-gray">Submission Date</h3>
        <p style={{ fontSize: "small", fontWeight: "500" }}>{submission}</p>
      </div>
    </div>
  );
};

export default TopicAreas;
