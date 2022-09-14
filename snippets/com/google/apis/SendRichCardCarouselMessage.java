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
import com.google.communications.businessmessages.v1.MediaHeight;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.UUID;

class SendRichCardCarouselMessage {
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

      // Create a rich card with two suggested replies
      BusinessMessagesMessage message = new BusinessMessagesMessage()
        .setMessageId(UUID.randomUUID().toString())
        .setFallback("Hello, world!\nSent with Business Messages\n\nReply with \"Suggestion #1\" or \"Suggestion #2\"")
        .setRichCard(new BusinessMessagesRichCard()
            .setCarouselCard(new BusinessMessagesCarouselCard().setCardWidth("MEDIUM")
                .setCardContents(Arrays.asList(
                    new BusinessMessagesCardContent()
                        .setTitle("Card #1")
                        .setDescription("The description for card #1")
                        .setSuggestions(Arrays.asList(new BusinessMessagesSuggestion()
                            .setReply(new BusinessMessagesSuggestedReply()
                                .setText("Card #1").setPostbackData("card_1")
                            )))
                        .setMedia(new BusinessMessagesMedia()
                            .setHeight(MediaHeight.MEDIUM.toString())
                            .setContentInfo(new BusinessMessagesContentInfo()
                                .setFileUrl("https://storage.googleapis.com/kitchen-sink-sample-images/cute-dog.jpg"))),
                    new BusinessMessagesCardContent()
                        .setTitle("Card #2")
                        .setDescription("The description for card #2")
                        .setSuggestions(Arrays.asList(new BusinessMessagesSuggestion()
                            .setReply(new BusinessMessagesSuggestedReply()
                                .setText("Card #2").setPostbackData("card_2")
                            )))
                        .setMedia(new BusinessMessagesMedia()
                            .setHeight(MediaHeight.MEDIUM.toString())
                            .setContentInfo(new BusinessMessagesContentInfo()
                                .setFileUrl("https://storage.googleapis.com/kitchen-sink-sample-images/elephant.jpg")))
                    )
                )))
        .setRepresentative(new BusinessMessagesRepresentative()
          .setRepresentativeType("TYPE"));

      // Create message request
      Businessmessages.Conversations.Messages.Create messageRequest
        = builder.build().conversations().messages()
          .create("conversations/" + conversationId, message);

      // Setup retries with exponential backoff
      HttpRequest httpRequest =
          ((AbstractGoogleClientRequest) messageRequest).buildHttpRequest();

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