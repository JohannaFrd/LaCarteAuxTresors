package org.carteauxtresors;

public class Montain extends ObjectFile implements  Square {

    public Montain(String[] line) {
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
        return false;
    }
}
