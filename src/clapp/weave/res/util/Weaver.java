package clapp.weave.res.util;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionFactory;

import clapp.run.util.ClpClassHandler;

/**
 * 
 * @author dragan
 *
 */
abstract public class Weaver {

  private JavaClass jc;
  private ConstantPoolGen _cpg;
  private InstructionFactory _factory;
  private ClassGen _clg;
  private boolean isClassNew; // will be treated as such, at some later time
                              // aimed to be used for classes created ex nihilo


  // ===== CONSTRUCTORS ========================================================

  public Weaver() {
    isClassNew = true;
  }

  public Weaver(String fullClassName) {
    setup(fullClassName);
  }

  // ===== PROTECTED METHODS ===================================================

  protected void setup(String fullClassName) {
    jc = findAndParse(System.getProperty("user.dir"),
                      fullClassName.replace('.', '/') + ".class");
    if (jc != null) {
      ConstantPool constants = jc.getConstantPool();
      _cpg = new ConstantPoolGen(constants);
      _factory = new InstructionFactory(_cpg);
      _clg = _factory.getClassGen();
      if (_clg == null) {
        _clg = new ClassGen(jc);
      }
      isClassNew = false;
    }
    else {
      isClassNew = true;
    }
  }


  // ===== PUBLIC METHODS ======================================================

  public ConstantPoolGen getConstantPoolGen() {
    return _cpg;
  }

  public ClassGen getClassGen() {
    return _clg;
  }

  public JavaClass getJavaClass() {
    return jc;
  }

  public InstructionFactory getInstructionFactory() {
    return _factory;
  }


  // ===== PRIVATE METHODS =====================================================

  private JavaClass findAndParse(String dir, String clname) {
    JavaClass java_class = null;
    try {
      java_class = new ClassParser(dir + "/" + clname + ".class").parse();
    }
    catch (ClassFormatException e) {
      e.printStackTrace();
    }
    catch (NullPointerException e) {
      boolean found = false;
      ArrayList<String> paths = ClpClassHandler.getInstance().getPaths();
      for (int i=0; !found && i<paths.size(); i++) {
        try {
          String jar = paths.get(i);
          if (jar.endsWith(".jar")) {
            java_class = new ClassParser(jar, clname).parse();
          }
          else {
            if (!jar.endsWith("/")) {
              jar += "/";
            }
            java_class = new ClassParser(jar + clname).parse();
          }
          found = true;
        }
        catch (ClassFormatException e1) {
        }
        catch (IOException e1) {
        }
        catch (Exception e1) {
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return java_class;
  }
}
