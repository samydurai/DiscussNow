package com.discussnow.resource.reply;

import com.discussnow.DiscussNowConstants;
import com.discussnow.resource.reply.exceptions.ReplyExistenceException;
import com.discussnow.resource.reply.exceptions.ReplyPersistenceException;
import com.discussnow.resource.response.exceptions.ResponseExistenceException;
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
@RequestMapping(DiscussNowConstants.REPLY)
public class ReplyResource {

  @Autowired
  private ReplyService replyService;

  @RequestMapping(value = DiscussNowConstants.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ReplySaveResponse save(@RequestBody ReplySaveRequest replySaveRequest, Principal principal)
      throws ReplyPersistenceException, UserExistenceException, ReplyExistenceException, ResponseExistenceException, TopicExistenceException {
    return replyService.save(replySaveRequest, principal);
  }
}
