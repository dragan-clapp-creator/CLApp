package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MetaScenario extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.MetaScenario metascenario = new clp.run.msc.MetaScenario();

  public clp.run.msc.MetaScenario getMetaScenario() {
    return metascenario;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("metaScenario", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("metaScenario ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    metascenario.setName(parser.sval);
    
    buffer.append( metascenario.getName() + " " );

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      MscBodyParser metaScenarioBody3 = new MscBodyParser();
        Boolean bmetaScenarioBody3 = metaScenarioBody3.parse(parser, isOptional);

        if (bmetaScenarioBody3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( metaScenarioBody3.getRendering() + " " );
        metascenario.setMetaScenarioBody(metaScenarioBody3.getMetaScenarioBody());

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
