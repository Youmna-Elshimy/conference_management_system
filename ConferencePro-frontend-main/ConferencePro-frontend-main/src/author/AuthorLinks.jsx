import MainNavBar from "../components/MainNavBar";

function AuthorLinks() {
  return (
    <MainNavBar
      li={[
        ["Dashboard", "/author"],
        ["+ New Submission", "/author/newsubmission"],
        ["My Submissions", "/author/submissions"],
        ["My Profile", "/author/my-profile"],
      ]}
    />
  );
}

export default AuthorLinks;
