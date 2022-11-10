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
public class ArrayTVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ArrayTVar arraytvar = new clp.run.res.ArrayTVar();

  public clp.run.res.ArrayTVar getArrayTVar() {
    return arraytvar;
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
        arraytvar.setArray(array0.getArray());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    arraytvar.setName(parser.sval);
    
    buffer.append( arraytvar.getName() + " " );

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
    arraytvar.setChar((char)token);

      TInitList tInitList1 = new TInitList();
      isOk = tInitList1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      arraytvar.setTInitList(tInitList1.getTInitList());
      arraytvar.setIsTInitList(true);
      buffer.append( tInitList1.getRendering()+" " );


    return true;
  }


}
