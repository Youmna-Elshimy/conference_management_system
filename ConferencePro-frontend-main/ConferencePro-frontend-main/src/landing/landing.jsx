import { Link } from 'react-router-dom';
import styles from './landing.module.scss';

import logo from '../images/logo.svg'
import graphic from '../images/graphic.png'

const Landing = () => {
  return (
    <div className={styles.pageContainer}>
      <div className={styles.leftContainer}>
        <p className={styles.logoContainer}>
          <img className={styles.logo} src={logo} alt="logo"/> 
          <span className={styles.title}>ConferencePro</span>
        </p>
        <p className={styles.welcomeText}>
          Welcome!<br/>
          Which account are you logging into?
        </p>
        <Link to='/login' state={{ userType: "author" }}><button className={styles.button} type="button">Author</button></Link>
        <Link to='/login' state={{ userType: "reviewer" }}><button className={styles.button} type="button">Reviewer</button></Link>
        <Link to='/login' state={{ userType: "chair" }}><button className={styles.button} type="button">Conference Chair</button></Link>
        <p className={styles.signupText}>
          <span>New to ConferencePro?</span><br/>
          <Link to="/signup">Sign up</Link> with us.
        </p>
      </div>
      <div className={styles.rightContainer}>
          <img className={styles.graphic} src={graphic} alt="graphic"/>
      </div>
    </div>
  );
};

export default Landing;