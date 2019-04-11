import React, {Component, useState} from 'react'
import {FilePond, registerPlugin} from 'react-filepond';
import FilePondPluginImagePreview from 'filepond-plugin-image-preview';
import 'filepond/dist/filepond.min.css';
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css';

registerPlugin(FilePondPluginImagePreview);

const Home = () => {
    const [files, setFiles] = useState([]);

    const handleInit = () => {
        console.log('FilePond instance has initialised', this.pond);
    };

    return (
        <div className="App">
            {/* Pass FilePond properties as attributes */}
            <FilePond ref={ref => this.pond = ref}
                      files={files}
                      allowMultiple={false}
                      maxFiles={1}
                      server="localhost:8080/recognizeFace"
                      oninit={() => handleInit()}
                      onupdatefiles={(fileItems) => {
                          setFiles({
                              files: fileItems.map(fileItem => fileItem.file)
                          });
                      }}>
            </FilePond>

        </div>
    );
};

export default Home;