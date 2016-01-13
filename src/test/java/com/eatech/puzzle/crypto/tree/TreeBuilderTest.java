package com.eatech.puzzle.crypto.tree;

import com.eatech.puzzle.crypto.BaseTreeBuilderTest;
import com.eatech.puzzle.crypto.model.Block;
import com.eatech.puzzle.crypto.model.Colour;
import com.eatech.puzzle.crypto.model.Node;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by jameseaton@hotmail.com
 * Created on 10/12/2015
 */
public class TreeBuilderTest extends BaseTreeBuilderTest {

  /**
   * If the board is 5x5
   * And the combinations are 2,1 then the following can only be the combinations
   *
   * WBBWB
   * BBWWB
   * BBWBW
   *
   *      Root
   *      /  \
   *     1    2
   *    / \    \
   *   4   5    5
   */
  @Test
  public void createTree() {
    Colour[] colours = {Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE};
    List<Integer> combinations = getCombinations();
    TreeBuilder treeBuilder = new TreeBuilder(colours, combinations);
    Node<Block> tree = treeBuilder.getTree();
    assertEquals(2, tree.getChildren().size());
    assertFalse(tree.getChildren().stream().findFirst().get().getChildren().isEmpty());

    // The nodes are either position 1 or 2
    assertThat(tree, allOf(hasChildPosition(1), hasChildPosition(2)));

    // The nodes are either position 3 or 5
    assertThat(tree.getChildren().stream().findFirst().get(), anyOf(hasChildPosition(4), hasChildPosition(5)));
    //assertThat(tree.getChildren().get(1), anyOf(hasChildPosition(4), hasChildPosition(5)));
  }

}
