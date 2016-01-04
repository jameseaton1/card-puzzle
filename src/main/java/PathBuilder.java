import model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This reduces of tree possible outcomes to List of paths in the tree from root -> leaf
 *
 * Created by jameseaton@hotmail.com
 * Created on 14/12/2015
 */
public class PathBuilder<T> {
  private final Node<T> tree;
  public PathBuilder(final Node<T> node) {
    this.tree = node;
  }

  private List<Node<T>> calculateLeaves(Node<T> node, List<Node<T>> leaves) {
    if (node.isLeaf()) {
      leaves.add(node);
    } else {
      node.getChildren().stream().forEach(x-> calculateLeaves(x, leaves));
    }
    return leaves;
  }

  public List<List<T>> build() {
    // for each leave we would like to resolve the complete path.
    // This would give us the complete solution
    return calculateLeaves(tree, new ArrayList<>()).stream().map(this::getPath).collect(Collectors.toList());

  }

  private List<T> getPath(final Node<T> leaf) {
    List<T> path = new ArrayList<>();
    path.add(leaf.getData().get());

    Optional<Node<T>> pathPart = leaf.getParent();
    while (pathPart.isPresent() && pathPart.get().getParent().isPresent()) {
      path.add(pathPart.get().getData().get());
      pathPart = pathPart.get().getParent();
    }

    Collections.reverse(path);
    return path;
  }
}
