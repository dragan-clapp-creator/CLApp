package clp.parse;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

import clp.parse.act.UActParser;
import clp.parse.cel.UHeapParser;
import clp.parse.msc.MetaScenario;
import clp.parse.res.Setter;
import clp.parse.res.UResParser;
import clp.parse.scn.UScnParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CLAppParser extends StreamTokenizer {

  private String msg;
  private StringBuffer buffer = new StringBuffer();

  // RT declaration
  private clp.run.CLApp clapp = new clp.run.CLApp();

  public clp.run.CLApp getCLApp() {
    return clapp;
  }



  public CLAppParser(Reader r) {
    super(r);
    setup();
  }

  private void setup() {
    eolIsSignificant(false);
    lowerCaseMode(false);
    quoteChar('\'');
    ordinaryChar('\\');
    ordinaryChar('/');
    ordinaryChar('.');
    ordinaryChar('-');
    ordinaryChar('°');
    wordChars('_', '_');
    slashSlashComments(true);
    slashStarComments(true);
  }

  public boolean parse() {
    try {
      MainParser mp = new MainParser();
      return mp.parse(this, false) != Boolean.FALSE;
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean isComplete() {
    try {
      int token = nextToken();
      if (token == TT_EOF) {
        return true;
      }
      pushBack();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public String collectGraphSentences() throws IOException {
    StringBuilder sb = new StringBuilder();
    if (nval == '{') {
      sb.append("{");
      while (nval != '}') {
        int token = nextToken();
        if (ttype == StreamTokenizer.TT_NUMBER) {
          sb.append(" " + nval);
        }
        else {
          sb.append(" " + sval);
        }
      }
    }
    return sb.toString();
  }


  public StringBuffer getRendering() {
      return buffer;
  }

  public Boolean errorLog(boolean isFirst) {
     if (isFirst) {
      pushBack();
      return null;
    }
    switch (ttype) {
      case TT_NUMBER:
        msg = "Error at line " + lineno() + " near " + nval;
        break;
      case TT_EOF:
        msg = "Error: unexpected end of line reached";
        break;
      case TT_WORD:
        msg = "Error at line " + lineno() + " near " + sval;
        break;
      default:
        msg = "Error at line " + lineno() + " near " + (char)ttype;
        break;
    }
    pushBack();
    return false;
  }

  public String getError() {
    return msg;
  }

  class MainParser extends AParser {

    @Override
    public StringBuffer getRendering() {
      return buffer;
    }

    @Override
    public Boolean parse(CLAppParser parser, boolean isOptional) throws IOException {
      Boolean isOk = true;
      int token;

      MetaScenario metaScenario0 = new MetaScenario();
      isOk = metaScenario0.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        clapp.setMetaScenario(metaScenario0.getMetaScenario());
        clapp.setIsMetaScenario(true);
        buffer.append( metaScenario0.getRendering()+" " );
      }
      isOk = true;

      isOk = ampersAndBlock1(parser, isOptional);
      if (isOk != Boolean.TRUE) {
        return parser.errorLog(true);
      }


      return isOk != Boolean.FALSE;
    }


    private boolean ampersAndBlock1(CLAppParser parser, boolean isOptional) throws IOException {
      int token = 0;
      Boolean b0 = null;
      Boolean b1 = null;
      Boolean b2 = null;
      Boolean b3 = null;
      Boolean b4 = null;

   
      try {
        boolean found = true;

        while (found) {
        AParser uscenario = new UScnParser();
        AParser uresources = new UResParser();
        AParser uactor = new UActParser();
        AParser uheap = new UHeapParser();
        AParser setter = new Setter();

          found = false;
          b0 = uscenario.parse(parser, true);
          if (b0 == Boolean.TRUE) {
            clapp.addScenario( ((UScnParser) uscenario).getScenario() );
            buffer.append( ((UScnParser) uscenario).getRendering()+" " );
            found = true;
          }
          else if (b0 == Boolean.FALSE) {
            b0 = null;
            parser.pushBack();
          }
          b1 = uresources.parse(parser, true);
          if (b1 == Boolean.TRUE) {
            clapp.addResources( ((UResParser) uresources).getResources() );
            buffer.append( ((UResParser) uresources).getRendering()+" " );
            found = true;
          }
          else if (b1 == Boolean.FALSE) {
            b1 = null;
            parser.pushBack();
          }
          b2 = uactor.parse(parser, true);
          if (b2 == Boolean.TRUE) {
            clapp.addActor( ((UActParser) uactor).getActor() );
            buffer.append( ((UActParser) uactor).getRendering()+" " );
            found = true;
          }
          else if (b2 == Boolean.FALSE) {
            b2 = null;
            parser.pushBack();
          }
          b3 = uheap.parse(parser, true);
          if (b3 == Boolean.TRUE) {
            clapp.addHeap( ((UHeapParser) uheap).getHeap() );
            buffer.append( ((UHeapParser) uheap).getRendering()+" " );
            found = true;
          }
          else if (b3 == Boolean.FALSE) {
            b3 = null;
            parser.pushBack();
          }
          b4 = setter.parse(parser, true);
          if (b4 == Boolean.TRUE) {
            clapp.addSetter( ((Setter) setter).getSetter() );
            buffer.append( ((Setter) setter).getRendering()+" " );
            found = true;
          }
          else if (b4 == Boolean.FALSE) {
            b4 = null;
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
         && b2 != Boolean.FALSE
         && b3 != Boolean.FALSE
         && b4 != Boolean.FALSE

      ; 
    }


  }
}
