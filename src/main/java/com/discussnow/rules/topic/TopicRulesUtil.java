package com.discussnow.rules.topic;

import com.discussnow.model.Topic;
import com.discussnow.model.User;
import com.discussnow.repository.ResponseRepository;
import com.discussnow.resource.topic.TopicConstants;
import com.discussnow.resource.topic.exceptions.ResponseMeaningException;
import com.discussnow.resource.topic.exceptions.TopicDeleteException;
import com.discussnow.resource.topic.exceptions.TopicUpdateException;
import com.discussnow.rules.RulesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicRulesUtil extends RulesUtil {

  @Autowired
  private ResponseRepository responseRepository;

  public void validateTopicEditInResponseContext(Topic topic) throws ResponseMeaningException {
    Long responses = responseRepository.countByTopic_TopicId(topic.getTopicId());
    if (responses > 0) {
      throw new ResponseMeaningException(TopicConstants.RESPONSE_MEANING_EXCEPTION,
          topic.getTopicId());
    }
  }

  public void validateEditRules(Topic topic, User loggedInUser) throws TopicUpdateException {
    Long topicOwnerId = topic.getUser().getUserId();
    Long loggedInUserId = loggedInUser.getUserId();
    if (!topicOwnerId.equals(loggedInUserId)) {
      throw new TopicUpdateException(TopicConstants.TOPIC_UPDATE_ERROR, topic.getTopicId(),
          topicOwnerId);
    }
  }

  public void validateDeleteRules(Topic topic, User loggedInUser) throws TopicDeleteException {
    Long topicOwnerId = topic.getUser().getUserId();
    Long loggedInUserId = loggedInUser.getUserId();
    if (!topicOwnerId.equals(loggedInUserId)) {
      throw new TopicDeleteException(TopicConstants.TOPIC_DELETE_ERROR, topic.getTopicId(),
          topicOwnerId);
    }
  }

  public void validateDeleteBasedOnResponseCount(Topic topic, User user)
      throws TopicDeleteException {
    Long responses = responseRepository.countByTopic_TopicId(topic.getTopicId());
    if (responses > 0) {
      throw new TopicDeleteException(TopicConstants.TOPIC_DELETE_WITH_RESPONSES, topic.getTopicId(),
          user.getUserId());
    }
  }
}
