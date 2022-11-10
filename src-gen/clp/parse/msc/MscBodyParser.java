package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.ResParser;
import clp.parse.scn.ScnParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MscBodyParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.MetaScenarioBody metascenariobody = new clp.run.msc.MetaScenarioBody();

  public clp.run.msc.MetaScenarioBody getMetaScenarioBody() {
    return metascenariobody;
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

      MscTasksParser mscTasks2 = new MscTasksParser();
        Boolean bmscTasks2 = mscTasks2.parse(parser, isOptional);

        if (bmscTasks2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( mscTasks2.getRendering() + " " );
        metascenariobody.setMscTasks(mscTasks2.getMscTasks());

      Port port3 = new Port();
      isOk = port3.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        metascenariobody.setPort(port3.getPort());
        metascenariobody.setIsPort(true);
        buffer.append( port3.getRendering()+" " );
      }
      isOk = true;

      MscOutput mscOutput4 = new MscOutput();
      isOk = mscOutput4.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        metascenariobody.setMscOutput(mscOutput4.getMscOutput());
        metascenariobody.setIsMscOutput(true);
        buffer.append( mscOutput4.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );

      isOk = ampersAndBlock6(parser, isOptional);
      if (isOk != Boolean.TRUE) {
        return parser.errorLog(true);
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


    private boolean ampersAndBlock6(CLAppParser parser, boolean isOptional) throws IOException {
      int token = 0;
      Boolean b0 = null;
      Boolean b1 = null;

   
      try {
        boolean found = true;

        while (found) {
        AParser scenario = new ScnParser();
        AParser resources = new ResParser();

          found = false;
          b0 = scenario.parse(parser, true);
          if (b0 == Boolean.TRUE) {
            metascenariobody.addScenario( ((ScnParser) scenario).getScenario() );
            buffer.append( ((ScnParser) scenario).getRendering()+" " );
            found = true;
          }
          else if (b0 == Boolean.FALSE) {
            b0 = null;
            parser.pushBack();
          }
          b1 = resources.parse(parser, true);
          if (b1 == Boolean.TRUE) {
            metascenariobody.addResources( ((ResParser) resources).getResources() );
            buffer.append( ((ResParser) resources).getRendering()+" " );
            found = true;
          }
          else if (b1 == Boolean.FALSE) {
            b1 = null;
            parser.pushBack();
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

      ; 
    }


}
