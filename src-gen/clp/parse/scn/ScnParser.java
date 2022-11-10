package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.msc.MetaScenario;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.Scenario scenario = new clp.run.scn.Scenario();

  public clp.run.scn.Scenario getScenario() {
    return scenario;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("scenario", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("scenario ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    scenario.setName(parser.sval);
    
    buffer.append( scenario.getName() + " " );

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      ScenarioBody scenarioBody4 = new ScenarioBody();
        Boolean bscenarioBody4 = scenarioBody4.parse(parser, isOptional);

        if (bscenarioBody4 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( scenarioBody4.getRendering() + " " );
        scenario.setScenarioBody(scenarioBody4.getScenarioBody());

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
