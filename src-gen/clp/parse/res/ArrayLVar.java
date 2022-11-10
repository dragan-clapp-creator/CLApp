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
public class ArrayLVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayLVar arraylvar = new clp.run.res.ArrayLVar();

  public clp.run.res.ArrayLVar getArrayLVar() {
    return arraylvar;
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
        arraylvar.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraylvar.setName(parser.sval);
    
    buffer.append( arraylvar.getName() + " " );

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
    arraylvar.setChar((char)token);

      ArrayLInit arrayLInit1 = new ArrayLInit();
      isOk = arrayLInit1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      arraylvar.setArrayLInit(arrayLInit1.getArrayLInit());
      arraylvar.setIsArrayLInit(true);
      buffer.append( arrayLInit1.getRendering()+" " );


    return true;
  }


}
