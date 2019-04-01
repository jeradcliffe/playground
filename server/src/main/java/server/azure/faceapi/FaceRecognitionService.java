package server.azure.faceapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import server.azure.configuration.AzureProperties;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class FaceRecognitionService {

    private Gson gson;
    private JsonParser jsonParser;
    private HttpClient httpClient;

    private AzureProperties azureProperties;
//    private static final String FACE_ATTRIBUTE_PARAMS = "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,accessories";
    private static final String  FACE_ATTRIBUTE_PARAMS =
            "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";
    private static final String imageWithFaces = "{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg\"}";

    public FaceRecognitionService(AzureProperties azureProperties) {
        this.azureProperties = azureProperties;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jsonParser = new JsonParser();
        this.httpClient = HttpClientBuilder.create().build();
    }

    public void recognizeFace(String directory) {
        try {
            URI uri = buildUri();
//            HttpPost postRequest = buildHttpRequestViaUri(uri);
            HttpPost postRequest = buildHttpRequestViaByteArray(uri, directory);
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
        uriBuilder.setParameter("returnFaceAttributes", FACE_ATTRIBUTE_PARAMS);

        return uriBuilder.build();
    }

    private HttpPost buildHttpRequestViaByteArray(URI uri, String directory) throws IOException {
        HttpPost postRequest = new HttpPost(uri);

        postRequest.setHeader("Content-Type", "application/octet-stream");
        postRequest.setHeader("Ocp-Apim-Subscription-Key", azureProperties.getSubscriptionKey());

        byte[] image = pullImageFromDirectory(directory);
        ByteArrayEntity reqEntity = new ByteArrayEntity(image);
        postRequest.setEntity(reqEntity);

        return postRequest;
    }

    private HttpPost buildHttpRequestViaUri(URI uri) throws UnsupportedEncodingException {
        HttpPost postRequest = new HttpPost(uri);

        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("Ocp-Apim-Subscription-Key", azureProperties.getSubscriptionKey());

        StringEntity reqEntity = new StringEntity(imageWithFaces);
        postRequest.setEntity(reqEntity);

        return postRequest;
    }

    private HttpEntity callAzureToAnalyzeFace(HttpPost postRequest) throws IOException {
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

    private byte[] pullImageFromDirectory(String directory) throws IOException {
        return FileUtils.readFileToByteArray(new File(directory));
    }
}