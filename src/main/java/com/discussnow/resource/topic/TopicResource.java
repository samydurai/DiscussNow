package com.discussnow.resource.topic;

import com.discussnow.DiscussNowConstants;
import com.discussnow.resource.topic.exceptions.ResponseMeaningException;
import com.discussnow.resource.topic.exceptions.TopicDeleteException;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.resource.topic.exceptions.TopicUpdateException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DiscussNowConstants.TOPIC)
public class TopicResource {

  @Autowired
  private TopicService topicService;

  @RequestMapping(value = DiscussNowConstants.SAVE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public TopicSaveResponse save(@RequestBody TopicSaveRequest topicSaveRequest,
      Principal principal)
      throws UserExistenceException, TopicExistenceException, ResponseMeaningException, TopicUpdateException, TopicDeleteException {
    return topicService.save(topicSaveRequest, principal);
  }

  @RequestMapping(value = "/{topicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public TopicResourceObject loadTopicById(@PathVariable Long topicId)
      throws TopicExistenceException {
    return topicService.findTopicById(topicId);
  }

  @RequestMapping(value = "/loadAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<TopicResourceObject> loadAll() {
    return topicService.loadAll();
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<TopicResourceObject> searchTopicByTitle(@RequestParam("query") String query, Principal principal)
      throws UserExistenceException {
    return topicService.searchTopicByTitle(query, principal);
  }
}
