package io.github.liias.monkey.deserializer.type;

import java.nio.ByteBuffer;

public class MonkeyTypeString extends MonkeyType<String> {

  // offset in bytes from the beginning of the string block
  private final int offset;

  private String value;

  public MonkeyTypeString(ByteBuffer bb) {
    this.offset = bb.getInt();
  }

  @Override
  public int getSize() {
    return 4;
  }

  @Override
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getOffset() {
    return offset;
  }

  @Override
  public String toString() {
    return value;
  }
}
