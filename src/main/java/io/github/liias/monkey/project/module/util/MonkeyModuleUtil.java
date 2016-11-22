package io.github.liias.monkey.project.module.util;

import com.google.common.collect.ImmutableSet;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import io.github.liias.monkey.project.dom.manifest.Manifest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class MonkeyModuleUtil {
  public static final String MANIFEST_XML = "manifest.xml";

  public static final Set<String> FAKE_DEVICES = ImmutableSet.of("round_watch", "square_watch", "semi_round_watch", "tall_watch");

  public static boolean isRealDevice(String deviceId) {
    return !FAKE_DEVICES.contains(Optional.ofNullable(deviceId).orElse("").toLowerCase());
  }

  public static String generateProjectId() {
    UUID id = UUID.randomUUID();
    return String.format("%016X%016X", id.getMostSignificantBits(), id.getLeastSignificantBits());
  }

  public static VirtualFile createChildDirectoryIfNotExist(Project project, VirtualFile parent, String name) throws IOException {
    final VirtualFile child = parent.findChild(name);
    return child == null ? parent.createChildDirectory(project, name) : child;
  }


  public static Manifest getManifest(Project project, VirtualFile moduleRootDir) {
    VirtualFile manifestFile = moduleRootDir.findChild(MANIFEST_XML);
    return manifestFile != null ? MonkeyModuleUtil.loadDomElement(project, manifestFile, Manifest.class) : null;
  }

  @Nullable
  public static <T extends DomElement> T loadDomElement(@NotNull final Project project,
                                                        @NotNull final VirtualFile file,
                                                        @NotNull final Class<T> aClass) {
    return ApplicationManager.getApplication().runReadAction((Computable<T>) () -> {
      if (project.isDisposed()) return null;
      PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
      if (psiFile instanceof XmlFile) {
        return loadDomElementWithReadPermission(project, (XmlFile) psiFile, aClass);
      } else {
        return null;
      }
    });
  }

  /**
   * This method should be called under a read action.
   */
  @Nullable
  public static <T extends DomElement> T loadDomElementWithReadPermission(@NotNull Project project,
                                                                          @NotNull XmlFile xmlFile,
                                                                          @NotNull Class<T> aClass) {
    ApplicationManager.getApplication().assertReadAccessAllowed();
    DomManager domManager = DomManager.getDomManager(project);
    DomFileElement<T> element = domManager.getFileElement(xmlFile, aClass);
    if (element == null) return null;
    return element.getRootElement();
  }
}
