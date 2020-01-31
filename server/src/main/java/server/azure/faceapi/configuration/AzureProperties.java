package server.azure.faceapi.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "microsoft")
public class AzureProperties {
    private String uri;
    private String subscriptionKey;
}