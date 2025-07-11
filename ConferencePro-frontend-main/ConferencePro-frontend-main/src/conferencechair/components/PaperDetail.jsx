import React from "react";
import Authors from "./Authors";

const PaperDetail = ({ data }) => {
  return (
    <>
      <div className="flex" style={{ paddingTop: "2rem" }}>
        <div className="grow">
          <h3 className="text-gray">Title</h3>
          <p style={{ fontWeight: "bold", fontSize: "larger" }}>{data.title}</p>
        </div>

        <div className="grow">
          <h3 className="text-gray">Authors</h3>
          <Authors data={data.authors} />
        </div>
      </div>

      <div style={{ padding: "2rem 0" }}>
        <h3 className="text-gray">Abstract</h3>
        <p style={{ textAlign: "justify" }}>{data.paperAbstract}</p>
      </div>
    </>
  );
};

export default PaperDetail;
