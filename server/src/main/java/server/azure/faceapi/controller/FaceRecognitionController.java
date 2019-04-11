package server.azure.faceapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import server.azure.faceapi.service.FaceRecognitionService;
import server.azure.faceapi.model.FaceRecognitionRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FaceRecognitionController {

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @CrossOrigin
    @PostMapping(path = "/recognizeFace/{object}")
    public String recognizeFace(@PathVariable Object object) throws IOException, URISyntaxException {
        return String.valueOf(UUID.randomUUID());
        //        throw new IOException("hey I threw an IO");
//        faceRecognitionService.recognizeFace(faceRecognitionRequest.getDirectoryPath());
//        return faceRecognitionRequest.getDirectoryPath();
    }

    @ExceptionHandler({IOException.class, URISyntaxException.class})
    public void faceRecognitionRequestError(HttpServletRequest req, Exception e) {
        log.error("Request: " + req.getRequestURL() + " raised " + e);
        e.printStackTrace();
    }
}
