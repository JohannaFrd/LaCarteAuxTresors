package org.carteauxtresors.player;

import org.carteauxtresors.direction.Direction;
import org.carteauxtresors.Position;

import java.util.Objects;

/**
 * The type Adventurer.
 */
public class Adventurer  implements Player {
    private final char[] moves ;
    private final Direction direction ;
    private int step =0;

    private int tresors = 0;

    private final String name;

    /**
     * Instantiates a new Adventurer.
     *
     * @param line the line
     */
    public Adventurer(String[] line) {
        this.moves= Objects.requireNonNull(line[5].toCharArray());
        this.direction = Direction.getDirection(Objects.requireNonNull(line[5].charAt(0)));
        this.name =  Objects.requireNonNull(line[1]);
    }

    @Override
    public Position getNextMove(Position position) {
        if (step > moves.length-1 ) return null;
        step++;
        if(moves[step-1] == 'A' ){
            return direction.top(position);
        } else if (moves[step-1] == 'G' ) {
            return direction.left(position);
        } else if (moves[step-1] == 'D'){
            return direction.right(position);
        }else {
            return null;
        }
    }

    @Override
    public void AddTresor() {
        tresors ++ ;
    }

    @Override
    public String toString(Position position) {
        return "A"+" - "+this.name+" - "+ position.positionX() +" - "+position.positionY()+" - "+this.direction.toString() +" - "+ tresors;
    }


}
