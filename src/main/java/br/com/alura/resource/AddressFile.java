package br.com.alura.resource;

import br.com.alura.model.Address;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class AddressFile {

  /**
   * Saves the provided Address as JSON to a new or existing file
   * @param address the address object to be saved
   * @return address in json format
   */
  public static String saveJson(Address address) {
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    try (FileWriter addressFile = new FileWriter(STR."./enderecos/\{address.postalCode()}.json")) {
      addressFile.write(gson.toJson(address));
    } catch (IOException e) {
      System.out.println(STR."Ocorreu um erro ao salvar o arquivo: \{e.getMessage()}");
    }
    return gson.toJson(address);
  }

}
