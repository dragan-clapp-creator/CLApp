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
public class ArrayIVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayIVariable arrayivariable = new clp.run.res.ArrayIVariable();

  public clp.run.res.ArrayIVariable getArrayIVariable() {
    return arrayivariable;
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
        arrayivariable.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arrayivariable.setName(parser.sval);
    
    buffer.append( arrayivariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      ArrayIInit arrayIInit3 = new ArrayIInit();
        Boolean barrayIInit3 = arrayIInit3.parse(parser, isOptional);

        if (barrayIInit3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( arrayIInit3.getRendering() + " " );
        arrayivariable.setArrayIInit(arrayIInit3.getArrayIInit());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
