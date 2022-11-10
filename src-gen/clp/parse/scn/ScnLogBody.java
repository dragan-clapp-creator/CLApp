package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnLogBody extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnLogBody scnlogbody = new clp.run.scn.ScnLogBody();

  public clp.run.scn.ScnLogBody getScnLogBody() {
    return scnlogbody;
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
        AParser scnDeact = new ScnDeact();
        AParser scnLevel = new ScnLevel();
        AParser scnLtype = new ScnLtype();

          found = false;
          if (scnDeact != null) {
            b0 = scnDeact.parse(parser, isOptional);
            if (b0 == Boolean.TRUE) {
              scnlogbody.setScnDeact( ((ScnDeact) scnDeact).getScnDeact() );
              buffer.append( ((ScnDeact) scnDeact).getRendering()+" " );
              scnDeact = null;
              found = true;
            }
          }
          if (scnLevel != null) {
            b1 = scnLevel.parse(parser, isOptional);
            if (b1 == Boolean.TRUE) {
              scnlogbody.setScnLevel( ((ScnLevel) scnLevel).getScnLevel() );
              buffer.append( ((ScnLevel) scnLevel).getRendering()+" " );
              scnLevel = null;
              found = true;
            }
          }
          if (scnLtype != null) {
            b2 = scnLtype.parse(parser, true);
            if (b2 == Boolean.FALSE) {
              return false;
            }
            if (b2 == Boolean.TRUE) {
              scnlogbody.setScnLtype( ((ScnLtype) scnLtype).getScnLtype() );
              scnlogbody.setIsScnLtype( true );
              buffer.append( ((ScnLtype) scnLtype).getRendering()+" " );
              scnLtype = null;
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
