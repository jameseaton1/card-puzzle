package model;

import org.junit.Test;

import static model.Colour.BLACK;
import static model.Colour.WHITE;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by jameseaton@hotmail.com
 * Created on 14/12/2015
 */
public class NodeTest {

  @Test
  public void isLeafNode() {
    Colour[] data = new Colour[]{WHITE, WHITE, WHITE, WHITE, WHITE};
    Node<Colour[]> node = Node.build(data, null);

    // No children then you are a leaf node
    assertTrue(node.isLeaf());
  }

  @Test
  public void addTreeLeaf() {
    Colour[] data = new Colour[]{WHITE, WHITE, WHITE, WHITE, WHITE};
    Node<Colour[]> tree = Node.build(data, null);

    Node<Colour[]> node1 = getCombination1(tree);
    Node<Colour[]> node2 = getCombination2(tree);
    Node<Colour[]> node3 = getCombination3(tree);

    assertEquals(3, tree.getChildren().size());

    //Test the children
    assertThat(tree.getChildren(), hasItem(node1));
    assertThat(tree.getChildren(), hasItem(node2));
    assertThat(tree.getChildren(), hasItem(node3));

    //Test the parent
    assertEquals(tree, tree.getChildren().get(0).getParent().get());

  }

  private Node<Colour[]> getCombination1(Node<Colour[]> parent) {
    return Node.build(new Colour[]{BLACK, BLACK, WHITE, BLACK, WHITE}, parent);
  }

  private Node<Colour[]> getCombination2(Node<Colour[]> parent) {
    return Node.build(new Colour[]{WHITE, BLACK, BLACK, WHITE, BLACK}, parent);
  }

  private Node<Colour[]> getCombination3(Node<Colour[]> parent) {
    return Node.build(new Colour[]{BLACK, BLACK, WHITE, WHITE, BLACK}, parent);
  }




}
