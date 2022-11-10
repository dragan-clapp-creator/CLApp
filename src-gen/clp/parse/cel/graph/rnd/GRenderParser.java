package clp.parse.cel.graph.rnd;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.cel.graph.GraphRef;
import clp.parse.msc.OutputTarget;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GRenderParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.rnd.GRenderer grenderer = new clp.run.cel.graph.rnd.GRenderer();

  public clp.run.cel.graph.rnd.GRenderer getGRenderer() {
    return grenderer;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("render", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("render ");
    

      GraphRef graphRef1 = new GraphRef();
        Boolean bgraphRef1 = graphRef1.parse(parser, isOptional);

        if (bgraphRef1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( graphRef1.getRendering() + " " );
        grenderer.setGraphRef(graphRef1.getGraphRef());

    token = parser.nextToken();
    isOk = match("to", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("to ");
    

      OutputTarget outputTarget3 = new OutputTarget();
        Boolean boutputTarget3 = outputTarget3.parse(parser, isOptional);

        if (boutputTarget3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( outputTarget3.getRendering() + " " );
        grenderer.setOutputTarget(outputTarget3.getOutputTarget());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
