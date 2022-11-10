package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnQueue extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnQueue scnqueue = new clp.run.scn.ScnQueue();

  public clp.run.scn.ScnQueue getScnQueue() {
    return scnqueue;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(true);
    }
    isOk = true;
    scnqueue.setName(parser.sval);
    
    buffer.append( scnqueue.getName() + " " );

    token = parser.nextToken();
    isOk = match("income", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("income ");
    

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      SortOrder sortOrder3 = new SortOrder();
        Boolean bsortOrder3 = sortOrder3.parse(parser, isOptional);

        if (bsortOrder3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( sortOrder3.getRendering() + " " );
        scnqueue.setSortOrder(sortOrder3.getSortOrder());

    token = parser.nextToken();
    isOk = match(';', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "; " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
