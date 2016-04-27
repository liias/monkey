package io.github.liias.monkey.deserializer.type;

import java.nio.ByteBuffer;

public class MonkeyTypeFloat extends MonkeyType<Float> {
  private byte type = MonkeyType.Type.FLOAT;

  private final float value;

  public MonkeyTypeFloat(ByteBuffer bb) {
    value = bb.getFloat();
  }

  public MonkeyTypeFloat(Float value) {
    this.value = value;
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
  public byte[] serialize() {
    ByteBuffer bb = ByteBuffer.allocate(1+ getSize());
    bb.put(type);
    bb.putFloat(value);
    return bb.array();
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

}
