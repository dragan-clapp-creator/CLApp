package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CellMarkSet extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.CellMarkSet cellmarkset = new clp.run.res.CellMarkSet();

  public clp.run.res.CellMarkSet getCellMarkSet() {
    return cellmarkset;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "{ " );

      CellMarks cellMarks1 = new CellMarks();
        Boolean bcellMarks1 = cellMarks1.parse(parser, isOptional);

        if (bcellMarks1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( cellMarks1.getRendering() + " " );
        cellmarkset.setCellMarks(cellMarks1.getCellMarks());

      isOk = starBlock2(parser, isOptional);
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

  private Boolean starBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock2(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    CellMarks cellMarks = new CellMarks();
    isOk = cellMarks.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    cellmarkset.addCellMarks(cellMarks.getCellMarks());
    buffer.append( cellMarks.getRendering()+" " );


    return isOk;
  }


}
