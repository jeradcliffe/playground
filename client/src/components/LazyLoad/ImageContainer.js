import React from 'react';
import './ImageContainer.css';

import ImageWithStatusText from "../LazyLoad/ImageWithStatusText";

const ImageContainer = () => {
    const imgSrc = "https://sites.google.com/site/314radcliffe/_/rsrc/1449984587885/home/profPic.jpg";
    return (
        <div className="imageContai">
            <ImageWithStatusText
                className="lazy"
                image='erin.jpg'
                loading="lazy"
            />
            <ImageWithStatusText
                className="eager"
                image={imgSrc}
                loading="eager"
            />
        </div>
    );
};

export default ImageContainer;
