package io.github.liias.monkey.project.sdk.skeleton;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import io.github.liias.monkey.project.sdk.MonkeySdkType;
import io.github.liias.monkey.yard.YardDecompiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SdkSkeleton {
  /**
   * Name of directory where skeleton files (despite the value) are stored.
   */
  public static final String SKELETON_DIR_NAME = "monkey_stubs";

  public static void updateSdk(final Sdk sdk) {
    SdkModificator sdkModificator = sdk.getSdkModificator();
    sdkModificator.removeRoots(OrderRootType.CLASSES);
    sdkModificator.commitChanges();


    String skeletonsPath = getSkeletonsPath(PathManager.getSystemPath(), sdk.getHomePath());
    File skeletonsDir = new File(skeletonsPath);
    if (skeletonsDir.exists()) {
      ApplicationManager.getApplication().runWriteAction(() -> {
        try {
          LocalFileSystem.getInstance().refreshAndFindFileByPath(skeletonsPath).delete(sdk);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
    skeletonsDir.mkdirs();

    String skeletonFilePath = skeletonsPath + File.separator + "connectiq.mc";
    Path path = Paths.get(skeletonFilePath);

    String apiDocPath = MonkeySdkType.getApiDocPath(sdk);
    YardDecompiler yardDecompiler = new YardDecompiler(apiDocPath);
    String skeletonsAsOneFile = yardDecompiler.parse();

    /*
    String sdkBinPath = MonkeySdkType.getBinPath(sdk);
    ApiReader apiReader = new ApiReader(sdkBinPath);
    String skeletonsAsOneFile = apiReader.parseApiDebugXml();
    */

    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      writer.write(skeletonsAsOneFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    final VirtualFile root = LocalFileSystem.getInstance().refreshAndFindFileByPath(skeletonsPath);
    sdkModificator = sdk.getSdkModificator();
    sdkModificator.addRoot(root, OrderRootType.CLASSES);
    sdkModificator.commitChanges();
  }

  public static String getSkeletonsPath(String basePath, String sdkHome) {
    return getSkeletonsRootPath(basePath) + File.separator + FileUtil.toSystemIndependentName(sdkHome).hashCode() + File.separator;
  }

  public static String getSkeletonsRootPath(String basePath) {
    return basePath + File.separator + SKELETON_DIR_NAME;
  }
}
