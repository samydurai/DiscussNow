package com.discussnow.resource.topic;

import java.util.List;
import java.util.Map;

public class TopicSaveResponse {
  List<TopicResourceObject> topicResourceObjects;
  Map<Long, Long> idMap;

  public List<TopicResourceObject> getTopicResourceObjects() {
    return topicResourceObjects;
  }

  public void setTopicResourceObjects(
      List<TopicResourceObject> topicResourceObjects) {
    this.topicResourceObjects = topicResourceObjects;
  }

  public Map<Long, Long> getIdMap() {
    return idMap;
  }

  public void setIdMap(Map<Long, Long> idMap) {
    this.idMap = idMap;
  }
}
