package io.github.liias.monkey.deserializer;

import io.github.liias.monkey.deserializer.type.MonkeyType;
import io.github.liias.monkey.deserializer.type.MonkeyTypeCollection;
import io.github.liias.monkey.deserializer.type.MonkeyTypeString;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;

public class Deserializer {
  private static final int STRING_BLOCK_MARKER = 0xABCDABCD;
  private static final int DATA_BLOCK_MARKER = 0xDA7ADA7A;

  @NotNull
  private final List<MonkeyType> monkeyTypes;

  @NotNull
  private final Map<Integer, String> stringsByOffset;

  public Deserializer(byte[] bytes) {
    int stringBlockOffset = 0;
    int stringBlockSize = getBlockSize(bytes, stringBlockOffset, STRING_BLOCK_MARKER);
    byte[] stringBlockBytes = getBlockBytes(bytes, stringBlockOffset, stringBlockSize);
    stringsByOffset = deserializeStrings(stringBlockBytes);

    int dataBlockOffset = stringBlockSize == 0 ? 0 : stringBlockSize + 8;
    int dataBlockSize = getBlockSize(bytes, dataBlockOffset, DATA_BLOCK_MARKER);
    byte[] dataBlockBytes = getBlockBytes(bytes, dataBlockOffset, dataBlockSize);

    monkeyTypes = deserializeDataBlock(dataBlockBytes);
  }

  public List<MonkeyType> getTypes() {
    return monkeyTypes;
  }

  private static Map<Integer, String> deserializeStrings(byte[] bytes) {
    Map<Integer, String> stringsByOffset = new HashMap<>();

    int offset = 0;
    while (offset < bytes.length) {
      int stringSize = ByteBuffer.wrap(bytes, offset, 2).getShort();
      byte[] stringBytes = Arrays.copyOfRange(bytes, offset + 2, offset + 2 + stringSize - 1);
      String stringValue = new String(stringBytes, StandardCharsets.UTF_8);
      stringsByOffset.put(offset, stringValue);
      offset += (2 + stringSize);
    }

    return stringsByOffset;
  }

  private List<MonkeyType> deserializeDataBlock(byte[] bytes) {
    LinkedList<MonkeyType> deserializedQueue = fillDeserializedQueue(bytes);
    return fillCollections(deserializedQueue);
  }

  @NotNull
  private LinkedList<MonkeyType> fillDeserializedQueue(byte[] bytes) {
    LinkedList<MonkeyType> deserializedQueue = new LinkedList<>();
    int i = 0;
    while (i < bytes.length) {
      byte type = bytes[i];
      int size = MonkeyType.getSize(type);
      ByteBuffer bb = ByteBuffer.wrap(bytes, 1 + i, size);
      MonkeyType monkeyType = MonkeyType.of(type, bb);
      if (monkeyType instanceof MonkeyTypeString) {
        MonkeyTypeString monkeyTypeString = (MonkeyTypeString) monkeyType;
        monkeyTypeString.setValue(stringsByOffset.get(monkeyTypeString.getOffset()));
      }
      deserializedQueue.add(monkeyType);
      i = i + 1 + monkeyType.getSize();
    }
    return deserializedQueue;
  }

  private List<MonkeyType> fillCollections(LinkedList<MonkeyType> deserializedQueue) {
    List<MonkeyType> deserialized = new ArrayList<>();

    do {
      MonkeyType monkeyType = deserializedQueue.poll();
      if (monkeyType instanceof MonkeyTypeCollection) {
        fillCollection((MonkeyTypeCollection) monkeyType, deserializedQueue);
      }
      deserialized.add(monkeyType);
    } while (!deserializedQueue.isEmpty());
    return deserialized;
  }

  private static void fillCollection(MonkeyTypeCollection monkeyType, LinkedList<MonkeyType> deserializedQueue) {
    IntStream.range(0, monkeyType.getChildCount()).forEach(i -> monkeyType.fill(deserializedQueue));
    monkeyType.getChildren().stream()
        .filter(mt -> mt instanceof MonkeyTypeCollection)
        .map(mt -> (MonkeyTypeCollection) mt)
        .forEach(mt -> fillCollection(mt, deserializedQueue));
  }

  private static byte[] getBlockBytes(byte[] bytes, int offset, int size) {
    if (size > 0) {
      return Arrays.copyOfRange(bytes, offset + 8, offset + 8 + size);
    }
    return new byte[0];
  }

  private static int getBlockSize(byte[] bytes, int offset, int startMarker) {
    ByteBuffer bb = ByteBuffer.wrap(bytes, offset, 8);
    // getInt() also increments position by 4
    if (bb.getInt() == startMarker) {
      return bb.getInt();
    }
    return 0;
  }
}