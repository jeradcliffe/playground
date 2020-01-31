package server.azure.faceapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.azure.faceapi.service.FaceRecognitionService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class FaceRecognitionController {

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @CrossOrigin
    @PostMapping(path = "/uploadPhoto")
    public String uploadPhoto(@ModelAttribute Object object) {
        return String.valueOf(UUID.randomUUID());
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(path = "/analyzePhoto", params = "image")
    public String recognizeFace(@RequestBody byte[] image) throws IOException, URISyntaxException {
        return faceRecognitionService.recognizeFace(image);
    }


    @ExceptionHandler({IOException.class, URISyntaxException.class})
    public void faceRecognitionRequestError(HttpServletRequest req, Exception e) {
        log.error("Request: " + req.getRequestURL() + " raised " + e);
        e.printStackTrace();
    }
}

