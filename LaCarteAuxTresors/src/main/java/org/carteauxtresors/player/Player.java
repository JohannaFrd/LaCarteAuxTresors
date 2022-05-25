package org.carteauxtresors.player;

import org.carteauxtresors.Position;

/**
 * The interface Player.
 */
public interface Player {
     /**
      * Gets next move.
      *
      * @param position the position
      * @return the next move
      */
     Position getNextMove(Position position);

     /**
      * Add tresor.
      */
     void AddTresor();

     /**
      * To string string.
      *
      * @param position the position
      * @return the string
      */
     String toString(Position position);




}
