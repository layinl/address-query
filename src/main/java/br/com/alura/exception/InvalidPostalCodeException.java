package br.com.alura.exception;

/**
 * The InvalidPostalCodeException is a runtime exception that occurs when
 * postal code is not valid
 */
public class InvalidPostalCodeException extends RuntimeException {

  public InvalidPostalCodeException() {
    super("O CEP informado é inválido");
  }

  public InvalidPostalCodeException(String message) {
    super(message);
  }

}
