package com.discussnow.resource.topic.exceptions;

import com.discussnow.exceptions.handler.AbstractExceptionTemplate;
import com.discussnow.exceptions.handler.DomainObject;
import com.discussnow.exceptions.handler.ExceptionUtil;
import com.discussnow.resource.topic.TopicConstants;
import com.discussnow.resource.user.exceptions.UserExistenceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TopicExceptionHandler extends ResponseEntityExceptionHandler implements ExceptionUtil {

  private String message;
  private String resolution;
  private HttpStatus status;

  @ExceptionHandler(TopicExistenceException.class)
  @ResponseBody
  protected ResponseEntity<AbstractExceptionTemplate> handleUserExistenceError(TopicExistenceException ex) {
    return buildExceptionTemplate(DomainObject.TOPIC, ex.getTopicId(), ex.getMessage(), TopicConstants.CREATE_TOPIC_BEFORE_READ, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TopicUpdateException.class)
  @ResponseBody
  protected ResponseEntity<AbstractExceptionTemplate> handleTopicUpdateError(TopicUpdateException ex) {
    return buildExceptionTemplate(DomainObject.TOPIC, ex.topicId, ex.getMessage(), TopicConstants.TOPIC_UPDATE_ERROR, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(TopicDeleteException.class)
  @ResponseBody
  protected ResponseEntity<AbstractExceptionTemplate> handleTopicDeleteError(TopicDeleteException ex) {
    return buildExceptionTemplate(DomainObject.TOPIC, ex.topicId, ex.getMessage(), TopicConstants.TOPIC_DELETE_ERROR, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ResponseMeaningException.class)
  @ResponseBody
  protected ResponseEntity<AbstractExceptionTemplate> handleTopicDeleteError(ResponseMeaningException ex) {
    return buildExceptionTemplate(DomainObject.TOPIC, ex.getTopicId(), ex.getMessage(), TopicConstants.RESPONSE_MEANING_EXCEPTION, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(UserExistenceException.class)
  @ResponseBody
  protected ResponseEntity<AbstractExceptionTemplate> handleUserExistenceError(UserExistenceException ex) {
    message = ex.getMessage();
    resolution = TopicConstants.REGISTER_BEFORE_CONTRIBUTING;
    status = HttpStatus.UNAUTHORIZED;
    AbstractExceptionTemplate exceptionTemplate = new AbstractExceptionTemplate(message, resolution, status);
    return new ResponseEntity<>(exceptionTemplate, exceptionTemplate.getStatus());
  }
}
