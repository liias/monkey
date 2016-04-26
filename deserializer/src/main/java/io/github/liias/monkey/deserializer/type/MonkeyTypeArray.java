package io.github.liias.monkey.deserializer.type;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MonkeyTypeArray extends MonkeyType<List<MonkeyType>> implements MonkeyTypeCollection {

  private final int childCount;

  @NotNull
  private final List<MonkeyType> items;

  public MonkeyTypeArray(ByteBuffer bb) {
    childCount = bb.getInt();
    items = new ArrayList<>();
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
