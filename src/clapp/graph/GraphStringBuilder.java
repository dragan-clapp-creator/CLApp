package clapp.graph;

import java.io.IOException;
import java.io.StreamTokenizer;

import akdl.ker.def.util.ItemKeys;

import clp.parse.CLAppParser;

public class GraphStringBuilder {

  private String gstring;

  public Boolean gather(CLAppParser parser) throws IOException {
    StringBuffer sb = new StringBuffer();
    int token = 0;
    int count = 0;
    boolean finish = (token == StreamTokenizer.TT_EOF);
    if (finish) {
      return parser.errorLog(true);
    }
    while (!finish) {
      ItemKeys key = getKey(token);
      if (key == null) {
        append(sb, parser, token);
      }
      else switch(key) {
        case C_BRACE:
          count++;
          sb.append((char)token);
          break;
        case C_BRACE_END:
          if (count == 1) {
            finish = true;
          }
          else {
            count--;
          }
          sb.append((char)token);
          break;
        case C_QUOTE:
        case C_2QUOTES:
          sb.append((char)key.getKey());
          if (parser.sval != null) {
            sb.append(parser.sval);
            sb.append((char)key.getKey());
          }
          break;
        default:
          append(sb, parser, token);
          break;
      }
      sb.append("\n");
      if (!finish) {
        token = parser.nextToken();
      }
    }
    gstring = sb.toString();
    return !gstring.isEmpty();
  }

  //
  @SuppressWarnings("static-access")
  private void append(StringBuffer sb, CLAppParser parser, int token) {
    if (parser.sval != null) {
      sb.append(parser.sval);
    }
    else if (parser.TT_NUMBER != token) {
      sb.append((char)token);
    }
    else {
      sb.append(parser.nval);
    }
  }

  //
  private ItemKeys getKey(int token) {
    for (ItemKeys value : ItemKeys.values()) {
      if (value.getKey() == token) {
        return value;
      }
    }
    return null;
  }


  /**
   * return graph string (to be parsed)
   * @return
   */
  public String getGraphString() {
    return gstring;
  }
}
