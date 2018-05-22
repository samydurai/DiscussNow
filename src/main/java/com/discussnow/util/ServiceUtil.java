package com.discussnow.util;

import com.discussnow.model.Response;
import com.discussnow.model.Topic;
import com.discussnow.model.User;
import com.discussnow.repository.ResponseRepository;
import com.discussnow.repository.TopicRepository;
import com.discussnow.repository.UserRepository;
import com.discussnow.resource.response.ResponseConstants;
import com.discussnow.resource.response.exceptions.ResponseExistenceException;
import com.discussnow.resource.topic.TopicConstants;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.resource.user.UserConstants;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

@Service
public class ServiceUtil {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private ResponseRepository responseRepository;

  protected Map<String, String> loadLoggedInUserDetails(Principal principal) {
    if (principal != null) {
      OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
      Authentication authentication = oAuth2Authentication.getUserAuthentication();
      return (Map<String, String>) authentication.getDetails();
    }
    return Collections.emptyMap();
  }

  protected User getUser(Long userId) throws UserExistenceException {
    if (userId == null) {
      throw new RuntimeException(UserConstants.USER_NULL);
    }
    Optional<User> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      throw new UserExistenceException(UserConstants.USER_VALIDATION_ERROR, userId);
    }
    return user.get();
  }

  protected Topic getTopic(Long topicId) throws TopicExistenceException {
    if (topicId == null) {
      throw new RuntimeException(ResponseConstants.RESPONSE_ASSOCIATION_ERROR);
    }
    Optional<Topic> topic = topicRepository.findById(topicId);
    if (!topic.isPresent()) {
      throw new TopicExistenceException(TopicConstants.TOPIC_NULL, topicId);
    }
    return topic.get();
  }

  protected Response getResponse(Long responseId) throws ResponseExistenceException {
    if (responseId == null) {
      throw new RuntimeException(ResponseConstants.RESPONSE_ID_NULL);
    }
    Optional<Response> response = responseRepository.findById(responseId);
    if (!response.isPresent()) {
      throw new ResponseExistenceException(ResponseConstants.RESPONSE_NULL, responseId);
    }
    return response.get();
  }
}
