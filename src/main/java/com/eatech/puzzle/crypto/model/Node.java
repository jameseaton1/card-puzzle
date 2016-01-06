package com.eatech.puzzle.crypto.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jameseaton@hotmail.com
 * Created on 14/12/2015
 */
public class Node<T> {

  private final Optional<T> data;
  private final Set<Node<T>> children = new HashSet<>();
  private final Optional<Node<T>> parent;

  private Node(final T data, final Node<T> parent) {
    this.data = Optional.ofNullable(data);
    this.parent = Optional.ofNullable(parent);
    this.parent.ifPresent(x -> x.addChild(this));
  }
  public void addChild(final Node<T> node) {
    children.add(node);
  }

  public Set<Node<T>> getChildren() {
    return children;
  }

  public boolean isLeaf() {
    return children.isEmpty();
  }

  public static <T> Node<T> buildRoot() {
    return new Node<>(null, null);
  }

  /**
   *
   * @param data the payload of the tree
   * @param parent the parent
   * @param <T> type of payload for the tea
   * @return
   */
  public static <T> Node<T> build(final T data, Node<T> parent) {
    return new Node<>(data, parent);
  }

  public Optional<Node<T>> getParent() {
    return this.parent;
  }

  public Optional<T> getData() {
    return data;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Node node = (Node) o;

    if (!data.equals(node.data)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return data.hashCode();
  }
}
