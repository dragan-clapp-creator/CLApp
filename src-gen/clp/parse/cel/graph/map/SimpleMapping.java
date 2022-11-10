package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleMapping extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.SimpleMapping simplemapping = new clp.run.cel.graph.map.SimpleMapping();

  public clp.run.cel.graph.map.SimpleMapping getSimpleMapping() {
    return simplemapping;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      MapOperator mapOperator0 = new MapOperator();
        Boolean bmapOperator0 = mapOperator0.parse(parser, isOptional);

        if (bmapOperator0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( mapOperator0.getRendering() + " " );
        simplemapping.setMapOperator(mapOperator0.getMapOperator());

      GraphMapType graphMapType1 = new GraphMapType();
        Boolean bgraphMapType1 = graphMapType1.parse(parser, isOptional);

        if (bgraphMapType1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( graphMapType1.getRendering() + " " );
        simplemapping.setGraphMapType(graphMapType1.getGraphMapType());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
