import MainNavBar from "../../components/MainNavBar";

function ReviewerLinks() {
  return (
    <MainNavBar
      li={[
        ["Dashboard", "/reviewer/dashboard"],
        ["Edit Preferences", "/reviewer/edit-preferences"],
        ["Bid for Papers", "/reviewer/bid"],
        ["My Reviews", "/reviewer/my-reviews"],
      ]}
    />
  );
}

export default ReviewerLinks;
