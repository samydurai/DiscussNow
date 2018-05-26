package com.discussnow.resource.reply.exceptions;

import com.discussnow.exceptions.handler.AbstractExceptionTemplate;
import com.discussnow.exceptions.handler.DomainObject;
import com.discussnow.exceptions.handler.ExceptionUtil;
import com.discussnow.resource.reply.ReplyConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ReplyExceptionHandler extends ResponseEntityExceptionHandler implements ExceptionUtil {
  private String message;
  private String resolution;
  private HttpStatus status;

  @ExceptionHandler(ReplyExistenceException.class)
  public ResponseEntity<AbstractExceptionTemplate> handleReplyExistenceException(ReplyExistenceException ex) {
    return buildExceptionTemplate(DomainObject.REPLY, ex.getReplyId(), ex.getMessage(),
        ReplyConstants.REPLY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ReplyPersistenceException.class)
  public ResponseEntity<AbstractExceptionTemplate> handleReplyPersistenceException(ReplyPersistenceException ex) {
    return buildExceptionTemplate(DomainObject.REPLY, ex.getReplyId(), ex.getMessage(), NOT_AVAILABLE, HttpStatus.UNAUTHORIZED);
  }
}
