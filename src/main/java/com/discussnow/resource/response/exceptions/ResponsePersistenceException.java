package com.discussnow.resource.response.exceptions;

public class ResponsePersistenceException extends RuntimeException {

  private Long responseId;

  public ResponsePersistenceException(String message) {
    super(message);
  }

  public ResponsePersistenceException(String message, Long responseId) {
    super(message);
    this.responseId = responseId;
  }

  public Long getResponseId() {
    return responseId;
  }
}
