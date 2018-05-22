package com.discussnow.resource.user;

import com.discussnow.DiscussNowConstants;
import com.discussnow.resource.topic.TopicResourceObject;
import com.discussnow.resource.user.exceptions.UserRegistrationException;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DiscussNowConstants.USER)
public class UserResource {

  @Autowired
  private UserService userService;

  @RequestMapping(value = DiscussNowConstants.CREATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public UserResourceObject createUser(Principal principal) {
    return userService.createUser(principal);
  }

  @RequestMapping(value = "/topics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<TopicResourceObject> loadAllUserCreatedTopics(Principal principal)
      throws UserRegistrationException {
    return userService.loadAllUserCreatedObject(principal);
  }

}
