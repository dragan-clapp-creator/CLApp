package clp.parse.cel;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.cel.dom.Adomain;
import clp.parse.cel.dom.Ddomain;
import clp.parse.cel.dom.Xdomain;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Cell extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.Cell cell = new clp.run.cel.Cell();

  public clp.run.cel.Cell getCell() {
    return cell;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("cell", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("cell ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    cell.setName(parser.sval);
    
    buffer.append( cell.getName() + " " );

      isOk = hutBlock4(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      isOk = hutBlock5(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      isOk = ampersAndBlock7(parser, isOptional);
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

  private Boolean hutBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("usedHeap", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("usedHeap ");
    cell.setIsUsedHeap(true);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      cell.setHeapName(parser.sval);
      cell.setIsHeapName(true);
      buffer.append( cell.getHeapName() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }

  private Boolean hutBlock5(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER && token != '\"') { return parser.errorLog(true); }
    cell.setActivity((int) parser.nval);
    cell.setIsActivity(true);
    buffer.append( cell.getActivity() + " " );


    return true;
  }


    private boolean ampersAndBlock7(CLAppParser parser, boolean isOptional) throws IOException {
      int token = 0;
      Boolean b0 = null;
      Boolean b1 = null;
      Boolean b2 = null;

   
      try {
        boolean found = true;

        while (found) {
        AParser adomain = new Adomain();
        AParser ddomain = new Ddomain();
        AParser xdomain = new Xdomain();

          found = false;
          if (adomain != null) {
            b0 = adomain.parse(parser, true);
            if (b0 == Boolean.FALSE) {
              return false;
            }
            if (b0 == Boolean.TRUE) {
              cell.setAdomain( ((Adomain) adomain).getAdomain() );
              cell.setIsAdomain( true );
              buffer.append( ((Adomain) adomain).getRendering()+" " );
              adomain = null;
              found = true;
            }
          }
          if (ddomain != null) {
            b1 = ddomain.parse(parser, true);
            if (b1 == Boolean.FALSE) {
              return false;
            }
            if (b1 == Boolean.TRUE) {
              cell.setDdomain( ((Ddomain) ddomain).getDdomain() );
              cell.setIsDdomain( true );
              buffer.append( ((Ddomain) ddomain).getRendering()+" " );
              ddomain = null;
              found = true;
            }
          }
          if (xdomain != null) {
            b2 = xdomain.parse(parser, true);
            if (b2 == Boolean.FALSE) {
              return false;
            }
            if (b2 == Boolean.TRUE) {
              cell.setXdomain( ((Xdomain) xdomain).getXdomain() );
              cell.setIsXdomain( true );
              buffer.append( ((Xdomain) xdomain).getRendering()+" " );
              xdomain = null;
              found = true;
            }
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

      ; 
    }


}
