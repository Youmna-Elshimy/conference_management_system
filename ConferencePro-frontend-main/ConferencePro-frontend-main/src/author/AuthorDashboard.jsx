import secureLocalStorage from "react-secure-storage";
import Dashboard from "../components/Dashboard";
import AuthorLinks from "./AuthorLinks";

function AuthorDashboard() {
  const user = JSON.parse(secureLocalStorage.getItem("user"))

  return (
    <div className="page__container">
      <AuthorLinks />

      <Dashboard
        userName={`${user.firstName} ${user.lastName}`}
        dashboardItems={[
          [
            "New Submission",
            "Submit your paper for the International STEM Conference 2023 here.",
            "/author/newsubmission",
          ],
          [
            "My Submissions",
            "A full list of your submissions have been saved here.",
            "/author/submissions",
          ],
          [
            "My Profile",
            "Manage your preferences and personal information here.",
            "/author/my-profile",
          ],
        ]}
      />
    </div>
  );
}

export default AuthorDashboard;
