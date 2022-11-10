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
public class ArraySVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArraySVariable arraysvariable = new clp.run.res.ArraySVariable();

  public clp.run.res.ArraySVariable getArraySVariable() {
    return arraysvariable;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      Array array0 = new Array();
        Boolean barray0 = array0.parse(parser, isOptional);

        if (barray0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( array0.getRendering() + " " );
        arraysvariable.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraysvariable.setName(parser.sval);
    
    buffer.append( arraysvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      SInitList sInitList3 = new SInitList();
        Boolean bsInitList3 = sInitList3.parse(parser, isOptional);

        if (bsInitList3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( sInitList3.getRendering() + " " );
        arraysvariable.setSInitList(sInitList3.getSInitList());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
