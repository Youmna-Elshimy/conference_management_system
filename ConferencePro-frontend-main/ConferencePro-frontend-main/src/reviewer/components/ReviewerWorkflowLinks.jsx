import MainNavBar from "../../components/MainNavBar";

function ReviewerWorkflowLinks() {
  return (
    <MainNavBar
      li={[
        ["Dashboard", "/reviewer/dashboard"],
        ["Edit Preferences", "/reviewer/edit-preferences"],
        ["Bid for Papers", "/reviewer/bid"],
        ["My Reviews", "/reviewer/my-reviews"],
        ["–– Assigned Paper", "/reviewer/my-reviews"],
        ["–– Rating & Review", "/reviewer/my-reviews"],
        ["–– View Past Reviews", "/reviewer/my-reviews"],
      ]}
    />
  );
}

export default ReviewerWorkflowLinks;
