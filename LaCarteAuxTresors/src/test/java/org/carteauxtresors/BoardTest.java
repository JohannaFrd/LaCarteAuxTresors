package org.carteauxtresors;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Optional;


public class BoardTest extends TestCase {

    @Test
    public void testBoardFactoryWithDataset1() {
        Optional<Board> myBoard = Board.boardFactory("src/data/data1");
        assertNotNull(myBoard.get());


    }

    @Test
    public void testBoardFactoryWithDataSet2() {
        Optional<Board> myBoard = Board.boardFactory("src/data/data2");
        assertNotNull(myBoard.get());
    }

    @Test
    public void testBoardFactoryWithDataSet3() {
        Optional<Board> myBoard = Board.boardFactory("src/data/data3");
        assertNotNull(myBoard.get());
    }


    @Test
    public void testBoardFactoryWithWrongPathFile() {
        Optional<Board> myBoard = Board.boardFactory("src/data/wrong");
        assertThrows(NoSuchElementException.class,  ()->myBoard.get());
    }

    @Test
    public void testBoardFactoryWithWrongConfFile() {
        Optional<Board> myBoard = Board.boardFactory("src/data/wrongData");
        assertThrows(NoSuchElementException.class,  ()->myBoard.get());
    }


    @Test
    public void testBoardFactoryWithWrongConfFile2() {
        Optional<Board> myBoard = Board.boardFactory("src/data/wrongData2");
        assertThrows(NoSuchElementException.class,  ()->myBoard.get());
    }

    @Test
    public void testBoardFactoryWithWrongConfFile3() {
        Optional<Board> myBoard = Board.boardFactory("src/data/wrongData3");
        assertThrows(NoSuchElementException.class,  ()->myBoard.get());
    }
    /*
    @Test
    public void testBoardFactoryWithWrongConfFile4() {
        Optional<Board> myBoard = Board.boardFactory("src/data/wrongData4");
        assertThrows(NoSuchElementException.class,  ()->myBoard.get());
    }
    */


}