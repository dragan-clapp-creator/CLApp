package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ClassReference extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.ClassReference classreference = new clp.run.msc.ClassReference();

  public clp.run.msc.ClassReference getClassReference() {
    return classreference;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      isOk = hutBlock0(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ": " );

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(false);
    }
    isOk = true;
    classreference.setClazz(parser.sval);
    
    buffer.append( classreference.getClazz() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock0(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD && token != '\"') { return parser.errorLog(true); }
    classreference.setPack(parser.sval);
    classreference.setIsPack(true);
    buffer.append( classreference.getPack() + " " );


    return true;
  }


}
