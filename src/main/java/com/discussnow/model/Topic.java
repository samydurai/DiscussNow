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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TOPIC")
@NamedQueries(
    @NamedQuery(name = "Topic.searchByTitle", query = "SELECT t from Topic t where t.title LIKE ?1")
)
public class Topic extends Updatable {

  private Long topicId;
  private User user;
  private String title;
  private String description;
  private List<Response> responses;
  private List<Reply> replies;

  @Id
  @SequenceGenerator(name = "TOPIC_SEQ", sequenceName = "TOPIC_SEQ", allocationSize = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOPIC_SEQ")
  @Column(name = "TOPIC_ID")
  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Column(name = "TITLE", nullable = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "DESCRIPTION", nullable = false)
  @Lob
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic", targetEntity = Response.class, cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic", targetEntity = Reply.class, cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Reply> getReplies() {
    return replies;
  }

  public void setReplies(List<Reply> replies) {
    this.replies = replies;
  }

}
