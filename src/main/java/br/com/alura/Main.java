package br.com.alura;

import br.com.alura.exception.InvalidPostalCodeException;
import br.com.alura.model.Address;
import br.com.alura.resource.ViaCEPRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  void main() {
    // tente o cep 20220200 e não ria
    Scanner scan = new Scanner(System.in);
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    String postalCode = "";
    Address address;
      do {
        try {
          System.out.println("Digite o CEP (ou sair para encerrar):");
          postalCode = scan.next();
          if (postalCode.equals("sair")) {
            return;
          }
          address = ViaCEPRequest.sendRequestByPostalCode(postalCode);

          System.out.println(gson.toJson(address));

          try (FileWriter addressFile = new FileWriter(STR."./enderecos/\{postalCode}.json")) {
            addressFile.write(gson.toJson(address));
          } catch (IOException e) {
            System.out.println(STR."Ocorreu um erro ao salvar o arquivo: \{e.getMessage()}");
          }
        } catch (InvalidPostalCodeException e) {
          System.out.println(STR."Ocorreu um erro no CEP inserido: \{e.getMessage()}");
        } catch (IOException | InterruptedException e) {
          System.out.println(STR."Ocorreu um erro na requisição: \{e.getMessage()}");
        } catch (Exception e) {
          System.out.println(STR."Ocorreu um erro inesperado: \{e.getMessage()}");
        }
      } while (!postalCode.equalsIgnoreCase("sair"));
    }

}

