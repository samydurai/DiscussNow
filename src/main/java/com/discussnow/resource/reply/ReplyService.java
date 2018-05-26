package com.discussnow.resource.reply;

import com.discussnow.model.Reply;
import com.discussnow.resource.enm.SaveAction;
import com.discussnow.resource.reply.exceptions.ReplyExistenceException;
import com.discussnow.resource.reply.exceptions.ReplyPersistenceException;
import com.discussnow.resource.response.exceptions.ResponseExistenceException;
import com.discussnow.resource.topic.exceptions.TopicExistenceException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import com.discussnow.rules.reply.ReplyPersistenceRules;
import com.discussnow.util.ServiceUtil;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService extends ServiceUtil {

  @Autowired
  private ReplyPersistenceRules replyPersistenceRules;

  public ReplySaveResponse save(ReplySaveRequest replySaveRequest, Principal principal)
      throws ReplyPersistenceException, UserExistenceException, ReplyExistenceException, TopicExistenceException, ResponseExistenceException {
    List<ReplyResourceObject> replyResourceObjects = new ArrayList<>();
    Map<Long, Long> idMap = new HashMap<>();
    Map<String, String> loggedInUserDetails = loadLoggedInUserDetails(principal);
    if (validateSaveRequest(replySaveRequest, SaveAction.DELETE)) {
      for (Long replyId : replySaveRequest.getReply().getDeletedItems()) {
        replyPersistenceRules.delete(replyId, loggedInUserDetails);
      }
    }

    if (validateSaveRequest(replySaveRequest, SaveAction.UPDATE)) {
      for (ReplyResourceObject replyResourceObject : replySaveRequest.getReply()
          .getUpdatedItems()) {
        Reply reply = convertReplyResourceObject(replyResourceObject, SaveAction.UPDATE);
        reply = replyPersistenceRules.update(reply, loggedInUserDetails);
        replyResourceObjects.add(new ReplyResourceObject(reply));
        idMap.put(replyResourceObject.getReplyId(), reply.getReplyId());
      }
    }

    if (validateSaveRequest(replySaveRequest, SaveAction.CREATE)) {
      for (ReplyResourceObject replyResourceObject : replySaveRequest.getReply().getAddedItems()) {
        Reply reply = convertReplyResourceObject(replyResourceObject, SaveAction.CREATE);
        reply = replyPersistenceRules.create(reply, loggedInUserDetails);
        replyResourceObjects.add(new ReplyResourceObject(reply));
        idMap.put(replyResourceObject.getReplyId(), reply.getReplyId());
      }
    }

    ReplySaveResponse replySaveResponse = new ReplySaveResponse();
    replySaveResponse.setReplies(replyResourceObjects);
    replySaveResponse.setIdMap(idMap);
    return replySaveResponse;
  }

  private Reply convertReplyResourceObject(ReplyResourceObject replyResourceObject,
      SaveAction saveAction)
      throws ReplyExistenceException, ResponseExistenceException, TopicExistenceException {
    Reply reply;
    if (SaveAction.UPDATE == saveAction) {
      reply = getReply(replyResourceObject.getReplyId());
    } else {
      reply = new Reply();
    }
    if (replyResourceObject.getParentReplyId() != null) {
      reply.setParentReply(getReply(replyResourceObject.getParentReplyId()));
    }
    reply.setReply(replyResourceObject.getReply());
    reply.setResponse(getResponse(replyResourceObject.getResponseId()));
    reply.setTopic(getTopic(replyResourceObject.getTopicId()));
    return reply;
  }

  private boolean validateSaveRequest(ReplySaveRequest replySaveRequest, SaveAction saveAction) {
    if (saveAction != null && replySaveRequest != null && replySaveRequest.getReply() != null) {
      switch (saveAction) {
        case CREATE:
          return replySaveRequest.getReply().getAddedItems() != null;
        case UPDATE:
          return replySaveRequest.getReply().getUpdatedItems() != null;
        case DELETE:
          return replySaveRequest.getReply().getDeletedItems() != null;
        default:
          throw new RuntimeException("Invalid Save Action");
      }
    }
    return false;
  }
}
