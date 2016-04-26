package io.github.liias.monkey.deserializer.type;

import java.util.LinkedList;
import java.util.List;

public interface MonkeyTypeCollection {
  int getChildCount();

  void fill(LinkedList<MonkeyType> deserializedQueue);

  List<MonkeyType> getChildren();
}
