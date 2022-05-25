package org.carteauxtresors;

import org.carteauxtresors.square.NormalSquare;
import org.carteauxtresors.square.Square;

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
    private static final   Logger LOGGER = Logger.getLogger(Board.class.getName());
    /**
     * Board table
     * X = First dimension
     * Y = Second dimension
     */
    private final Square[][] boardTab;

    private final int sizeX;
    private final int sizeY;

    /**
     * Board constructor
     * Private method which create a new board object
     */
    private Board(Square[][] boardTab, int sizeX, int sizeY) {
        this.boardTab = Arrays.stream(boardTab).toArray(Square[][]::new);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }


    /**
     * Check index
     *
     * @param array the array
     * @param maxX  the max x
     * @param maxY  the max y
     * @return boolean boolean
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
     * Gets size x.
     *
     * @return the size x
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Gets size y.
     *
     * @return the size y
     */
    public int getSizeY() {
        return sizeY;
    }


    /**
     * Update square.
     *
     * @param square   the square
     * @param position the position
     */
    public void updateSquare(Square square, Position position){
        Objects.requireNonNull(position);
        boardTab[position.positionX()][position.positionY()] = Objects.requireNonNull(square);
    }

    /**
     * Get square square.
     *
     * @param position the position
     * @return the square
     */
    public Square getSquare(Position position){

        if (position.positionX() > sizeX-1 || position.positionY() > sizeY-1  || position.positionY() < 0  || position.positionX() < 0  ){
            return null;
        }
        return boardTab[position.positionX()][position.positionY()];
    }


    /**
     * The type Board builder.
     */
    public static class BoardBuilder{
        private final Square[][] boardTab;
        private final int sizeX;
        private final int sizeY;

        /**
         * Instantiates a new Board builder.
         *
         * @param sizeX the size x
         * @param sizeY the size y
         */
        public BoardBuilder(int sizeX, int sizeY) {
            this.boardTab = new Square[sizeX][sizeY];
            this.sizeX=sizeX;
            this.sizeY=sizeY;

        }

        /**
         * Set square board builder.
         *
         * @param square    the square
         * @param positionX the position x
         * @param positionY the position y
         * @return the board builder
         */
        public BoardBuilder setSquare(Square square, int positionX, int positionY){
            if(positionX > sizeX || positionY > sizeY) LOGGER.warning(" out of board !");
            if (boardTab[positionX][positionY] != null )  LOGGER.warning(" Already defined !");
            boardTab[positionX][positionY] = Objects.requireNonNull(square);
            return this;
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
                        boardTab[i][j] = new NormalSquare(new String[]{i+"",j+""}, null);
                    }
                }
            }
            return boardTab;
        }

        /**
         * Build board board.
         *
         * @return the board
         */
        public Board buildBoard(){
            Square[][] filledSquare = fillSquare(boardTab, sizeX, sizeY);
            Square[][] cloneSquare = new Square[sizeX][sizeY];
            for (int i = 0; i < sizeX; i++) {
                System.arraycopy(filledSquare[i], 0, cloneSquare[i], 0, sizeY);
            }
            return new Board(cloneSquare, sizeX, sizeY);
        }
    }
}
