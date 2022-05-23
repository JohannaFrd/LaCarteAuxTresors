package org.carteauxtresors;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Optional;


public class BoardTest extends TestCase {

    @Test
    public void testBoardFactoryWithDataset1() {
        var game = Game.gameFactory("src/data/data1");
        assertNotNull(game.get());
    }

    @Test
    public void testBoardFactoryWithDataSet2() {
        var game = Game.gameFactory("src/data/data2");
        assertNotNull(game.get());
    }

    @Test
    public void testBoardFactoryWithDataSet3() {
        var game = Game.gameFactory("src/data/data3");
        assertNotNull(game.get());
    }


    @Test
    public void testBoardFactoryWithWrongPathFile() {
        var game = Game.gameFactory("src/data/wrong");
        assertThrows(NoSuchElementException.class,  ()->game.get());
    }

    @Test
    public void testBoardFactoryWithWrongConfFile() {
        var game = Game.gameFactory("src/data/wrongData");
        assertThrows(NoSuchElementException.class,  ()->game.get());
    }


    @Test
    public void testBoardFactoryWithWrongConfFile2() {
        var game = Game.gameFactory("src/data/wrongData2");
        assertThrows(NoSuchElementException.class,  ()->game.get());
    }

    @Test
    public void testBoardFactoryWithWrongConfFile3() {
        var game = Game.gameFactory("src/data/wrongData3");
        assertThrows(NoSuchElementException.class,  ()->game.get());
    }
    /*
    @Test
    public void testBoardFactoryWithWrongConfFile4() {
        Optional<Board> myBoard = Board.boardFactory("src/data/wrongData4");
        assertThrows(NoSuchElementException.class,  ()->myBoard.get());
    }
    */


}