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
 * (build: 2021-01-14 21:39:54 UTC)
 * on 2021-01-14 at 21:40:20 UTC 
 * Modify at your own risk.
 */

package com.google.api.services.businessmessages.v1.model;

/**
 * An image.
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
public final class BusinessMessagesImage extends com.google.api.client.json.GenericJson {

  /**
   * Information about an image, including the URL of the image and the URL of the image's
   * thumbnail.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BusinessMessagesContentInfo contentInfo;

  /**
   * Information about an image, including the URL of the image and the URL of the image's
   * thumbnail.
   * @return value or {@code null} for none
   */
  public BusinessMessagesContentInfo getContentInfo() {
    return contentInfo;
  }

  /**
   * Information about an image, including the URL of the image and the URL of the image's
   * thumbnail.
   * @param contentInfo contentInfo or {@code null} for none
   */
  public BusinessMessagesImage setContentInfo(BusinessMessagesContentInfo contentInfo) {
    this.contentInfo = contentInfo;
    return this;
  }

  @Override
  public BusinessMessagesImage set(String fieldName, Object value) {
    return (BusinessMessagesImage) super.set(fieldName, value);
  }

  @Override
  public BusinessMessagesImage clone() {
    return (BusinessMessagesImage) super.clone();
  }

}