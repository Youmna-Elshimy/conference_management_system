import React from "react";

const Authors = ({ data }) => {
  return (
    <ol className="authors_list">
      {React.Children.toArray(
        data.map((item) => (
          <li>
            <div className="flex items-center">
              <p className="author_name">{item}</p>
            </div>
          </li>
        ))
      )}
    </ol>
  );
};

export default Authors;
