package io.github.liias.monkey.project.sdk;

import io.github.liias.monkey.project.sdk.skeleton.ApiReader;
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

}
