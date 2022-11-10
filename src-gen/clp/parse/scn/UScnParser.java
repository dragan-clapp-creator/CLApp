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
public class UScnParser extends AParser {

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
    isOk = match("assignTo", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("assignTo ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    scenario.setMsc(parser.sval);
    
    buffer.append( scenario.getMsc() + " " );

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      ScenarioBody scenarioBody5 = new ScenarioBody();
        Boolean bscenarioBody5 = scenarioBody5.parse(parser, isOptional);

        if (bscenarioBody5 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( scenarioBody5.getRendering() + " " );
        scenario.setScenarioBody(scenarioBody5.getScenarioBody());

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
