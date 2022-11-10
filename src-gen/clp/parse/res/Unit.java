package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Unit extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Unit unit;

  public clp.run.res.Unit getUnit() {
    return unit;
  }

  public void setUnit(clp.run.res.Unit ref) {
    unit = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (unit == null) {
      clp.run.res.Unit[] values = clp.run.res.Unit.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.Unit unitResult = values[i];
        if (match(unitResult.getVal(), parser.sval)) {
          unit = unitResult;
          buffer.append( unit.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(unit.getVal(), parser.sval)) {
        buffer.append( unit.getVal() + " " );
        return true;
      }
    }
    parser.pushBack();
    isOk = null;



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
