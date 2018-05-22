package com.discussnow.resource.topic.exceptions;

public class TopicExistenceException extends Exception {

  private Long topicId;

  public TopicExistenceException(String message) {
    super(message);
  }

  public TopicExistenceException(String message, Long topicId) {
    super(message);
    this.topicId = topicId;
  }

  public Long getTopicId() {
    return topicId;
  }
}
