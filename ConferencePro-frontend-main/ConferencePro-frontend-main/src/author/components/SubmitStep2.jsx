import styles from "../submit2.module.scss";

const SubmitStep2 = ({ formState, setFormState, setHeaderText }) => {
  setHeaderText("New Submission - Title & Abstract");

  const handleTitleChange = (event) => {
    setFormState({
      ...formState,
      title: event.target.value
    })
  }

  const handleAbstractChange = (event) => {
    setFormState({
      ...formState,
      paperAbstract: event.target.value
    })
  }

  return ( 
    <>
      <label className={`${styles.heading} ${styles.boldText}`} htmlFor='title'>Title</label>
      <br/>
      <input className={styles.inputField} type="text" id='title' onChange={handleTitleChange}/>
      <br/><br/>
      <label className={`${styles.heading} ${styles.boldText}`} htmlFor='abstract'>Abstract</label>
      <br/>
      <textarea className={styles.textArea} id='abstract' onChange={handleAbstractChange}></textarea>
    </>
   );
}
 
export default SubmitStep2;