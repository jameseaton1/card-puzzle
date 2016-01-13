package com.eatech.puzzle.crypto;

import com.eatech.puzzle.crypto.model.Block;
import com.eatech.puzzle.crypto.model.Node;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jameseaton@hotmail.com
 * Created on 04/01/2016
 */
public class BaseTreeBuilderTest {

  protected List<Integer> getCombinations() {
    return IntStream.of(2, 1).boxed().collect(Collectors.toList());
  }


  /**
   * Does a node having a child containing a block with that position
   *
   * @param position
   * @return
   */
  protected BaseMatcher<Node> hasChildPosition(int position) {
    return new BaseMatcher<Node>() {
      @Override
      public boolean matches(final Object item) {
        Node<Block> node = (Node<Block>) item;

        for (Node<Block> child : node.getChildren()) {
          if (child.getData().get().getPosition() == position) {
            return true;
          }
        }
        return false;
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("Node should contain child with a position of ").appendValue(position);
      }

      @Override
      public void describeMismatch(final Object item, final
      Description description) {
        Node<Block> node = (Node<Block>) item;
        if (node.getData().isPresent()) {
          description.appendText("was").appendValue(((Node<Block>) item).getData().get().getPosition());
        } else {
          description.appendText("was null");
        }
      }

    };
  }



}
