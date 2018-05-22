package com.discussnow.resource.topic;

public interface TopicConstants {

  String TOPIC_NULL = "Topic does not exist for the given topic id";
  String USER_ID_NULL_CANNOT_CREATE = "A Topic must be created by an user";
  String USER_EXISTENCE_ERROR = "User must be registered to create a topic";
  String USER_CONTEXT_ERROR = "The eho tried creating the topic does not matches the logged in user";
  String TOPIC_UPDATE_ERROR = "A topic can be only edited by the owner";
  String RESPONSE_MEANING_EXCEPTION = "A topic cannot be edited if it has more than one response as it might change the meaning of the existing responses";
  String TOPIC_DELETE_ERROR = "A topic can be only deleted by the owner";
  String TOPIC_DELETE_WITH_RESPONSES = "A topic cannot be deleted if it has responses";

  //Exception Handling Constants
  String CREATE_TOPIC_BEFORE_READ = "Create the topic before manipulating it";
  String REGISTER_BEFORE_CONTRIBUTING = "Before contributing the user must be registered in discuss-now platform";
}
