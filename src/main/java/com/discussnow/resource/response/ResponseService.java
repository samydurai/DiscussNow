package com.discussnow.resource.response;

import com.discussnow.model.Response;
import com.discussnow.resource.enm.SaveAction;
import com.discussnow.resource.response.exceptions.ResponseExistenceException;
import com.discussnow.resource.response.exceptions.ResponseUserContextException;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import com.discussnow.rules.response.ResponsePersistenceRules;
import com.discussnow.util.ServiceUtil;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService extends ServiceUtil {

  @Autowired
  private ResponsePersistenceRules responsePersistenceRules;

  public ResponseSaveResponse save(ResponseSaveRequest responseSaveRequest, Principal principal)
      throws TopicExistenceException, UserExistenceException, ResponseUserContextException, ResponseExistenceException {
    List<ResponseResourceObject> responseResourceObjects = new ArrayList<>();
    Map<Long, Long> idMap = new HashMap<>();
    Map<String, String> loggedInUserDetails = loadLoggedInUserDetails(principal);
    if (validateSaveRequest(responseSaveRequest, SaveAction.DELETE)) {
      for (Long responseId : responseSaveRequest.getResponse().getDeletedItems()) {
        responsePersistenceRules.delete(responseId, loggedInUserDetails);
      }
    }

    if (validateSaveRequest(responseSaveRequest, SaveAction.UPDATE)) {
      for (ResponseResourceObject responseResourceObject : responseSaveRequest.getResponse().getUpdatedItems()) {
        Response response = convertResponseResourceObject(responseResourceObject, SaveAction.UPDATE);
        response = responsePersistenceRules.update(response, loggedInUserDetails);
        responseResourceObjects.add(new ResponseResourceObject(response));
        idMap.put(responseResourceObject.getResponseId(), response.getResponseId());
      }
    }

    if (validateSaveRequest(responseSaveRequest, SaveAction.CREATE)) {
      for (ResponseResourceObject responseResourceObject : responseSaveRequest.getResponse().getAddedItems()) {
        Response response = convertResponseResourceObject(responseResourceObject, SaveAction.CREATE);
        response = responsePersistenceRules.create(response, loggedInUserDetails);
        responseResourceObjects.add(new ResponseResourceObject(response));
        idMap.put(responseResourceObject.getResponseId(), response.getResponseId());
      }
    }
    ResponseSaveResponse responseSaveResponse = new ResponseSaveResponse();
    responseSaveResponse.setResponseResourceObjects(responseResourceObjects);
    responseSaveResponse.setIdMap(idMap);
    return responseSaveResponse;
  }

  private Response convertResponseResourceObject(ResponseResourceObject responseResourceObject,
      SaveAction saveAction)
      throws TopicExistenceException, ResponseExistenceException {
    Response response;
    validateResponseId(responseResourceObject, saveAction);
    if (responseResourceObject.getResponseId() < 0L) {
      response = new Response();
    } else {
      response = getResponse(responseResourceObject.getResponseId());
    }
    response.setTopic(getTopic(responseResourceObject.getTopicId()));
    response.setTopicResponse(responseResourceObject.getResponse());
    return response;
  }

  private void validateResponseId(ResponseResourceObject responseResourceObject, SaveAction saveAction) {
    if (responseResourceObject.getResponseId() == null) {
      throw new RuntimeException("Id is required to create a response");
    }

    if (responseResourceObject.getResponseId() < 0 && SaveAction.UPDATE == saveAction) {
      throw new RuntimeException("Invalid primary key for update operation");
    }

    if (responseResourceObject.getResponseId() > 0 && SaveAction.CREATE == saveAction) {
      throw new RuntimeException("Create operation cannot have the primary key set in the request payload");
    }
  }

  private boolean validateSaveRequest(ResponseSaveRequest responseSaveRequest,
      SaveAction saveAction) {
    if (saveAction != null && responseSaveRequest != null
        && responseSaveRequest.getResponse() != null) {
      switch (saveAction) {
        case CREATE:
          return responseSaveRequest.getResponse().getAddedItems() != null;
        case UPDATE:
          return responseSaveRequest.getResponse().getUpdatedItems() != null;
        case DELETE:
          return responseSaveRequest.getResponse().getDeletedItems() != null;
        default:
          throw new RuntimeException("Invalid Save Action for Response");
      }
    }
    return false;
  }
}
