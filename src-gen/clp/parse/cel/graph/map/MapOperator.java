package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MapOperator extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.MapOperator mapoperator;

  public clp.run.cel.graph.map.MapOperator getMapOperator() {
    return mapoperator;
  }

  public void setMapOperator(clp.run.cel.graph.map.MapOperator ref) {
    mapoperator = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (mapoperator == null) {
      clp.run.cel.graph.map.MapOperator[] values = clp.run.cel.graph.map.MapOperator.values();
      for (int i=0; i<values.length; i++) {
        clp.run.cel.graph.map.MapOperator mapoperatorResult = values[i];
        if (token == parser.TT_WORD && match(mapoperatorResult.getVal(), parser.sval) ||
            match(mapoperatorResult.getVal().charAt(0), token)) {
          mapoperator = mapoperatorResult;
          buffer.append( mapoperator.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(mapoperator.getVal().charAt(0), token)) {
        buffer.append( mapoperator.getVal() + " " );
        return true;
      }
    }
    parser.pushBack();
    isOk = null;



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
