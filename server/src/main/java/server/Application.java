package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import server.azure.configuration.AzureProperties;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(AzureProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		// faceRecognitionService.recognizeFace();
	}

}
