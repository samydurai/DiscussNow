package com.discussnow.rules;

import com.discussnow.DiscussNowConstants;
import com.discussnow.model.User;
import com.discussnow.repository.UserRepository;
import com.discussnow.resource.topic.TopicConstants;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RulesUtil {

  @Autowired
  private UserRepository userRepository;

  public User validateUserExistence(Map<String, String> loggedInUserDetails)
      throws UserExistenceException {
    User user = getLoggedInUser(loggedInUserDetails);
    validateUserExistence(user, loggedInUserDetails);
    return user;
  }

  public void validateUserExistence(User user, Map<String, String> loggedInUserDetails)
      throws UserExistenceException {
    if (user == null) {
      throw new UserExistenceException(TopicConstants.USER_EXISTENCE_ERROR,
          loggedInUserDetails.get(DiscussNowConstants.USER_DETAIL_EMAIL));
    }
  }

  public User getLoggedInUser(Map<String, String> loggedInUserDetails) {
    String loggedInUserEmail = loggedInUserDetails.get(DiscussNowConstants.USER_DETAIL_EMAIL);
    if (loggedInUserEmail != null) {
      return userRepository.findUsersByUserEmail(loggedInUserEmail);
    }
    throw new RuntimeException(DiscussNowConstants.PRINCIPAL_CONTEXT_EMPTY);
  }
}
