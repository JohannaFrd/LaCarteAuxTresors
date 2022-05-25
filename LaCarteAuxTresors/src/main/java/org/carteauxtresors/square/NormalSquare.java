package org.carteauxtresors.square;

import org.carteauxtresors.Position;
import org.carteauxtresors.player.Player;

/**
 * The type Normal square.
 */
public class NormalSquare implements Square {


    private Player player;


    public NormalSquare(String[] line, Player player) {
        this.player = player;
    }



    @Override
    public Square accept(SquareVisitor squareVisitor, Player player) {
        return squareVisitor.visit(this, player);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString(Position position) {
        return "";
    }


    @Override
    public void removePlayer() {
        this.player = null;
    }


}
