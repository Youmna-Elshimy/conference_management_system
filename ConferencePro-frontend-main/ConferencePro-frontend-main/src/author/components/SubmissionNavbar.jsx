import "./SubmissionNavbar.scss";
import logo from "../../images/ConfPro-logo.png";
import { NavLink } from "react-router-dom";

function SubmissionNavbar() {
  return (
    <nav className="navbar-menu">
      <div className="logo__wrapper">
        <img className="logo__img" src={logo} alt="Conference Pro logo" />
      </div>

      <ul className="navbar__list">
        <nav>
          <div className="navbar__li-box">
            <li className="navbar__li">
              <NavLink to="/author" end activeClassName="active">
                Dashboard
              </NavLink>
            </li>
          </div>
          <div className="navbar__li-box">
            <li className="navbar__li">
              <NavLink to="/author/newsubmission" end activeClassName="active">
                + New Submission
              </NavLink>
            </li>
          </div>
          <div className="navbar__li-box">
            <li className="navbar__li">
              <div className="navbar__submissionStep">
                <div>Stage 1:</div>
                <div className="navbar__boldText">Track</div>
              </div>
            </li>
          </div>
          <div className="navbar__li-box">
            <li className="navbar__li">
              <div className="navbar__submissionStep">
                <div>Stage 2:</div>
                <div className="navbar__boldText">Title & Abstract</div>
              </div>
            </li>
          </div>
          <div className="navbar__li-box">
            <li className="navbar__li">
              <div className="navbar__submissionStep">
                <div>Stage 3:</div>
                <div className="navbar__boldText">Authors</div>
              </div>
            </li>
          </div>
          <div className="navbar__li-box">
            <li className="navbar__li">
              <div className="navbar__submissionStep">
                <div>Stage 4:</div>
                <div className="navbar__boldText">Topics</div>
              </div>
            </li>
          </div>
        </nav>
      </ul>
    </nav>
  );
}

export default SubmissionNavbar;
