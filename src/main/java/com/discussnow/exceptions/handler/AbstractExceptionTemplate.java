package com.discussnow.exceptions.handler;

import org.springframework.http.HttpStatus;

public class AbstractExceptionTemplate {

  private DomainObject domainObject;
  private Long domainObjectId;
  private String message;
  private String resolution;
  private HttpStatus status;

  public AbstractExceptionTemplate(DomainObject domainObject, Long domainObjectId,
      String message, String resolution, HttpStatus status) {
    this.domainObject = domainObject;
    this.domainObjectId = domainObjectId;
    this.message = message;
    this.resolution = resolution;
    this.status = status;
  }

  public AbstractExceptionTemplate() {
  }

  public AbstractExceptionTemplate(String message, String resolution, HttpStatus status) {
    this.message = message;
    this.resolution = resolution;
    this.status = status;
  }

  public DomainObject getDomainObject() {
    return domainObject;
  }

  public void setDomainObject(DomainObject domainObject) {
    this.domainObject = domainObject;
  }

  public Long getDomainObjectId() {
    return domainObjectId;
  }

  public void setDomainObjectId(Long domainObjectId) {
    this.domainObjectId = domainObjectId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getResolution() {
    return resolution;
  }

  public void setResolution(String resolution) {
    this.resolution = resolution;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
