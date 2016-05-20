package io.github.liias.monkey.project.sdk;

import io.github.liias.monkey.project.sdk.skeleton.ApiReader;
import io.github.liias.monkey.yard.YardDecompiler;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class SkeletonTest {

  @Test
  @Ignore
  public void testSomething() {
    String sdkBinPath = "C:\\Users\\madis\\Downloads\\iqsdk" + File.separator + "bin" + File.separator;
    ApiReader apiReader = new ApiReader(sdkBinPath);
    apiReader.parseApiDebugXml();
  }

  @Test
  @Ignore
  public void testSomething2() {
    String docDirPath = "D:\\connectiq\\connectiq-sdk-win-1.2.9\\doc\\";
    YardDecompiler yardDecompiler = new YardDecompiler(docDirPath);
    String parsed = yardDecompiler.parse();
    System.out.println(parsed);
  }
}
