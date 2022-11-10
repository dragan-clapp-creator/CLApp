package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.act.ActParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScenarioBody extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScenarioBody scenariobody = new clp.run.scn.ScenarioBody();

  public clp.run.scn.ScenarioBody getScenarioBody() {
    return scenariobody;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("properties", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("properties ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      ScnPropBody scnPropBody2 = new ScnPropBody();
        Boolean bscnPropBody2 = scnPropBody2.parse(parser, isOptional);

        if (bscnPropBody2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( scnPropBody2.getRendering() + " " );
        scenariobody.setScnPropBody(scnPropBody2.getScnPropBody());

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );

      isOk = starBlock4(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock4(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    ActParser actor = new ActParser();
    isOk = actor.parse(parser, true);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      scenariobody.addActor(actor.getActor());
      buffer.append( actor.getRendering()+" " );
    }


    return isOk;
  }


}
