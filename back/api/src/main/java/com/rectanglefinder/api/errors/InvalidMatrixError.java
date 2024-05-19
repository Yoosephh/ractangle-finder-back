package com.rectanglefinder.api.errors;

public class InvalidMatrixError extends RuntimeException {
  public InvalidMatrixError(String message) {
    super(message);
  }
}
