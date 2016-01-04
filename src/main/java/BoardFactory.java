import model.Colour;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jameseaton@hotmail.com
 * Created on 10/12/2015
 */
public class BoardFactory {

  private Board loadBlackSquares(Board board) {
    URL resource = Board.class.getResource("blacks.dat");
    try {
      Files.lines(Paths.get(resource.toURI()))
          .map(l -> l.split(","))
          .forEach(x -> board.setColour(Integer.valueOf(x[0]),Integer.valueOf(x[1]), Colour.BLACK));
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    return board;
  }

  private List<List<Integer>> loadYCombinations() {
    return loadCombinations("y.dat");
  }



  private List<List<Integer>> loadXCombinations() {
    return loadCombinations("x.dat");
  }

  private List<List<Integer>> loadCombinations(String filename) {
    URL resource = Board.class.getResource(filename);
    try {
      return Files.lines(Paths.get(resource.toURI()))
                  .map(l -> Arrays.asList(l.split(","))
                  .stream()
                     .map(Integer::valueOf)
                     .collect(Collectors.toList()))
                  .collect(Collectors.toList());
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Board getBoard(int boardSize) {
    return loadBlackSquares(new Board(boardSize, loadXCombinations(), loadYCombinations()));
  }
}
