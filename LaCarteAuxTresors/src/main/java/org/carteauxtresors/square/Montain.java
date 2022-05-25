package org.carteauxtresors.square;

import org.carteauxtresors.player.Player;
import org.carteauxtresors.Position;

public class Montain   implements Square {

    private final Player player;

    public Montain( Player player) {
        this.player = player;
    }


    @Override
    public void removePlayer() {
        return;
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
        return "M"+" - "+position.positionX()+" - "+ position.positionY();
    }

}
