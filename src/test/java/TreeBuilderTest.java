import model.Block;
import model.Colour;
import model.Node;
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
    assertFalse(tree.getChildren().get(0).getChildren().isEmpty());

    // The nodes are either position 1 or 2
    assertThat(tree, allOf(hasChildrenPosition(1), hasChildrenPosition(2)));

    // The nodes are either position 3 or 5
    assertThat(tree.getChildren().get(0), anyOf(hasChildrenPosition(4), hasChildrenPosition(5)));
    assertThat(tree.getChildren().get(1), anyOf(hasChildrenPosition(4), hasChildrenPosition(5)));
  }


  /**
   *

  @Test
  public void shouldGenerateRowCombinations() {
    Board board = new Board(5, createTwoOneCombinationForBoard(), createTwoOneCombinationForBoard());
    TreeBuilder combinationBuilder = new TreeBuilder(board);
    List<Colour[]> combinations = combinationBuilder.getXCombinations(1);
    assertEquals("There should be 3 combinations", 3, combinations.size());
  }

  private List<List<Integer>> createTwoOneCombinationForBoard() {
    List<Integer> oneTwoCombinations = IntStream.of(2,1).boxed().collect(Collectors.toList());
    List<List<Integer>> rows = new ArrayList<>(5);
    for (int i = 0;i<5;i++) {
      rows.add(oneTwoCombinations);
    }
    return rows;
  }
   */
}
