import PageHeader from "../components/PageHeader";
import ReviewerLinks from "./components/ReviewerLinks";
import ReviewersPreferencesForm from "./components/ReviewerPreferences";

function EditPreferences() {
  return (
    <div className="page__container">
      <ReviewerLinks />
      <div className="page-content__container">
        <PageHeader pageTitle="Edit Preferences" />
        <div className="page-content-item__wrapper">
          <ReviewersPreferencesForm />
        </div>
      </div>
    </div>
  );
}

export default EditPreferences;
