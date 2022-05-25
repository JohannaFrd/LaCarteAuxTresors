package org.carteauxtresors.square;

import org.carteauxtresors.player.Player;
import org.carteauxtresors.square.Montain;
import org.carteauxtresors.square.NormalSquare;
import org.carteauxtresors.square.Square;
import org.carteauxtresors.square.TresorsSquare;

/**
 * The interface Square visitor.
 */
public interface SquareVisitor {
    /**
     * Visit square.
     *
     * @param normalSquare the normal square
     * @param player       the player
     * @return the square
     */
    public Square visit(NormalSquare normalSquare, Player player);

    /**
     * Visit square.
     *
     * @param tresorsSquare the tresors square
     * @param player        the player
     * @return the square
     */
    public Square visit(TresorsSquare tresorsSquare, Player player);

    /**
     * Visit square.
     *
     * @param montain the montain
     * @param player  the player
     * @return the square
     */
    public Square visit(Montain montain, Player player);

}
