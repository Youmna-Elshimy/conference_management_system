import { useEffect, useState } from 'react';
import Select from 'react-select'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';

import styles from "../submit3.module.scss";

const dropdownOptions = [
  { value: 'Mr', label: 'Mr'},
  { value: 'Mrs', label: 'Mrs'},
  { value: 'Ms', label: 'Ms'},
  { value: 'Dr', label: 'Dr'}
]

const customStyles = {  // CSS Styling for <Select> element
  control: (base) => ({
    ...base,
    height: '6vh',
    minHeight: '6vh',
    width: '9vw',
    background: '#fdfdfd',
    borderRadius: '10px',
    marginTop: '1vh',
    marginBottom: '2vh',
    borderWidth: '2px',
    borderColor: '#D9D9D8',
    boxSizing: 'border-box',
    fontWeight: '400',
  }),

  container: (base) => ({
    ...base,
    height: '6vh',
  }),

  valueContainer: (base) => ({
    ...base,
    paddingLeft: '5%',
    paddingRight: '5%',
    fontSize: '2.2vh',
    height: '6vh'
  }),

  indicatorsContainer: (base) => ({
    ...base,
    height: '6vh'
  }),

  menu: (base) => ({
    ...base,
    background: '#fdfdfd',
    borderRadius: '1vh',
    fontSize: '2.2vh'
  }),

  placeholder: (base) => ({
    ...base,
    color: 'rgba(0, 0, 0, 0.35)',
  }),

  singleValue: (base) => ({
    ...base,
    color: 'rgb(0, 0, 0)',
  }),

  option: (base) => ({
    ...base,
    fontWeight: '400',
    color: 'rgb(0, 0, 0)',
  })
}

const AuthorInfo = ({ arrayIndex, paperAuthors, setPaperAuthors }) => {
  const handleChange = (event) => {
    let updatedPaperAuthors = [...paperAuthors];
    updatedPaperAuthors[arrayIndex] = event.target.value;
    setPaperAuthors([...updatedPaperAuthors]);
  }

  return (
    <div className={styles.authorInfoContainer}>
      <div className={styles.infoItem}>
        Title
        <br/>
        <Select styles={customStyles} name="title" isSearchable={false} options={dropdownOptions} />
      </div>
      <div className={styles.infoItem}>
        First Name
        <br/>
        <input className={styles.inputField} name="firstName" type="text" />
      </div>
      <div className={styles.infoItem}>
        Last Name
        <br/>
        <input className={styles.inputField} name='lastName' type="text" />
      </div>
      <div className={styles.infoItem}>
        Email
        <br/>
        <input className={styles.inputField} name='email' type="text" onChange={handleChange} />
      </div>
    </div>
  );
}

const SubmitStep3 = ({ formState, setFormState, setHeaderText }) => {
  setHeaderText("New Submission - Authors");

  const [paperAuthors, setPaperAuthors] = useState([""])

  const addAuthor = () => {
    setPaperAuthors([...paperAuthors, ""])
  }

  useEffect(() => {
    setFormState({
      ...formState,
      authors: [...paperAuthors],
    });
  // eslint-disable-next-line
  },[paperAuthors]);

  return (
    <>
      {paperAuthors.map((item, index) => (
        <AuthorInfo arrayIndex={index} paperAuthors={paperAuthors} setPaperAuthors={setPaperAuthors} />
      ))}
      <div className={styles.addAuthorButton}>
        <FontAwesomeIcon icon={faCirclePlus} size="lg" style={{color: "#e1705d",}} />
        <div className={styles.addAuthorButtonText} onClick={addAuthor}>
          Add another author
        </div>
      </div>
    </>
  );
}
 
export default SubmitStep3;