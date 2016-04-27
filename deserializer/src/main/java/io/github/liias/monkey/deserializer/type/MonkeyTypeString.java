package io.github.liias.monkey.deserializer.type;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MonkeyTypeString extends MonkeyType<String> {
  private byte type = Type.STRING;

  // offset in bytes from the beginning of the string block
  private int offset;

  private String value;

  public MonkeyTypeString(ByteBuffer bb) {
    this.offset = bb.getInt();
  }

  public MonkeyTypeString(String value) {
    this.value = value;
  }

  @Override
  public int getSize() {
    return 4;
  }

  @Override
  public byte[] serialize() {
    ByteBuffer bb = ByteBuffer.allocate(1 + getSize());
    bb.put(type);
    bb.putInt(offset);
    return bb.array();
  }

  @Override
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getOffset() {
    return offset;
  }

  @Override
  public String toString() {
    return value;
  }

  public byte[] serializeString() {
    byte[] valueAsBytes = value.getBytes(StandardCharsets.UTF_8);
    ByteBuffer bb = ByteBuffer.allocate(2 + valueAsBytes.length + 1);
    bb.putShort((short) (valueAsBytes.length + 1));
    bb.put(valueAsBytes);
    bb.put((byte) 0);
    return bb.array();
  }
}
