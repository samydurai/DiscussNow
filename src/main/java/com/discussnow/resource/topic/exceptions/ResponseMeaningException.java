package com.discussnow.resource.topic.exceptions;

public class ResponseMeaningException extends Exception {

  private Long topicId;

  public ResponseMeaningException(String message) {
    super(message);
  }

  public ResponseMeaningException(String message, Long topicId) {
    super(message);
    this.topicId = topicId;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }
}
