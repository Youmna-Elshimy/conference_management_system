import { useEffect, useState } from "react";

import styles from "../submit4.module.scss";

const topicList = [
  "Software Engineering",
  "Computer Science",
  "Database Systems",
  "Computer & IT",
  "Virtual Reality",
  "Artifical Intelligence",
  "Data Mining",
  "Software Design",
  "Algorithms & Data Structures",
  "Information Systems",
];

const TopicSelector = ({ formState, setFormState }) => {
  const [checked, setChecked] = useState([]);

  const handleCheck = (event) => {
    let updatedList = [...checked];

    if (event.target.checked) {
      updatedList = [...checked, event.target.value]; // If checkbox is checked add to list
    } else {
      updatedList.splice(checked.indexOf(event.target.value), 1); // Otherwise remove from list
    }

    setChecked(updatedList);
  };

  useEffect(() => {
    // Ensure setChecked is finished before running following code
    setFormState({
      ...formState,
      keywords: checked,
    });
    // eslint-disable-next-line
  }, [checked]);

  return (
    <div className={styles.topicSelectorContainer}>
      <div className={styles.topicSelectorHeadingContainer}>
        <div className={styles.topicSelectorHeading}>TOPICS</div>
        <div className={styles.topicSelectorSubHeading}>10 Topics</div>
      </div>
      <div className={styles.topicSelectorCheckboxContainer}>
        {topicList.map((item, index) => (
          <div key={index}>
            <input value={item} type="checkbox" onChange={handleCheck} /> {item}
          </div>
        ))}
      </div>
    </div>
  );
};

const SubmitStep4 = ({ formState, setFormState, setHeaderText }) => {
  setHeaderText("New Submission - Topics");

  return (
    <div className={styles.formContainer}>
      <div className={styles.message}>
        Please select a maximum of five (5) topics from the list below.
      </div>
      <TopicSelector formState={formState} setFormState={setFormState} />
    </div>
  );
};

export default SubmitStep4;
