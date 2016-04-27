package io.github.liias.monkey.deserializer.type;

public class MonkeyTypeNull extends MonkeyType<Object> {
  private byte type = Type.NULL;

  @Override
  public Object getValue() {
    return null;
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public byte[] serialize() {
    return new byte[]{type};
  }

  @Override
  public String toString() {
    return "Null";
  }
}
