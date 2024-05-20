package com.rectanglefinder.api.errors;

public class InvalidMatrixContentError extends RuntimeException{
  public InvalidMatrixContentError(String message) {
    super(message);
  }
}
