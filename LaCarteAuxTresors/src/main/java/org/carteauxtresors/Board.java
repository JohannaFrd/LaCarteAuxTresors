package org.carteauxtresors;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Board.
 */
public class Board {

    /**
     * Logger for board Class
     */
    private final static  Logger LOGGER = Logger.getLogger(Board.class.getName());
    /**
     * Board table
     * X = First dimension
     * Y = Second dimension
     */
    private final Square[][] boardTab;

    /**
     * Board constructor
     * Private method which create a new board object
     */
    private Board( Square[][] boardTab) {
        this.boardTab = Arrays.stream(boardTab).toArray(Square[][]::new);
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
    public static Optional<Board> boardFactory(String path){
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
            Optional<Square[][]> squares = createBoard(lines.toList());

            if (squares.isEmpty() ){
                LOGGER.severe("Impossible to create the board ");
                return Optional.empty();
            }

            Board newBoard = new Board(squares.get());
            LOGGER.info("Board created");
            return Optional.of(newBoard); //TODO check if is good to do return in try with ressources

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
    private static Optional<Square[][]> createBoard(List<String> lines) {
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

        Square[][] boardSquare = new Square[sizeX][sizeY];

        for (String line : lines) {
            if (line.startsWith("#")) continue;
            if(! line.matches("\\w - \\d+ - \\d+")){
                LOGGER.warning("Wrong line format !");
                continue;
            }


            String[] characters = line.replace(" ", "").split("-");
            if (characters[0].equalsIgnoreCase("T")){
                //TODO (do not forget to check index)
                System.out.printf("create treasor ");
            } else if (characters[0].equalsIgnoreCase("M")) {
                //TODO (do not forget to check index)
                System.out.printf("create montain ");
            } else if (characters[0].equalsIgnoreCase("A")) {
                System.out.printf("create adventurer ");
            } else if (characters[0].equalsIgnoreCase("C")) {
                System.out.printf("Get configuration ");
            }
        }

        return Optional.of(boardSquare);
    }


}
