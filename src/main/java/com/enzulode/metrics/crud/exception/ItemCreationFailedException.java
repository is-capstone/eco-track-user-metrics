package com.enzulode.metrics.crud.exception;

import java.io.Serial;

public class ItemCreationFailedException extends RuntimeException {

  @Serial private static final long serialVersionUID = 78124121231L;

  public ItemCreationFailedException() {
    super("An error occurred while creating the item");
  }

  public ItemCreationFailedException(String message) {
    super(message);
  }

  public ItemCreationFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
