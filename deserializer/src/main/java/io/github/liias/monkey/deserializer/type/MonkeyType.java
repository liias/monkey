package io.github.liias.monkey.deserializer.type;

import com.google.common.collect.ImmutableMap;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public abstract class MonkeyType<T> {

  public abstract T getValue();

  // number of bytes
  public abstract int getSize();

  public abstract byte[] serialize();

  public int getNumberOfBytes() {
    return 1 + getSize();
  }

  public interface Type {
    byte NULL = 0;
    byte INT = 1;
    byte FLOAT = 2;
    byte STRING = 3;
    byte ARRAY = 5;
    byte BOOLEAN = 9;
    byte HASH = 11;
  }

  public static Map<Byte, Integer> sizes = ImmutableMap.<Byte, Integer>builder()
      .put(Type.NULL, 0)
      .put(Type.INT, 4)
      .put(Type.FLOAT, 4)
      .put(Type.STRING, 4)
      .put(Type.ARRAY, 4)
      .put(Type.BOOLEAN, 1)
      .put(Type.HASH, 4)
      .build();

  public static int getSize(byte type) {
    return sizes.get(type);
  }

  public static MonkeyType of(byte type, ByteBuffer bb) {
    switch (type) {
      case Type.NULL:
        return new MonkeyTypeNull();
      case Type.INT:
        return new MonkeyTypeInt(bb);
      case Type.FLOAT:
        return new MonkeyTypeFloat(bb);
      case Type.STRING:
        return new MonkeyTypeString(bb);
      case Type.ARRAY:
        return new MonkeyTypeArray(bb);
      case Type.BOOLEAN:
        return new MonkeyTypeBool(bb);
      case Type.HASH:
        return new MonkeyTypeHash(bb);
    }

    throw new IllegalArgumentException("unknown type");
  }

  public static <T> MonkeyType ofJavaObject(T javaObject) {
    if (javaObject == null) {
      return new MonkeyTypeNull();
    } else if (javaObject instanceof MonkeyType) {
      return (MonkeyType) javaObject;
    } else if (javaObject instanceof Integer) {
      return new MonkeyTypeInt((Integer) javaObject);
    } else if (javaObject instanceof Long) {
      return new MonkeyTypeInt(((Long) javaObject).intValue());
    } else if (javaObject instanceof Float) {
      return new MonkeyTypeFloat((Float) javaObject);
    } else if (javaObject instanceof Double) {
      return new MonkeyTypeFloat(new Float((Double) javaObject));
    } else if (javaObject instanceof String) {
      return new MonkeyTypeString((String) javaObject);
    } else if (javaObject instanceof List) {
      //noinspection unchecked
      return new MonkeyTypeArray((List<Object>) javaObject);
    } else if (javaObject instanceof Boolean) {
      return new MonkeyTypeBool((Boolean) javaObject);
    } else if (javaObject instanceof Map) {
      //noinspection unchecked
      return new MonkeyTypeHash((Map<Object, Object>) javaObject);
    }
    return new MonkeyTypeNull();
  }
}
