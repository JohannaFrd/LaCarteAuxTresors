package org.carteauxtresors;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.carteauxtresors.Board.checkIndex;


public class Game {

    private final List<Player> players;
    private final Board board;

    private final static Logger LOGGER = Logger.getLogger(Board.class.getName());

    public Game(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }


    /**
     * Board factory
     * Check the file path
     * Create a new double array and fill this array with the configuration file
     * Create a new board instance with the new array
     *
     * Return a empty optionnal if the path is wrong , if the file is empty or access denied.
     *
     * @param path the path
     * @return a optional of board
     */
    public static Optional<Game> gameFactory(String path){
        /**
         * Check parameters
         */
        Objects.requireNonNull(path);
        if(path.isEmpty()){
            LOGGER.severe("Map file not found");
            return Optional.empty();
        }

        /**
         * Try with ressources to read configuration file
         */
        try(Stream<String> lines = Files.lines(Path.of(path), Charset.defaultCharset())){
            LOGGER.info("File Found");
            Optional<Game> game = createGame(lines.toList());
            if(game.isEmpty()){
                return Optional.empty();
            }

            LOGGER.info("Board created");
            return game;
        }catch (IOException ioException){
            LOGGER.severe("I/O exception");
            return Optional.empty();

        }catch (SecurityException securityException){
            LOGGER.severe("Security Exception");
            return Optional.empty();
        }
    }


    /**
     * Method to fill board
     * @param lines
     * @return Square tab
     */
    private static Optional<Game> createGame(List<String> lines) {
        if(Objects.requireNonNull(lines).isEmpty()){
            LOGGER.severe("The configuration file is empty");
            return Optional.empty();
        }
        String mapConfiguration = lines.get(0);
        if( !mapConfiguration.startsWith("C") ){
            LOGGER.severe("Map configuration not found !");
            return Optional.empty();
        }
        String[] configurationCharacteres = lines.get(0).replace(" ", "").split("-");

        if(configurationCharacteres.length <= 2){
            LOGGER.severe("Wrong map configuration !");
            return Optional.empty();
        }

        int sizeX ;
        int sizeY;

        try {
            sizeX = Integer.parseInt(configurationCharacteres[1]);
            sizeY = Integer.parseInt(configurationCharacteres[2]);
        }catch (NumberFormatException numberFormatException){
            LOGGER.severe("Wrong map configuration !");
            return Optional.empty();
        }
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder(sizeX, sizeY);
        List<Player> playerList = new ArrayList<>();

        lines.stream()
                .filter(s -> ! s.startsWith("#"))
                .filter(s -> s.matches("\\w - \\d+ - \\d+"))
                .filter(objects -> objects.length() >= 2)
                .map(s -> Arrays.stream(s.replace(" ", "").split("-")) )
                .forEach(stringStream -> {

                    var array =  stringStream.toArray(String[]::new);
                    if (checkIndex(array, sizeX, sizeY)) return;

                    switch (array[0]){
                        case "A" -> {
                            playerList.add(new Adventurer(array));
                        }
                        case "T" -> {
                           boardBuilder.setSquare(new TresorsSquare(array), Integer.parseInt(array[1]), Integer.parseInt(array[2]) );
                        }
                        case "M" -> {
                            boardBuilder.setSquare(new Montain(array), Integer.parseInt(array[1]), Integer.parseInt(array[2]) );
                        }
                        default -> LOGGER.warning(" Instruction not found ");
                    }
                } );
        Board finalBoard = boardBuilder.buildBoard();
        return Optional.of(new Game(List.copyOf(playerList), finalBoard));
    }

}
