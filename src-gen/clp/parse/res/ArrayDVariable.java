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
public class ArrayDVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayDVariable arraydvariable = new clp.run.res.ArrayDVariable();

  public clp.run.res.ArrayDVariable getArrayDVariable() {
    return arraydvariable;
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
        arraydvariable.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraydvariable.setName(parser.sval);
    
    buffer.append( arraydvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      DInitList dInitList3 = new DInitList();
        Boolean bdInitList3 = dInitList3.parse(parser, isOptional);

        if (bdInitList3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( dInitList3.getRendering() + " " );
        arraydvariable.setDInitList(dInitList3.getDInitList());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
