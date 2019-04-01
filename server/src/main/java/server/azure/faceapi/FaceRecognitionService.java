package server.azure.faceapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import server.azure.configuration.AzureProperties;

@Service
public class FaceRecognitionService {

    private AzureProperties azureProperties;
    // @Value("${microsoft.uri}")
    // private final String uri;
    // @Value("${microsoft.subscriptionKey}")
    // private final String subscriptionKey;

    private static final String FACE_ATTRIBUTE_PARAMS = "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,accessories";

    // replace with byte array version later
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

    public void recognizeFace() throws URISyntaxException, ClientProtocolException, IOException {
        try {
            URI uri = buildUri();
            HttpPost postRequest = buildHttpRequest(uri);
            HttpEntity responseEntity = callAzureToAnalyzeFace(postRequest);
            printPrettyJson(responseEntity);
        } catch (Exception e) {
            System.out.println("Error in the FaceRecognitionService: " + e.getMessage());
            e.printStackTrace();
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

    private void printPrettyJson(HttpEntity responseEntity) throws ParseException, IOException {
        if (responseEntity == null) {
            throw new IllegalStateException("Null response received from Face API.");
        }
        String responseString = EntityUtils.toString(responseEntity).trim();
        JsonElement jsonElement = jsonParser.parse(responseString);
        String prettyJsonElement = gson.toJson(jsonElement);

        System.out.println("Response from Face Api: ");
        System.out.println(prettyJsonElement);
    }
}