package com.rectanglefinder.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
  
  @ExceptionHandler({UnknownBodyError.class})
  public ResponseEntity<String> handleUnknownBody(UnknownBodyError exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @ExceptionHandler({InvalidMatrixError.class})
  public ResponseEntity<String> handleInvalidMatrix(InvalidMatrixError exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @ExceptionHandler({InvalidMatrixContentError.class})
  public ResponseEntity<String> handleInvalidMatrixContent(InvalidMatrixContentError exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }
}
