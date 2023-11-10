package linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineaTest {

    @Test public void test01LineaInstantiatesWithTheCorrectAmountOfColumns() {
        Linea line = new Linea(3, 4, 'C');
        assertTrue(line.isColumnAvailable(1));
        assertTrue(line.isColumnAvailable(2));
        assertTrue(line.isColumnAvailable(3));
    }

    @Test public void test02LineaInstantiatesOnlyWithTheCorrectAmountOfColumns() {
        Linea line = new Linea(3, 4, 'C');
        assertFalse(line.isColumnAvailable(0));
        assertFalse(line.isColumnAvailable(4));
    }

    @Test public void test03RedStarts() {
        Linea line = gameWithARedPiece();
        assertTrue(line.positionContains(1,1, 'R'));
    }

    private Linea gameWithARedPiece() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(1);
        return line;
    }

    @Test public void test04GameFailsWhenBlueStarts() {
        Linea line = new Linea(3, 4, 'C');
        assertThrows( Exception.class, () -> line.playBlueAt(1));
    }

    @Test public void test05RedTriesToPlayTwice() {
        Linea line = gameWithARedPiece();
        assertThrows( Exception.class, () -> line.playRedkAt(1));
    }

    @Test public void test06BlueTriesToPlayTwice() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(1);
        assertThrows( Exception.class, () -> line.playBlueAt(1));
    }

    @Test public void test07AddingABluePieceToAnEmptyColumn() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(3);
        line.playBlueAt(1);
        assertTrue(line.positionContains(1,1, 'B'));
    }

    @Test public void test08TryingToAddABluePieceToAnInvalidColumn() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(2);
        assertThrows( Exception.class, () -> line.playBlueAt(0));
    }

    @Test public void test09TryingToAddARedPieceToAnInvalidColumn() {
        Linea line = new Linea(3, 4, 'C');
        assertThrows( Exception.class, () -> line.playRedkAt(0));
    }

    @Test public void test10AddingAPieceUponAnother() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(1);
        assertTrue(line.positionContains(1,1, 'R'));
        assertTrue(line.positionContains(1,2, 'B'));
    }

    @Test public void test11TryingToAddABluePieceToAFullColumn() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(1);
        line.playRedkAt(1);
        assertThrows( Exception.class, () -> line.playBlueAt(1));
    }

    @Test public void test12TryingToAddARedPieceToAFullColumn() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(1);
        assertThrows( Exception.class, () -> line.playRedkAt(1));
    }

    @Test public void test13GameFinishesInCaseOfDraw() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(3);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(3);
        assertTrue( line.finished() );
    }

    @Test public void test14RedWinsVerticallyWithGameModeA() {
        Linea line = new Linea(3, 4, 'A');
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        assertTrue( line.won('R') );
    }

    @Test public void test15BlueWinsVerticallyWithGameModeA() {
        Linea line = new Linea(3, 4, 'A');
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(3);
        line.playBlueAt(1);
        assertTrue( line.won('B') );
    }

    @Test public void test16RedWinsHorizontallyWithGameModeA() {
        Linea line = new Linea(5, 4, 'A');
        line.playRedkAt(1);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        assertTrue( line.won('R') );
    }

    @Test public void test17BlueWinsHorizontallyWithGameModeA() {
        Linea line = new Linea(5, 4, 'A');
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        assertTrue( line.won('B') );
    }

    @Test public void test18RedDoesNotWinDiagonallyWithGameModeA() {
        Linea line = new Linea(5, 5, 'A');
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        assertFalse( line.won('R') );
    }

    @Test public void test19BlueDoesNotWinDiagonallyWithGameModeA() {
        Linea line = new Linea(5, 5, 'A');
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        assertFalse( line.won('B') );
    }

    @Test public void test20RedDoesNotWinInTheOppositeDiagonalWithGameModeA() {
        Linea line = new Linea(5, 5, 'A');
        line.playRedkAt(5);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(5);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        assertFalse( line.won('R') );
    }

    @Test public void test21BlueDoesNotWinInTheOppositeDiagonalWithGameModeA() {
        Linea line = new Linea(5, 5, 'A');
        line.playRedkAt(1);
        line.playBlueAt(5);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(5);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        assertFalse( line.won('B') );
    }

    @Test public void test22RedWinsDiagonallyWithGameModeB() {
        Linea line = new Linea(5, 5, 'B');
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        assertTrue( line.won('R') );
    }

    @Test public void test23BlueWinsDiagonallyWithGameModeB() {
        Linea line = new Linea(5, 5, 'B');
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        assertTrue( line.won('B') );
    }

    @Test public void test24RedWinsInTheOppositeDiagonalWithGameModeB() {
        Linea line = new Linea(5, 5, 'B');
        line.playRedkAt(5);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(5);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        assertTrue( line.won('R') );
    }

    @Test public void test25BlueWinsInTheOppositeDiagonalWithGameModeB() {
        Linea line = new Linea(5, 5, 'B');
        line.playRedkAt(1);
        line.playBlueAt(5);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(5);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        assertTrue( line.won('B') );
    }

    @Test public void test26RedDoesNotWinVerticallyWithGameModeB() {
        Linea line = new Linea(3, 4, 'B');
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        assertFalse( line.won('R') );
    }

    @Test public void test27BlueDoesNotWinVerticallyWithGameModeB() {
        Linea line = new Linea(3, 4, 'B');
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(3);
        line.playBlueAt(1);
        assertFalse( line.won('B') );
    }

    @Test public void test28RedDoesNotWinHorizontallyWithGameModeB() {
        Linea line = new Linea(5, 4, 'B');
        line.playRedkAt(1);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        assertFalse( line.won('R') );
    }

    @Test public void test29BlueDoesNotWinHorizontallyWithGameModeB() {
        Linea line = new Linea(5, 4, 'B');
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        assertFalse( line.won('B') );
    }

    @Test public void test30RedWinsVerticallyWithGameModeC() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        assertTrue( line.won('R') );
    }

    @Test public void test31BlueWinsVerticallyWithGameModeC() {
        Linea line = new Linea( 3, 4, 'C' );
        line.playRedkAt(2);
        line.playBlueAt( 1 );
        line.playRedkAt( 2 );
        line.playBlueAt( 1 );
        line.playRedkAt( 2 );
        line.playBlueAt( 1 );
        line.playRedkAt( 3 );
        line.playBlueAt( 1 );
        assertTrue( line.won( 'B' ) );
    }

    @Test public void test32RedWinsHorizontallyWithGameModeC() {
        Linea line = new Linea(5, 4, 'C');
        line.playRedkAt(1);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        assertTrue( line.won('R') );
    }

    @Test public void test33BlueWinsHorizontallyWithGameModeC() {
        Linea line = new Linea(5, 4, 'C');
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        assertTrue( line.won('B') );
    }

    @Test public void test34RedWinsDiagonallyWithGameModeC() {
        Linea line = new Linea(5, 5, 'C');
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        assertTrue( line.won('R') );
    }

    @Test public void test35BlueWinsDiagonallyWithGameModeC() {
        Linea line = new Linea(5, 5, 'C');
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        assertTrue( line.won('B') );
    }

    @Test public void test36RedWinsInTheOppositeDiagonalWithGameModeC() {
        Linea line = new Linea(5, 5, 'C');
        line.playRedkAt(5);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(5);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        assertTrue( line.won('R') );
    }

    @Test public void test37BlueWinsInTheOppositeDiagonalWithGameModeC() {
        Linea line = new Linea(5, 5, 'C');
        line.playRedkAt(1);
        line.playBlueAt(5);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(5);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        assertTrue( line.won('B') );
    }

    @Test public void test38GetStringWorksForAnEmptySlot() {
        Linea line = new Linea(1, 1, 'C');
        assertEquals( "-", line.getPositionAsString(1, 1) );
    }

    @Test public void test39GetRedPositionString() {
        Linea line = new Linea(1, 1, 'C');
        line.playRedkAt(1);
        assertEquals( "R", line.getPositionAsString(1, 1) );
    }

    @Test public void test40GetBluePositionString() {
        Linea line = new Linea(2, 2, 'C');
        line.playRedkAt(2);
        line.playBlueAt(1);
        assertEquals( "B", line.getPositionAsString(1, 1) );
    }

    @Test public void test41ShowIsCorrectForASmallBoard() {
        Linea line = new Linea(1, 1, 'C');
        assertEquals( "| - |\n", line.show() );
    }

    @Test public void test42ShowIsCorrectAfterAddingARedPiece() {
        Linea line = new Linea(1, 1, 'C');
        line.playRedkAt(1);
        assertEquals( "| R |\n", line.show() );
    }

    @Test public void test43ShowIsCorrectAfterAddingABluePiece() {
        Linea line = new Linea(2, 1, 'C');
        line.playRedkAt(2);
        line.playBlueAt(1);
        assertEquals( "| B | R |\n", line.show() );
    }

    @Test public void test44AddingARedPieceOnABiggerBoard() {
        Linea line = new Linea( 5, 5, 'C');
        line.playRedkAt(1);
        assertEquals( "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| R | - | - | - | - |\n",
                line.show());
    }

    @Test public void test45TyingToPlayAfterTheGameFinished() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        assertTrue( line.won('R') );
        assertThrows( Exception.class , () -> line.playBlueAt(3));
    }

}