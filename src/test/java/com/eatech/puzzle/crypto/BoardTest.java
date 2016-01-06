package com.eatech.puzzle.crypto;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jameseaton@hotmail.com
 * Created on 09/12/2015
 */
public class BoardTest {


  @Test
  public void shouldHaveCorrectYCombinations() {
    BoardFactory boardFactory = new BoardFactory();
    Board board = boardFactory.getBoard(25);
    assertArrayEquals(IntStream.of(7, 3, 1, 1, 7).toArray(), board.getYCombinations().get(0).stream()
                                                                                      .mapToInt(Integer::intValue)
                                                                                      .toArray());
  }

  @Test
  public void shouldHaveCorrectXCombinations() {
    BoardFactory boardFactory = new BoardFactory();
    Board board = boardFactory.getBoard(25);
    assertArrayEquals(IntStream.of(7, 2, 1, 1, 7).toArray(), board.getXCombinations().get(0).stream()
        .mapToInt(Integer::intValue)
        .toArray());
  }

  @Test
  public void shouldHaveCorrectBlackPieces() {
    BoardFactory boardFactory = new BoardFactory();
    Board board = boardFactory.getBoard(25);
    assertNotNull("The board has squares", board.getSquares());
  }



  /*
  @Test
  public void getRow() {
    com.eatech.puzzle.crypto.Board board = new com.eatech.puzzle.crypto.Board();
    Assert.assertEquals("Row is 25", 25, board.getRow(1).length);
  }

  @Test
  public void getColumn() {
    com.eatech.puzzle.crypto.Board board = new com.eatech.puzzle.crypto.Board();
    Assert.assertEquals("Row is 25", 25, board.getColumn(1).length);
  }

  @Test
  public void setColour() {
    com.eatech.puzzle.crypto.Board board = new com.eatech.puzzle.crypto.Board();
    board.setColour(0, 0, Colour.BLACK);
    Assert.assertEquals("Square set to black", board.getColumn(0)[0] = Colour.BLACK) ;
  }

  /*
  @Test
  public void boardSize() {
    com.eatech.puzzle.crypto.Board board = new com.eatech.puzzle.crypto.Board();
    assertEquals("The size of the board should be 626", 25 * 25, board.getSquares().size());
  }

  @Test
  public void boardShouldContainCorrectBlackSquares(){
    com.eatech.puzzle.crypto.Board board = new com.eatech.puzzle.crypto.Board();
    buildBlackSquares().stream().forEach(x-> Assert.assertTrue("com.eatech.puzzle.crypto.Board Contains Black Square", board.getSquares().contains(x)));
  }

  private Set<Square> buildBlackSquares() {
    return getBlackSCoordinates().stream().map(c -> new Square(c.getKey(), c.getValue(), Colour.BLACK)).collect(Collectors.toSet());
  }

  private List<Pair<Integer, Integer>> getBlackSCoordinates() {
    List<Pair<Integer, Integer>> coordinates = new ArrayList<Pair<Integer, Integer>>();
    coordinates.add(buildPair(4, 4));
    coordinates.add(buildPair(5, 4));
    coordinates.add(buildPair(10, 4));
    coordinates.add(buildPair(11, 4));
    coordinates.add(buildPair(16, 4));
    coordinates.add(buildPair(21, 4));
    coordinates.add(buildPair(22, 4));

    coordinates.add(buildPair(7, 9));
    coordinates.add(buildPair(12, 9));
    coordinates.add(buildPair(17, 9));
    coordinates.add(buildPair(21, 9));

    coordinates.add(buildPair(7, 17));
    coordinates.add(buildPair(8, 17));
    coordinates.add(buildPair(11, 17));
    coordinates.add(buildPair(15, 17));
    coordinates.add(buildPair(16, 17));
    coordinates.add(buildPair(19, 17));

    coordinates.add(buildPair(4, 22));
    coordinates.add(buildPair(5, 22));
    coordinates.add(buildPair(13, 22));
    coordinates.add(buildPair(14, 22));
    coordinates.add(buildPair(22, 22));
    return coordinates;
  }

  private Pair<Integer, Integer> buildPair(Integer x, Integer y) {
    return new Pair<Integer, Integer>(x,y);
  }*/

}
