import React, {Component} from "react";

class ImageWithStatusText extends Component {
    constructor(props) {
        super(props);
        this.state = {
            imageStatus: "loading",
            ogRenderTime: Date.now(),
            finalRenderTime: null,
        };
    }

    handleImageLoaded() {
        this.setState({imageStatus: "loaded"});
        this.setState({finalRenderTime: Date.now()})
    }

    render() {
        const {image, loadType} = this.props;
        const {ogRenderTime, finalRenderTime} = this.state;
        return (
            <div>
                <img
                    src={image}
                    loading={loadType}
                    onLoad={this.handleImageLoaded.bind(this)}
                />
                {"Original Render Time: " + ogRenderTime}
                {finalRenderTime && "Final Render Time: " + finalRenderTime}
                {finalRenderTime && "Difference: " + finalRenderTime - ogRenderTime}
            </div>
        );
    }
}

export default ImageWithStatusText;