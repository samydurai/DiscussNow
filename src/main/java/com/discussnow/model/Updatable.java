package com.discussnow.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Updatable {

  private Date createTimestamp;
  private Date updateTimestamp;

  @Column(name = "CREATE_TS", nullable = false, updatable = false)
  @Temporal(value = TemporalType.TIMESTAMP)
  public Date getCreateTimestamp() {
    return createTimestamp;
  }

  public void setCreateTimestamp(Date createTimestamp) {
    this.createTimestamp = createTimestamp;
  }

  @Column(name = "UPDATE_TS", nullable = false)
  @Temporal(value = TemporalType.TIMESTAMP)
  public Date getUpdateTimestamp() {
    return updateTimestamp;
  }

  public void setUpdateTimestamp(Date updateTimestamp) {
    this.updateTimestamp = updateTimestamp;
  }

  @PrePersist
  @PreUpdate
  public void updateAuditInformation() {
    setUpdateTimestamp(new Date());
    if (getCreateTimestamp() == null) {
      setCreateTimestamp(new Date());
    }
  }
}
