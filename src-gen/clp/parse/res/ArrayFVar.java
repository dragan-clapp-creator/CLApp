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
public class ArrayFVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayFVar arrayfvar = new clp.run.res.ArrayFVar();

  public clp.run.res.ArrayFVar getArrayFVar() {
    return arrayfvar;
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
        arrayfvar.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arrayfvar.setName(parser.sval);
    
    buffer.append( arrayfvar.getName() + " " );

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
    arrayfvar.setChar((char)token);

      ArrayFInit arrayFInit1 = new ArrayFInit();
      isOk = arrayFInit1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      arrayfvar.setArrayFInit(arrayFInit1.getArrayFInit());
      arrayfvar.setIsArrayFInit(true);
      buffer.append( arrayFInit1.getRendering()+" " );


    return true;
  }


}
