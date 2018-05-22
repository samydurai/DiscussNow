package com.discussnow.resource.user;

import com.discussnow.DiscussNowConstants;
import com.discussnow.model.User;
import com.discussnow.repository.UserRepository;
import com.discussnow.resource.topic.TopicResourceObject;
import com.discussnow.resource.topic.TopicService;
import com.discussnow.resource.user.exceptions.UserRegistrationException;
import com.discussnow.util.ServiceUtil;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends ServiceUtil {

  private static final String USER_EMAIL = "email";
  private static final String NAME = "name";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TopicService topicService;

  public UserResourceObject registerUser(Map<String, String> userDetails) {
    User user = convertUserObject(userDetails);
    user = userRepository.save(user);
    return new UserResourceObject(user, Boolean.TRUE);
  }

  private User convertUserObject(Map<String, String> userDetails) {
    User user = new User();
    user.setUserEmail(userDetails.get(USER_EMAIL));
    user.setName(userDetails.get(NAME));
    return user;
  }

  @Nullable
  public User getUserIfAlreadyRegistered(@NotNull String email) {
    return userRepository.findUsersByUserEmail(email);
  }

  /**
   * Returns the logged in user after persisting into {@link User} if the user is not already
   * registered in discuss-now platform. If the user details map is empty then this method throws
   * {@link UserRegistrationException}
   * <p>
   * If the user is already registered, isNewUser flag in {@link UserResourceObject} will be set
   * FALSE, otherwise TRUE
   *
   * @param principal security context which holds information about the logged in user
   * @return logged in user
   */
  @Transactional
  public UserResourceObject createUser(Principal principal) {
    User user = getUserFromPrincipal(principal);
    Map<String, String> userDetailsMap = loadLoggedInUserDetails(principal);
    if (user != null) {
      return new UserResourceObject(user, Boolean.FALSE);
    }
    if (userDetailsMap.isEmpty()) {
      throw new RuntimeException(DiscussNowConstants.PRINCIPAL_CONTEXT_EMPTY);
    }
    return registerUser(userDetailsMap);
  }

  public List<TopicResourceObject> loadAllUserCreatedObject(Principal principal)
      throws UserRegistrationException {
    User user = getUserFromPrincipal(principal);
    if (user != null) {
      return topicService.findAllTopicsByUser(user);
    }
    throw new UserRegistrationException("Logged in user not registered in discuss-now");
  }

  private User getUserFromPrincipal(Principal principal) {
    Map<String, String> userDetails = loadLoggedInUserDetails(principal);
    if (!userDetails.isEmpty()) {
      String userEMailAddress = userDetails.get(USER_EMAIL);
      if (userEMailAddress != null && !userEMailAddress.isEmpty()) {
        return getUserIfAlreadyRegistered(userEMailAddress);
      }
    }
    throw new RuntimeException(DiscussNowConstants.PRINCIPAL_CONTEXT_EMPTY);
  }
}
