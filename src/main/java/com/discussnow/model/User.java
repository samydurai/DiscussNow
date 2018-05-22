package com.discussnow.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends Updatable {

  private Long userId;
  private String userEmail;
  private String name;
  private List<Topic> topic;
  private List<Response> responses;
  private List<Reply> replies;

  @Id
  @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
  @Column(name = "USER_ID")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "EMAIL", unique = true, nullable = false)
  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  @Column(name = "NAME", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Topic.class, cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Topic> getTopic() {
    return topic;
  }

  public void setTopic(List<Topic> topic) {
    this.topic = topic;
  }

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Response.class, orphanRemoval = true, mappedBy = "user")
  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Reply.class, cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Reply> getReplies() {
    return replies;
  }

  public void setReplies(List<Reply> replies) {
    this.replies = replies;
  }
}
