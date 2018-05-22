package com.discussnow.resource.topic.exceptions;

public class TopicUpdateException extends Exception {

  Long topicId;
  Long TopicOwnerId;

  public TopicUpdateException(String message) {
    super(message);
  }

  public TopicUpdateException(String message, Long topicId, Long topicOwnerId) {
    super(message);
    this.topicId = topicId;
    this.TopicOwnerId = topicOwnerId;
  }
}
