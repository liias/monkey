package io.github.liias.monkey.deserializer.type;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MonkeyTypeHash extends MonkeyType<Map<MonkeyType, MonkeyType>> implements MonkeyTypeCollection {

  private final int childCount;

  @NotNull
  private final Map<MonkeyType, MonkeyType> items;

  public MonkeyTypeHash(ByteBuffer bb) {
    childCount = bb.getInt();
    items = new HashMap<>();
  }

  @Override
  public int getChildCount() {
    return childCount;
  }

  @Override
  public Map<MonkeyType, MonkeyType> getValue() {
    return items;
  }

  @Override
  public int getSize() {
    return 4;
  }

  public void add(MonkeyType key, MonkeyType value) {
    items.put(key, value);
  }

  @Override
  public void fill(LinkedList<MonkeyType> deserializedQueue) {
    add(deserializedQueue.poll(), deserializedQueue.poll());
  }

  @Override
  public List<MonkeyType> getChildren() {
    ImmutableList.Builder<MonkeyType> builder = ImmutableList.builder();
    items.forEach((key, value) -> builder.add(key, value));
    return builder.build();
  }

  public Map<MonkeyType, MonkeyType> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return items.toString();
  }
}
