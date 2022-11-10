package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnPropBody extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnPropBody scnpropbody = new clp.run.scn.ScnPropBody();

  public clp.run.scn.ScnPropBody getScnPropBody() {
    return scnpropbody;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      isOk = ampersAndBlock0(parser, isOptional);
      if (isOk != Boolean.TRUE) {
        return parser.errorLog(true);
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


    private boolean ampersAndBlock0(CLAppParser parser, boolean isOptional) throws IOException {
      int token = 0;
      Boolean b0 = null;
      Boolean b1 = null;
      Boolean b2 = null;

   
      try {
        boolean found = true;

        while (found) {
        AParser scnLogic = new ScnLogic();
        AParser scnTasks = new ScnTasks();
        AParser scnQueues = new ScnQueues();

          found = false;
          if (scnLogic != null) {
            b0 = scnLogic.parse(parser, isOptional);
            if (b0 == Boolean.TRUE) {
              scnpropbody.setScnLogic( ((ScnLogic) scnLogic).getScnLogic() );
              buffer.append( ((ScnLogic) scnLogic).getRendering()+" " );
              scnLogic = null;
              found = true;
            }
          }
          if (scnTasks != null) {
            b1 = scnTasks.parse(parser, isOptional);
            if (b1 == Boolean.TRUE) {
              scnpropbody.setScnTasks( ((ScnTasks) scnTasks).getScnTasks() );
              buffer.append( ((ScnTasks) scnTasks).getRendering()+" " );
              scnTasks = null;
              found = true;
            }
          }
          if (scnQueues != null) {
            b2 = scnQueues.parse(parser, isOptional);
            if (b2 == Boolean.TRUE) {
              scnpropbody.setScnQueues( ((ScnQueues) scnQueues).getScnQueues() );
              buffer.append( ((ScnQueues) scnQueues).getRendering()+" " );
              scnQueues = null;
              found = true;
            }
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
         && b2 != Boolean.FALSE

      ; 
    }


}
