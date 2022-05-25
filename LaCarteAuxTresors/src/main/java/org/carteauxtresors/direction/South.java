package org.carteauxtresors.direction;

import org.carteauxtresors.Position;
import org.carteauxtresors.direction.Direction;

public class South implements Direction {
    @Override
    public Position top(Position position) {
        return new Position(position.positionX(), position.positionY()-1 );
    }

    @Override
    public Position left(Position position) {
        return new Position(position.positionX() +1 , position.positionY() );
    }

    @Override
    public Position right(Position position) {
        return new Position(position.positionX() - 1 , position.positionY() );
    }

    @Override
    public String toString() {
        return "S";
    }
}
