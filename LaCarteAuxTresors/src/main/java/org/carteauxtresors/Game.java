package org.carteauxtresors;

import org.carteauxtresors.player.Adventurer;
import org.carteauxtresors.player.Player;
import org.carteauxtresors.square.ApplyVisitor;
import org.carteauxtresors.square.Montain;
import org.carteauxtresors.square.Square;
import org.carteauxtresors.square.TresorsSquare;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.carteauxtresors.Board.checkIndex;


public class Game {


    private final LinkedHashMap<Player, Position> squarePlayerHashMap ;
    private final Board board;


    private static final ApplyVisitor applyVisitor = new ApplyVisitor();

    private  static final Logger LOGGER = Logger.getLogger(Board.class.getName());

    public Game(LinkedHashMap<Player, Position> players, Board board) {
        this.squarePlayerHashMap = players;
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

        Objects.requireNonNull(path);
        if(path.isEmpty()){
            LOGGER.severe("Map file not found");
            return Optional.empty();
        }


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
        LinkedHashMap<Player, Position> playerList = new LinkedHashMap<>();


        lines.stream()
                .filter(s -> ! s.startsWith("#"))
                .filter(s -> s.matches("\\w - \\d+ - \\d+") || s.matches("\\w - \\d+ - \\d+ - \\d+") || s.matches("\\w - \\w+ - \\d+ - \\d+ - \\w - \\w+") )
                .filter(objects -> objects.length() >= 2)
                .map(s -> Arrays.stream(s.replace(" ", "").split("-")) )
                .forEach(stringStream -> {
                    var array =  stringStream.toArray(String[]::new);

                    switch (array[0]){
                        case "A" -> {
                            playerList.put(new Adventurer(array), new Position(Integer.parseInt(array[2]), Integer.parseInt(array[3])));
                        }
                        case "T" -> {
                            if (!checkIndex(array, sizeX, sizeY)) return;
                            boardBuilder.setSquare(new TresorsSquare(array, null), Integer.parseInt(array[1]), Integer.parseInt(array[2]) );
                        }
                        case "M" -> {
                            if (!checkIndex(array, sizeX, sizeY)) return;
                            boardBuilder.setSquare(new Montain( null), Integer.parseInt(array[1]), Integer.parseInt(array[2]) );
                        }
                        default -> LOGGER.warning(" Instruction not found ");
                    }
                } );

        Board finalBoard = boardBuilder.buildBoard();
        playerList.forEach((player, position) -> {
            Square square = finalBoard.getSquare(position).accept(applyVisitor, player);
            finalBoard.updateSquare(square, position);
        });


        return Optional.of(new Game( playerList, finalBoard));
    }

    public void start(Path path){
        AtomicBoolean on = new AtomicBoolean(true);
        while (on.get()){
            on.set(false);
            squarePlayerHashMap.forEach((player, position) -> {
                Position playerPosition = player.getNextMove(position);
                if (playerPosition == null)  return;
                if(playerPosition.positionX() > board.getSizeX() || playerPosition.positionY() > board.getSizeY()) {
                    LOGGER.warning("out of board");
                    return;
                }
                on.set(true);
                if(playerPosition.positionX() > board.getSizeX()-1 || playerPosition.positionY() > board.getSizeY()-1 ) return;
                Square newPositionSquare = board.getSquare(playerPosition);
                if(newPositionSquare == null || newPositionSquare.getPlayer() != null){
                    return;
                }
                Square square = newPositionSquare.accept(applyVisitor, player);
                board.updateSquare(square, playerPosition);
                board.getSquare(position).removePlayer();
                squarePlayerHashMap.replace(player, playerPosition);
            });
        }

        printIntoFile(path);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.getSizeX(); i++) {
            for (int j = 0; j < board.getSizeY(); j++) {
                Position position = new Position(i, j);
                Square square = board.getSquare(position);
                stringBuilder.append(square.toString(position)).append("\n");
                if (square.getPlayer() != null) stringBuilder.append(square.getPlayer().toString(position)).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Print into file.
     *
     * @param path the path
     */
    public void printIntoFile(Path path)  {
        try(var writer = Files.newBufferedWriter(path)) {
            writer.write(this.toString());
        }catch (IOException e) {
            LOGGER.severe("Imposible to write the result");
        }
    }
}
