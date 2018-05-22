package com.discussnow.resource.topic;

import com.discussnow.model.Topic;
import java.util.Date;

public class TopicResourceObject {

  private Long topicId;
  private String title;
  private String description;
  private Long userId;
  private Date updateTs;

  public TopicResourceObject() {
  }

  public TopicResourceObject(Topic topic) {
    topicId = topic.getTopicId();
    title = topic.getTitle();
    description = topic.getDescription();
    userId = topic.getUser().getUserId();
    updateTs = topic.getUpdateTimestamp();
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getUpdateTs() {
    return updateTs;
  }

  public void setUpdateTs(Date updateTs) {
    this.updateTs = updateTs;
  }
}
