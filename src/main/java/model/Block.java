package model;

/**
 * Created by jameseaton@hotmail.com
 * Created on 04/01/2016
 */
public class Block {
  private final int position;
  private final int length;

  private Block(final int position, final int length) {
    this.position = position;
    this.length = length;
  }

  public int getPosition() {
    return position;
  }

  public int getLength() {
    return length;
  }

  public static Block buildFor(final int position, final int length) {
    return new Block(position, length);
  }
}
