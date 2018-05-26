package com.discussnow.resource.reply;

import com.discussnow.save.util.BatchResourceObject;

public class ReplySaveRequest {

  private BatchResourceObject<ReplyResourceObject, Long> reply;

  public BatchResourceObject<ReplyResourceObject, Long> getReply() {
    return reply;
  }

  public void setReply(
      BatchResourceObject<ReplyResourceObject, Long> reply) {
    this.reply = reply;
  }
}
