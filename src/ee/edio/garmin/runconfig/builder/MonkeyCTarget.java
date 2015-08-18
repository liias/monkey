package ee.edio.garmin.runconfig.builder;

import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.*;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.indices.IgnoredFileIndex;
import org.jetbrains.jps.indices.ModuleExcludeIndex;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaClasspathKind;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsTypedModuleSourceRoot;

import java.io.File;
import java.util.*;

public class MonkeyCTarget extends ModuleBasedTarget<MonkeyCSourceRootDescriptor> {
  public MonkeyCTarget(ModuleBasedBuildTargetType<?> targetType, @NotNull JpsModule module) {
    super(targetType, module);
  }

  @Override
  public boolean isTests() {
    return getMonkeyCTargetType().isTests();
  }

  @NotNull
  @Override
  public String getId() {
    return getModule().getName();
  }

  @NotNull
  @Override
  public Collection<BuildTarget<?>> computeDependencies(BuildTargetRegistry targetRegistry, TargetOutputIndex outputIndex) {
    List<BuildTarget<?>> dependencies = new ArrayList<>();
    Set<JpsModule> modules = JpsJavaExtensionService.dependencies(myModule).includedIn(JpsJavaClasspathKind.compile(isTests())).getModules();
    for (JpsModule module : modules) {
      //if (module.getModuleType() == JpsGoModuleType.INSTANCE) {
      dependencies.add(new MonkeyCTarget(getMonkeyCTargetType(), module));
      //}
    }
    if (isTests()) {
      dependencies.add(new MonkeyCTarget(MonkeyCTargetType.PRODUCTION, getModule()));
    }
    return dependencies;
  }

  @NotNull
  @Override
  public List<MonkeyCSourceRootDescriptor> computeRootDescriptors(JpsModel model, ModuleExcludeIndex index, IgnoredFileIndex ignoredFileIndex, BuildDataPaths dataPaths) {
    List<MonkeyCSourceRootDescriptor> result = new ArrayList<>();
    JavaSourceRootType type = isTests() ? JavaSourceRootType.TEST_SOURCE : JavaSourceRootType.SOURCE;
    for (JpsTypedModuleSourceRoot<JavaSourceRootProperties> root : myModule.getSourceRoots(type)) {
      result.add(new MonkeyCSourceRootDescriptor(root.getFile(), this));
    }
    return result;
  }

  @Nullable
  @Override
  public MonkeyCSourceRootDescriptor findRootDescriptor(String rootId, BuildRootIndex rootIndex) {
    return ContainerUtil.getFirstItem(rootIndex.getRootDescriptors(new File(rootId), Collections.singletonList(getMonkeyCTargetType()), null));

  }

  @NotNull
  @Override
  public String getPresentableName() {
    return "Module '" + getModule().getName() + "' " + (isTests() ? "tests" : "production");
  }

  @NotNull
  @Override
  public Collection<File> getOutputRoots(CompileContext context) {
    return ContainerUtil.createMaybeSingletonList(JpsJavaExtensionService.getInstance().getOutputDirectory(myModule, isTests()));
  }

  @NotNull
  public MonkeyCTargetType getMonkeyCTargetType() {
    return (MonkeyCTargetType) getTargetType();
  }
}
