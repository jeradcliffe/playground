package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import server.azure.configuration.AzureProperties;
import server.azure.faceapi.FaceRecognitionService;

@SpringBootApplication
public class Application {

	@Autowired
	private final FaceRecognitionService faceRecognitionService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		// faceRecognitionService.recognizeFace();
	}

}
