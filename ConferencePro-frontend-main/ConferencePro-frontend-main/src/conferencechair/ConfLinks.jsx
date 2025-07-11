import MainNavBar from "../components/MainNavBar";

function ConfLinks() {
  return (
    <MainNavBar
      li={[
        ["Dashboard", "/chair/dashboard"],
        ["All Reviewers", "/chair/all-reviewers"],
        ["All Papers", "/chair/all-papers"],
        ["Inbox", "/chair/inbox"],
      ]}
    />
  );
}

export default ConfLinks;
