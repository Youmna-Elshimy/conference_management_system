import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import Select from "react-select";
import { signup } from "../slices/userauthSlice";

import styles from "./signup.module.scss";

import logo from "../images/logo.svg";
import graphic from "../images/graphic.png";

const dropdownOptions = [
  { value: "author", label: "Author" },
  { value: "reviewer", label: "Reviewer" },
];

const customStyles = {
  // CSS Styling for <Select> element
  control: (base) => ({
    ...base,
    height: "5.5vh",
    minHeight: "5.5vh",
    width: "22vw",
    background: "#fdfdfd",
    borderRadius: "10px",
    marginTop: "1vh",
    marginBottom: "2vh",
    borderWidth: "1px",
    borderColor: "rgba(0, 0, 0, 0.25)",
    boxSizing: "border-box",
  }),

  container: (base) => ({
    ...base,
    height: "5.5vh",
  }),

  valueContainer: (base) => ({
    ...base,
    paddingLeft: "1.25vw",
    paddingRight: "1.25vw",
    fontSize: "1.8vh",
    height: "5.5vh",
  }),

  indicatorsContainer: (base) => ({
    ...base,
    height: "5.5vh",
  }),

  menu: (base) => ({
    ...base,
    background: "#fdfdfd",
    borderRadius: "10px",
    fontSize: "1.8vh",
  }),

  placeholder: (base) => ({
    ...base,
    color: "rgba(14, 25, 77, 0.35)",
  }),

  singleValue: (base) => ({
    ...base,
    color: "rgba(14, 25, 77, 1)",
  }),
};

const Signup = () => {
  const dispatch = useDispatch();

  const navigate = useNavigate();

  const handleSignup = async (event) => {
    event.preventDefault(); // Stop page from reloading

    const fullName = event.target.signupFullname.value.split(" ");

    const userDetails = {
      email: event.target.signupEmail.value,
      password: event.target.signupPassword.value,
      firstName: fullName[0],
      lastName: fullName[1] ? fullName[1] : "",
      userType: event.target.signupRole.value,
    };

    try {
      await dispatch(signup(userDetails)).unwrap();

      navigate("/login", {
        state: { userType: event.target.signupRole.value },
      });
    } catch (error) {
      console.log(error);
      alert("Signup failed. Please try again.");
    }
  };

  return (
    <div className={styles.pageContainer}>
      <div className={styles.topCornerContainer}>
        <Link to="/landing">
          <img className={styles.topCornerLogo} src={logo} alt="logo" />
          <span className={styles.topCornerTitle}>ConferencePro</span>
        </Link>
      </div>
      <div className={styles.leftContainer}>
        <div className={styles.headingContainer}>
          <p className={styles.heading}>Create an account</p>
          <p className={styles.headingSubtext}>
            Explore our powerful conference management system.
            <br />
            Built for painless peer review.
          </p>
        </div>
        <form className={styles.form} onSubmit={handleSignup}>
          <label htmlFor="signupFullname">Your name</label>
          <br />
          <input
            className={styles.formInput}
            type="text"
            id="signupFullname"
            name="signupFullname"
            placeholder="Claudia Alves"
          ></input>
          <br />

          <label htmlFor="signupEmail">Your email address</label>
          <br />
          <input
            className={styles.formInput}
            type="email"
            id="signupEmail"
            name="signupEmail"
            placeholder="myemail@email.com"
          ></input>
          <br />

          <label htmlFor="signupRole">I am a...</label>
          <br />
          <Select
            styles={customStyles}
            id="signupRole"
            name="signupRole"
            placeholder="Choose your role"
            isSearchable={false}
            options={dropdownOptions}
          />
          <br />

          <label htmlFor="signupPassword">Password</label>
          <br />
          <input
            className={styles.formInput}
            type="password"
            id="signupPassword"
            name="signupPassword"
            placeholder="********"
          ></input>
          <br />

          <input
            className={`${styles.button} ${styles.submitButton}`}
            type="submit"
            id="signupSubmit"
            value="Create my account"
          ></input>
        </form>
        <p className={styles.bottomText}>
          By continuing you agree to our
          <br />
          <span>Terms of Service</span> and <span>Privacy Policy</span>
        </p>
      </div>

      <div className={styles.rightContainer}>
        <img className={styles.graphic} src={graphic} alt="graphic" />
      </div>
    </div>
  );
};

export default Signup;
