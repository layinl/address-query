package br.com.alura.request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCEPRequest {

  public static String sendRequestByPostalCode (String postalCode) throws IOException, InterruptedException {
    String address = STR."https://viacep.com.br/ws/\{postalCode}/json/";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(address))
      .build();
    HttpResponse<String> response = client
      .send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }

}
