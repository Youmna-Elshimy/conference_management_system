import React, { useEffect, useState } from "react";
import { updateUser } from "../../services/userService";
import { getUser } from "../../services/userService";
import secureLocalStorage from "react-secure-storage";

import "./ReviewerPreferences.scss";

function ReviewersPreferencesForm() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [subjectInterests, setTopics] = useState([]);
  const [paperLimit, setpaperLimit] = useState("");

  useEffect(() => {
    const user = JSON.parse(secureLocalStorage.getItem("user"));

    setFirstName(user.firstName);
    setLastName(user.lastName);
    setEmail(user.email);
    setTopics(user.subjectInterests);
    setpaperLimit(user.paperLimit);
  }, []);

  const handleFirstNameChange = (event) => {
    setFirstName(event.target.value);
  };

  const handleLastNameChange = (event) => {
    setLastName(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleTopicChange = (event) => {
    const topic = event.target.value;
    const isChecked = event.target.checked;

    if (isChecked) {
      setTopics((prevTopics) => [...prevTopics, topic]);
    } else {
      setTopics((prevTopics) => prevTopics.filter((t) => t !== topic));
    }
  };

  const handlepaperLimitChange = (event) => {
    setpaperLimit(event.target.value);
  };

  const userType = "Reviewer";

  const handleSubmit = (event) => {
    event.preventDefault();

    // Prepare the data to be sent to the updateUser function
    const formData = {
      firstName,
      lastName,
      email,
      userType,
      subjectInterests,
      paperLimit: parseInt(paperLimit),
    };

    // Call the updateUser function from userService.js
    updateUser(formData)
      .then((response) => {
        // Handle the response from the endpoint
        console.log("Preferences saved:", response);
        // Save the preferences to local storage
        secureLocalStorage.setItem("user", JSON.stringify(formData));
        alert("Preferences saved!");
      })
      .catch((error) => {
        // Handle any errors that occur during the request
        console.error("Error saving preferences:", error);
        // Perform any error handling or display an error message
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="pref-form__container">
        <div className="pref-form__row-wrapper">
          <div className="pref-form__title-field pref-form-name">
            <label>
              <h3>First Name</h3>
              <input
                type="text"
                value={firstName}
                onChange={handleFirstNameChange}
                required
              />
            </label>
          </div>
          <div className="pref-form__title-field pref-form-name">
            <label>
              <h3>Last Name</h3>
              <input
                type="text"
                value={lastName}
                onChange={handleLastNameChange}
                required
              />
            </label>
          </div>
          <div className="pref-form__title-field pref-form-org">
            <label>
              <h3>Email</h3>
              <input
                type="text"
                value={email}
                onChange={handleEmailChange}
                required
              />
            </label>
          </div>
        </div>
        <div className="pref-form__title-field">
          <h3>Topics</h3>
          <p>
            Choose topics that match your area of expertise. These topics will
            be used to assign you submissions to review.
          </p>
          <div className="pref-form__row-wrapper">
            <div className="pref-row__topic-wrapper">
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Computer Science"
                  checked={subjectInterests.includes("Computer Science")}
                  onChange={handleTopicChange}
                />
                Computer Science
              </div>
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Software Engineering"
                  checked={subjectInterests.includes("Software Engineering")}
                  onChange={handleTopicChange}
                />
                Software Engineering
              </div>
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Database Systems"
                  checked={subjectInterests.includes("Database Systems")}
                  onChange={handleTopicChange}
                />
                Database Systems
              </div>
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Computer & IT"
                  checked={subjectInterests.includes("Computer & IT")}
                  onChange={handleTopicChange}
                />
                Computer & IT
              </div>
            </div>
            <div className="pref-row__topic-wrapper">
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Virtual Reality"
                  checked={subjectInterests.includes("Virtual Reality")}
                  onChange={handleTopicChange}
                />
                Virtual Reality
              </div>
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Artificial Intelligence"
                  checked={subjectInterests.includes("Artificial Intelligence")}
                  onChange={handleTopicChange}
                />
                Artificial Intelligence
              </div>
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Data Mining"
                  checked={subjectInterests.includes("Data Mining")}
                  onChange={handleTopicChange}
                />
                Data Mining
              </div>
            </div>
            <div className="pref-row__topic-wrapper">
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Software Design"
                  checked={subjectInterests.includes("Software Design")}
                  onChange={handleTopicChange}
                />
                Software Design
              </div>
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Algorithms & Data Structures"
                  checked={subjectInterests.includes(
                    "Algorithms & Data Structures"
                  )}
                  onChange={handleTopicChange}
                />
                Algorithms & Data Structures
              </div>
              <div className="pref-form-topic">
                <input
                  type="checkbox"
                  value="Information Systems"
                  checked={subjectInterests.includes("Information Systems")}
                  onChange={handleTopicChange}
                />
                Information Systems
              </div>
            </div>
          </div>
        </div>
        <div>
          <label>
            Maximum no. of papers to review:
            <input
              className="pref-form-max-num"
              type="number"
              value={paperLimit}
              onChange={handlepaperLimitChange}
              max={10}
            />
          </label>
        </div>
        <div className="pref-form__btn-wrapper">
          <button className="pref-form__submit-btn" type="submit">
            Save Preferences
          </button>
        </div>
      </div>
    </form>
  );
}

export default ReviewersPreferencesForm;
