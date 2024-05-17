package br.com.alura.exception;

public class InvalidPostalCodeException extends RuntimeException {

  public InvalidPostalCodeException() {
    super("O CEP informado é inválido");
  }

  public InvalidPostalCodeException(String message) {
    super(message);
  }

}
