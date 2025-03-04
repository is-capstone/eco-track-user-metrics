package com.enzulode.metrics.crud.exception;

import java.io.Serial;

public class ItemNotFoundException extends RuntimeException {

  @Serial private static final long serialVersionUID = -18924891637812L;

  public ItemNotFoundException() {
    super("Requested item could not be found");
  }

  public ItemNotFoundException(String message) {
    super(message);
  }

  public ItemNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
