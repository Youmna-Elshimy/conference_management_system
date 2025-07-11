import "../components/PageHeader.scss";

function PageHeader({ pageTitle }) {
  return (
    <div className="pageHeader-container">
      <h1>{pageTitle}</h1>
      <div className="pageHeader__divider"></div>
    </div>
  );
}

export default PageHeader;
