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
public class ArrayIVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayIVar arrayivar = new clp.run.res.ArrayIVar();

  public clp.run.res.ArrayIVar getArrayIVar() {
    return arrayivar;
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
        arrayivar.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arrayivar.setName(parser.sval);
    
    buffer.append( arrayivar.getName() + " " );

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
    arrayivar.setChar((char)token);

      ArrayIInit arrayIInit1 = new ArrayIInit();
      isOk = arrayIInit1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      arrayivar.setArrayIInit(arrayIInit1.getArrayIInit());
      arrayivar.setIsArrayIInit(true);
      buffer.append( arrayIInit1.getRendering()+" " );


    return true;
  }


}
