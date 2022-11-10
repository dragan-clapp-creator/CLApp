package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.cel.java.JavaCall;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class JavaProcessor extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.JavaProcessor javaprocessor = new clp.run.cel.graph.map.JavaProcessor();

  public clp.run.cel.graph.map.JavaProcessor getJavaProcessor() {
    return javaprocessor;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("JAVA", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("JAVA ");
    

      JavaCall javaCall1 = new JavaCall();
        Boolean bjavaCall1 = javaCall1.parse(parser, isOptional);

        if (bjavaCall1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( javaCall1.getRendering() + " " );
        javaprocessor.setJavaCall(javaCall1.getJavaCall());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
