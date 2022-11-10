package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleFVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleFVariable simplefvariable = new clp.run.res.SimpleFVariable();

  public clp.run.res.SimpleFVariable getSimpleFVariable() {
    return simplefvariable;
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
    simplefvariable.setName(parser.sval);
    
    buffer.append( simplefvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      Fsigned fsigned2 = new Fsigned();
        Boolean bfsigned2 = fsigned2.parse(parser, isOptional);

        if (bfsigned2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( fsigned2.getRendering() + " " );
        simplefvariable.setFsigned(fsigned2.getFsigned());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
