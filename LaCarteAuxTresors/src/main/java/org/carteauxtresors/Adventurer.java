package org.carteauxtresors;

public class Adventurer extends ObjectFile implements Player{

    public Adventurer(String[] line) {
        super(line);
    }

    @Override
    public boolean addOnBoard() {
        return false;
    }

    @Override
    public boolean addOnPlayer() {
        return true;
    }
}
