import { useState } from "react";

import SubmissionForm from "./components/SubmissionForm";
import SubmissionNavbar from "./components/SubmissionNavbar";

import styles from './newsubmission.module.scss'

import tick from '../images/tick.svg'

const ImportantInformation = ({ continueToForm }) => {
  return (
    <>
      <p className={styles.bodyText}>
        <span className={`${styles.heading} ${styles.boldText}`}>IMPORTANT INFORMATION:</span>
        <br/>
        <ul>
          <li>
            Paper titles should be <span className={styles.boldText}>Title Case Format</span> (example: The End of History?)
          </li>
          <li>
            Please check abstracts for errors before submitting and ensure that correct email addresses are provided for authors.
          </li>
          <li>
            Authors must use the same email address during the submissions process and when registering for the PSA Conference.
          </li>
          <li>
            Need Guidance? On the left-hand side of your profile dashboard there is a Help section - click 'Author Articles' for submission guides. Contact details are also provided if you require any further assistance.
          </li>
        </ul>
        <span className={styles.boldText}>Please read the <span className={styles.textHighlight}>Terms & Conditions</span> before proceeding.</span>
        <br/>
        <img className={styles.tick} src={tick} alt="tick"/> <span className={styles.inlineText}>I have read and agree to the Terms & Conditions</span>
      </p>
      <div className={styles.buttonContainer}>
          <button className={styles.button} onClick={continueToForm}>Start Workflow</button>
      </div>
    </>
  ); 
}

const NewSubmission = () => {
  const [headerText, setHeaderText] = useState("New Submission")

  const [showForm, setShowForm] = useState(false);

  const continueToForm = () => {
    setShowForm(true);
  }

  return ( 
    <div className={styles.pageContainer}>
      <SubmissionNavbar />

      <div className={"page-content__container"}>
        <div className={styles.headerContainer}>
          <span className={styles.headerText}>{ headerText }</span>
        </div>
        <div className={styles.bodyTextContainer}>
          { showForm ? <SubmissionForm setHeaderText={setHeaderText} /> : <ImportantInformation continueToForm={continueToForm} /> }
        </div>
      </div>
    </div>
  );
}
 
export default NewSubmission;