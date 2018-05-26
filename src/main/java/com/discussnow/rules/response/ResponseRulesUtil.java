package com.discussnow.rules.response;

import com.discussnow.model.Response;
import com.discussnow.model.Topic;
import com.discussnow.model.User;
import com.discussnow.repository.ResponseRepository;
import com.discussnow.repository.TopicRepository;
import com.discussnow.resource.response.ResponseConstants;
import com.discussnow.resource.response.exceptions.ResponseExistenceException;
import com.discussnow.resource.response.exceptions.ResponsePersistenceException;
import com.discussnow.resource.response.exceptions.ResponseUserContextException;
import com.discussnow.resource.topic.TopicConstants;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.rules.topic.TopicRulesUtil;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseRulesUtil {

  @Autowired
  private TopicRulesUtil util;

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private ResponseRepository responseRepository;

  public void validateResponseRequest(Response response, Map<String, String> loggedInUserDetails)
      throws ResponseUserContextException {
    Long responseUserId = response.getUser().getUserId();
    Long loggedInUserId = util.getLoggedInUser(loggedInUserDetails).getUserId();
    if (!responseUserId.equals(loggedInUserId)) {
      throw new ResponseUserContextException(ResponseConstants.RESPONSE_USER_CONTEXT_ERROR);
    }
  }

  public Response getResponseIfExists(Long responseId) throws ResponseExistenceException {
    Optional<Response> response = responseRepository.findById(responseId);
    if (!response.isPresent()) {
      throw new ResponseExistenceException(ResponseConstants.RESPONSE_NULL, responseId);
    }
    return response.get();
  }

  public void validateUser(Response existingResponse, User loggedInUser) {
    Long userWhoCreatedTheResponse = existingResponse.getUser().getUserId();
    if (userWhoCreatedTheResponse != null) {
      if (!userWhoCreatedTheResponse.equals(loggedInUser.getUserId())) {
        throw new ResponsePersistenceException(ResponseConstants.RESPONSE_USER_CONTEXT_ERROR,
            existingResponse.getResponseId());
      }
    }
    if (userWhoCreatedTheResponse == null) {
      throw new RuntimeException("Invalid Response. User field cannot be empty");
    }
  }

  public void validateTopicExistence(Long topicId) throws TopicExistenceException {
    Optional<Topic> topic = topicRepository.findById(topicId);
    if (!topic.isPresent()) {
      throw new TopicExistenceException(TopicConstants.TOPIC_NULL, topicId);
    }
  }
}
