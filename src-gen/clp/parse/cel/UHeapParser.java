package clp.parse.cel;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UHeapParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.Heap heap = new clp.run.cel.Heap();

  public clp.run.cel.Heap getHeap() {
    return heap;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("heap", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("heap ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    heap.setName(parser.sval);
    
    buffer.append( heap.getName() + " " );

    token = parser.nextToken();
    isOk = match("assignTo", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("assignTo ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    heap.setAct(parser.sval);
    
    buffer.append( heap.getAct() + " " );

      isOk = hutBlock4(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match("loadOn", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("loadOn ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    heap.setLoad(parser.sval);
    
    buffer.append( heap.getLoad() + " " );

      isOk = hutBlock7(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      isOk = starBlock9(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
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
    isOk = match("usedResources", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("usedResources ");
    heap.setIsUsedResources(true);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      heap.setRes(parser.sval);
      heap.setIsRes(true);
      buffer.append( heap.getRes() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }

  private Boolean hutBlock7(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('[', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "[ " );
    heap.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER || token == '\"') {
      heap.setActivity((int) parser.nval);
      heap.setIsActivity(true);
      buffer.append( heap.getActivity() + " " );
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    isOk = match(']', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "] " );
    heap.setChar((char)token);


    return true;
  }

  private Boolean starBlock9(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock9(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock9(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    Cell cell = new Cell();
    isOk = cell.parse(parser, true);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      heap.addCell(cell.getCell());
      buffer.append( cell.getRendering()+" " );
    }


    return isOk;
  }


}
