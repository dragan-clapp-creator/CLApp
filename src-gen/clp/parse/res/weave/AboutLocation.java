package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class AboutLocation extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.AboutLocation aboutlocation = new clp.run.res.weave.AboutLocation();

  public clp.run.res.weave.AboutLocation getAboutLocation() {
    return aboutlocation;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      Position position0 = new Position();
        Boolean bposition0 = position0.parse(parser, isOptional);

        if (bposition0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( position0.getRendering() + " " );
        aboutlocation.setPosition(position0.getPosition());

      KindOf kind1 = new KindOf();
        Boolean bkind1 = kind1.parse(parser, isOptional);

        if (bkind1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( kind1.getRendering() + " " );
        aboutlocation.setKind(kind1.getKindOf());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    aboutlocation.setName(parser.sval);
    
    buffer.append( aboutlocation.getName() + " " );

      isOk = hutBlock3(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      isOk = hutBlock4(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      isOk = hutBlock5(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ": " );
    aboutlocation.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      aboutlocation.setType(parser.sval);
      aboutlocation.setIsType(true);
      buffer.append( aboutlocation.getType() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }

  private Boolean hutBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('(', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "( " );
    aboutlocation.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER || token == '\"') {
      aboutlocation.setIndex((int) parser.nval);
      aboutlocation.setIsIndex(true);
      buffer.append( aboutlocation.getIndex() + " " );
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    isOk = match(')', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ") " );
    aboutlocation.setChar((char)token);


    return true;
  }

  private Boolean hutBlock5(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER && token != '\"') { return parser.errorLog(true); }
    aboutlocation.setOccurNumber((int) parser.nval);
    aboutlocation.setIsOccurNumber(true);
    buffer.append( aboutlocation.getOccurNumber() + " " );


    return true;
  }


}
