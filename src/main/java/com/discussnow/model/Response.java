package com.discussnow.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RESPONSE")
public class Response extends Updatable {

  private Long responseId;
  private Topic topic;
  private String topicResponse;
  private User user;
  private List<Reply> replies;

  @Id
  @SequenceGenerator(name = "RESPONSE_SEQ", sequenceName = "RESPONSE_SEQ", allocationSize = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESPONSE_SEQ")
  @Column(name = "RESPONSE_ID")
  public Long getResponseId() {
    return responseId;
  }

  public void setResponseId(Long responseId) {
    this.responseId = responseId;
  }

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Topic.class)
  @JoinColumn(name = "TOPIC_ID", referencedColumnName = "TOPIC_ID", nullable = false)
  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  @Column(name = "TOPIC_RESPONSE")
  @Lob
  public String getTopicResponse() {
    return topicResponse;
  }

  public void setTopicResponse(String topicResponse) {
    this.topicResponse = topicResponse;
  }

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @OneToMany(fetch = FetchType.LAZY, targetEntity = Reply.class, mappedBy = "response", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Reply> getReplies() {
    return replies;
  }

  public void setReplies(List<Reply> replies) {
    this.replies = replies;
  }
}
