package io.github.liias.monkey.deserializer;

import io.github.liias.monkey.deserializer.type.MonkeyType;
import io.github.liias.monkey.deserializer.type.MonkeyTypeCollection;
import io.github.liias.monkey.deserializer.type.MonkeyTypeString;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Serializer {

  private final List<MonkeyTypeString> monkeyTypesString;

  private final List<MonkeyType> monkeyTypesData;

  private int totalStringBytes;

  private int totalDataBytes;

  public Serializer(MonkeyType monkeyType) {
    monkeyTypesString = new ArrayList<>();
    monkeyTypesData = new ArrayList<>();

    addToData(monkeyType);
    addToStrings(monkeyType);
  }

  private byte[] serializeStringBlock() {
    ByteBuffer bb = ByteBuffer.allocate(totalStringBytes);
    for (int i = 0; i < monkeyTypesString.size(); i++) {
      MonkeyTypeString s = monkeyTypesString.get(i);
      bb.put(s.serializeString());
    }
    return bb.array();
  }

  private byte[] serializeDataBlock() {
    LinkedList<MonkeyType> serializedQueue = new LinkedList<>();
    serializedQueue.addAll(monkeyTypesData);

    ByteBuffer bb = ByteBuffer.allocate(totalDataBytes);
    MonkeyType type;
    do {
      type = serializedQueue.poll();
      if (type != null) {
        bb.put(type.serialize());
        if (type instanceof MonkeyTypeCollection) {
          serializedQueue.addAll(((MonkeyTypeCollection) type).getChildren());
        }
      }
    } while (type != null);

    return bb.array();
  }

  public byte[] serialize() {
    byte[] stringBytes = serializeStringBlock();
    byte[] dataBytes = serializeDataBlock();

    int size;
    if (stringBytes.length > 0) {
      size = 4 + 4 + stringBytes.length + 4 + 4 + dataBytes.length;
    } else {
      size = 4 + 4 + dataBytes.length;
    }
    ByteBuffer bb = ByteBuffer.allocate(size);

    if (stringBytes.length > 0) {
      // Write string block
      bb.putInt(Deserializer.STRING_BLOCK_MARKER);
      bb.putInt(stringBytes.length);
      bb.put(stringBytes);
    }

    // Write data block
    bb.putInt(Deserializer.DATA_BLOCK_MARKER);
    bb.putInt(dataBytes.length);
    bb.put(dataBytes);

    return bb.array();
  }

  private void addToStrings(MonkeyType monkeyType) {
    if (monkeyType instanceof MonkeyTypeString) {
      MonkeyTypeString monkeyTypeString = (MonkeyTypeString) monkeyType;
      int offset = addString(monkeyTypeString);
      monkeyTypeString.setOffset(offset);
    } else if (monkeyType instanceof MonkeyTypeCollection) {
      MonkeyTypeCollection monkeyTypeCollection = (MonkeyTypeCollection) monkeyType;
      monkeyTypeCollection.getChildren().forEach(this::addToStrings);
    }
  }

  private int addString(MonkeyTypeString monkeyType) {
    int offset = getOffsetFor(monkeyType);
    monkeyType.setOffset(offset);
    if (!monkeyTypesString.contains(monkeyType)) {
      monkeyTypesString.add(monkeyType);
      totalStringBytes += 2 + monkeyType.getValue().getBytes(StandardCharsets.UTF_8).length + 1;
    }
    return offset;
  }

  private int getOffsetFor(MonkeyTypeString aString) {
    int offset = 0;
    for (int i = 0; i < monkeyTypesString.size(); i++) {
      if (monkeyTypesString.get(i).equals(aString)) {
        break;
      }
      offset += 2 + monkeyTypesString.get(i).getValue().getBytes(StandardCharsets.UTF_8).length + 1;
    }
    return offset;
  }

  private void addToData(MonkeyType monkeyType) {
    totalDataBytes = monkeyType.getNumberOfBytes();
    monkeyTypesData.add(monkeyType);
  }
}
