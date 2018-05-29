package com.discussnow.rules.topic;

import com.discussnow.DiscussNowConstants;
import com.discussnow.model.Topic;
import com.discussnow.model.User;
import com.discussnow.repository.TopicRepository;
import com.discussnow.resource.topic.exceptions.ResponseMeaningException;
import com.discussnow.resource.topic.exceptions.TopicCreationException;
import com.discussnow.resource.topic.exceptions.TopicDeleteException;
import com.discussnow.resource.topic.exceptions.TopicUpdateException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import com.discussnow.rules.RulesUtil;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicPersistenceRules extends RulesUtil {

  @Autowired
  private TopicRulesUtil util;

  @Autowired
  private TopicRepository topicRepository;

  /**
   * Returns the persisted {@link Topic} object after evaluating the basic rules. If the logged in
   * user does not matches the user who created the topic then this method throws {@link
   * TopicCreationException}
   * <p>
   * If the logged in user email does not present in {@link User} then this method throws {@link
   * UserExistenceException}
   *
   * @param topic the topic to persist
   * @param loggedInUserDetails map of logged in user information extracted from {@link
   * java.security.Principal}
   * @return persisted topic object
   */
  public Topic create(Topic topic, Map<String, String> loggedInUserDetails)
      throws UserExistenceException {
    if (!loggedInUserDetails.isEmpty()) {
      User user = util.validateUserExistence(loggedInUserDetails);
      topic.setUser(user);
      return topicRepository.save(topic);
    }
    throw new RuntimeException(DiscussNowConstants.PRINCIPAL_CONTEXT_EMPTY);
  }

  /**
   * Returns the updated topic. This function validates three things.
   * <p>
   * It validates whether the user is registered to discuss-now. If not it throws {@link
   * UserExistenceException}
   * <p>
   * It validates whether the logged in user and the user who created the topic are same. If not it
   * throws {@link TopicUpdateException}
   * <p>
   * It validates whether the response count is zero or not. If not it throws {@link
   * ResponseMeaningException}
   *
   * @param topic the topic to update
   */
  public Topic update(Topic topic, Map<String, String> loggedInUserDetails)
      throws UserExistenceException, TopicUpdateException, ResponseMeaningException {
    User user = util.validateUserExistence(loggedInUserDetails);
    util.validateEditRules(topic, user);
    util.validateTopicEditInResponseContext(topic);
    return topicRepository.save(topic);
  }

  /**
   * Returns the deleted topic. This function validates three things.
   * <p>
   * It validates whether the user is registered to discuss-now. If not it throws {@link
   * UserExistenceException}
   * </p>
   * It validates whether the logged in user and the user who created the topic are same. If not it
   * throws {@link TopicDeleteException}
   * <p>
   * It validates whether the response count is zero or not. If not it throws {@link
   * TopicDeleteException}
   */
  public Topic delete(Long topicId, Map<String, String> loggedInUserDetails)
      throws UserExistenceException, TopicDeleteException {
    Optional<Topic> existingTopic = topicRepository.findById(topicId);
    if (existingTopic.isPresent()) {
      Topic topic = existingTopic.get();
      User loggedInUser = util.getLoggedInUser(loggedInUserDetails);
      util.validateUserExistence(loggedInUser, loggedInUserDetails);
      util.validateDeleteRules(topic, loggedInUser);
      util.validateDeleteBasedOnResponseCount(topic, topic.getUser());
      topicRepository.deleteById(topicId);
      return topic;
    }
    return null;
  }

  public void validateUser(Map<String, String> loggedInUserDetails) throws UserExistenceException {
    validateUserExistence(loggedInUserDetails);
  }
}
