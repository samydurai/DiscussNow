package com.discussnow.resource.reply;

public interface ReplyConstants {

  String REPLY_DOES_NOT_EXIST = "Reply does not exist for the given ID";
  String REPLY_DELETE_USER_MISMATCH_ERROR = "User who created the reply does not matches the logged in user information";
  String REPLY_UPDATE_USER_MISMATCH_ERROR = REPLY_DELETE_USER_MISMATCH_ERROR;
  String CANNOT_CREATE_REPLY_WITHOUT_TOPIC = "Cannot create/update reply without an associated topic";
  String CANNOT_CREATE_REPLY_WITHOUT_RESPONSE = "Cannot create/update reply without an associated topic response";
  String CANNOT_CREATE_REPLY_WITHOUT_USER_INFO = "Cannot create/update reply without user information";
}
