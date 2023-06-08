import React, { useState } from 'react';
import './Form.css';
import Popup from './Popup';
import axios from 'axios';

const cors = require('cors');

const Form = () => {
  const [board, setBoard] = useState("");
  const [wordList, setWordList] = useState([]);
  const [showPopup, setShowPopup] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("board", board);

    axios.post("http://localhost:8080/api/submit", formData, {
      headers: {
        'Content-Type': 'application/json', // allows for JSON to be sent
        'Access-Control-Allow-Origin': '*',
      }
    })
    .then((response) => {
      console.log(response.data);
      setWordList(response.data);
      setShowPopup(true);
    })
    .catch((error) => {
      console.log(error.response.data);
    });
  };

  const handleChange = (event) => {
    setBoard(event.target.value); // updates board state with user input
  }

  const handlePopupClose = () => {
    // window.history.back(); // goes back one page in browser history
    setShowPopup(false);
    setBoard(""); // resets board state to empty string
  };

  return (
    <div>
      <form onSubmit={ handleSubmit } >
        <label>
          Board:
          <input
            type="text"
            className="textInput"
            placeholder="your board here..."
            value={ board }
            onChange={ handleChange }
          />
        </label>
        <input type="submit" className="submitInput" value="Submit"/>
      </form>
      {/* <Popup wordList={wordList} onClose={handlePopupClose} /> */}
      {showPopup && <Popup wordList={ wordList } onClose={ handlePopupClose } />}
    </div>
  );
};

export default Form;