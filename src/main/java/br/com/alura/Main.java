package br.com.alura;

import br.com.alura.exception.InvalidPostalCodeException;
import br.com.alura.request.ViaCEPRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  void main() {
    // tente o cep 20220200 e não ria
    Scanner scan = new Scanner(System.in);
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    String address, postalCode = "";
    do {
      try {
        System.out.println("Digite o CEP (ou sair para encerrar):");
        postalCode = scan.next();
        if (postalCode.equals("sair")) {
          return;
        }
        address = ViaCEPRequest.sendRequestByPostalCode(postalCode);
        System.out.println(address);
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

