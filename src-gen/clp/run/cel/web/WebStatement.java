package clp.run.cel.web;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class WebStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 306L;


  //=== Attributes =============================================================

  private boolean isSendItems;
  private clp.run.cel.web.SendItems sendItems;
  private char ckey;
  private String res;
  private boolean isRes;
  private String webVarName;
  private int timeout;
  private boolean isTimeout;
  private boolean isReceiving;
  private boolean isResponse;
  private clp.run.cel.web.Response response;


  //=== Constructor ============================================================

  public WebStatement() {
  }

  //=== Methods ================================================================

  public boolean isSendItems() {
    return isSendItems;
  }

  public void setIsSendItems(boolean x) {
    this.isSendItems = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.web.SendItems getSendItems() {
    return sendItems;
  }

  public void setSendItems(clp.run.cel.web.SendItems x) {
    this.sendItems = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public String getRes() {
    return res;
  }

  public void setRes(String x) {
    this.res = x;
  }

  //----------------------------------------------------------------------------


  public boolean isRes() {
    return isRes;
  }

  public void setIsRes(boolean x) {
    this.isRes = x;
  }

  //----------------------------------------------------------------------------

  public String getWebVarName() {
    return webVarName;
  }

  public void setWebVarName(String x) {
    this.webVarName = x;
  }

  //----------------------------------------------------------------------------

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int x) {
    this.timeout = x;
  }

  //----------------------------------------------------------------------------


  public boolean isTimeout() {
    return isTimeout;
  }

  public void setIsTimeout(boolean x) {
    this.isTimeout = x;
  }

  //----------------------------------------------------------------------------

  public boolean isReceiving() {
    return isReceiving;
  }

  public void setIsReceiving(boolean x) {
    this.isReceiving = x;
  }

  //----------------------------------------------------------------------------

  public boolean isResponse() {
    return isResponse;
  }

  public void setIsResponse(boolean x) {
    this.isResponse = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.web.Response getResponse() {
    return response;
  }

  public void setResponse(clp.run.cel.web.Response x) {
    this.response = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitWebStatement(this);
  }



}
