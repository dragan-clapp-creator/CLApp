package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VarIdentifier extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.VarIdentifier varidentifier = new clp.run.res.ui.VarIdentifier();

  public clp.run.res.ui.VarIdentifier getVarIdentifier() {
    return varidentifier;
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
    varidentifier.setVar(parser.sval);
    
    buffer.append( varidentifier.getVar() + " " );

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
    isOk = match('[', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "[ " );
    varidentifier.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER || token == '\"') {
      varidentifier.setIndex((int) parser.nval);
      varidentifier.setIsIndex(true);
      buffer.append( varidentifier.getIndex() + " " );
    }
    else {
      parser.pushBack();
    }
      isOk = hutBlock2(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match(']', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "] " );
    varidentifier.setChar((char)token);


    return true;
  }

  private Boolean hutBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );
    varidentifier.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER || token == '\"') {
      varidentifier.setIndex2((int) parser.nval);
      varidentifier.setIsIndex2(true);
      buffer.append( varidentifier.getIndex2() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
