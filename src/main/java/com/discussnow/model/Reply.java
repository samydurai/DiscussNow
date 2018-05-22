package com.discussnow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REPLY")
public class Reply extends Updatable {

  private Long replyId;
  private Response response;
  private Topic topic;
  private User user;
  private Long parentReplyId;

  @Id
  @SequenceGenerator(name = "REPLY_SEQ", sequenceName = "REPLY_SEQ", allocationSize = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLY_SEQ")
  @Column(name = "REPLY_ID")
  public Long getReplyId() {
    return replyId;
  }

  public void setReplyId(Long replyId) {
    this.replyId = replyId;
  }

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Response.class)
  @JoinColumn(name = "RESPONSE_ID", referencedColumnName = "RESPONSE_ID", nullable = false)
  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Topic.class)
  @JoinColumn(name = "TOPIC_ID", referencedColumnName = "TOPIC_ID")
  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Column(name = "PARENT_REPLY_ID")
  public Long getParentReplyId() {
    return parentReplyId;
  }

  public void setParentReplyId(Long parentReplyId) {
    this.parentReplyId = parentReplyId;
  }
}
