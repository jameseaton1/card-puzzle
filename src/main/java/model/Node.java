package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jameseaton@hotmail.com
 * Created on 14/12/2015
 */
public class Node<T> {

  private final Optional<T> data;
  private final List<Node<T>> children = new ArrayList<>();
  private final Optional<Node<T>> parent;

  private Node(final T data, final Node<T> parent) {
    this.data = Optional.ofNullable(data);
    this.parent = Optional.ofNullable(parent);
    this.parent.ifPresent(x -> x.addChildren(this));
  }
  public void addChildren(final Node<T> node) {
    children.add(node);
  }

  public List<Node<T>> getChildren() {
    return children;
  }

  public boolean isLeaf() {
    return children.isEmpty();
  }

  public static <T> Node<T> buildRoot() {
    return new Node<>(null, null);
  }

  public static <T> Node<T> build(final T data, Node<T> parent) {
    return new Node<>(data, parent);
  }

  public Optional<Node<T>> getParent() {
    return this.parent;
  }

  public Optional<T> getData() {
    return data;
  }
}
