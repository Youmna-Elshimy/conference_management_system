import React from "react";
import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";

/* 
Decide what the base url redirects to 
*/
const RootRoute = ({ children }) => {
  const { isLoggedIn, user } = useSelector((state) => state.userAuth);

  const userObj = JSON.parse(user);

  if (isLoggedIn) {
    if (userObj !== null) {
      if (userObj.userType === "author" || userObj.userType === "Author") {
        return <Navigate to="/author" />;
      } else if (
        userObj.userType === "reviewer" ||
        userObj.userType === "Reviewer"
      ) {
        return <Navigate to="/reviewer" />;
      } else if (
        userObj.userType === "chair" ||
        userObj.userType === "ConferenceChair"
      ) {
        return <Navigate to="/chair" />;
      }
    } else {
      return <Navigate to="/landing" />;
    }
  } else {
    return <Navigate to="/landing" />;
  }
};

export default RootRoute;
