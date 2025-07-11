import secureLocalStorage from "react-secure-storage";
import Dashboard from "../components/Dashboard";
import ReviewerLinks from "./components/ReviewerLinks";

function ReviewerDashboard() {
  const user = JSON.parse(secureLocalStorage.getItem("user"));

  return (
    <div className="page__container">
      <ReviewerLinks />

      <Dashboard
        userName={`${user.firstName} ${user.lastName}`}
        dashboardItems={[
          [
            "Edit Preferences",
            "Choose topics that match your area of expertise and manage your overall workload.",
            "/reviewer/edit-preferences",
          ],
          [
            "Bid for Papers",
            "Here's where you can go to bid for papers that may fall within your area of expertise.",
            "/reviewer/bid",
          ],
          [
            "My Reviews",
            "A full list of reviews that have been assigned to you",
            "/reviewer/my-reviews",
          ],
        ]}
      />
    </div>
  );
}

export default ReviewerDashboard;
