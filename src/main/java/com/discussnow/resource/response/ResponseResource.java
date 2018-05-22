package com.discussnow.resource.response;

import com.discussnow.DiscussNowConstants;
import com.discussnow.resource.response.exceptions.ResponseExistenceException;
import com.discussnow.resource.response.exceptions.ResponseUserContextException;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DiscussNowConstants.RESPONSE)
public class ResponseResource {

  @Autowired
  private ResponseService responseService;

  @RequestMapping(value = DiscussNowConstants.SAVE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseSaveResponse save(@RequestBody ResponseSaveRequest responseSaveRequest,
      Principal principal)
      throws UserExistenceException, TopicExistenceException, ResponseUserContextException, ResponseExistenceException {
    return responseService.save(responseSaveRequest, principal);
  }
}
