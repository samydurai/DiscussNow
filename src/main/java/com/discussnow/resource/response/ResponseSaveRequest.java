package com.discussnow.resource.response;

import com.discussnow.save.util.BatchResourceObject;

public class ResponseSaveRequest {

  BatchResourceObject<ResponseResourceObject, Long> response;

  public BatchResourceObject<ResponseResourceObject, Long> getResponse() {
    return response;
  }

  public void setResponse(
      BatchResourceObject<ResponseResourceObject, Long> response) {
    this.response = response;
  }
}
