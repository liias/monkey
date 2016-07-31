package io.github.liias.monkey.ide.conf;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@State(
  name = "MonkeyOptions", // specifies the name of the state (name of the root tag in XML).
  storages = @Storage("monkey.options.xml")
)
public class MonkeyOptions implements PersistentStateComponent<MonkeyOptions> {
  public String keyPath;

  public MonkeyOptions() {
  }

  // Gets options on application level
  public static MonkeyOptions getInstance() {
    return ServiceManager.getService(MonkeyOptions.class);
  }

  @Nullable
  @Override
  public MonkeyOptions getState() {
    return this;
  }

  @Override
  public void loadState(MonkeyOptions state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public String getKeyPath() {
    return keyPath;
  }

  public void setKeyPath(String keyPath) {
    this.keyPath = keyPath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MonkeyOptions that = (MonkeyOptions) o;
    return Objects.equals(getKeyPath(), that.getKeyPath());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKeyPath());
  }
}
