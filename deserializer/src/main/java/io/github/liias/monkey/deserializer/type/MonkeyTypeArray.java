package io.github.liias.monkey.deserializer.type;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MonkeyTypeArray extends MonkeyType<List<MonkeyType>> implements MonkeyTypeCollection {

  private byte type = Type.ARRAY;

  private final int childCount;

  @NotNull
  private final List<MonkeyType> items;

  public MonkeyTypeArray(ByteBuffer bb) {
    childCount = bb.getInt();
    items = new ArrayList<>();
  }

  public MonkeyTypeArray(List<Object> values) {
    childCount = values.size();
    items = values.stream()
        .map(MonkeyType::ofJavaObject)
        .collect(Collectors.toList());
  }

  @Override
  public int getChildCount() {
    return childCount;
  }

  @Override
  public List<MonkeyType> getValue() {
    return items;
  }

  @Override
  public int getSize() {
    return 4;
  }

  // bytes
  public int getNumberOfBytes() {
    int numberOfBytes = 1 + getSize();

    for (MonkeyType child : getChildren()) {
      numberOfBytes += child.getNumberOfBytes();
    }
    return numberOfBytes;
  }

  @Override
  public byte[] serialize() {
    ByteBuffer bb = ByteBuffer.allocate(1 + getSize());
    bb.put(type);
    bb.putInt(childCount);
    return bb.array();
  }

  public void add(MonkeyType monkeyType) {
    items.add(monkeyType);
  }

  @Override
  public void fill(LinkedList<MonkeyType> deserializedQueue) {
    add(deserializedQueue.poll());
  }

  @Override
  public List<MonkeyType> getChildren() {
    return items;
  }

  @Override
  public String toString() {
    return Arrays.toString(items.toArray());
  }

}
