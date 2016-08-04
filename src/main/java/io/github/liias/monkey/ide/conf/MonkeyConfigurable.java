package io.github.liias.monkey.ide.conf;

import com.google.common.base.Strings;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;

public class MonkeyConfigurable implements SearchableConfigurable, Configurable.NoScroll {
  public static final String ID = "monkeyc.settings";
  public static final String DISPLAY_NAME = "Connect IQ";
  private final MonkeyOptions monkeyOptions;

  private JPanel mainPanel;

  private TextFieldWithBrowseButton keyPathField;
  private JButton generateKeyButton;

  public MonkeyConfigurable() {
    this.monkeyOptions = MonkeyOptions.getInstance();

    FileChooserDescriptor chooserDescriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor()
      .withTitle("Choose File for Developer Key")
      .withDescription("This file will be used as a developer key or to generate one. Make a copy of your key!");
    TextBrowseFolderListener listener = new TextBrowseFolderListener(chooserDescriptor);
    keyPathField.addBrowseFolderListener(listener);
    keyPathField.setText(getActiveKeyPath().orElse(""));

    generateKeyButton.addActionListener(e -> {
      String keyPathStr = keyPathField.getText();
      if (Strings.isNullOrEmpty(keyPathStr)) {
        throw new IllegalArgumentException("Please set key output path first (including filename)");
      }
      Path path = FileSystems.getDefault().getPath(keyPathStr);
      KeyGenerator.generate(path);
    });
  }

  @Nls
  @Override
  public String getDisplayName() {
    return DISPLAY_NAME;
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return mainPanel;
  }

  @Override
  public boolean isModified() {
    String activeKey = getActiveKeyPath().orElse("");
    String newKeyPath = keyPathField.getText();
    return !activeKey.equals(newKeyPath);
  }

  @Override
  public void apply() throws ConfigurationException {
    String newKeyPath = keyPathField.getText();
    monkeyOptions.setKeyPath(newKeyPath);
  }

  private Optional<String> getActiveKeyPath() {
    return Optional.ofNullable(monkeyOptions.getKeyPath());
  }

  @Override
  public void reset() {

  }

  @Override
  public void disposeUIResources() {
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Nullable
  @Override
  public Runnable enableSearch(String option) {
    return null;
  }
}
