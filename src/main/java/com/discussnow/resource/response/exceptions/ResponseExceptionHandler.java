package com.discussnow.resource.response.exceptions;

import com.discussnow.exceptions.handler.AbstractExceptionTemplate;
import com.discussnow.exceptions.handler.DomainObject;
import com.discussnow.exceptions.handler.ExceptionUtil;
import com.discussnow.resource.response.ResponseConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler implements
    ExceptionUtil {

  private static final String NOT_APPLICABLE = "NOT_APPLICABLE";

  @ExceptionHandler(ResponseExistenceException.class)
  @ResponseBody
  protected ResponseEntity<AbstractExceptionTemplate> handleResponseExistenceError(
      ResponseExistenceException ex) {
    return buildExceptionTemplate(DomainObject.RESPONSE, ex.getResponseId(), ex.getMessage(),
        ResponseConstants.CREATE_RESPONSE_BEFORE_EDIT, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResponsePersistenceException.class)
  @ResponseBody
  protected ResponseEntity<AbstractExceptionTemplate> handleResponsePersistenceError(
      ResponsePersistenceException ex) {
    return buildExceptionTemplate(DomainObject.RESPONSE, ex.getResponseId(), ex.getMessage(),
        NOT_APPLICABLE, HttpStatus.BAD_REQUEST);
  }

}
