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
public class CompoundMapping extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.CompoundMapping compoundmapping = new clp.run.cel.graph.map.CompoundMapping();

  public clp.run.cel.graph.map.CompoundMapping getCompoundMapping() {
    return compoundmapping;
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
    compoundmapping.setNode(parser.sval);
    
    buffer.append( compoundmapping.getNode() + " " );

      SimpleMapping simpleMapping1 = new SimpleMapping();
        Boolean bsimpleMapping1 = simpleMapping1.parse(parser, isOptional);

        if (bsimpleMapping1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( simpleMapping1.getRendering() + " " );
        compoundmapping.setSimpleMapping(simpleMapping1.getSimpleMapping());

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
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );
    compoundmapping.setChar((char)token);

      CompoundMapping compoundMapping1 = new CompoundMapping();
      isOk = compoundMapping1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      compoundmapping.setCompoundMapping(compoundMapping1.getCompoundMapping());
      compoundmapping.setIsCompoundMapping(true);
      buffer.append( compoundMapping1.getRendering()+" " );


    return true;
  }


}
