package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Mapping extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.Mapping mapping;

  public clp.run.cel.graph.map.Mapping getMapping() {
    return mapping;
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

    SimpleMapping bSimpleMapping = new SimpleMapping();
    isOk = bSimpleMapping.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      mapping = bSimpleMapping.getSimpleMapping();
      buffer.append( bSimpleMapping.getRendering()+" " );
      return true;
    }

    CompoundMapping bCompoundMapping = new CompoundMapping();
    isOk = bCompoundMapping.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      mapping = bCompoundMapping.getCompoundMapping();
      buffer.append( bCompoundMapping.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
