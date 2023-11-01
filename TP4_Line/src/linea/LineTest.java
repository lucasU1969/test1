package linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {

    @Test public void test01NumberOfColumnsIsCorrectWhenCreated() {
        Linea linea = new Linea( 3, 5, '*' );
        assertTrue( linea.isColumnWithinBounds( 1));
        assertTrue( linea.isColumnWithinBounds( 2));
        assertTrue( linea.isColumnWithinBounds(3));
    }

    @Test public void test02ColumnsOutsideTheBoundsAreNotInTheGame() {
        Linea linea = new Linea( 3, 5, '*' );
        assertFalse( linea.isColumnWithinBounds( 0));
        assertFalse( linea.isColumnWithinBounds( 4));
    }

    @Test public void test03NumberOfRowsIsCorrectWhenCreated() {
        Linea linea = new Linea( 3, 5, '*' );
        assertTrue( linea.isRowWithinBounds( 1));
        assertTrue( linea.isRowWithinBounds( 2));
        assertTrue( linea.isRowWithinBounds( 3));
        assertTrue( linea.isRowWithinBounds( 4));
        assertTrue( linea.isRowWithinBounds( 5));
    }

    @Test public void test04RowsOutsideTheBoundsAreNotInTheGame() {
        Linea linea = new Linea( 3, 5, '*' );
        assertFalse( linea.isRowWithinBounds( 0));
        assertFalse( linea.isRowWithinBounds( 6));
    }

//    @Test public void test05LineGameIsNotFinishedWhenCreated() {
//        Linea linea = new Linea( 3, 5, '*' );
//        assertFalse( linea.finished() );
//    }
    @Test public void test05LineaShowsIsCorrectFor1x1Board() {
        Linea linea = new Linea( 1, 1, '*' );
        assertEquals( "| - |\n",
                linea.show() );
    }

    @Test public void test06LineaShowsAnArbitraryNumberOfColumns() {
        Linea linea = new Linea( 3, 1, '*' );
        assertEquals( "| - - - |\n",
                linea.show() );
    }

    @Test public void test07LineaShowsAnArtibraryNumberOfRows() {
        Linea linea = new Linea( 1, 3, '*' );
        assertEquals( "| - |\n" +
                      "| - |\n" +
                      "| - |\n",
                linea.show() );
    }


}
