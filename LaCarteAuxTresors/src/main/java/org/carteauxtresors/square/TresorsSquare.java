package org.carteauxtresors.square;

import org.carteauxtresors.player.Player;
import org.carteauxtresors.Position;

import java.util.Objects;

/**
 * The type Tresors square.
 */
public class TresorsSquare  implements Square {

    private int tresors;
    private Player player;


    /**
     * Instantiates a new Tresors square.
     *
     * @param line   the line
     * @param player the player
     */
    public TresorsSquare(String[] line, Player player) {
        this.player = player;
        this.tresors = Objects.requireNonNull(Integer.parseInt(line[3]));
    }

    /**
     * Remove tresor boolean.
     *
     * @return the boolean
     */
    public boolean removeTresor(){
        if(tresors <= 0 ){
            return false;
        }else {
            tresors --;
            return true;
        }
    }

    /**
     * Get tresors int.
     *
     * @return the int
     */
    public int getTresors(){
        return tresors;
    }


    @Override
    public void removePlayer() {
        this.player = null;
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
        return "T"+" - "+position.positionX()+" - "+ position.positionY() +" - "+ tresors;
    }

}
