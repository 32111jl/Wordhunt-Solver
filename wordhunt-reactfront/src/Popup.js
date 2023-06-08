import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Popup.css';
import darkModeIcon from './dark-mode.svg';
import closeIcon from './bot-left-arrow.svg';

function Popup({ wordList = [], onClose }) {

  const [darkMode, setDarkMode] = useState(false);
  const [closed, setClosed] = useState(false);
  // const navigate = useNavigate();

  const handleToggleMode = () => {
    setDarkMode(!darkMode);
  };

  const handleClose = () => {
    // window.location.href = '/';
    // navigate("/");
    // if (onClose) {
    //   onClose();
    //   setClosed(true);
    // }
    setClosed(true);
    setTimeout(() => {
      if (onClose) {
        onClose();
      }
    }, 1000);
  };

  const renderPopup = () => {
    if (wordList.length > 0) {
      return (
        <div className={`popup ${darkMode ? 'dark-mode' : 'light-mode'} ${closed ? 'closed' : ''}`}>
          <div className="popup-inner">
            {/* <dialog open> */}
              <div className="popup-content">
                <h3 className="popup-header">Here are the words that can be made!</h3>
                <ul className="word-list">
                  {wordList.map((word, index) => (
                    <li key={ index } >{ word }</li>
                  ))}
                </ul>
                <div className="popup-controls">
                  <button className="dark-mode-button"
                  onClick={ handleToggleMode } 
                  title="Enable dark mode">
                    <img className="dark-mode-icon" src={ darkModeIcon } ></img>
                  </button>

                  <button className="close-button"
                  onClick={ handleClose }
                  title="Start a new search">
                    <img className="close-icon" src={ closeIcon } ></img>
                  </button>
                </div>
              </div>
            {/* </dialog> */}
          </div>
        </div>
      );
    }
    return null;
  };
  
  return (
    <div>
      {renderPopup()}
    </div>
  );
}

export default Popup;