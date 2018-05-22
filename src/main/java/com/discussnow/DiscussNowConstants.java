package com.discussnow;

public interface DiscussNowConstants {

  //CRUD operations
  String CREATE = "/create";
  String UPDATE = "/update";
  String DELETE = "/delete";
  String SAVE = "/save";

  //Entities
  String TOPIC = "/topic";
  String USER = "/user";
  String RESPONSE = "/response";

  //Property
  String USER_DETAIL_EMAIL = "email";

  //Error constants

  String NO_RESOLUTION = "This could be an user registration issue. Try clearing the cookies and login";
  String PRINCIPAL_CONTEXT_EMPTY = "Security Principal context is empty";
  String INVALID_UPDATE = "Update parameters are not equal";
}
