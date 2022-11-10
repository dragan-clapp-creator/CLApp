package clapp.weave.res;

import clp.run.res.weave.CstOrVar;

public class AttributeReference  {

  private Jmodifier jmodifier;
  private boolean isJmodifier;
  private CstOrVar type;
  private CstOrVar attribute;
  private boolean isFinal;
  private boolean isStatic;
  private boolean isVisibility;
  private Visibility visibility;

  /**
   * @return the jmodifier
   */
  public Jmodifier getJmodifier() {
    return jmodifier;
  }
  /**
   * @param jmodifier the jmodifier to set
   */
  public void setJmodifier(Jmodifier jmodifier) {
    this.jmodifier = jmodifier;
  }
  /**
   * @return the isJmodifier
   */
  public boolean isJmodifier() {
    return isJmodifier;
  }
  /**
   * @param isJmodifier the isJmodifier to set
   */
  public void setJmodifier(boolean isJmodifier) {
    this.isJmodifier = isJmodifier;
  }
  /**
   * @return the type
   */
  public CstOrVar getType() {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(CstOrVar type) {
    this.type = type;
  }
  /**
   * @return the attribute
   */
  public CstOrVar getAttribute() {
    return attribute;
  }
  /**
   * @param attribute the attribute to set
   */
  public void setAttribute(CstOrVar attribute) {
    this.attribute = attribute;
  }
  /**
   * @return the isFinal
   */
  public boolean isFinal() {
    return isFinal;
  }
  /**
   * @param isFinal the isFinal to set
   */
  public void setFinal(boolean isFinal) {
    this.isFinal = isFinal;
  }
  /**
   * @return the isStatic
   */
  public boolean isStatic() {
    return isStatic;
  }
  /**
   * @param isStatic the isStatic to set
   */
  public void setStatic(boolean isStatic) {
    this.isStatic = isStatic;
  }
  /**
   * @return the isVisibility
   */
  public boolean isVisibility() {
    return isVisibility;
  }
  /**
   * @param isVisibility the isVisibility to set
   */
  public void setVisibility(boolean isVisibility) {
    this.isVisibility = isVisibility;
  }
  /**
   * @return the visibility
   */
  public Visibility getVisibility() {
    return visibility;
  }
  /**
   * @param visibility the visibility to set
   */
  public void setVisibility(Visibility visibility) {
    this.visibility = visibility;
  }
}
