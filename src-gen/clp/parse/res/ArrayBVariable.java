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
public class ArrayBVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayBVariable arraybvariable = new clp.run.res.ArrayBVariable();

  public clp.run.res.ArrayBVariable getArrayBVariable() {
    return arraybvariable;
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
        arraybvariable.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraybvariable.setName(parser.sval);
    
    buffer.append( arraybvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      ArrayBInit arrayBInit3 = new ArrayBInit();
        Boolean barrayBInit3 = arrayBInit3.parse(parser, isOptional);

        if (barrayBInit3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( arrayBInit3.getRendering() + " " );
        arraybvariable.setArrayBInit(arrayBInit3.getArrayBInit());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
