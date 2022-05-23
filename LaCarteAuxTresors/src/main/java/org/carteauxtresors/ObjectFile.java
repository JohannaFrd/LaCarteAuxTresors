package org.carteauxtresors;

import java.util.Objects;

public abstract class ObjectFile {

    protected ObjectFile(String[] line) {
        Objects.requireNonNull(line);
    }

    public abstract boolean addOnBoard();
    public abstract boolean addOnPlayer();
}
