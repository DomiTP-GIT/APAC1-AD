package com.ieseljust.ad.figures;

/*
Aquesta classe serveix per validar Strings que 
representen un color en Hexadecimal.

Font: https://examples.javacodegeeks.com/core-java/util/regex/matcher/how-to-validate-hex-color-code-with-regular-expression/

*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexColorValidator {


  // Son 6 numeros en hexadecimal. Minuscules o majúscules
  private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
  private static final Pattern pattern = Pattern.compile(HEX_PATTERN);
  private static Matcher matcher;


  // comprova si la cadena és vlida
  public static boolean validate(final String hexColorCode) {

    matcher = pattern.matcher(hexColorCode);
    return matcher.matches();

  }
}