package org.carteauxtresors.direction;

import org.carteauxtresors.Position;


/**
 * The type West.
 */
public class West implements Direction {
    @Override
    public Position top(Position position) {
        return new Position(position.positionX()+1, position.positionY() );
    }

    @Override
    public Position left(Position position) {
        return new Position(position.positionX()  , position.positionY()+1 );
    }

    @Override
    public Position right(Position position) {
        return new Position(position.positionX() - 1 , position.positionY()-1 );
    }

    @Override
    public String toString() {
        return "W";
    }
}
