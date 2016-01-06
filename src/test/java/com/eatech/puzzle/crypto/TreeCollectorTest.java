package com.eatech.puzzle.crypto;

import com.eatech.puzzle.crypto.model.Block;
import com.eatech.puzzle.crypto.model.Node;
import com.eatech.puzzle.crypto.tree.TreeCollector;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by jameseaton@hotmail.com
 * Created on 04/01/2016
 */
public class TreeCollectorTest extends BaseTreeBuilderTest {


  /**
   *  Given the following paths
   *
   *  1     1
   *  |     |
   *  3     3
   *  |     |
   *  5     4
   *
   *  Then the tree should look like
   *
   *     root
   *      |
   *      1
   *      |
   *      3
   *     / \
   *    4   5
   */
  @Test
  public void treeCollectTest() {

    Node<Block> tree = createPaths().stream().collect(new TreeCollector());

    Node<Block> firstNode = tree.getChildren().stream().findFirst().get();
    assertEquals(firstNode.getChildren().size(), 1);
    assertEquals(firstNode.getData().get(), Block.buildFor(1, 1));

    Node<Block> secondNode = firstNode.getChildren().stream().findFirst().get();
    assertEquals(secondNode.getChildren().size(), 2);
    assertEquals(secondNode.getData().get(), Block.buildFor(3, 1));

    Node<Block> thirdNode = secondNode.getChildren().stream().findFirst().get();
    assertThat(thirdNode.getData().get().getPosition(), CoreMatchers.either(CoreMatchers.is(5)).or(CoreMatchers.is(4)));



  }

  private List<List<Block>> createPaths() {

    List<List<Block>> paths = new ArrayList<>();

    List<Block> firstPath = new ArrayList<>();
    Block first = Block.buildFor(1,1);
    firstPath.add(first);
    Block second = Block.buildFor(3,1);
    firstPath.add(second);
    Block third = Block.buildFor(5,1);
    firstPath.add(third);

    paths.add(firstPath);

    List<Block> secondPath = new ArrayList<>();
    secondPath.add(first);
    secondPath.add(second);
    Block alternativeThird = Block.buildFor(4,1);
    secondPath.add(alternativeThird);

    paths.add(secondPath);

    return paths;
  }


}
