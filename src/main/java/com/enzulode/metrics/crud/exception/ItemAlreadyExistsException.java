package com.enzulode.metrics.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemAlreadyExistsException extends RuntimeException {

  @Serial private static final long serialVersionUID = 81647112412L;

  public ItemAlreadyExistsException(String message) {
    super(message);
  }

  public ItemAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
