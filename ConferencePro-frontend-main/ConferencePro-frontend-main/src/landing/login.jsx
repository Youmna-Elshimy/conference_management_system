import { useDispatch } from "react-redux";
import { Link, useLocation } from "react-router-dom";
import { login } from "../slices/userauthSlice";

import styles from "./login.module.scss";

import logo from "../images/logo.svg";
import graphic from "../images/graphic.png";

const Login = () => {
  const { state } = useLocation();

  const dispatch = useDispatch();

  const handleLogin = (event) => {
    event.preventDefault(); // Stop page from reloading
    const userDetails = {
      email: event.target.loginEmail.value,
      password: event.target.loginPassword.value,
      firstName: "",
      lastName: "",
      userType: state.userType,
    };
    dispatch(login(userDetails));
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
        <div className={styles.welcomeBack}>
          <p className={styles.heading}>Welcome back!</p>
        </div>
        <form className={styles.form} onSubmit={handleLogin}>
          <label htmlFor="loginEmail">Email</label>
          <br />
          <input
            className={styles.formInput}
            type="email"
            id="loginEmail"
            name="loginEmail"
            placeholder="myemail@email.com"
          ></input>
          <br />

          <label htmlFor="loginPassword">Password</label>
          <br />
          <input
            className={styles.formInput}
            type="password"
            id="loginPassword"
            name="loginPassword"
            placeholder="********"
          ></input>
          <br />

          <input
            className={`${styles.button} ${styles.submitButton}`}
            type="submit"
            id="loginSubmit"
            value="Login"
          ></input>
          <p className={styles.forgetPassword}>Forgot your password?</p>
        </form>
        <p className={styles.signupText}>
          <span>New to ConferencePro?</span>
          <br />
          <Link to="/signup">Sign up</Link> with us.
        </p>
      </div>
      
      <div className={styles.rightContainer}>
        <img className={styles.graphic} src={graphic} alt="graphic" />
      </div>
    </div>
  );
};

export default Login;
