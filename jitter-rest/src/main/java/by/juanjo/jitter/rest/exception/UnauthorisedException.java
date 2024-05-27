package by.juanjo.jitter.rest.exception;

public class UnauthorisedException extends RuntimeException {

  public UnauthorisedException() {
  }

  public UnauthorisedException(Throwable cause) {
    super(cause);
  }

  public UnauthorisedException(String message) {
    super(message);
  }

  public UnauthorisedException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnauthorisedException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
