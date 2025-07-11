import "./MainNavBar.scss";
import logo from "../images/ConfPro-logo.png";
import { NavLink, Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { logout } from "../slices/userauthSlice";

function MainNavBar({ li }) {
  const dispatch = useDispatch();

  const handleLogout = () => {
    dispatch(logout())
      .unwrap()
      .then(() => {
        window.location.reload();
      });
  };

  return (
    <nav className="navbar-menu">
      <div className="logo__wrapper">
        <img className="logo__img" src={logo} alt="Conference Pro logo" />
      </div>

      <ul className="navbar__list">
        <nav>
          {li.map((item, i) => (
            <div className="navbar__li-box" key={i}>
              <li className="navbar__li">
                <NavLink to={`${item[1]}`} end activeClassName="active">
                  {item[0]}
                </NavLink>
              </li>
            </div>
          ))}
          <Link className="navbar__logout" onClick={handleLogout}>
            Logout
          </Link>
        </nav>
      </ul>
    </nav>
  );
}

export default MainNavBar;
