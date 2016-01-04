import model.Block;
import model.Colour;
import model.Node;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by jameseaton@hotmail.com
 * Created on 14/12/2015
 */
public class PathBuilderTest {

  /**
   * The should be two paths: 2,3 and 3,4
   *
   * Since 3 and 4 will be both leaves of the tree with 2 and 3 being their respective parents
   *
   */
  @Test
  public void createPathsTreeShape1() {
    Colour[] colours = new Colour[]{Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE};

    Node<Block> root = Node.buildRoot();

    Node<Block> firstLevelNode1 = Node.build(Block.buildFor(2,2), root);
    Node<Block> firstLevelNode2 = Node.build(Block.buildFor(3,2), root);

    Node.build(Block.buildFor(3,2), firstLevelNode1);
    Node.build(Block.buildFor(4,2), firstLevelNode2);


    PathBuilder<Block> pathBuilder = new PathBuilder<>(colours, root);

    List<List<Block>> paths = pathBuilder.build();

    assertEquals("There should be 2 paths", 2, paths.size());
   // assertThat("There should the first be a correct path", paths, hasItem(IntStream.of(2, 3).boxed().collect(toList())));
   // assertThat("There should the second be a correct path", paths, hasItem(IntStream.of(3, 4).boxed().collect(toList())));
  }



  /**
   * The should be three paths: 1,3 ..... 1,4 and 1,5
   *
   *
   */
  @Test
  public void createPathsTreeShape2() {
    Colour[] colours = new Colour[]{Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE};

    Node<Integer> root = Node.buildRoot();

    Node<Integer> firstLevelNode1 = Node.build(1, root);

    Node.build(3, firstLevelNode1);
    Node.build(4, firstLevelNode1);
    Node.build(5, firstLevelNode1);

    PathBuilder pathBuilder = new PathBuilder(colours, root);

    List<List<Integer>> paths = pathBuilder.build();

    assertEquals("There should be 3 paths", 3, paths.size());
    assertThat("There should the first be a correct path", paths, hasItem(IntStream.of(1, 3).boxed().collect(toList())));
    assertThat("There should the second be a correct path", paths, hasItem(IntStream.of(1, 4).boxed().collect(toList())));
    assertThat("There should the third be a correct path", paths, hasItem(IntStream.of(1, 5).boxed().collect(toList())));
  }


  /**
   * The should be two paths: 1,2,3 ..... 1,2,4
   *
   *
   */
  @Test
  public void createPathsTreeShape3() {
    Colour[] colours = new Colour[]{Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE, Colour.WHITE};

    Node<Integer> root = Node.buildRoot();

    Node<Integer> firstLevelNode = Node.build(1, root);

    Node<Integer> secondLevelNode = Node.build(2, firstLevelNode);
    Node.build(3, secondLevelNode);
    Node.build(4, secondLevelNode);

    PathBuilder pathBuilder = new PathBuilder(colours, root);

    List<List<Integer>> paths = pathBuilder.build();

    assertEquals("There should be 2 paths", 2, paths.size());
    assertThat("There should the first be a correct path", paths, hasItem(IntStream.of(1, 2, 3).boxed().collect(toList())));
    assertThat("There should the second be a correct path", paths, hasItem(IntStream.of(1, 2, 4).boxed().collect(toList())));
  }

}
