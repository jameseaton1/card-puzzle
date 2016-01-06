package com.eatech.puzzle.crypto;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jameseaton@hotmail.com
 * Created on 10/12/2015
 */
public class BoardFactoryTest {

  @Test
  public void createBoard() {
    BoardFactory factory = new BoardFactory();
    Assert.assertNotNull("Create factory", factory.getBoard(25));
  }
}
