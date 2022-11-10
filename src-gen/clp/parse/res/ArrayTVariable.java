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
public class ArrayTVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayTVariable arraytvariable = new clp.run.res.ArrayTVariable();

  public clp.run.res.ArrayTVariable getArrayTVariable() {
    return arraytvariable;
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
        arraytvariable.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraytvariable.setName(parser.sval);
    
    buffer.append( arraytvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      TInitList tInitList3 = new TInitList();
        Boolean btInitList3 = tInitList3.parse(parser, isOptional);

        if (btInitList3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( tInitList3.getRendering() + " " );
        arraytvariable.setTInitList(tInitList3.getTInitList());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
