package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiTable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiTable uitable = new clp.run.res.ui.UiTable();

  public clp.run.res.ui.UiTable getUiTable() {
    return uitable;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("TABLE", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("TABLE ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    uitable.setName(parser.sval);
    
    buffer.append( uitable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      VarIdentifier varIdentifier3 = new VarIdentifier();
        Boolean bvarIdentifier3 = varIdentifier3.parse(parser, isOptional);

        if (bvarIdentifier3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( varIdentifier3.getRendering() + " " );
        uitable.setVarIdentifier(varIdentifier3.getVarIdentifier());

      isOk = hutBlock4(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("SEL", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("SEL ");
    uitable.setIsSEL(true);

      SelType selType1 = new SelType();
      isOk = selType1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      uitable.setSelType(selType1.getSelType());
      uitable.setIsSelType(true);
      buffer.append( selType1.getRendering()+" " );


    return true;
  }


}
