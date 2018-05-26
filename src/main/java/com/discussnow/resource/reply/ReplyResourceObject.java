package com.discussnow.resource.reply;

import com.discussnow.model.Reply;

public class ReplyResourceObject {

  private Long replyId;
  private Long responseId;
  private Long topicId;
  private Long parentReplyId;
  private String reply;
  private boolean isRootReply;

  public ReplyResourceObject(Reply reply) {
    this.replyId = reply.getReplyId();
    this.responseId = reply.getResponse().getResponseId();
    this.topicId = reply.getTopic().getTopicId();
    this.parentReplyId = reply.getParentReply() != null ? reply.getParentReply().getReplyId() : null;
    this.reply = reply.getReply();
    if (parentReplyId == null) {
      this.isRootReply = true;
    }
  }

  public ReplyResourceObject() {
  }

  public Long getReplyId() {
    return replyId;
  }

  public void setReplyId(Long replyId) {
    this.replyId = replyId;
  }

  public Long getResponseId() {
    return responseId;
  }

  public void setResponseId(Long responseId) {
    this.responseId = responseId;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public Long getParentReplyId() {
    return parentReplyId;
  }

  public void setParentReplyId(Long parentReplyId) {
    this.parentReplyId = parentReplyId;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public boolean isRootReply() {
    return isRootReply;
  }

  public void setRootReply(boolean rootReply) {
    isRootReply = rootReply;
  }
}
