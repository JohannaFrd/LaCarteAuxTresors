package org.carteauxtresors.square;

import org.carteauxtresors.player.Player;

/**
 * The type Apply visitor.
 */
public class ApplyVisitor implements SquareVisitor {
    @Override
    public Square visit(NormalSquare normalSquare, Player player) {
        String[] strings = new String[1];
        return new NormalSquare(strings, player);
    }

    @Override
    public Square visit(TresorsSquare tresorsSquare, Player player) {
        player.AddTresor();
        String[] strings = new String[4];
        strings[3] = tresorsSquare.getTresors()+"";
        TresorsSquare square = new TresorsSquare(strings, player);
        square.removeTresor();
        return square;
    }

    @Override
    public Square visit(Montain montain, Player player) {
        return null;
    }

}
