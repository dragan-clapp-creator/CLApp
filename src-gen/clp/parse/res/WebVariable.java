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
public class WebVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.WebVariable webvariable = new clp.run.res.WebVariable();

  public clp.run.res.WebVariable getWebVariable() {
    return webvariable;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TWEB;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TWEB0 = new VarType();
      TWEB0.setVarType(refVarType);
        Boolean bTWEB0 = TWEB0.parse(parser, isOptional);

        if (bTWEB0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TWEB0.getRendering() + " " );
        webvariable.setTWEB(TWEB0.getVarType());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    webvariable.setName(parser.sval);
    
    buffer.append( webvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      Encryption encryption3 = new Encryption();
      isOk = encryption3.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        webvariable.setEncryption(encryption3.getEncryption());
        webvariable.setIsEncryption(true);
        buffer.append( encryption3.getRendering()+" " );
      }
      isOk = true;

      Address address4 = new Address();
      isOk = address4.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        webvariable.setAddress(address4.getAddress());
        webvariable.setIsAddress(true);
        buffer.append( address4.getRendering()+" " );
      }
      isOk = true;

      Port port5 = new Port();
        Boolean bport5 = port5.parse(parser, isOptional);

        if (bport5 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( port5.getRendering() + " " );
        webvariable.setPort(port5.getPort());

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
