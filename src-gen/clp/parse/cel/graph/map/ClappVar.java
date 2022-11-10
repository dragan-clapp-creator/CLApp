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
public class ClappVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.ClappVar clappvar = new clp.run.cel.graph.map.ClappVar();

  public clp.run.cel.graph.map.ClappVar getClappVar() {
    return clappvar;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(true);
    }
    isOk = true;
    clappvar.setName(parser.sval);
    
    buffer.append( clappvar.getName() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
