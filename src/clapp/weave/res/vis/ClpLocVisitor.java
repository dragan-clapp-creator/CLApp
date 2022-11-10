package clapp.weave.res.vis;


import clp.run.res.weave.AboutLocation;
import clp.run.res.weave.AtLocation;
import clp.run.res.weave.KindOf;
import clp.run.res.weave.LineNumber;
import clp.run.res.weave.LocationVisitor;
import clp.run.res.weave.Place;
import clp.run.res.weave.Position;

public class ClpLocVisitor implements LocationVisitor {

  String key;
  private KindOf kind;
  private Position position;
  private Place place;
  private String name;
  private String type;
  private int loopNumber;
  private boolean isLoop;
  private int occurences;
  private boolean isLineNumber;
  private int lineNumber;
  private int index;

  @Override
  public void visitAtLocation(AtLocation at) {
    place = at.getPlace();
    key = at.getPlace().getVal();
  }

  @Override
  public void visitLineNumber(LineNumber x) {
    lineNumber = x.getNumber();
    isLineNumber = true;
  }

  public KindOf getKind() {
    return kind;
  }

  public Place getPlace() {
    return place;
  }

  public Position getPosition() {
    return position;
  }

  public boolean isLoop() {
    return isLoop;
  }

  public boolean isLineNumber() {
    return isLineNumber;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public String getName() {
    return name;
  }

  public int getIndex() {
    return index;
  }

  public int getOccurences() {
    return occurences;
  }

  public int getLoopNumber() {
    return loopNumber;
  }

  public String getKey() {
    return key;
  }

  @Override
  public void visitAboutLocation(AboutLocation x) {
    position = x.getPosition();
    kind = x.getKind();
    name = x.getName();
    type = x.getType();
    index = (x.getIndex() > 0 ? x.getIndex() : -1);
    occurences = (x.getOccurNumber() > 0 ? x.getOccurNumber() : 1);
    key = name + kind.getVal() + position.getVal() + occurences;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

}
