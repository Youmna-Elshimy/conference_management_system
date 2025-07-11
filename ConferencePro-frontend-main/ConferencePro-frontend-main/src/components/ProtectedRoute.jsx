import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useSelector } from "react-redux";

/*
  Redirect user to landing page if not logged in
*/
const ProtectedRoute = ({ redirectPath, children }) => {
    const { isLoggedIn }  = useSelector((state) => state.userAuth)

    return !isLoggedIn
        ? <Navigate to={redirectPath} />
        : <Outlet />
}

export default ProtectedRoute;