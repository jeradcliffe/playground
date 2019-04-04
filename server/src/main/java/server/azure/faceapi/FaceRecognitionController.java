package server.azure.faceapi;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/faceRecognition")
public class FaceRecognitionController {

    private FaceRecognitionService faceRecognitionService;

    public FaceRecognitionController(FaceRecognitionService faceRecognitionService) {
        this.faceRecognitionService = faceRecognitionService;
    }

    @GetMapping(value = "/recognizeFace")
    public CompletableFuture<String> recognizeFaceViaByteArray() {
        return faceRecognitionService.recognizeFace();
    }
}
