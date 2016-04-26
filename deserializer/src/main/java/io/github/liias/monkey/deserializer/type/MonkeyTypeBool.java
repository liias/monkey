package io.github.liias.monkey.deserializer.type;

import java.nio.ByteBuffer;

public class MonkeyTypeBool extends MonkeyType<Boolean> {
  final boolean value;

  public MonkeyTypeBool(ByteBuffer bb) {
    value = bb.get() > 0;
  }

  @Override
  public Boolean getValue() {
    return value;
  }

  @Override
  public int getSize() {
    return 1;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
