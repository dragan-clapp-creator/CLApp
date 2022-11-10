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
public class CellMarks extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.CellMarks cellmarks = new clp.run.res.CellMarks();

  public clp.run.res.CellMarks getCellMarks() {
    return cellmarks;
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
    cellmarks.setCellName(parser.sval);
    
    buffer.append( cellmarks.getCellName() + " " );

      CellMarkCheck cellMarkCheck1 = new CellMarkCheck();
        Boolean bcellMarkCheck1 = cellMarkCheck1.parse(parser, isOptional);

        if (bcellMarkCheck1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( cellMarkCheck1.getRendering() + " " );
        cellmarks.setCellMarkCheck(cellMarkCheck1.getCellMarkCheck());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
