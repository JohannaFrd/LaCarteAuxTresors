package org.carteauxtresors;

public class TresorsSquare extends ObjectFile implements Square {

    private int tresors;

    public TresorsSquare(String[] line) {
        super(line);
        tresors = Integer.parseInt(line[3]);
    }

    @Override
    public boolean addOnBoard() {
        return false;
    }

    @Override
    public boolean addOnPlayer() {
        return false;
    }

    public boolean removeTresor(){
        if(tresors <= 0 ){
            return false;
        }else {
            tresors --;
            return true;
        }
    }

    @Override
    public boolean AdventurerCanOn() {
        return true;
    }
}
