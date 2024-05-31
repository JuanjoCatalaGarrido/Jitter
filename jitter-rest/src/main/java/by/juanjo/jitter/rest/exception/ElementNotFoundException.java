package by.juanjo.jitter.rest.exception;

public class ElementNotFoundException extends RuntimeException {

  public ElementNotFoundException(String message) {
    super(message);
  }

  public ElementNotFoundException() {
  }

  public ElementNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ElementNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ElementNotFoundException(Throwable cause) {
    super(cause);
  }
}
