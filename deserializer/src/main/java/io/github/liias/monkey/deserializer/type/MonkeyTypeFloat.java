package io.github.liias.monkey.deserializer.type;

import java.nio.ByteBuffer;

public class MonkeyTypeFloat extends MonkeyType<Float> {

  private final float value;

  public MonkeyTypeFloat(ByteBuffer bb) {
    value = bb.getFloat();
  }

  @Override
  public Float getValue() {
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
