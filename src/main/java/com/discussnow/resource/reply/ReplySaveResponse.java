package com.discussnow.resource.reply;

import java.util.List;
import java.util.Map;

public class ReplySaveResponse {

  private List<ReplyResourceObject> replies;
  private Map<Long, Long> idMap;

  public List<ReplyResourceObject> getReplies() {
    return replies;
  }

  public void setReplies(List<ReplyResourceObject> replies) {
    this.replies = replies;
  }

  public Map<Long, Long> getIdMap() {
    return idMap;
  }

  public void setIdMap(Map<Long, Long> idMap) {
    this.idMap = idMap;
  }
}
