import React from "react";
import "../styles/modal.scss";
import cross from "../../images/cross.png";
import tickRed from "../../images/tick-red.png";
import tickGreen from "../../images/tick-green.png";

export default function StatusModal({ closeModal, title, message, color }) {
  return (
    <div className="status_modal">
      <div className="status_modal_content">
        <div className="close_icon_container" onClick={closeModal}>
          <img src={cross} alt="close" width={30} height={30} />
        </div>
        <div className="tick_container">
          <img
            src={color === "red" ? tickRed : tickGreen }
            alt=""
            width="150"
          />
        </div>

        <h4 className="status_modal_title">{title}</h4>
        <p className="status_modal_message">{message}</p>
      </div>
    </div>
  );
}
