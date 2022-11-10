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
public class ArrayLVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayLVariable arraylvariable = new clp.run.res.ArrayLVariable();

  public clp.run.res.ArrayLVariable getArrayLVariable() {
    return arraylvariable;
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
        arraylvariable.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraylvariable.setName(parser.sval);
    
    buffer.append( arraylvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      ArrayLInit arrayLInit3 = new ArrayLInit();
        Boolean barrayLInit3 = arrayLInit3.parse(parser, isOptional);

        if (barrayLInit3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( arrayLInit3.getRendering() + " " );
        arraylvariable.setArrayLInit(arrayLInit3.getArrayLInit());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
