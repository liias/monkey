package io.github.liias.monkey.ide.conf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

public class KeyGenerator {
  public static void generate(Path outputKeyPath) {
    try {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
      keyGen.initialize(4096);
      KeyPair pair = keyGen.generateKeyPair();

      PrivateKey privateKey = pair.getPrivate();
      byte[] keyBytes = privateKey.getEncoded();
      Files.write(outputKeyPath, keyBytes);
    } catch (NoSuchAlgorithmException | IOException e) {
      e.printStackTrace();
    }
  }

}
