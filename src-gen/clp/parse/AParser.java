package clp.parse;

import java.io.IOException;

import clp.parse.CLAppParser;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
abstract public class AParser {

  abstract public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException;

  abstract public StringBuffer getRendering();


  public boolean match(String string, String sval) {
    return string.equals(sval);
  }

  public boolean match(char car, int token) {
    return token == car;
  }
}
