package com.discussnow.rules.reply;

import com.discussnow.model.Reply;
import com.discussnow.model.User;
import com.discussnow.repository.ReplyRepository;
import com.discussnow.resource.reply.ReplyConstants;
import com.discussnow.resource.reply.exceptions.ReplyExistenceException;
import com.discussnow.resource.reply.exceptions.ReplyPersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplyRulesUtil {

  @Autowired
  private ReplyRepository replyRepository;

  protected Reply getReplyIfExists(Long replyId) throws ReplyExistenceException {
    Optional<Reply> reply = replyRepository.findById(replyId);
    if (!reply.isPresent()) {
      throw new ReplyExistenceException(ReplyConstants.REPLY_DOES_NOT_EXIST, replyId);
    }
    return reply.get();
  }

  public void validateReplyDeletion(Reply reply, User loggedInUser)
      throws ReplyPersistenceException {
    validateUserContext(reply.getUser(), loggedInUser,
        ReplyConstants.REPLY_DELETE_USER_MISMATCH_ERROR, reply.getReplyId());
  }

  public void validateReplyUpdate(Reply existingReply, User loggedInUser)
      throws ReplyPersistenceException {
    validateUserContext(existingReply.getUser(), loggedInUser,
        ReplyConstants.REPLY_UPDATE_USER_MISMATCH_ERROR, existingReply.getReplyId());
  }

  private void validateUserContext(User existingUser, User loggedInUser, String replyErrorMsg,
      Long replyId)
      throws ReplyPersistenceException {
    Long ownerId = existingUser.getUserId();
    Long loggedInUserId = loggedInUser.getUserId();
    if (!ownerId.equals(loggedInUserId)) {
      throw new ReplyPersistenceException(ReplyConstants.REPLY_DELETE_USER_MISMATCH_ERROR, replyId);
    }
  }

  public List<Reply> getChildReplies(Reply reply) {
    List<Reply> childReplies = new ArrayList<>();
    Reply childReply = replyRepository.findByParentReply(reply);
    while (childReply != null) {
      childReplies.add(childReply);
      childReply = replyRepository.findByParentReply(childReply);
    }
    return childReplies;
  }
}
