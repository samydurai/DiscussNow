package com.discussnow.resource.user;

import com.discussnow.model.User;

public class UserResourceObject {

  private Long userId;
  private String userEmail;
  private String name;
  private Boolean isNewUser;

  UserResourceObject() {
  }

  UserResourceObject(User user, Boolean isNewUser) {
    userId = user.getUserId();
    userEmail = user.getUserEmail();
    name = user.getName();
    this.isNewUser = isNewUser;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getIsNewUser() {
    return isNewUser;
  }

  public void setIsNewUser(Boolean newUser) {
    isNewUser = newUser;
  }
}
