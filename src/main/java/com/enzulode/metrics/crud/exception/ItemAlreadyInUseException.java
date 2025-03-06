package com.enzulode.metrics.crud.exception;

import java.io.Serial;

public class ItemUpdateFailedException extends RuntimeException {

  @Serial private static final long serialVersionUID = 187651728013212L;

  public ItemUpdateFailedException() {
    super("Requested item could not be updated");
  }

  public ItemUpdateFailedException(String message) {
    super(message);
  }

  public ItemUpdateFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
