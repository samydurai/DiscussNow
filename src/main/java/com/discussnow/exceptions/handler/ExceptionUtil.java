package com.discussnow.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ExceptionUtil {

  String NOT_AVAILABLE = "NOT_AVAILABLE";

  default ResponseEntity<AbstractExceptionTemplate> buildExceptionTemplate(
      DomainObject domainObject, Long domainObjectId, String message, String resolution,
      HttpStatus status) {
    AbstractExceptionTemplate exceptionTemplate = new AbstractExceptionTemplate(domainObject,
        domainObjectId, message, resolution, status);
    return new ResponseEntity<>(exceptionTemplate, exceptionTemplate.getStatus());
  }
}
