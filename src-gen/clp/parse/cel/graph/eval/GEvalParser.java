package clp.parse.cel.graph.eval;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.cel.graph.GraphRef;
import clp.parse.cel.graph.map.ABSTRACT;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GEvalParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.eval.GEvaluator gevaluator = new clp.run.cel.graph.eval.GEvaluator();

  public clp.run.cel.graph.eval.GEvaluator getGEvaluator() {
    return gevaluator;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("evaluate", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("evaluate ");
    

      GraphRef graphRef1 = new GraphRef();
        Boolean bgraphRef1 = graphRef1.parse(parser, isOptional);

        if (bgraphRef1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( graphRef1.getRendering() + " " );
        gevaluator.setGraphRef(graphRef1.getGraphRef());

      isOk = hutBlock2(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("using", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("using ");
    gevaluator.setIsUsing(true);

      ABSTRACT processor1 = new ABSTRACT();
      isOk = processor1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      gevaluator.setProcessor(processor1.getProcessor());
      gevaluator.setIsProcessor(true);
      buffer.append( processor1.getRendering()+" " );


    return true;
  }


}
