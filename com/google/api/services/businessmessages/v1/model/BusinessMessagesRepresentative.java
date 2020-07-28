/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2020-07-27 18:22:59 EDT)
 * on 2020-07-27 at 22:23:15 UTC 
 * Modify at your own risk.
 */

package com.google.api.services.businessmessages.v1.model;

/**
 * Details about the representative (human or chatbot) that sent the message.
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
public final class BusinessMessagesRepresentative extends com.google.api.client.json.GenericJson {

  /**
   * Optional. Name of the representative.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String displayName;

  /**
   * Required. The type of representative.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String representativeType;

  /**
   * Optional. Name of the representative.
   * @return value or {@code null} for none
   */
  public java.lang.String getDisplayName() {
    return displayName;
  }

  /**
   * Optional. Name of the representative.
   * @param displayName displayName or {@code null} for none
   */
  public BusinessMessagesRepresentative setDisplayName(java.lang.String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Required. The type of representative.
   * @return value or {@code null} for none
   */
  public java.lang.String getRepresentativeType() {
    return representativeType;
  }

  /**
   * Required. The type of representative.
   * @param representativeType representativeType or {@code null} for none
   */
  public BusinessMessagesRepresentative setRepresentativeType(java.lang.String representativeType) {
    this.representativeType = representativeType;
    return this;
  }

  @Override
  public BusinessMessagesRepresentative set(String fieldName, Object value) {
    return (BusinessMessagesRepresentative) super.set(fieldName, value);
  }

  @Override
  public BusinessMessagesRepresentative clone() {
    return (BusinessMessagesRepresentative) super.clone();
  }

}