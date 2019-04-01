package server.azure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.azure.faceapi.FaceRecognitionService;
import server.azure.model.FaceRecognitionRequest;

@RestController
@RequestMapping("/faceRecognition")
public class FaceRecognitionController {

    @Autowired
    private FaceRecognitionService faceRecognitionService;


    @GetMapping(path = "/recognizeFace")
    @ResponseBody
    public String recognizeFace(@RequestBody FaceRecognitionRequest faceRecognitionRequest) {
        faceRecognitionService.recognizeFace(faceRecognitionRequest.getDirectoryPath());
        return faceRecognitionRequest.getDirectoryPath();
    }
}
