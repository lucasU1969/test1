package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

// to do:
// mensaje de que termina el juego y el ganador
// clase de casilleros ocupados y vacíos (?)
// meter assertThrowsLike en los tests
// refactorizar los tests
// ver si podemos juntar las clases en un solo archivo
// sacar el if de finished()

public class Linea {

    private ArrayList<ArrayList<Character>> columns = new ArrayList();
    private char red = 'R';
    private char blue = 'B';
    private int maxHeight;
    private TurnHandler turnHandler = new RedTurn();
    private GameMode mode;

    public Linea(int numberOfColumns, int numberOfRows, char gameMode) {
        IntStream.range(0, numberOfColumns).forEach( aColumn -> columns.add(new ArrayList()));
        maxHeight = numberOfRows;
        mode = GameMode.gameModeFor( gameMode );
    }

    public boolean finished() { // double dispatch con gameMode
        return won(red) || won(blue) || draw();
    }

    private boolean draw() {
        return IntStream.range(0, columns.size()).allMatch(aColumn -> columnIsFull(aColumn + 1));
    }

    public void playRedkAt(int aColumnIndex) {
        turnHandler = turnHandler.playRed();
        safePlaceGamePiece(aColumnIndex, red);
    }

    public void playBlueAt(int aColumnIndex) {
        turnHandler = turnHandler.playBlue();
        safePlaceGamePiece(aColumnIndex, blue);
    }

    private void safePlaceGamePiece(int aColumnIndex, char blue) {
        if (!isWithinBoardLimits(aColumnIndex) || columnIsFull(aColumnIndex)) throw new RuntimeException("It's not possible to play at this position");
        columns.get(aColumnIndex - 1).add(blue);
        turnHandler = turnHandler.finished(this);
    }

    private boolean columnIsFull(int aColumnIndex) {
        return columns.get(aColumnIndex - 1).size() == maxHeight;
    }

    public boolean isColumnAvailable(int aColumnIndex) {
        return aColumnIndex > 0 && aColumnIndex <= columns.size();
    }

    public boolean positionContains(int numberOfColumn, int height, Character color) {
        if (!isWithinBoardLimits(numberOfColumn) || height > columns.get(numberOfColumn - 1).size()) return false;
        return columns.get(numberOfColumn - 1).get(height - 1).equals(color);
    }

    public boolean isWithinBoardLimits(int aColumnIndex) {
        return isColumnAvailable(aColumnIndex) && columns.get(aColumnIndex - 1).size() <= maxHeight;
    }

    public boolean won(char player) {
        return mode.checkWinningCondition(this, player);
    }

    public boolean wonVertically(char player) {
        return IntStream.range(0, columns.size()).anyMatch(aColumn -> IntStream.range(0, columns.get(aColumn).size() - 3).anyMatch(slot ->
                columns.get(aColumn).subList(slot, slot + 4).stream().allMatch(color -> color.equals(player))));
    }

    public boolean wonHorizontally(char player) {
        return IntStream.range(0, maxHeight)
                .anyMatch(row -> IntStream.range(0, columns.size() - 3).anyMatch(aColumn -> IntStream.range(0, 4).allMatch(offset -> positionContains(aColumn + offset + 1, row + 1, player))));
    }

    public boolean wonDiagonally(char player) {
        return IntStream.range( 1 - maxHeight, columns.size() + 1 )
                .anyMatch( aColumn -> IntStream.range( 0, maxHeight - 3 ).anyMatch( row -> IntStream.range( 0, 4 ).allMatch( offset -> positionContains( aColumn + offset + 1, row + offset + 1, player ) ) ) );
    }

    public boolean wonDiagonallyOpposite( char player) {
        return IntStream.range( 1 - maxHeight, columns.size() + 1 )
                .anyMatch( aColumn -> IntStream.range( 0, maxHeight - 3 ).anyMatch( row -> IntStream.range( 0, 4 ).allMatch( offset -> positionContains( aColumn + offset + 1, maxHeight - row - offset, player ) ) ) );
    }

    public String show() {
        return IntStream.range( 0, maxHeight )
                .mapToObj( row -> IntStream.range( 0, columns.size() ).mapToObj( column -> "| " + getPositionAsString( column + 1, maxHeight - row ) + " " ).reduce( "", String::concat ) + "|\n" ).reduce( "", String::concat);
    }

    public String getPositionAsString( int column, int row) {
        if ( ! ( positionContains( column, row, red ) || positionContains( column, row, blue ) ) ) return "-";
        return columns.get( column - 1 ).get( row - 1).toString();
    }

}
