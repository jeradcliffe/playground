import React, {useState} from 'react'
import axios from 'axios';

const FaceRecognition = () => {
  const [image, setImage] = useState(null);
  const [enableButton, setButton] = useState(false);

  function analyzePhoto() {
    return (
      axios.get('http://localhost:8080/analyzePhoto', {params: {image: image}})
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        })
    )
  }

  const getFile = () => {
    const file = document.getElementById('fileToUpload').files[0];
    if (file) {
      const reader = new FileReader();
      reader.readAsText(file);
      reader.onload = function (e) {
        setImage(e.target.result);
        setButton(true)
        console.log(e.target.result);
      };
    }
  };

  const toggleButton = () => {
    return <button type="button"
                   className={enableButton ? "btn btn-lg btn-primary" : "btn btn-lg btn-danger disabled"}
                   onClick={analyzePhoto}
    >
      Analyze Photo</button>

    // return (
    //     image ?
    //         <button type="button" onClick={analyzePhoto} className="btn btn-lg btn-primary">Analyze Photo</button> :
    //         <button type="button" className="btn btn-lg btn-primary" disabled>Analyze Photo</button>
    // )
  };

  return (
    <div className="App">
      <input
        type="file"
        id="fileToUpload"
        onChange={() => getFile()}
      />
      {toggleButton()}
    </div>
  );
};

export default FaceRecognition;