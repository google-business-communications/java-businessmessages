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
 * A standalone rich card or a carousel of rich cards sent from the agent to the user.
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
public final class BusinessMessagesRichCard extends com.google.api.client.json.GenericJson {

  /**
   * Carousel of cards.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BusinessMessagesCarouselCard carouselCard;

  /**
   * Standalone card.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BusinessMessagesStandaloneCard standaloneCard;

  /**
   * Carousel of cards.
   * @return value or {@code null} for none
   */
  public BusinessMessagesCarouselCard getCarouselCard() {
    return carouselCard;
  }

  /**
   * Carousel of cards.
   * @param carouselCard carouselCard or {@code null} for none
   */
  public BusinessMessagesRichCard setCarouselCard(BusinessMessagesCarouselCard carouselCard) {
    this.carouselCard = carouselCard;
    return this;
  }

  /**
   * Standalone card.
   * @return value or {@code null} for none
   */
  public BusinessMessagesStandaloneCard getStandaloneCard() {
    return standaloneCard;
  }

  /**
   * Standalone card.
   * @param standaloneCard standaloneCard or {@code null} for none
   */
  public BusinessMessagesRichCard setStandaloneCard(BusinessMessagesStandaloneCard standaloneCard) {
    this.standaloneCard = standaloneCard;
    return this;
  }

  @Override
  public BusinessMessagesRichCard set(String fieldName, Object value) {
    return (BusinessMessagesRichCard) super.set(fieldName, value);
  }

  @Override
  public BusinessMessagesRichCard clone() {
    return (BusinessMessagesRichCard) super.clone();
  }

}
