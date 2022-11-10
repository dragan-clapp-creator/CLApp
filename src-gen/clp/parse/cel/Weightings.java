package clp.parse.cel;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.Weighting;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Weightings extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.Weightings weightings = new clp.run.cel.Weightings();

  public clp.run.cel.Weightings getWeightings() {
    return weightings;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      Weighting weighting0 = new Weighting();
        Boolean bweighting0 = weighting0.parse(parser, isOptional);

        if (bweighting0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( weighting0.getRendering() + " " );
        weightings.setWeighting(weighting0.getWeighting());

      isOk = starBlock1(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock1(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    Weighting weighting = new Weighting();
    isOk = weighting.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    weightings.addWeighting(weighting.getWeighting());
    buffer.append( weighting.getRendering()+" " );


    return isOk;
  }


}
