package com.discussnow.rules.response;

import com.discussnow.model.Response;
import com.discussnow.model.User;
import com.discussnow.repository.ResponseRepository;
import com.discussnow.resource.response.exceptions.ResponseExistenceException;
import com.discussnow.resource.response.exceptions.ResponsePersistenceException;
import com.discussnow.resource.response.exceptions.ResponseUserContextException;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import com.discussnow.rules.RulesUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponsePersistenceRules extends RulesUtil {

  @Autowired
  private ResponseRulesUtil util;

  @Autowired
  private ResponseRepository responseRepository;

  /**
   * <p>
   * Deletes the response by ID. If the response for given id does not exists it throws {@link
   * ResponseExistenceException}
   * </p>
   * <p>
   * If logged in user does not matches the user who created the response, then this method throws
   * {@link ResponseUserContextException}
   * </p>
   * <p>
   * If the response has replies (>=1) then this method throws {@link ResponsePersistenceException}
   * </p>
   *
   * @param responseId response to be deleted
   */
  public void delete(Long responseId, Map<String, String> loggedInUserDetails)
      throws ResponseExistenceException, ResponseUserContextException {
    Response response = util.getResponseIfExists(responseId);
    util.validateResponseRequest(response, loggedInUserDetails);
    responseRepository.delete(response);
  }

  /**
   * <p>
   * Returns the updated response. This method validates three things.
   * </p>
   * <p>
   * If the logged in user is not registered in discuss-now platform, then it throws {@link
   * UserExistenceException}
   * </p>
   * <p>
   * If the user who created the response does not matches with the logged in user, then it throws
   * {@link ResponsePersistenceException}
   * </p>
   * <p>
   * If the response has replies, then it throws {@link ResponsePersistenceException} Because,
   * changing response will potentially alter the meaning of replies.
   * </p>
   */
  public Response update(Response responseToBeUpdated, Map<String, String> loggedInUserDetails)
      throws ResponseExistenceException, UserExistenceException {
    Response existingResponse = util.getResponseIfExists(responseToBeUpdated.getResponseId());
    User loggedInUser = validateUserExistence(loggedInUserDetails);
    responseToBeUpdated.setUser(loggedInUser);
    util.validateUser(existingResponse, loggedInUser);
    return responseRepository.save(responseToBeUpdated);

  }

  /**
   * Returns the newly created response. Before that it validated two things
   * <p>
   * If the logged in user is not registered in discuss-now platform, then it throws {@link
   * UserExistenceException}
   * </p>
   * <p>
   * If the topic for which the response is posted does not exist, then it throws {@link
   * TopicExistenceException}
   * </p>
   */
  public Response create(Response response, Map<String, String> loggedInUserDetails)
      throws TopicExistenceException, UserExistenceException {
    User user = validateUserExistence(loggedInUserDetails);
    response.setUser(user);
    util.validateTopicExistence(response.getTopic().getTopicId());
    return responseRepository.save(response);
  }
}
