package br.com.alura;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {

  /**
   * Testing RexEx...
   */
  void main() {
    Scanner scan = new Scanner(System.in);
    Pattern pattern = Pattern.compile("\\d{8}|\\d{5}-\\d{3}");
    Matcher matcher;
    String postalCode = "";

    while (!postalCode.equalsIgnoreCase("-1")) {
      postalCode = scan.next();
      matcher = pattern.matcher(postalCode);
      System.out.println(matcher.find());
    }

  }

}
