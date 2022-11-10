package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GMapParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.GMapper gmapper = new clp.run.cel.graph.map.GMapper();

  public clp.run.cel.graph.map.GMapper getGMapper() {
    return gmapper;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("map", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("map ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    gmapper.setGname(parser.sval);
    
    buffer.append( gmapper.getGname() + " " );

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ": " );

      Mapping mapping3 = new Mapping();
        Boolean bmapping3 = mapping3.parse(parser, isOptional);

        if (bmapping3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( mapping3.getRendering() + " " );
        gmapper.setMapping(mapping3.getMapping());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
