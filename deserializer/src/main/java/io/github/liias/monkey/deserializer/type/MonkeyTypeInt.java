package io.github.liias.monkey.deserializer.type;

import java.nio.ByteBuffer;

public class MonkeyTypeInt extends MonkeyType<Integer> {
  private final int value;

  public MonkeyTypeInt(ByteBuffer bb) {
    value = bb.getInt();
  }

  @Override
  public Integer getValue() {
    return value;
  }

  @Override
  public int getSize() {
    return 4;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
