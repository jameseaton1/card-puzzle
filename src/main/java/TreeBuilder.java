import model.Block;
import model.Colour;
import model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates all the position combinations of positions of blocks for a row.
 * The combinations are organised in a tree.
 *
 * Created by jameseaton@hotmail.com
 * Created on 10/12/2015
 */
public class TreeBuilder {

  private static final int PADDING_LENGTH = 1;

  private final Node<Block> tree;
  private final Colour[] colours;

  public TreeBuilder(final Colour[] colours, final List<Integer> combinations) {
    this.colours = colours;
    tree = build(combinations, Node.buildRoot(), colours.length);
  }

  /**
   * Takes a list of possible lengths of each series of blocks and the size of the space they should fit in.
   * This is turned into a Tree representing all the possible positions of the blocks.
   *
   * @param combinations
   */
  private Node<Block> build(final List<Integer> combinations, Node<Block> tree, int length) {

    int blockSize = combinations.remove(0);

    int lengthOfRemainingPieces = combinations.stream().mapToInt(x->x).sum();
    int paddingOfRemainingPieces = (int) combinations.stream().mapToInt(x->x).count() * PADDING_LENGTH;
    int possibleLocations = factorial(length - lengthOfRemainingPieces - paddingOfRemainingPieces) / factorial(blockSize);


    int currentTreeLength = calculateCurrentTreeLength(tree);

    for(int location = currentTreeLength; location < possibleLocations + currentTreeLength; location++) {
      Node<Block> node = Node.build(Block.buildFor(location + 1, blockSize), tree);
      if (combinations.size() >= 1) {
        build(new ArrayList<>(combinations), node, colours.length - location - blockSize);
      }
    }
    return tree;

  }

  private int calculateCurrentTreeLength(Node<Block> tree) {
    if (tree.getData().isPresent()) {
      Block block = tree.getData().get();
      return block.getLength() + (block.getPosition() - 1) + PADDING_LENGTH;
    } else {
      return 0;
    }
  }



  public Node<Block> getTree() {

    return tree;
  }

  public int factorial(int n) {
    if (n > 20) throw new IllegalArgumentException(n + " is out of range");
    int product = 1;
    for (int i = 2; i < n; i++) {
      product *= i;
    }
    System.out.println("Factorial result: " + product);
    return product;
  }


 /* private final Board board;
  private final List<List<Colour[]>> xCombinations;

  public TreeBuilder(final Board board) {
    this.board = board;
    this.xCombinations = generateCombinations(board.getXCombinations());
  }

  private List<List<Colour[]>> generateCombinations(final List<List<Integer>> combinations) {
    return combinations.stream().map(this::generateColourCombinations).collect(Collectors.toList());
  }

  private List<Colour[]> generateColourCombinations(final List<Integer> values) {
    // Calculate of many for positions for first
    // Size required for other combinations = sum(1..n) + (n-1) --size of the pieces together + minimum gap between

    int length = values.get(0);
    int lengthOfRemainingPieces = values.stream().mapToInt(x->x).sum() - length;
    int paddingOfRemainingPieces = (int)(values.stream().mapToInt(x->x).count() -1);
    int possibleLocations = board.getBoardSize() - lengthOfRemainingPieces - paddingOfRemainingPieces;

    for (int location = 0; location < possibleLocations; location++) {

    }


    return null;
  }

  public List<Colour[]> getXCombinations(final int x) {
    return null;
  }*/
}
