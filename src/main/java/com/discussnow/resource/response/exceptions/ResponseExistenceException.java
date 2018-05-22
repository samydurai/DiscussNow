package com.discussnow.resource.response.exceptions;

public class ResponseExistenceException extends Exception {

  private Long responseId;

  public ResponseExistenceException(String message) {
    super(message);
  }

  public ResponseExistenceException(String message, Long responseId) {
    super(message);
    this.responseId = responseId;
  }

  public Long getResponseId() {
    return responseId;
  }
}
