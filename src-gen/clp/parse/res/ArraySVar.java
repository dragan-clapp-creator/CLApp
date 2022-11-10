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
public class ArraySVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArraySVar arraysvar = new clp.run.res.ArraySVar();

  public clp.run.res.ArraySVar getArraySVar() {
    return arraysvar;
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
        arraysvar.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraysvar.setName(parser.sval);
    
    buffer.append( arraysvar.getName() + " " );

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
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "= " );
    arraysvar.setChar((char)token);

      SInitList sInitList1 = new SInitList();
      isOk = sInitList1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      arraysvar.setSInitList(sInitList1.getSInitList());
      arraysvar.setIsSInitList(true);
      buffer.append( sInitList1.getRendering()+" " );


    return true;
  }


}
