# [Google's Business Messages: Java Client](https://github.com/google-business-communications/java-businessmessages)

[Business Messages](https://developers.google.com/business-communications/business-messages/guides/learn) is a mobile conversational channel that combines entry points on Google Maps, Search, and brand websites to create rich, asynchronous messaging experiences.

This document contains an [API reference](https://developers.google.com/business-communications/business-messages/reference/rest), samples, and other resources useful to developing Java applications.
For additional help developing Business Messages applications, in Java and other languages, see our
[Business Messages quickstart](https://developers.google.com/business-communications/business-messages/guides/quickstarts/echo-agent)
guide.

## Documentation

The documentation for the Business Messages API can be found [here](https://developers.google.com/business-communications/business-messages/reference/rest).

## Quickstart

### Before you begin

1.  [Register with Business Messages](https://developers.google.com/business-communications/business-messages/guides/set-up/register).
1.  Once registered, follow the instructions to [enable the APIs for your project](https://developers.google.com/business-communications/business-messages/guides/set-up/register#enable-api).

### Installation

The Business Messages library is hosted on Maven central.
To use the library in your project, add the following to the dependencies section of your
project’s build.gradle.

```
repositories {
   mavenCentral()
}

dependencies {
   compile group: 'com.google.apis', name: 'google-api-services-businessmessages', version: '1.25.4'
}
```

If using maven, add the following to your pom.xml file.

```xml
<dependency>
  <groupId>com.google.apis</groupId>
  <artifactId>google-api-services-businessmessages</artifactId>
  <version>1.25.4</version>
</dependency>
```

### Using the client library

```java
/**
 * Initializes credentials used by the Business Messages API.
 */
private static Businessmessages.Builder getBusinessMessagesBuilder() {
  System.out.println("Initializing credentials for the Business Communications API.");

  Businessmessages.Builder builder = null;
  try {
    GoogleCredential credential = GoogleCredential.getApplicationDefault();
    
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
    String conversationId = "valid-conversation-id";

    // Create client library reference
    Businessmessages.Builder builder = getBusinessMessagesBuilder();

    // Create a basic text message
    BusinessMessagesMessage message = new BusinessMessagesMessage()
      .setMessageId(UUID.randomUUID().toString())
      .setText("Hello, World!")
      .setRepresentative(new BusinessMessagesRepresentative()
        .setRepresentativeType("BOT"));

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
```

## Sample usage

Samples below assume a similar library initialization as shown in the [Using the client library](https://github.com/google-business-communications/java-businessmessages#using-the-client-library) section.

### Sending a text message

```java
// Create a basic text message
BusinessMessagesMessage message = new BusinessMessagesMessage()
  .setMessageId(UUID.randomUUID().toString())
  .setText("Hello, World!")
  .setRepresentative(new BusinessMessagesRepresentative()
      .setRepresentativeType("BOT"));

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
```

### Sending a text message with suggested replies and actions

```java
// Create a text message with a suggested reply and actions
BusinessMessagesMessage message = new BusinessMessagesMessage()
  .setMessageId(UUID.randomUUID().toString())
  .setText("Hello, World!")
  .setSuggestions(new ArrayList<BusinessMessagesSuggestion>() {{
    add(new BusinessMessagesSuggestion()
        .setReply(new BusinessMessagesSuggestedReply()
            .setText("Sample Chip").setPostbackData("sample_chip")
        ));
    add(new BusinessMessagesSuggestion()
        .setAction(new BusinessMessagesSuggestedAction()
            .setText("URL Action").setPostbackData("url_action")
            .setOpenUrlAction(
                new BusinessMessagesOpenUrlAction().setUrl("https://www.google.com"))
        ));
    add(new BusinessMessagesSuggestion()
        .setAction(new BusinessMessagesSuggestedAction()
            .setText("Dial Action").setPostbackData("dial_action")
            .setDialAction(new BusinessMessagesDialAction().setPhoneNumber("+12223334444"))
        ));
  }})
  .setRepresentative(new BusinessMessagesRepresentative()
      .setRepresentativeType("BOT"));

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
```

### Sending a rich card

```java
// Create a rich card message
BusinessMessagesMessage message = new BusinessMessagesMessage()
  .setMessageId(UUID.randomUUID().toString())
  .setRichCard(new BusinessMessagesRichCard()
      .setStandaloneCard(new BusinessMessagesStandaloneCard()
          .setCardContent(
              new BusinessMessagesCardContent()
                  .setTitle("Business Messages!!!")
                  .setDescription("This is an example rich card")
                  .setSuggestions(new ArrayList<BusinessMessagesSuggestion>() {
                    {
                      add(new BusinessMessagesSuggestion()
                          .setReply(new BusinessMessagesSuggestedReply()
                              .setText("Sample Chip").setPostbackData("sample_chip")
                          ));
                    }
                  })
                  .setMedia(new BusinessMessagesMedia()
                      .setHeight(MediaHeight.MEDIUM.toString())
                      .setContentInfo(
                          new BusinessMessagesContentInfo()
                              .setFileUrl("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")
                      ))
          )))
  .setRepresentative(new BusinessMessagesRepresentative()
      .setRepresentativeType("BOT"));

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
```

### Sending a carousel

```java
// Create a carousel card message
BusinessMessagesMessage message = new BusinessMessagesMessage()
  .setMessageId(UUID.randomUUID().toString())
  .setRichCard(new BusinessMessagesRichCard()
      .setCarouselCard(new BusinessMessagesCarouselCard().setCardWidth("MEDIUM")
          .setCardContents(new ArrayList<BusinessMessagesCardContent>() {{
            add(new BusinessMessagesCardContent()
                .setTitle("Card #1")
                .setDescription("The description for card #1")
                .setSuggestions(new ArrayList<BusinessMessagesSuggestion>() {{
                  add(new BusinessMessagesSuggestion()
                      .setReply(new BusinessMessagesSuggestedReply()
                          .setText("Card #1").setPostbackData("card_1")
                      ));
                }})
                .setMedia(new BusinessMessagesMedia()
                    .setHeight(MediaHeight.MEDIUM.toString())
                    .setContentInfo(new BusinessMessagesContentInfo()
                        .setFileUrl("https://storage.googleapis.com/kitchen-sink-sample-images/cute-dog.jpg")))
            );
            add(new BusinessMessagesCardContent()
                .setTitle("Card #2")
                .setDescription("The description for card #2")
                .setSuggestions(new ArrayList<BusinessMessagesSuggestion>() {{
                  add(new BusinessMessagesSuggestion()
                      .setReply(new BusinessMessagesSuggestedReply()
                          .setText("Card #2").setPostbackData("card_2")
                      ));
                }})
                .setMedia(new BusinessMessagesMedia()
                    .setHeight(MediaHeight.MEDIUM.toString())
                    .setContentInfo(new BusinessMessagesContentInfo()
                        .setFileUrl("https://storage.googleapis.com/kitchen-sink-sample-images/elephant.jpg")))
            );
          }})))
  .setRepresentative(new BusinessMessagesRepresentative()
      .setRepresentativeType("BOT"));

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
```

## Code Samples

See the code examples to see example usage for most API features. The samples' `README.md` has instructions for running the samples.

| Sample                      | Source Code                       |
| --------------------------- | --------------------------------- |
| Echo Bot | [source code](https://github.com/google-business-communications/bm-java-echo-bot) |
| Kitchen Sink | [source code](https://github.com/google-business-communications/bm-java-kitchen-sink) |

## Snippets

Code snippets are in the [`snippets/`](https://github.com/google-business-communications/java-businessmessages/tree/master/snippets)

| Snippet                      | Source Code                       | Try it |
| --------------------------- | --------------------------------- | ------ |
| ReadReceipt | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/ReadReceiptSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/ReadReceiptSnippet.java) |
| SendAuthenticationRequestSuggestion | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendAuthenticationRequestSuggestionSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendAuthenticationRequestSuggestionSnippet.java) |
| SendDialAction | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendDialActionSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendDialActionSnippet.java) |
| SendImageMessage | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendImageMessageSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendImageMessageSnippet.java) |
| SendLiveAgentRequestSuggestion | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendLiveAgentRequestSuggestionSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendLiveAgentRequestSuggestionSnippet.java) |
| SendRichCardCarouselMessage | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendRichCardCarouselMessage.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendRichCardCarouselMessage.java) |
| SendRichCardMessage | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendRichCardMessageSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendRichCardMessageSnippet.java) |
| SendRichTextMessageSnippet | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendRichTextMessageSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendRichTextMessageSnippet.java) |
| SendSuggestedAction| [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendSuggestedActionSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendSuggestedActionSnippet.java) |
| SendSuggestedReply | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendSuggestedReplySnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendSuggestedReplySnippet.java) |
| SendSurvey | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendSurveySnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendSurveySnippet.java) |
| SendTextMessage | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/SendTextMessageSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/SendTextMessageSnippet.java) |
| TestFallbackTest | [source code](https://github.com/google-business-communications/java-businessmessages/blob/master/snippets/com/google/apis/TestFallbackTestSnippet.java) | [![Open in Cloud Shell][shell_img]](https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/google-business-communications/java-businessmessages/&page=editor&open_in_editor=snippets/com/google/apis/TestFallbackTestSnippet.java) |

## Contributing

Contributions welcome! See the [Contributing Guide](https://github.com/google-business-communications/java-businessmessages/CONTRIBUTING.md).

## License

Apache Version 2.0

See [LICENSE](https://github.com/google-business-communications/java-businessmessages/LICENSE)

[shell_img]: https://gstatic.com/cloudssh/images/open-btn.png