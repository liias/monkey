package io.github.liias.monkey.deserializer.type;

public class MonkeyTypeNull extends MonkeyType<Object> {
  @Override
  public Object getValue() {
    return null;
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public String toString() {
    return "Null";
  }
}
