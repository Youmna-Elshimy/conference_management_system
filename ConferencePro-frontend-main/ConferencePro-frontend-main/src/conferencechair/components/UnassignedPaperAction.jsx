import React from 'react';

const UnassignedPaperAction = ({ viewReviewers }) => {
  return (
    <div className='unassigned_paper_actions_container'>
      <h2>Do you wish to assign this paper?</h2>

      <div className='buttons_container'>
        <button
          className='btn view_reviewers_btn'
          onClick={viewReviewers}
        >
          View Reviewers
        </button>
      </div>
    </div>
  );
};

export default UnassignedPaperAction;
