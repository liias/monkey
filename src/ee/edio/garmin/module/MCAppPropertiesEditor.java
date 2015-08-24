package ee.edio.garmin.module;


import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MCAppPropertiesEditor {
  private JTextField myApplicationNameField;
  private JTextField myPackageNameField;
  private JCheckBox myHelloAndroidCheckBox;
  private JPanel myActivtiyPanel;
  private JTextField myActivityNameField;
  private JLabel myErrorLabel;
  private JPanel myContentPanel;

  private final ModulesProvider myModulesProvider;
  private boolean myApp;
  private boolean myPackageNameFieldChangedByUser;

  public MCAppPropertiesEditor(String moduleName, ModulesProvider modulesProvider) {
    myModulesProvider = modulesProvider;

    String defaultAppName = moduleName != null ? moduleName : "myapp";
    myApplicationNameField.setText(defaultAppName);
    myApplicationNameField.selectAll();
    myPackageNameField.setText(getDefaultPackageNameByModuleName(defaultAppName));

    myHelloAndroidCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateActivityPanel();
      }
    });
    myPackageNameField.getDocument().addDocumentListener(new DocumentAdapter() {
      @Override
      protected void textChanged(DocumentEvent e) {
        myPackageNameFieldChangedByUser = true;
        String message = validatePackageName(!myApp);
        myErrorLabel.setText(message);
      }
    });
    myActivityNameField.getDocument().addDocumentListener(new DocumentAdapter() {
      @Override
      protected void textChanged(DocumentEvent e) {
        String message = validateActivityName();
        myErrorLabel.setText(message);
      }
    });
    myApplicationNameField.getDocument().addDocumentListener(new DocumentAdapter() {
      @Override
      protected void textChanged(DocumentEvent e) {
        if (!myPackageNameFieldChangedByUser) {
          updatePackageNameField();
          myPackageNameFieldChangedByUser = false;
        }
      }
    });
  }

  private void updatePackageNameField() {
    final String appName = myApplicationNameField.getText().trim();

    if (appName.length() > 0) {
      myPackageNameField.setText(getDefaultPackageNameByModuleName(appName));
    }
  }

  public void update(boolean app) {
    myApplicationNameField.setEnabled(app);
    myHelloAndroidCheckBox.setEnabled(app);
    if (app) {
      updateActivityPanel();
    } else {
      UIUtil.setEnabled(myActivtiyPanel, app, true);
    }
    myApp = app;
    final String message = validatePackageName(!app);
    myErrorLabel.setText(message);
  }

  @NotNull
  public static String getDefaultPackageNameByModuleName(@NotNull String moduleName) {
    return "com.example." + toIdentifier(moduleName);
  }

  @NotNull
  private static String toIdentifier(@NotNull String s) {
    final StringBuilder result = new StringBuilder();

    for (int i = 0, n = s.length(); i < n; i++) {
      final char c = s.charAt(i);

      if (Character.isJavaIdentifierPart(c)) {
        if (i == 0 && !Character.isJavaIdentifierStart(c)) {
          result.append('_');
        }
        result.append(c);
      } else {
        result.append('_');
      }
    }
    return result.toString();
  }

  public void updateActivityPanel() {
    myErrorLabel.setForeground(JBColor.RED);
    UIUtil.setEnabled(myActivtiyPanel, myHelloAndroidCheckBox.isSelected(), true);
  }

  private String validatePackageName(boolean library) {
    final String candidate = myPackageNameField.getText().trim();
    return doValidatePackageName(library, candidate, myModulesProvider);
  }

  @NotNull
  static String doValidatePackageName(boolean library, @NotNull String candidate, @Nullable ModulesProvider modulesProvider) {
    return "";
  }

  private String validateActivityName() {
    String candidate = myActivityNameField.getText().trim();
    return "";
  }

  public JPanel getContentPanel() {
    return myContentPanel;
  }

  public void validate(boolean library) throws ConfigurationException {
    String message = validatePackageName(library);
    if (message.length() > 0) {
      throw new ConfigurationException(message);
    }
    if (!library) {
      message = validateActivityName();
      if (message.length() > 0) {
        throw new ConfigurationException(message);
      }
    }
  }

  public String getActivityName() {
    return myHelloAndroidCheckBox.isSelected() ? myActivityNameField.getText().trim() : "";
  }

  public String getPackageName() {
    return myPackageNameField.getText().trim();
  }

  public String getApplicationName() {
    return myApplicationNameField.getText().trim();
  }

  public JTextField getApplicationNameField() {
    return myApplicationNameField;
  }

  public JTextField getPackageNameField() {
    return myPackageNameField;
  }
}
