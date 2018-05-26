package com.discussnow.resource.reply.exceptions;

public class ReplyExistenceException extends Exception {

  private Long replyId;

  public ReplyExistenceException(String message) {
    super(message);
  }

  public ReplyExistenceException(String message, Long replyId) {
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
