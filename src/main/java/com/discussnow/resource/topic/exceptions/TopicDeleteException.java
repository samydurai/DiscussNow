package com.discussnow.resource.topic.exceptions;

public class TopicDeleteException extends Exception {

  Long topicId;
  Long TopicOwnerId;

  public TopicDeleteException(String message) {
    super(message);
  }

  public TopicDeleteException(String message, Long topicId, Long topicOwnerId) {
    super(message);
    this.topicId = topicId;
    this.TopicOwnerId = topicOwnerId;
  }
}
