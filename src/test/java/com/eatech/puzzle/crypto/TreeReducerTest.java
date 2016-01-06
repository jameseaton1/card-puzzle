package com.eatech.puzzle.crypto;

import com.eatech.puzzle.crypto.model.Block;
import com.eatech.puzzle.crypto.model.Colour;
import com.eatech.puzzle.crypto.model.Node;
import com.eatech.puzzle.crypto.tree.TreeBuilder;
import com.eatech.puzzle.crypto.tree.TreeReducer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by jameseaton@hotmail.com
 * Created on 04/01/2016
 */
public class TreeReducerTest extends BaseTreeBuilderTest {


  /**
   * Given the following tree
   *
   *
   *      Root
   *      /  \
   *     1    2
   *    / \    \
   *   4   5    5
   *
   *   And their is a block configuration of
   *   WWWWBW
   *
   *   Then there should only be 1 path
   *
   *      Root
   *      /
   *     1
   *    /
   *   4
   *
   */
  @Test
  public void testTreeReduction() {
    Colour[] colours = {Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE};
    List<Integer> combinations = getCombinations();
    TreeBuilder treeBuilder = new TreeBuilder(colours, combinations);
    Node<Block> tree = treeBuilder.getTree();
    TreeReducer treeReducer = new TreeReducer(tree, colours);
    tree = treeReducer.getTree();
    assertEquals(1, tree.getChildren().size());

    assertThat(tree.getChildren().stream().findFirst().get(), hasChildrenPosition(4));


  }
}
