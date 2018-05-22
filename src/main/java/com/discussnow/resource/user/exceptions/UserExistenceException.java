package com.discussnow.resource.user.exceptions;

public class UserExistenceException extends Exception {

  private String email;
  private Long userId;

  public UserExistenceException(String message) {
    super(message);
  }

  public UserExistenceException(String message, String email) {
    super(message);
    this.email = email;
  }

  public UserExistenceException(String message, Long userId) {
    super(message);
    this.userId = userId;
  }
}
