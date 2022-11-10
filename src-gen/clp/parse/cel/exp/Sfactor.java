package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Sfactor extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.Sfactor sfactor;

  public clp.run.cel.exp.Sfactor getSfactor() {
    return sfactor;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    S_dval bS_dval = new S_dval();
    isOk = bS_dval.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sfactor = bS_dval.getS_dval();
      buffer.append( bS_dval.getRendering()+" " );
      return true;
    }

    S_tval bS_tval = new S_tval();
    isOk = bS_tval.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sfactor = bS_tval.getS_tval();
      buffer.append( bS_tval.getRendering()+" " );
      return true;
    }

    S_ival bS_ival = new S_ival();
    isOk = bS_ival.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sfactor = bS_ival.getS_ival();
      buffer.append( bS_ival.getRendering()+" " );
      return true;
    }

    S_constant bS_constant = new S_constant();
    isOk = bS_constant.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sfactor = bS_constant.getS_constant();
      buffer.append( bS_constant.getRendering()+" " );
      return true;
    }

    S_var bS_var = new S_var();
    isOk = bS_var.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sfactor = bS_var.getS_var();
      buffer.append( bS_var.getRendering()+" " );
      return true;
    }

    S_exp bS_exp = new S_exp();
    isOk = bS_exp.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sfactor = bS_exp.getS_exp();
      buffer.append( bS_exp.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
