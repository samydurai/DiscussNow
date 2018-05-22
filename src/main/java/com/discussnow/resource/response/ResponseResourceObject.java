package com.discussnow.resource.response;

import com.discussnow.model.Response;

public class ResponseResourceObject {

  private Long responseId;
  private Long topicId;
  private Long userId;
  private String response;

  public ResponseResourceObject() {
  }

  public ResponseResourceObject(Response response) {
    this.responseId = response.getResponseId();
    this.topicId = response.getTopic().getTopicId();
    this.userId = response.getUser().getUserId();
    this.response = response.getTopicResponse();
  }

  public Long getResponseId() {
    return responseId;
  }

  public void setResponseId(Long responseId) {
    this.responseId = responseId;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }
}
