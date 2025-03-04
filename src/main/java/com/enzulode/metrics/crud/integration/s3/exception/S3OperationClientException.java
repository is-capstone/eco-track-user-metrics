package com.enzulode.metrics.crud.integration.s3.exception;

import java.io.Serial;

public class S3OperationClientException extends RuntimeException {

  @Serial private static final long serialVersionUID = 712637123L;

  public S3OperationClientException(String message) {
    super(message);
  }

  public S3OperationClientException(String message, Throwable cause) {
    super(message, cause);
  }
}
