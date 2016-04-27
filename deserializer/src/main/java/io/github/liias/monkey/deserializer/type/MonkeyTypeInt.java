package io.github.liias.monkey.deserializer.type;

import java.nio.ByteBuffer;

public class MonkeyTypeInt extends MonkeyType<Integer> {
  private byte type = Type.INT;

  private final int value;

  public MonkeyTypeInt(ByteBuffer bb) {
    value = bb.getInt();
  }

  public MonkeyTypeInt(Integer value) {
    this.value = value;
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
  public byte[] serialize() {
    ByteBuffer bb = ByteBuffer.allocate(1 + getSize());
    bb.put(type);
    bb.putInt(value);
    return bb.array();
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
