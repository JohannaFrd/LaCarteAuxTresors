package org.carteauxtresors;

public class NormalSquare extends ObjectFile implements Square  {

    public NormalSquare(String[] line) {
        super(line);
    }

    @Override
    public boolean addOnBoard() {
        return true;
    }

    @Override
    public boolean addOnPlayer() {
        return false;
    }

    @Override
    public boolean AdventurerCanOn() {
        return true;
    }
}
