package com.discussnow.resource.topic;

import com.discussnow.save.util.BatchResourceObject;

public class TopicSaveRequest {

  private BatchResourceObject<TopicResourceObject, Long> topic;

  public BatchResourceObject<TopicResourceObject, Long> getTopic() {
    return topic;
  }

  public void setTopic(
      BatchResourceObject<TopicResourceObject, Long> topic) {
    this.topic = topic;
  }
}
