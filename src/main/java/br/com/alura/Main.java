package br.com.alura;

import br.com.alura.request.ViaCEPRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  void main() {
    // tente 20220200 e não ria
    Scanner scan = new Scanner(System.in);
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    String address;
    try {
      System.out.println("Digite o CEP:");
      address = ViaCEPRequest.sendRequestByPostalCode(scan.nextLine());
      System.out.println(address);
    } catch (IOException | InterruptedException e) {
      System.out.println(STR."There was an error with the request: \{e.getMessage()}");
    } catch (Exception e) {
      System.out.println(STR."There was an unexpected error: \{e.getMessage()}");
    }
  }

}

