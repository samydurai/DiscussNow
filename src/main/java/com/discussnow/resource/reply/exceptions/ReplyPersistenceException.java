package com.discussnow.resource.reply.exceptions;

public class ReplyPersistenceException extends Exception {

  Long replyId;

  public ReplyPersistenceException(String message) {
    super(message);
  }

  public ReplyPersistenceException(String message, Long replyId) {
    super(message);
    this.replyId = replyId;
  }

  public Long getReplyId() {
    return replyId;
  }

  public void setReplyId(Long replyId) {
    this.replyId = replyId;
  }
}
