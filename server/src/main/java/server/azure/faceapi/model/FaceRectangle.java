package server.azure.faceapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaceRectangle {

    private int top;
    private int left;
    private int width;
    private int height;
}
