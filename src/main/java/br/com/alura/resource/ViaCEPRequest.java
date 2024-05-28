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

/**
 * The ViaCEPRequest class organizes the postal code request sending. It
 * also checks if the postal code is valid.
 */
public class ViaCEPRequest {

  /**
   * Sends a request to ViaCEP to fetch the address by a valid Postal Code
   * and returns the response as an Address object
   * @param postalCode the postal code, or CEP, to search
   * @return the response as an Address object
   * @throws IOException if there's a problem with I/O while sending or
   * receiving
   * @throws InterruptedException if there's interruption
   */
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
      throw new InvalidPostalCodeException("O CEP é válido, mas não existe");
    return gson.fromJson(response.body(), Address.class);
  }

  /**
   * Checks if the provided postalCode is valid.
   * <br/>
   * A valid postal code might be either of these:
   * <ul>
   *   <li>XXXXXXXX</li>
   *   <li>XXXXX-XXX</li>
   * </ul>
   * @param postalCode the postal code to be validated
   * @throws InvalidPostalCodeException if the postal code is not valid
   */
  private static void checkPostalCode(String postalCode) {
    final Pattern pattern = Pattern.compile("\\d{8}|\\d{5}-\\d{3}");
    Matcher matcher = pattern.matcher(postalCode);
    if(!matcher.find() || postalCode.length() > 9)
      throw new InvalidPostalCodeException("O CEP informado é inválido");
  }

}
