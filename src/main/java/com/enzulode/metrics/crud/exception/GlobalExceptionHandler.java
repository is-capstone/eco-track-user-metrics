package com.enzulode.metrics.crud.exception;

import com.enzulode.metrics.crud.dto.api.ErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

  private final MessageSource ms;

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorDto> illegalArgumentsProvided(IllegalArgumentException e) {
    var msg = List.of(ms.getMessage("api.error.invalidargs", null, LocaleContextHolder.getLocale()));
    return ResponseEntity.badRequest()
        .body(new ErrorDto(msg));
  }

  @ExceptionHandler(ItemCreationFailedException.class)
  public ResponseEntity<ErrorDto> itemCreationFailed(ItemCreationFailedException e) {
    var msg = List.of(ms.getMessage("api.error.creation", null, LocaleContextHolder.getLocale()));
    return ResponseEntity.badRequest()
        .body(new ErrorDto(msg));
  }

  @ExceptionHandler(ItemUpdateFailedException.class)
  public ResponseEntity<ErrorDto> itemUpdateFailed(ItemUpdateFailedException e) {
    var msg = List.of(ms.getMessage("api.error.update", null, LocaleContextHolder.getLocale()));
    return ResponseEntity.badRequest()
        .body(new ErrorDto(msg));
  }

  @ExceptionHandler(ItemNotFoundException.class)
  public ResponseEntity<ErrorDto> itemNotFound(ItemNotFoundException e) {
    return ResponseEntity.notFound().build();
  }
}
