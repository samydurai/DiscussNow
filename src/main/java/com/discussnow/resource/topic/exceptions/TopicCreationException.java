package com.discussnow.resource.topic.exceptions;

public class TopicCreationException extends Exception {

  private Long userId;
  private String resolution;

  public TopicCreationException(String message) {
    super(message);
  }

  public TopicCreationException(String message, Long userId, String resolution) {
    super(message);
    this.userId = userId;
    this.resolution = resolution != null ? resolution : "Id of the logged in user is required";
  }
}
