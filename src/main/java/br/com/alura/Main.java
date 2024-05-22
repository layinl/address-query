package br.com.alura;

import br.com.alura.exception.InvalidPostalCodeException;
import br.com.alura.model.Address;
import br.com.alura.resource.AddressFile;
import br.com.alura.resource.ViaCEPRequest;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Address Query program searches for Brazil's Addresses by consuming
 * the <a href="https://viacep.com.br/">ViaCepAPI</a> using the <b>Postal Code (CEP)</b>.
 * <br/>
 * It returns the corresponing address and saves in a file named enderecos/<i>XXXXX-XXX</i>.json.
 */
public class Main {

  void main() {
    // tente o cep 20220200 e não ria
    Scanner scan = new Scanner(System.in);
    String postalCode = "";
    Address address;
      do {
        try {
          System.out.println("Digite o CEP (ou sair para encerrar):");
          postalCode = scan.nextLine();

          if (postalCode.equals("sair")) {
            return;
          }
          address = ViaCEPRequest.sendRequestByPostalCode(postalCode);
          System.out.println(AddressFile.saveJson(address));
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

