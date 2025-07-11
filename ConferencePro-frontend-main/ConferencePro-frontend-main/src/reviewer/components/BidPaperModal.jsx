import "./BidPaperModal.scss";

function BidPaperModal({ paper, okBtn }) {
  return (
    <>
      <div className="modal">
        <h2 className="modal__paper-title">{paper.title}</h2>
        <h4 className="modal__abstract">Abstract</h4>
        <div className="modal__abstract-content">
          <p>{paper.paperAbstract}</p>
        </div>
        <button className="modal__close-btn" onClick={okBtn}>
          OK
        </button>
      </div>
      <div className="backdrop" />
    </>
  );
}

export default BidPaperModal;
