import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.businessmessages.v1.Businessmessages;
import com.google.api.services.businessmessages.v1.model.*;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.UUID;

class SendSurveySnippet {
  /**
   * Initializes credentials used by the Business Messages API.
   */
  private static Businessmessages.Builder getBusinessMessagesBuilder() {
    Businessmessages.Builder builder = null;
    try {
      GoogleCredential credential = GoogleCredential
            .fromStream(new FileInputStream("PATH_TO_SERVICE_ACCOUNT_KEY"));

      credential = credential.createScoped(Arrays.asList(
            "https://www.googleapis.com/auth/businessmessages"));

      credential.refreshToken();

      HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

      // Create instance of the Business Messages API
      builder = new Businessmessages
        .Builder(httpTransport, jsonFactory, null)
        .setApplicationName("Sample Application");

      // Set the API credentials and endpoint
      builder.setHttpRequestInitializer(credential);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return builder;
  }

  public static void main(String args[]) {
    try {
      String conversationId = "CONVERSATION_ID";

      // Create client library reference
      Businessmessages.Builder builder = getBusinessMessagesBuilder();

      // Create a new survey to send to the user associated with the conversationId
      Businessmessages.Conversations.Surveys.Create request
          = bmBuilder.build().conversations().surveys()
          .create("conversations/" + conversationId,
              new BusinessMessagesSurvey());

      request.setSurveyId(UUID.randomUUID().toString());

      // Setup retries with exponential backoff
      HttpRequest httpRequest =
          ((AbstractGoogleClientRequest) request).buildHttpRequest();

      httpRequest.setUnsuccessfulResponseHandler(new
          HttpBackOffUnsuccessfulResponseHandler(
          new ExponentialBackOff()));

      // Execute request
      httpRequest.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}