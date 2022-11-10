package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ABSTRACT extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.Processor processor = new clp.run.cel.graph.map.Processor();

  public clp.run.cel.graph.map.Processor getProcessor() {
    return processor;
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

    CoreProcessor bCoreProcessor = new CoreProcessor();
    isOk = bCoreProcessor.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      processor.setCoreProcessor(bCoreProcessor.getCoreProcessor());
      buffer.append( bCoreProcessor.getRendering()+" " );
      return true;
    }

    JavaProcessor bJavaProcessor = new JavaProcessor();
    isOk = bJavaProcessor.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      processor.setJavaProcessor(bJavaProcessor.getJavaProcessor());
      buffer.append( bJavaProcessor.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
