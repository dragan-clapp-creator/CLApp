package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnTaskName extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnTaskName scntaskname;

  public clp.run.scn.ScnTaskName getScnTaskName() {
    return scntaskname;
  }

  public void setScnTaskName(clp.run.scn.ScnTaskName ref) {
    scntaskname = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (scntaskname == null) {
      clp.run.scn.ScnTaskName[] values = clp.run.scn.ScnTaskName.values();
      for (int i=0; i<values.length; i++) {
        clp.run.scn.ScnTaskName scntasknameResult = values[i];
        if (match(scntasknameResult.getVal(), parser.sval)) {
          scntaskname = scntasknameResult;
          buffer.append( scntaskname.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(scntaskname.getVal(), parser.sval)) {
        buffer.append( scntaskname.getVal() + " " );
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
