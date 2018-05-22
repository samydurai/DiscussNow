package com.discussnow.save.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BatchResourceObject<T, U> {

  public List<T> added;
  public List<T> updated;
  public List<U> deleted;

  public BatchResourceObject() {
    added = new ArrayList<>();
    updated = new ArrayList<>();
    deleted = new ArrayList<>();
  }

  public Collection<T> getAddedItems() {
    return added;
  }

  public Collection<U> getDeletedItems() {
    return deleted;
  }

  public Collection<T> getUpdatedItems() {
    return updated;
  }
}
