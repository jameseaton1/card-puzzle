package com.eatech.puzzle.crypto;

import com.eatech.puzzle.crypto.model.Colour;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jameseaton@hotmail.com
 * Created on 09/12/2015
 */
public class Board {

  private final int boardSize;
  private Colour[][] squares = new Colour[25][25];
  private final List<List<Integer>> xCombinations;
  private final List<List<Integer>> yCombinations;


  public Board(int boardSize, List<List<Integer>> xCombinations, List<List<Integer>> yCombinations) {

    this.boardSize = boardSize;
    this.squares = new Colour[boardSize][boardSize];
    this.xCombinations = xCombinations;
    this.yCombinations = yCombinations;

    for (int x = 0; x<boardSize; x++) {
      for (int y = 0; y<boardSize; y++) {
        squares[x][y] = Colour.WHITE;
      }
    }
  }

  public List<List<Integer>> getYCombinations() {
    return yCombinations;
  }

  public Colour[][] getSquares() {
    Colour[][] copyOfSquares = new Colour[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      copyOfSquares[i] = Arrays.copyOf(squares[i], squares.length);
    }

    return copyOfSquares;
  }

  public void setColour(final int x, final int y, final Colour colour) {
    squares[x][y] = colour;
  }

  public List<List<Integer>> getXCombinations() {
    return xCombinations;
  }

  public int getBoardSize() {
    return boardSize;
  }
}
