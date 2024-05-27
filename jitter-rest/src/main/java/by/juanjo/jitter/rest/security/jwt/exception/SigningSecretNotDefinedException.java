package by.juanjo.jitter.rest.security.jwt.exception;

public class SigningSecretNotDefinedException extends RuntimeException {

  public SigningSecretNotDefinedException() {
  }

  public SigningSecretNotDefinedException(String message) {
    super(message);
  }

  public SigningSecretNotDefinedException(String message, Throwable cause) {
    super(message, cause);
  }

  public SigningSecretNotDefinedException(Throwable cause) {
    super(cause);
  }

  public SigningSecretNotDefinedException(String message, Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
