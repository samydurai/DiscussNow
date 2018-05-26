package com.discussnow.rules.reply;

import com.discussnow.model.Reply;
import com.discussnow.model.User;
import com.discussnow.repository.ReplyRepository;
import com.discussnow.resource.reply.ReplyConstants;
import com.discussnow.resource.reply.exceptions.ReplyExistenceException;
import com.discussnow.resource.reply.exceptions.ReplyPersistenceException;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import com.discussnow.rules.RulesUtil;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplyPersistenceRules extends RulesUtil {

  @Autowired
  private ReplyRulesUtil util;

  @Autowired
  private ReplyRepository replyRepository;

  public void delete(Long replyId, Map<String, String> loggedInUserDetails)
      throws ReplyExistenceException, UserExistenceException, ReplyPersistenceException {
    Reply reply = util.getReplyIfExists(replyId);
    User loggedInUser = validateUserExistence(loggedInUserDetails);
    util.validateReplyDeletion(reply, loggedInUser);
    List<Reply> childReplies = util.getChildReplies(reply);
    childReplies.add(reply);
    replyRepository.deleteAll(childReplies);
  }

  public Reply update(Reply reply, Map<String, String> loggedInUserDetails)
      throws ReplyExistenceException, UserExistenceException, ReplyPersistenceException {
    validateReply(reply);
    Reply existingReply = util.getReplyIfExists(reply.getReplyId());
    User loggedInUser = validateUserExistence(loggedInUserDetails);
    util.validateReplyUpdate(existingReply, loggedInUser);
    reply.setUser(loggedInUser);
    return replyRepository.save(reply);
  }

  public Reply create(Reply reply, Map<String, String> loggedInUserDetails)
      throws UserExistenceException, ReplyPersistenceException {
    validateReply(reply);
    User loggedInUser = validateUserExistence(loggedInUserDetails);
    reply.setUser(loggedInUser);
    return replyRepository.save(reply);
  }

  private void validateReply(Reply reply) throws ReplyPersistenceException {
    if (reply.getTopic() == null) {
      throw new ReplyPersistenceException(ReplyConstants.CANNOT_CREATE_REPLY_WITHOUT_TOPIC);
    }
    if (reply.getResponse() == null) {
      throw new ReplyPersistenceException(ReplyConstants.CANNOT_CREATE_REPLY_WITHOUT_RESPONSE);
    }
  }
}
