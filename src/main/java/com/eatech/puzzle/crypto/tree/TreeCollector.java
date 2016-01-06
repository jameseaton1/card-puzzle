package com.eatech.puzzle.crypto.tree;

import com.eatech.puzzle.crypto.model.Block;
import com.eatech.puzzle.crypto.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by jameseaton@hotmail.com
 * Created on 04/01/2016
 */
public class TreeCollector implements Collector<List<Block>, List<List<Block>>, Node<Block>> {

  @Override
  public Supplier<List<List<Block>>> supplier() {
    return ArrayList::new;
  }

  @Override
  public BiConsumer<List<List<Block>>, List<Block>> accumulator() {
    return (acc, ele) -> acc.add(ele);
  }

  @Override
  public BinaryOperator<List<List<Block>>> combiner() {
    return (acc1, acc2) -> {
      throw new UnsupportedOperationException();
    };
  }

  @Override
  public Function<List<List<Block>>, Node<Block>> finisher() {
    return (acc) -> foldTree(acc, Node.buildRoot());
  }

  private Node<Block> foldTree(List<List<Block>> acc, final Node<Block> node) {
    acc.stream().forEach(x-> fold(node, x, 0));
    return node;

  }

  /**
   * Recursive function for iterate down the tree and create a path if one doesn't exist
   *
   * @param node
   * @param path
   * @param index
   */
  private void fold(final Node<Block> node, final List<Block> path, final int index) {

    // search for the node which matches the block
    Optional<Node<Block>> foundNode = node.getChildren().stream()
        .filter(x -> x.getData().get().equals(path.get(index)))
        .findFirst();

    // if the block is not present in the node's children add a node containing the block
    if (!foundNode.isPresent()) {
      Node<Block> newNode = Node.build(path.get(index),node);
      node.addChild(newNode);
      foundNode = Optional.of(newNode);
    }

    if (index < path.size() - 1) {
      int i = index;
      fold(foundNode.get(), path, ++i);
    }

  }


  @Override
  public Set<Characteristics> characteristics() {
    return Collections.EMPTY_SET;
  }

  public static Collector<List<Block>, List<List<Block>>, Node<Block>> toTree() {
    return new TreeCollector();
  }
}
