package com.eatech.puzzle.crypto.model;

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

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Block block = (Block) o;

    if (length != block.length) return false;
    if (position != block.position) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = position;
    result = 31 * result + length;
    return result;
  }
}
