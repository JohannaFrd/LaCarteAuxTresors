package org.carteauxtresors;


import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;


/**
 * Hello world!
 *
 */
public class App 
{

    private final static Logger LOGGER = Logger.getLogger(Board.class.getName());
    public static void main( String[] args ) {
        Optional<Game> game = Game.gameFactory("src/data/data1");
        if (game.isEmpty()) {
            LOGGER.severe("Game not create");
            return;
        }

        game.get().start( Path.of("src/data/out1"));
    }
}
