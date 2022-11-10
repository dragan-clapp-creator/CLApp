package clp.parse.cel.graph;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GParseParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.GParser gparser = new clp.run.cel.graph.GParser();

  public clp.run.cel.graph.GParser getGParser() {
    return gparser;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("parse", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("parse ");
    

      ParseObject parseObject1 = new ParseObject();
        Boolean bparseObject1 = parseObject1.parse(parser, isOptional);

        if (bparseObject1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( parseObject1.getRendering() + " " );
        gparser.setParseObject(parseObject1.getParseObject());

    token = parser.nextToken();
    isOk = match("using", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("using ");
    

      GraphRef graphRef3 = new GraphRef();
        Boolean bgraphRef3 = graphRef3.parse(parser, isOptional);

        if (bgraphRef3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( graphRef3.getRendering() + " " );
        gparser.setGraphRef(graphRef3.getGraphRef());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
