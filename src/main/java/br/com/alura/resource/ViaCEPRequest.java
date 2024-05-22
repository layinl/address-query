package br.com.alura.resource;

import br.com.alura.exception.InvalidPostalCodeException;
import br.com.alura.model.Address;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViaCEPRequest {

  public static Address sendRequestByPostalCode (String postalCode) throws IOException, InterruptedException {
    checkPostalCode(postalCode);
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    String address = STR."https://viacep.com.br/ws/\{postalCode}/json/";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(address))
      .build();
    HttpResponse<String> response = client
      .send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() == 400 || response.body().contains("erro"))
      // redundant, but it's a workaround for now
      throw new InvalidPostalCodeException("O CEP é válido, mas não existe");
    return gson.fromJson(response.body(), Address.class);
  }

  private static void checkPostalCode(String postalCode) {
    final Pattern pattern = Pattern.compile("\\d{8}|\\d{5}-\\d{3}");
    Matcher matcher = pattern.matcher(postalCode);
    if(!matcher.find() || postalCode.length() > 9)
      throw new InvalidPostalCodeException("O CEP informado é inválido");
  }

}
