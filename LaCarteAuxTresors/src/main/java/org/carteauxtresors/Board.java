package org.carteauxtresors;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

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
     * Check index
     * @param array
     * @param maxX
     * @param maxY
     * @return boolean
     */
    public static boolean checkIndex(String[] array, int maxX , int maxY){
        try {
            if(Integer.parseInt(array[1]) > maxX || Integer.parseInt(array[1]) > maxY ){
                LOGGER.warning(" Wrong index ");
                return false;
            }
        }catch (NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }


    /**
     * Method to fill board with lambda squares
     * @param boardTab
     * @param sizeX
     * @param sizeY
     * @return Square tab
     */
    private static Square[][] fillSquare(Square[][] boardTab, int sizeX, int sizeY){
        Objects.requireNonNull(boardTab);

        for (int i = 0; i < sizeX ; i++) {
            for (int j = 0; j < sizeY ; j++) {

                if(boardTab[i][j] == null){
                    boardTab[i][j] = new NormalSquare(new String[]{i+"",j+""});
                }
            }
        }
        return boardTab;
    }


    public static class BoardBuilder{
        private final Square[][] boardTab;
        private final int sizeX;
        private final int sizeY;

        public BoardBuilder(int sizeX, int sizeY) {
            this.boardTab = new Square[sizeX][sizeY];
            this.sizeX=sizeX;
            this.sizeY=sizeY;

        }
        public BoardBuilder setSquare(Square square, int positionX, int positionY){
            if(positionX > sizeX || positionY > sizeY) LOGGER.warning(" out of board !");
            if (boardTab[positionX][positionY] != null )  LOGGER.warning(" Already defined !");
            boardTab[positionX][positionY] = Objects.requireNonNull(square);
            return this;
        }

        public Board buildBoard(){
            Square[][] filledSquare = fillSquare(boardTab, sizeX, sizeY);
            Square[][] cloneSquare = new Square[sizeX][sizeY];
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j <sizeY ; j++) {
                    cloneSquare[i][j] = filledSquare[i][j];
                }
            }
            return new Board(cloneSquare);
        }
    }



}
