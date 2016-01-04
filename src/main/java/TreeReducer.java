import model.Block;
import model.Colour;
import model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jameseaton@hotmail.com
 * Created on 04/01/2016
 */
public class TreeReducer {

  private final Node<Block> tree;

  public TreeReducer(final Node<Block> tree, final Colour[] colours) {
    this.tree = reduce(tree, getColourPositions(colours));

  }

  private Node<Block> reduce(final Node<Block> tree, final Colour[] colours) {
    List<Integer> positions = getColourPositions(colours);



    return null;
  }


  private List<Node<Block>> calculateLeaves(Node<Block> node, List<Node<Block>> leaves) {
    if (node.isLeaf()) {
      leaves.add(node);
    } else {
      node.getChildren().stream().forEach(x-> calculateLeaves(x, leaves));
    }
    return leaves;
  }

  public List<List<Block>> build() {
    // for each leave we would like to resolve the complete path.
    // This would give us the complete solution
    return calculateLeaves(tree, new ArrayList<>()).stream().map(this::getPath).collect(Collectors.toList());

  }

  private List<Block> getPath(final Node<Block> leaf) {
    List<Block> path = new ArrayList<>();
    path.add(leaf.getData().get());

    Optional<Node<Block>> pathPart = leaf.getParent();
    while (pathPart.isPresent() && pathPart.get().getParent().isPresent()) {
      path.add(pathPart.get().getData().get());
      pathPart = pathPart.get().getParent();
    }

    Collections.reverse(path);
    return path;
  }


  // Reduce to positions of blocks that have to be covered
  private List<Integer> getColourPositions(Colour[] colours) {
    List<Integer> positions = new ArrayList<>();
    for (int i = 0; i< colours.length; i++) {
      if (colours[i] == Colour.BLACK) {
        positions.add(i + 1);
      }
    }
    return positions;
  }

  public Node<Block> getTree() {
    return tree;
  }
}
