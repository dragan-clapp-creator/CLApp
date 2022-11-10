package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GraphMapType extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.GraphMapType graphmaptype;

  public clp.run.cel.graph.map.GraphMapType getGraphMapType() {
    return graphmaptype;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    ABSTRACT bABSTRACT = new ABSTRACT();
    isOk = bABSTRACT.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      graphmaptype = bABSTRACT.getProcessor();
      buffer.append( bABSTRACT.getRendering()+" " );
      return true;
    }

    ClappVar bClappVar = new ClappVar();
    isOk = bClappVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      graphmaptype = bClappVar.getClappVar();
      buffer.append( bClappVar.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
