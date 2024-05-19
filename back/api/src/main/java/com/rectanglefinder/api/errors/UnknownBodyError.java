package com.rectanglefinder.api.errors;

public class UnknownBodyError extends RuntimeException {
  public UnknownBodyError(String message) {
    super(message);
  }
}
