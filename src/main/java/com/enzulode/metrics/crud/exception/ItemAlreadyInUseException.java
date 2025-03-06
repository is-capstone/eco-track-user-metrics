package com.enzulode.metrics.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemAlreadyInUseException extends RuntimeException {

  @Serial private static final long serialVersionUID = 187651728013212L;

  public ItemAlreadyInUseException() {
    super("Requested item could not be updated");
  }

  public ItemAlreadyInUseException(String message) {
    super(message);
  }

  public ItemAlreadyInUseException(String message, Throwable cause) {
    super(message, cause);
  }
}
