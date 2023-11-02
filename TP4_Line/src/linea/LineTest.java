package linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {

    public static final char symbol = '-';

    @Test public void test01NumberOfColumnsIsCorrectWhenCreated() {
        Linea linea = new Linea( 3, 5, symbol);
        assertTrue( linea.isColumnWithinBounds(0));
        assertTrue( linea.isColumnWithinBounds( 1));
        assertTrue( linea.isColumnWithinBounds( 2));
    }

    @Test public void test02ColumnsOutsideTheBoundsAreNotInTheGame() {
        Linea linea = new Linea( 3, 5, symbol );
        assertFalse( linea.isColumnWithinBounds( -1));
        assertFalse( linea.isColumnWithinBounds( 4));
    }

    @Test public void test03NumberOfRowsIsCorrectWhenCreated() {
        Linea linea = new Linea( 3, 5, symbol);
        assertTrue( linea.isRowWithinBounds( 1));
        assertTrue( linea.isRowWithinBounds( 2));
        assertTrue( linea.isRowWithinBounds( 3));
        assertTrue( linea.isRowWithinBounds( 4));
        assertTrue( linea.isRowWithinBounds( 5));
    }

    @Test public void test04RowsOutsideTheBoundsAreNotInTheGame() {
        Linea linea = new Linea( 3, 5, symbol);
        assertFalse( linea.isRowWithinBounds( 0));
        assertFalse( linea.isRowWithinBounds( 6));
    }

    @Test public void test05EmptyLineaShowsIsCorrectFor1x1Board() {
        Linea linea = new Linea( 1, 1, symbol);
        assertEquals( "| - |\n",
                linea.show() );
    }
    @Test public void test06EmptyLineaShowsAnArbitraryNumberOfColumns() {
        Linea linea = new Linea( 3, 1, symbol);
        assertEquals( "| - - - |\n",
                linea.show() );
    }

    @Test public void test07EmptyLineaShowsAnArbitraryNumberOfRows() {
        Linea linea = new Linea( 1, 3, symbol);
        assertEquals(
                        "| - |\n" +
                        "| - |\n" +
                        "| - |\n",
                linea.show() );
    }

    @Test public void test08EmptyLineaShowsAnArbitraryNumberOfRowsAndColumns() {
        Linea linea = new Linea( 3, 2, symbol);
        assertEquals(
                        "| - - - |\n" +
                        "| - - - |\n",
                linea.show() );
    }

    @Test public void test09LineGameIsNotFinishedWhenCreated() {
        Linea linea = new Linea( 3, 5, symbol);
        assertFalse( linea.finished() );
    }

    @Test public void test10AddingAChipToAnBoardWithAColumn() {
        Linea linea = new Linea( 1, 5, symbol);
        linea.playRedkAt(0);
        assertEquals(
                        "| - |\n" +
                        "| - |\n" +
                        "| - |\n" +
                        "| - |\n" +
                        "| R |\n",
                linea.show() );
    }

    @Test public void test11AddingAChipToAnEmptyColumn() {
        Linea linea = new Linea( 3, 5, symbol);
        assertEquals(
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n",
                linea.show() );
        linea.playRedkAt(1);
        assertEquals(
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - R - |\n",
                linea.show() );
    }

    @Test public void test12AddedChipAppears() {
        Linea linea = new Linea( 3, 5, symbol);
        linea.playRedkAt(1);
        assertTrue( linea.isRed( 1, 0) );
        assertFalse( linea.isRed( 0, 0) );
    }

    @Test public void test13AddingBlueChips() {
        Linea linea = new Linea( 3, 5, symbol);
        linea.playBlueAt(1);
        assertTrue( linea.isBlue( 1, 0) );
        assertFalse( linea.isBlue( 0, 0) );
    }

    @Test public void test14RedAndBluePlaying() {
        Linea linea = new Linea( 3, 5, symbol);
        linea.playRedkAt(1);
        linea.playBlueAt(2);
        assertEquals(
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - R B |\n",
                linea.show() );
    }

    @Test public void test15AddingAChipUponAnotherOne() {
        Linea linea = new Linea( 3, 5, symbol);
        linea.playRedkAt(1);
        linea.playBlueAt(1);
        assertEquals(
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - - - |\n" +
                        "| - B - |\n" +
                        "| - R - |\n",
                linea.show() );
    }

    @Test public void test16AddingChipsToTheTop() {
        Linea linea = new Linea( 3, 5, symbol);
        linea.playRedkAt(1);
        linea.playBlueAt(1);
        linea.playRedkAt(1);
        linea.playBlueAt(1);
        linea.playRedkAt(1);
        assertEquals(
                        "| - R - |\n" +
                        "| - B - |\n" +
                        "| - R - |\n" +
                        "| - B - |\n" +
                        "| - R - |\n",
                linea.show() );
    }

    @Test public void test17TryingToAddAChipToAFullColumn() {
        Linea linea = new Linea( 3, 5, symbol);
        linea.playRedkAt(1);
        linea.playBlueAt(1);
        linea.playRedkAt(1);
        linea.playBlueAt(1);
        linea.playRedkAt(1);
        assertEquals( "Column is full", assertThrows(Exception.class, () -> linea.playBlueAt(1)).getMessage());
    }

    @Test public void test18GameIsFinishedWhenBoardIsFull() {
        Linea linea = new Linea( 1, 2, symbol);
        linea.playRedkAt(0);
        linea.playBlueAt(0);
        assertTrue( linea.finished() );
    }

    @Test public void test19RedWinsWithAColumnOf4Chips() {
        Linea linea = new Linea( 2, 5, symbol);
        linea.playRedkAt(0);
        linea.playBlueAt(1);
        linea.playRedkAt(0);
        linea.playBlueAt(1);
        linea.playRedkAt(0);
        linea.playBlueAt(1);
        linea.playRedkAt(0);
        assertTrue( linea.finished() );
    }

    @Test public void test20RedDoesNotWinsIfThereIsABlueInTheMiddle() {
        Linea linea = new Linea( 2, 5, symbol);
        linea.playRedkAt(0);
        linea.playBlueAt(1);
        linea.playRedkAt(0);
        linea.playBlueAt(1);
        linea.playRedkAt(0);
        linea.playBlueAt(0);
        linea.playRedkAt(0);
        assertFalse( linea.finished() );
    }
}
