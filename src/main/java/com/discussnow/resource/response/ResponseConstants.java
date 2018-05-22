package com.discussnow.resource.response;

public interface ResponseConstants {

  String RESPONSE_ASSOCIATION_ERROR = "A Response must be associated with the topic";
  String RESPONSE_USER_CONTEXT_ERROR = "The user who posted the response does not match the logged in user";
  String RESPONSE_ID_NULL = "Response primary must not be null";
  String RESPONSE_NULL = "Response does not exist for the given id";
  String CANNOT_DELETE_REPLIES_NOT_EMPTY = "Response cannot be deleted as it has replies";

  //Exception Resolution
  String CREATE_RESPONSE_BEFORE_EDIT = "A response must exist before updating/deleting it";
}
