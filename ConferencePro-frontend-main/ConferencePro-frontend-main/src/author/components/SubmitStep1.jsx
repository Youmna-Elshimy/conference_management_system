import styles from "../submit1.module.scss";

const SubmitStep1 = ({ formState, setFormState, setHeaderText }) => {
  setHeaderText("New Submission - Track");

  return (
    <>
      <span className={styles.heading}>Which track would you like to submit to?</span>
      <br/><br/>
      <input type="radio" name="track"/><span className={styles.radioLabel}>Open Call for Paper Submissions</span>
      <br/>
      <input type="radio" name="track"/><span className={styles.radioLabel}>Specialist Group Submissions</span>
    </>
  );
}
 
export default SubmitStep1;