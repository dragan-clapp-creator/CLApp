package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysStatement extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.SysStatement sysstatement = new clp.run.cel.dom.SysStatement();

  public clp.run.cel.dom.SysStatement getSysStatement() {
    return sysstatement;
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

    SysCommand bSysCommand = new SysCommand();
    isOk = bSysCommand.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sysstatement.setSysCommand(bSysCommand.getSysCommand());
      buffer.append( bSysCommand.getRendering()+" " );
      return true;
    }

    SysExp bSysExp = new SysExp();
    isOk = bSysExp.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sysstatement.setSysExp(bSysExp.getSysExp());
      buffer.append( bSysExp.getRendering()+" " );
      return true;
    }

    LoadMarks bLoadMarks = new LoadMarks();
    isOk = bLoadMarks.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      sysstatement.setLoadMarks(bLoadMarks.getLoadMarks());
      buffer.append( bLoadMarks.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
