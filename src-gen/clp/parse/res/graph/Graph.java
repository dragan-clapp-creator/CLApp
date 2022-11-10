package clp.parse.res.graph;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.VarType;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Graph extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.graph.Graph graph = new clp.run.res.graph.Graph();

  public clp.run.res.graph.Graph getGraph() {
    return graph;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TGRAPH;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TGRAPH0 = new VarType();
      TGRAPH0.setVarType(refVarType);
        Boolean bTGRAPH0 = TGRAPH0.parse(parser, isOptional);

        if (bTGRAPH0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TGRAPH0.getRendering() + " " );
        graph.setTGRAPH(TGRAPH0.getVarType());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    graph.setGraphName(parser.sval);
    
    buffer.append( graph.getGraphName() + " " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    graph.setGraphSentences(parser.collectGraphSentences());
    
    buffer.append( graph.getGraphSentences() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
