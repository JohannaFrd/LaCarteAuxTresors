package org.carteauxtresors.square;

import org.carteauxtresors.player.Player;
import org.carteauxtresors.Position;

/**
 * The interface Square.
 */
public interface Square  {
     /**
      * Remove player.
      */
     void removePlayer();

     /**
      * Accept square.
      *
      * @param squareVisitor the square visitor
      * @param player        the player
      * @return the square
      */
     public Square accept(SquareVisitor squareVisitor, Player player);

     /**
      * Gets player.
      *
      * @return the player
      */
     Player getPlayer();

     /**
      * To string string.
      *
      * @param position the position
      * @return the string
      */
     String toString(Position position);

}
