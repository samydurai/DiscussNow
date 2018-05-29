package com.discussnow.resource.topic;

import com.discussnow.model.Topic;
import com.discussnow.model.User;
import com.discussnow.repository.TopicRepository;
import com.discussnow.resource.enm.SaveAction;
import com.discussnow.resource.topic.exceptions.ResponseMeaningException;
import com.discussnow.resource.topic.exceptions.TopicDeleteException;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.resource.topic.exceptions.TopicUpdateException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import com.discussnow.rules.RulesUtil;
import com.discussnow.rules.topic.TopicPersistenceRules;
import com.discussnow.util.ServiceUtil;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService extends ServiceUtil {

  @Autowired
  private TopicPersistenceRules topicPersistenceRules;

  @Autowired
  private TopicRepository topicRepository;

  @Transactional
  public TopicSaveResponse save(TopicSaveRequest topicSaveRequest, Principal principal)
      throws UserExistenceException, TopicDeleteException, TopicExistenceException, ResponseMeaningException, TopicUpdateException {
    List<TopicResourceObject> topicResourceObjects = new ArrayList<>();
    Map<Long, Long> idMap = new HashMap<>();
    Map<String, String> loggedInUserDetails = loadLoggedInUserDetails(principal);
    if (validateSaveRequest(topicSaveRequest, SaveAction.DELETE)) {
      for (Long topicId : topicSaveRequest.getTopic().getDeletedItems()) {
        topicPersistenceRules.delete(topicId, loggedInUserDetails);
      }
    }

    if (validateSaveRequest(topicSaveRequest, SaveAction.UPDATE)) {
      for (TopicResourceObject topicResourceObject : topicSaveRequest.getTopic()
          .getUpdatedItems()) {
        Topic topic = convertTopicResourceObject(topicResourceObject, SaveAction.UPDATE);
        Topic updatedTopic = topicPersistenceRules.update(topic, loggedInUserDetails);
        topicResourceObjects.add(new TopicResourceObject(updatedTopic));
        idMap.put(topicResourceObject.getTopicId(), updatedTopic.getTopicId());

      }
    }

    if (validateSaveRequest(topicSaveRequest, SaveAction.CREATE)) {
      for (TopicResourceObject topicResourceObject : topicSaveRequest.getTopic().getAddedItems()) {
        Topic topic = convertTopicResourceObject(topicResourceObject, SaveAction.CREATE);
        Topic createdTopic = topicPersistenceRules.create(topic, loggedInUserDetails);
        topicResourceObjects.add(new TopicResourceObject(createdTopic));
        idMap.put(topicResourceObject.getTopicId(), createdTopic.getTopicId());
      }
    }
    TopicSaveResponse topicSaveResponse = new TopicSaveResponse();
    topicSaveResponse.setTopicResourceObjects(topicResourceObjects);
    topicSaveResponse.setIdMap(idMap);
    return topicSaveResponse;
  }

  private boolean validateSaveRequest(TopicSaveRequest topicSaveRequest, SaveAction saveAction) {
    if (saveAction != null) {
      switch (saveAction) {
        case CREATE:
          return topicSaveRequest != null && topicSaveRequest.getTopic().getAddedItems() != null;
        case UPDATE:
          return topicSaveRequest != null && topicSaveRequest.getTopic().getUpdatedItems() != null;
        case DELETE:
          return topicSaveRequest != null && topicSaveRequest.getTopic().getDeletedItems() != null;
        default:
          throw new RuntimeException("Invalid Save Action for Topic");
      }
    }
    return false;
  }

  public TopicResourceObject findTopicById(Long topicId) throws TopicExistenceException {
    Optional<Topic> topic = topicRepository.findById(topicId);
    if (!topic.isPresent()) {
      throw new TopicExistenceException(TopicConstants.TOPIC_NULL, topicId);
    }
    return new TopicResourceObject(topic.get());
  }

  public List<TopicResourceObject> findAllTopicsByUser(User user) {
    List<Topic> topics = topicRepository.findAllByUser(user);
    if (topics == null || topics.isEmpty()) {
      return Collections.emptyList();
    }
    return topics.stream().map(TopicResourceObject::new).collect(Collectors.toList());
  }

  private Topic convertTopicResourceObject(TopicResourceObject topicResourceObject,
      SaveAction saveAction)
      throws TopicExistenceException {
    Topic topic;
    validateTopicId(topicResourceObject, saveAction);
    if (topicResourceObject.getTopicId() < 0L && SaveAction.CREATE == saveAction) {
      topic = new Topic();
    } else {
      topic = getTopic(topicResourceObject.getTopicId());
    }
    topic.setTitle(topicResourceObject.getTitle());
    topic.setDescription(topicResourceObject.getDescription());
    return topic;
  }

  private void validateTopicId(TopicResourceObject topicResourceObject, SaveAction saveAction) {
    if (topicResourceObject.getTopicId() == null) {
      throw new RuntimeException("Id is required to save or update a topic");
    }

    if (SaveAction.CREATE == saveAction && topicResourceObject.getTopicId() > 0) {
      throw new RuntimeException(
          "A newly created topic cannot have primary key set in the request payload");
    }

    if (SaveAction.UPDATE == saveAction && topicResourceObject.getTopicId() < 0) {
      throw new RuntimeException("Invalid topic id");
    }
  }

  public List<TopicResourceObject> loadAll() {
    Iterable<Topic> topics = topicRepository.findAll();
    List<TopicResourceObject> topicResourceObjects = new ArrayList<>();
    topics.forEach(topic -> this.convertToTopicResourceObject(topic, topicResourceObjects));
    return topicResourceObjects;
  }

  private void convertToTopicResourceObject(Topic topic,
      List<TopicResourceObject> topicResourceObjects) {
    if (topic != null) {
      topicResourceObjects.add(new TopicResourceObject(topic));
    }
  }

  public List<TopicResourceObject> searchTopicByTitle(String query, Principal principal)
      throws UserExistenceException {
    Map<String, String> loggedInUserDetails = loadLoggedInUserDetails(principal);
    topicPersistenceRules.validateUserExistence(loggedInUserDetails);
    List<Topic> topics =  topicRepository.searchByTitle("%"+query+"%");
    return topics.stream().map(TopicResourceObject::new).collect(Collectors.toList());
  }
}
