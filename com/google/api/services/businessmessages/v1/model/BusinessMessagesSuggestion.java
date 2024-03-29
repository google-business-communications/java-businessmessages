/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2022-02-10 02:07:51 UTC)
 * on 2022-02-10 at 02:08:18 UTC 
 * Modify at your own risk.
 */

package com.google.api.services.businessmessages.v1.model;

/**
 * A suggestion within a chip list.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Business Messages API. For a detailed explanation
 * see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class BusinessMessagesSuggestion extends com.google.api.client.json.GenericJson {

  /**
   * A suggested action that initiates a native action on the device.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BusinessMessagesSuggestedAction action;

  /**
   * A request to start authentication flow.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BusinessMessagesAuthenticationRequest authenticationRequest;

  /**
   * A request to have a live agent join the conversation.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BusinessMessagesLiveAgentRequest liveAgentRequest;

  /**
   * A suggestion for the user to reply with specified text.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BusinessMessagesSuggestedReply reply;

  /**
   * A suggested action that initiates a native action on the device.
   * @return value or {@code null} for none
   */
  public BusinessMessagesSuggestedAction getAction() {
    return action;
  }

  /**
   * A suggested action that initiates a native action on the device.
   * @param action action or {@code null} for none
   */
  public BusinessMessagesSuggestion setAction(BusinessMessagesSuggestedAction action) {
    this.action = action;
    return this;
  }

  /**
   * A request to start authentication flow.
   * @return value or {@code null} for none
   */
  public BusinessMessagesAuthenticationRequest getAuthenticationRequest() {
    return authenticationRequest;
  }

  /**
   * A request to start authentication flow.
   * @param authenticationRequest authenticationRequest or {@code null} for none
   */
  public BusinessMessagesSuggestion setAuthenticationRequest(BusinessMessagesAuthenticationRequest authenticationRequest) {
    this.authenticationRequest = authenticationRequest;
    return this;
  }

  /**
   * A request to have a live agent join the conversation.
   * @return value or {@code null} for none
   */
  public BusinessMessagesLiveAgentRequest getLiveAgentRequest() {
    return liveAgentRequest;
  }

  /**
   * A request to have a live agent join the conversation.
   * @param liveAgentRequest liveAgentRequest or {@code null} for none
   */
  public BusinessMessagesSuggestion setLiveAgentRequest(BusinessMessagesLiveAgentRequest liveAgentRequest) {
    this.liveAgentRequest = liveAgentRequest;
    return this;
  }

  /**
   * A suggestion for the user to reply with specified text.
   * @return value or {@code null} for none
   */
  public BusinessMessagesSuggestedReply getReply() {
    return reply;
  }

  /**
   * A suggestion for the user to reply with specified text.
   * @param reply reply or {@code null} for none
   */
  public BusinessMessagesSuggestion setReply(BusinessMessagesSuggestedReply reply) {
    this.reply = reply;
    return this;
  }

  @Override
  public BusinessMessagesSuggestion set(String fieldName, Object value) {
    return (BusinessMessagesSuggestion) super.set(fieldName, value);
  }

  @Override
  public BusinessMessagesSuggestion clone() {
    return (BusinessMessagesSuggestion) super.clone();
  }

}
