package org.carteauxtresors.direction;

import org.carteauxtresors.*;

/**
 * The interface Direction.
 */
public interface Direction {

    /**
     * Top position.
     *
     * @param position the position
     * @return the position
     */
    Position top(Position position);

    /**
     * Left position.
     *
     * @param position the position
     * @return the position
     */
    Position left(Position position);

    /**
     * Right position.
     *
     * @param position the position
     * @return the position
     */
    Position right(Position position);

    /**
     * Get direction direction.
     *
     * @param c the direction
     * @return the direction
     */
    static Direction getDirection(char c){
        switch (c){
            case 'N' -> {
                return new North();
            }
            case 'E' -> {
                return new East();
            }
            case 'W' -> {
                return new West();
            }
            case 'S' -> {
                return new South();
            }
            default -> {
                return  new North();
            }
        }
    }
}
