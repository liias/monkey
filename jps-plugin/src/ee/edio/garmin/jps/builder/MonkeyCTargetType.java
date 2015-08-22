package ee.edio.garmin.jps.builder;

import ee.edio.garmin.jps.model.JpsMCModuleProperties;
import ee.edio.garmin.jps.model.JpsMCModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.ModuleBasedBuildTargetType;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.module.JpsTypedModule;

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
    for (JpsTypedModule<JpsSimpleElement<JpsMCModuleProperties>> module : model.getProject().getModules(JpsMCModuleType.INSTANCE)) {
      targets.add(new MonkeyCTarget(this, module));
    }
    return targets;
  }

  @NotNull
  @Override
  public BuildTargetLoader<MonkeyCTarget> createLoader(@NotNull final JpsModel model) {
    return new BuildTargetLoader<MonkeyCTarget>() {
      @Nullable
      @Override
      public MonkeyCTarget createTarget(@NotNull String targetId) {
        for (JpsTypedModule<JpsSimpleElement<JpsMCModuleProperties>> module : model.getProject().getModules(JpsMCModuleType.INSTANCE)) {
          if (module.getName().equals(targetId)) {
            return new MonkeyCTarget(MonkeyCTargetType.this, module);
          }
        }
        return null;
      }
    };
  }
}
