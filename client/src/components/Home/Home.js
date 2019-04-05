import React, {useState} from 'react';
import {FilePond, registerPlugin} from 'react-filepond';
import FilePondPluginImagePreview from 'filepond-plugin-image-preview';
import 'filepond/dist/filepond.min.css';
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css';

const Home = () => {
    registerPlugin(FilePondPluginImagePreview);

    const [files, setFiles] = useState(null);

    function handleInit() {
        // console.log('FilePond instance has initialised', this.pond);
    };

    return (
        <div>
            <FilePond ref={ref => this.pond = ref}
                      files={files}
                      allowMultiple={false}
                // server="/api/"
                      oninit={() => handleInit()}
                      onupdatefiles={(fileItems) => {
                          // Set current file objects to this.state
                          setFiles(fileItems);
                          debugger;
                          console.log("LOOOOOOSEEEERRR", fileItems[0].file);
                      }}>
            </FilePond>
        </div>
    );
};

export default Home;
