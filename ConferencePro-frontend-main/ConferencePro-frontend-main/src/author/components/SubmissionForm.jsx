import { useState } from "react";
import { useNavigate } from "react-router-dom";

import { createResearchPaper } from "../../services/researchpaperService";

import SubmitStep1 from "./SubmitStep1";
import SubmitStep2 from "./SubmitStep2";
import SubmitStep3 from "./SubmitStep3";
import SubmitStep4 from "./SubmitStep4";

import styles from "../newsubmission.module.scss";

const SubmissionForm = ({ setHeaderText }) => {
  const navigate = useNavigate();

  const [stepNum, setStepNum] = useState(1);

  const [formState, setFormState] = useState({
    title: "",
    paperAbstract: "",
    authors: [],
    keywords: [],
  });

  const handleNext = (event) => {
    event.preventDefault();
    setStepNum(stepNum + 1);
  }

  const handleSubmit = (event) => {
    event.preventDefault();

    const researchPaper = {
      title: formState.title,
      paperAbstract : formState.paperAbstract,
      authors : formState.authors.filter(author => author !== ""),
      keywords: formState.keywords,
      pdfLink: ""
    };

    /* Form Validation - Ensure no fields are left empty */

    if (researchPaper.title === "") {
      alert("Paper submission failed. Paper title can't be empty. Please try again.");
      window.location.reload();
      return;
    }

    if (researchPaper.paperAbstract === "") {
      alert("Paper submission failed. Paper abstract can't be empty. Please try again.");
      window.location.reload();
      return;
    }

    if (!researchPaper.authors.length) {
      alert("Paper submission failed. Paper authors can't be empty. Please try again.");
      window.location.reload();
      return;
    }

    if (!researchPaper.keywords.length) {
      alert("Paper submission failed. Paper must have at least one topic selected. Please try again.");
      window.location.reload();
      return;
    }

    createResearchPaper(researchPaper).then(navigate("/author"));
  }

  const conditionalComponent = () => {
    switch (stepNum) {
      case 1:
        return <SubmitStep1 formState={formState} setFormState={setFormState} setHeaderText={setHeaderText} />;
      case 2:
        return <SubmitStep2 formState={formState} setFormState={setFormState} setHeaderText={setHeaderText} />;
      case 3:
        return <SubmitStep3 formState={formState} setFormState={setFormState} setHeaderText={setHeaderText} />;
      case 4:
        return <SubmitStep4 formState={formState} setFormState={setFormState} setHeaderText={setHeaderText} />;
      default:
        return <SubmitStep1 formState={formState} setFormState={setFormState} setHeaderText={setHeaderText} />;
    }
  }

  return (
    <>
      <p className={styles.bodyText}>
        {conditionalComponent()}
      </p>
      <div className={styles.buttonContainer}>
        <button 
          className={styles.button}
          onClick={ stepNum < 4 ? handleNext : handleSubmit }
        >
          { stepNum < 4 ? "Next →" : "Submit" }
        </button>
      </div> 
    </>
  );
}
 
export default SubmissionForm;