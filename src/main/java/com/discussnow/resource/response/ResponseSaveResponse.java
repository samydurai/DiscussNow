package com.discussnow.resource.response;

import java.util.List;
import java.util.Map;

public class ResponseSaveResponse {

  private List<ResponseResourceObject> responseResourceObjects;
  private Map<Long, Long> idMap;

  public List<ResponseResourceObject> getResponseResourceObjects() {
    return responseResourceObjects;
  }

  public void setResponseResourceObjects(
      List<ResponseResourceObject> responseResourceObjects) {
    this.responseResourceObjects = responseResourceObjects;
  }

  public Map<Long, Long> getIdMap() {
    return idMap;
  }

  public void setIdMap(Map<Long, Long> idMap) {
    this.idMap = idMap;
  }
}
