import React, {useState} from 'react'
import {Get} from 'react-axios';
import 'filepond/dist/filepond.min.css';
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css';

const Home = () => {
    const [files, setFiles] = useState(null);
    const [enableButton, setButton] = useState(false);

    function analyzePhoto() {
        return (
            <Get url="http://localhost:8080/analyzePhoto" params={{image: files}}>
                {(error, response, isLoading, makeRequest, axios) => {
                    if (error) {
                        return (<div>Something bad happened: {error.message}
                            <button onClick={() => makeRequest({params: {reload: true}})}>Retry</button>
                        </div>)
                    }
                    else if (isLoading) {
                        return (<div>Loading...</div>)
                    }
                    else if (response !== null) {
                        return (<div>{response.data.message}
                            <button onClick={() => makeRequest({params: {refresh: true}})}>Refresh</button>
                        </div>)
                    }
                    return (<div>Default message before request is made.</div>)
                }}
            </Get>
        )
    }

    const getFile = () => {
        const file = document.getElementById('fileToUpload').files[0];
        if (file) {
            const reader = new FileReader();
            reader.readAsText(file);
            reader.onload = function (e) {
                setFiles(e.target.result);
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
        //     files ?
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

export default Home;