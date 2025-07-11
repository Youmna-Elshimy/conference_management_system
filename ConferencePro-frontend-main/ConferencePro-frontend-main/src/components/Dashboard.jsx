import { NavLink } from "react-router-dom";
import "./Dashboard.scss";

function Dashboard({ userName, dashboardItems }) {
  return (
    <div className="dashboard__wrapper">
      <div className="dashboard__content">
        <h1>Welcome, {userName}!</h1>
        <p>Feeling lost? Here's how to work your way around the site:</p>

        <div className="dashboard__items">
          {dashboardItems.map((item, i) => (
            <div className="dashboard__item-box" key={i}>
              <div className="dashboard__item">
                <NavLink to={item[2]}>
                  <h2>{item[0]}</h2>
                </NavLink>
                <p>{item[1]}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
