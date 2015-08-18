package ee.edio.garmin.runconfig.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.ModuleBasedBuildTargetType;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.ArrayList;
import java.util.List;

public class MonkeyCTargetType extends ModuleBasedBuildTargetType<MonkeyCTarget> {
  public static final MonkeyCTargetType PRODUCTION = new MonkeyCTargetType("mc-production", false);
  public static final MonkeyCTargetType TESTS = new MonkeyCTargetType("mc-tests", true);
  private final boolean tests;

  private MonkeyCTargetType(String typeId, boolean tests) {
    super(typeId);
    this.tests = tests;
  }

  public boolean isTests() {
    return tests;
  }

  @NotNull
  @Override
  public List<MonkeyCTarget> computeAllTargets(@NotNull JpsModel model) {
    List<MonkeyCTarget> targets = new ArrayList<>();
    for (JpsModule jpsModule : model.getProject().getModules()) {
      targets.add(new MonkeyCTarget(this, jpsModule));
    }
    /*for (JpsTypedModule<JpsSimpleElement<JpsGoModuleProperties>> module : model.getProject().getModules(JpsGoModuleType.INSTANCE)) {
      targets.add(new MonkeyCTarget(this, module));
    }*/
    return targets;
  }

  @NotNull
  @Override
  public BuildTargetLoader<MonkeyCTarget> createLoader(@NotNull final JpsModel model) {
    return new BuildTargetLoader<MonkeyCTarget>() {
      @Nullable
      @Override
      public MonkeyCTarget createTarget(@NotNull String targetId) {
        for (JpsModule jpsModule : model.getProject().getModules()) {
          if (jpsModule.getName().equals(targetId)) {
            return new MonkeyCTarget(MonkeyCTargetType.this, jpsModule);
          }
        }
        /*for (JpsTypedModule<JpsSimpleElement<JpsGoModuleProperties>> module : model.getProject().getModules(JpsGoModuleType.INSTANCE)) {
          if (module.getName().equals(targetId)) {
            return new MonkeyCTarget(MonkeyCTargetType.this, module);
          }
        }*/
        return null;
      }
    };
  }
}
