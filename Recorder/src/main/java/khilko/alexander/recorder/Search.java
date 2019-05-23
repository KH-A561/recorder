package khilko.alexander.recorder;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.*;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Search {
    /** Application name. */
    private static final String APPLICATION_NAME = "Recorder";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/youtube-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(YouTubeScopes.YOUTUBE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                new FileInputStream("C:\\Users\\User\\Desktop\\Thumbtack\\Practice\\Recorder\\src\\main\\java\\khilko\\alexander\\recorder\\recorder-221915-303bb3b0a48e.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = null;
        try {
            credential = new GoogleCredential.Builder()
                    .setTransport(HTTP_TRANSPORT)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId("112762885479451272242")
                    .setServiceAccountPrivateKeyFromP12File(new File("C:\\Users\\User\\Desktop\\Thumbtack\\Practice\\Recorder\\src\\main\\java\\khilko\\alexander\\recorder\\recorder-221915-167b1c95f8b6.p12"))
                    .setServiceAccountScopes(SCOPES)
                    .setServiceAccountUser("kh-a561@recorder-221915.iam.gserviceaccount.com")
                    .build();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return credential;
    }

    public static String check() {
        try {
            YouTube youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, authorize())
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("part", "snippet");
            parameters.put("channelId", "UCWYYieAoHdBBfkYVktkkzCA");
            parameters.put("eventType", "live");
            parameters.put("type", "video");
            parameters.put("key", "AIzaSyBDWldIQE0djM-MNsNCwVm9y4aboTBbQ-E");
            YouTube.Search.List searchListLiveEventsRequest = youTube.search().list("snippet");
            searchListLiveEventsRequest.setChannelId(parameters.get("channelId"));
            searchListLiveEventsRequest.setEventType(parameters.get("eventType"));
            searchListLiveEventsRequest.setType(parameters.get("type"));
            searchListLiveEventsRequest.setKey(parameters.get("key"));
            SearchListResponse response = searchListLiveEventsRequest.execute();
            if (response.getItems().size() != 0) {
                return response.getItems().get(0).getId().getVideoId();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
