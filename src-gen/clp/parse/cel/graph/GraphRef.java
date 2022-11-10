package clp.parse.cel.graph;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.graph.Graph;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GraphRef extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.GraphRef graphref = new clp.run.cel.graph.GraphRef();

  public clp.run.cel.graph.GraphRef getGraphRef() {
    return graphref;
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
    graphref.setGname(parser.sval);
    
    buffer.append( graphref.getGname() + " " );

      isOk = hutBlock1(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ": " );
    graphref.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      graphref.setNode(parser.sval);
      graphref.setIsNode(true);
      buffer.append( graphref.getNode() + " " );
    }
    else {
      parser.pushBack();
    }
      isOk = hutBlock2(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }


    return true;
  }

  private Boolean hutBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('(', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "( " );
    graphref.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER || token == '\"') {
      graphref.setElement((int) parser.nval);
      graphref.setIsElement(true);
      buffer.append( graphref.getElement() + " " );
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    isOk = match(')', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ") " );
    graphref.setChar((char)token);


    return true;
  }


}
