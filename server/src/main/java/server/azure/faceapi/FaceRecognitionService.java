package server.azure.faceapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestClientException;
import server.azure.configuration.AzureProperties;

@Service
public class FaceRecognitionService {

    private AzureProperties azureProperties;
    private static final String FACE_ATTRIBUTE_PARAMS = "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,accessories";
    private static final String imageWithFaces = "{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg\"}";

    private Gson gson;
    private JsonParser jsonParser;
    private HttpClient httpClient;

    public FaceRecognitionService(AzureProperties azureProperties) {
        this.azureProperties = azureProperties;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jsonParser = new JsonParser();
        this.httpClient = HttpClientBuilder.create().build();
    }

    @Async
    protected CompletableFuture<String> recognizeFace() {
        try {
            URI uri = buildUri();
            HttpPost postRequest = buildHttpRequest(uri);
            HttpEntity responseEntity = callAzureToAnalyzeFace(postRequest);
            return printPrettyJson(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestClientException("Failed to complete request. Status: " + HttpStatus.SC_BAD_REQUEST);
        }
    }

    private URI buildUri() throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(azureProperties.getUri());
        uriBuilder.setParameter("returnFaceId", "true");
        uriBuilder.setParameter("returnFaceLandmarks", "false");
        uriBuilder.setParameter("returnFaceParameters", FACE_ATTRIBUTE_PARAMS);

        return uriBuilder.build();
    }

    private HttpPost buildHttpRequest(URI uri) throws UnsupportedEncodingException {
        HttpPost postRequest = new HttpPost(uri);
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("Ocp-Apim-Subscription-Key", azureProperties.getSubscriptionKey());

        StringEntity reqEntity = new StringEntity(imageWithFaces);
        postRequest.setEntity(reqEntity);

        return postRequest;
    }

    private HttpEntity callAzureToAnalyzeFace(HttpPost postRequest) throws ClientProtocolException, IOException {
        HttpResponse response = httpClient.execute(postRequest);
        return response.getEntity();
    }

    private CompletableFuture<String> printPrettyJson(HttpEntity responseEntity) throws ParseException, IOException {
        if (responseEntity == null) {
            throw new NullPointerException("Null response received from Face API.");
        }
        String responseString = EntityUtils.toString(responseEntity).trim();
        JsonElement jsonElement = jsonParser.parse(responseString);
        String prettyJsonElement = gson.toJson(jsonElement);

        return CompletableFuture.completedFuture(prettyJsonElement);
    }
}