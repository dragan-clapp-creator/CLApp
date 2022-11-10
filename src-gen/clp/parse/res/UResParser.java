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
public class UResParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Resources resources = new clp.run.res.Resources();

  public clp.run.res.Resources getResources() {
    return resources;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("resources", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("resources ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    resources.setName(parser.sval);
    
    buffer.append( resources.getName() + " " );

    token = parser.nextToken();
    isOk = match("assignTo", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("assignTo ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    resources.setMsc(parser.sval);
    
    buffer.append( resources.getMsc() + " " );

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      isOk = ampersAndBlock5(parser, isOptional);
      if (isOk != Boolean.TRUE) {
        return parser.errorLog(true);
      }

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


    private boolean ampersAndBlock5(CLAppParser parser, boolean isOptional) throws IOException {
      int token = 0;
      Boolean b0 = null;
      Boolean b1 = null;
      Boolean b2 = null;
      Boolean b3 = null;

   
      try {
        boolean found = true;

        while (found) {
        AParser usedLib = new UsedLib();
        AParser events = new Events();
        AParser marks = new Marks();
        AParser variable = new Variable();

          found = false;
          if (usedLib != null) {
            b0 = usedLib.parse(parser, true);
            if (b0 == Boolean.FALSE) {
              return false;
            }
            if (b0 == Boolean.TRUE) {
              resources.setUsedLib( ((UsedLib) usedLib).getUsedLib() );
              resources.setIsUsedLib( true );
              buffer.append( ((UsedLib) usedLib).getRendering()+" " );
              usedLib = null;
              found = true;
            }
          }
          if (events != null) {
            b1 = events.parse(parser, true);
            if (b1 == Boolean.FALSE) {
              return false;
            }
            if (b1 == Boolean.TRUE) {
              resources.setEvents( ((Events) events).getEvents() );
              resources.setIsEvents( true );
              buffer.append( ((Events) events).getRendering()+" " );
              events = null;
              found = true;
            }
          }
          if (marks != null) {
            b2 = marks.parse(parser, true);
            if (b2 == Boolean.FALSE) {
              return false;
            }
            if (b2 == Boolean.TRUE) {
              resources.setMarks( ((Marks) marks).getMarks() );
              resources.setIsMarks( true );
              buffer.append( ((Marks) marks).getRendering()+" " );
              marks = null;
              found = true;
            }
          }
          b3 = variable.parse(parser, true);
          if (b3 == Boolean.TRUE) {
            resources.addVariable( ((Variable) variable).getVariable() );
            buffer.append( ((Variable) variable).getRendering()+" " );
            found = true;
          }
          else if (b3 == Boolean.FALSE) {
            b3 = null;
            parser.pushBack();
          }

  
        }
      }
      catch (NullPointerException e) {
        return false;
      }
      catch (RuntimeException e) {
        return false;
      }
      return
            b0 != Boolean.FALSE
         && b1 != Boolean.FALSE
         && b2 != Boolean.FALSE
         && b3 != Boolean.FALSE

      ; 
    }


}
