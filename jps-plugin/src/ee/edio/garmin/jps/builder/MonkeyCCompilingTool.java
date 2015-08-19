package ee.edio.garmin.jps.builder;

import com.intellij.util.ExceptionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.java.CannotCreateJavaCompilerException;
import org.jetbrains.jps.javac.JavacMain;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class MonkeyCCompilingTool {
  public static final String MONKEYBRAINS_ID = "Monkeybrains";

  @NotNull
  public String getId() {
    return MONKEYBRAINS_ID;
  }

  @NotNull
  public String getDescription() {
    return "Monkeybrains compiler";
  }

  @NotNull
  public JavaCompiler createCompiler() throws CannotCreateJavaCompilerException {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    if (compiler != null) {
      return compiler;
    }

    String message = "System Java Compiler was not found in classpath";
    // trying to obtain additional diagnostic for the case when compiler.jar is present, but there were problems with compiler class loading:
    try {
      Class.forName("com.sun.tools.javac.api.JavacTool", false, JavacMain.class.getClassLoader());
    } catch (Throwable ex) {
      message = message + ":\n" + ExceptionUtil.getThrowableText(ex);
    }
    throw new CannotCreateJavaCompilerException(message);
  }

  @NotNull
  public List<File> getAdditionalClasspath() {
    return Collections.emptyList();
  }

  public List<String> getDefaultCompilerOptions() {
    return Collections.singletonList("-implicit:class");
  }
}
