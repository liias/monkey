package io.github.liias.monkey.jps.builder;

import io.github.liias.monkey.jps.model.JpsMonkeyModuleProperties;
import io.github.liias.monkey.jps.model.JpsMonkeyModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.ModuleBasedBuildTargetType;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.module.JpsTypedModule;

import java.util.ArrayList;
import java.util.List;

public class MonkeyBuildTargetType extends ModuleBasedBuildTargetType<MonkeyBuildTarget> {
  public static final MonkeyBuildTargetType PRODUCTION = new MonkeyBuildTargetType("mc-production", false);
  public static final MonkeyBuildTargetType TESTS = new MonkeyBuildTargetType("mc-tests", true);
  private final boolean tests;

  private MonkeyBuildTargetType(String typeId, boolean tests) {
    super(typeId);
    this.tests = tests;
  }

  public boolean isTests() {
    return tests;
  }

  @NotNull
  @Override
  public List<MonkeyBuildTarget> computeAllTargets(@NotNull JpsModel model) {
    List<MonkeyBuildTarget> targets = new ArrayList<>();
    for (JpsTypedModule<JpsSimpleElement<JpsMonkeyModuleProperties>> module : model.getProject().getModules(JpsMonkeyModuleType.INSTANCE)) {
      targets.add(new MonkeyBuildTarget(this, module));
    }
    return targets;
  }

  @NotNull
  @Override
  public BuildTargetLoader<MonkeyBuildTarget> createLoader(@NotNull final JpsModel model) {
    return new BuildTargetLoader<MonkeyBuildTarget>() {
      @Nullable
      @Override
      public MonkeyBuildTarget createTarget(@NotNull String targetId) {
        for (JpsTypedModule<JpsSimpleElement<JpsMonkeyModuleProperties>> module : model.getProject().getModules(JpsMonkeyModuleType.INSTANCE)) {
          if (module.getName().equals(targetId)) {
            return new MonkeyBuildTarget(MonkeyBuildTargetType.this, module);
          }
        }
        return null;
      }
    };
  }
}
