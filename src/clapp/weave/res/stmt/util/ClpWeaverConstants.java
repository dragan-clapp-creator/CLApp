package clapp.weave.res.stmt.util;

public interface ClpWeaverConstants {

  public static final String RESOURCE_HANDLER = "clapp.weave.res.CLAppResourceHandler";
  public static final String EXPORT           = "export";
  public static final String COPY_FROM_STREAM = "copyFromStream";
  public static final String GET_INSTANCE     = "getInstance";
  public static final String STRING           = "java.lang.String";
  public static final String OBJECT           = "java.lang.Object";
  public static final String VOID             = "void";
  public static final String BOOLEAN          = "boolean";
  public static final String INT              = "int";
  public static final String LONG             = "long";
  public static final String CONTROL_FIELD    = "clappControl";
  public static final String ARG_NAME         = "arg";
  public static final String CONTROL_SETTER   = "setClappControl";
  public static final String SUSPENDER        = "clappSuspend";

  public enum ToStatement { TO_STORE, TO_LOAD }

  public enum InvokeStatement { INVOKESTATIC, INVOKEVIRTUAL, INVOKESPECIAL }

  public enum MiscStatement { PUSH, POP, DUP, NEW }
}
