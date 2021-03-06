package com.eatech.puzzle.crypto.tree;

import com.eatech.puzzle.crypto.PathBuilder;
import com.eatech.puzzle.crypto.model.Block;
import com.eatech.puzzle.crypto.model.Colour;
import com.eatech.puzzle.crypto.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class is to narrow the tree down from are possibilities to the ones that only work in the already positioned
 * blocks on the board.
 *
 * Created by jameseaton@hotmail.com
 * Created on 04/01/2016
 */
public class TreeReducer {

  private final Node<Block> tree;
  private final Colour[] colours;

  public TreeReducer(final Node<Block> tree, final Colour[] colours) {
    this.colours = colours;
    this.tree = reduce(tree);
  }

  private Node<Block> reduce(final Node<Block> tree) {
    PathBuilder<Block> pathBuilder = new PathBuilder<>(tree);
    return pathBuilder.build()
                      .stream()
                      .filter(this::doesPathFit)
                      .collect(new TreeCollector());

  }

  // This selects the path that can work with if all the colour blocks can fit into the combination
  private Boolean doesPathFit(List<Block> path) {
    List<Boolean> checks = getColourPositions(colours).stream()
                                                      .map(x -> doesBlockFit(x, path))
                                                      .collect(Collectors.toList());
    return checks.stream().allMatch(x -> x.equals(Boolean.TRUE));
  }

  private Boolean doesBlockFit(Integer colourPosition, List<Block> path) {
    // Does the path cover the colour block already on the board
    return path.stream().filter(x -> x.getPosition() >= colourPosition && x.getPosition() + (x.getLength() - 1) <= colourPosition)
                        .findAny()
                        .isPresent();
  }

  // Return a list of leaf nodes from the tree
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

  // Get the path from root to leaf. Calculated leaf first so needs reversing
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
