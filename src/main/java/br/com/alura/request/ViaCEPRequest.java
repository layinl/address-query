package br.com.alura.request;

import br.com.alura.exception.InvalidPostalCodeException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViaCEPRequest {

  public static String sendRequestByPostalCode (String postalCode) throws IOException, InterruptedException {
    checkPostalCode(postalCode);
    String address = STR."https://viacep.com.br/ws/\{postalCode}/json/";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(address))
      .build();
    HttpResponse<String> response = client
      .send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() == 400)
      // redundant, but it's a workaround for now
      throw new InvalidPostalCodeException("HTTP 400 - O CEP informado é inválido");
    return response.body();
  }

  private static void checkPostalCode(String postalCode) {
    final Pattern pattern = Pattern.compile("\\d{8}|\\d{5}-\\d{3}");
    Matcher matcher = pattern.matcher(postalCode);
    if(!matcher.find() || postalCode.length() > 9)
      throw new InvalidPostalCodeException("O CEP informado é inválido");
  }

}
