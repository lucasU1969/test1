package linea2;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea {

    public ArrayList<ArrayList<Character>> columns = new ArrayList();
    private char red = 'R';
    private char blue = 'B';
    private int maxHeight;
    private char gameMode;
    private TurnHandler turnHandler = new RedTurn();

    public Linea(int numberOfColumns, int numberOfRows, char gameMode) {
        IntStream.range(0, numberOfColumns).forEach( aColumn -> columns.add(new ArrayList()));
        maxHeight = numberOfRows;
        this.gameMode = gameMode;
    }

    public String show() {
        return "";
    }

    public boolean finished() {// double dispatch con gameMode
        return won(red) || won(blue) || draw();
    }

    private boolean draw() {
        return IntStream.range(0, columns.size()).allMatch(aColumn -> columns.get(aColumn).size() == maxHeight);
    }

    public void playRedkAt(int aColumnIndex) {
        turnHandler = turnHandler.playRed();
        if (!isWithinBoardLimits(aColumnIndex)) throw new RuntimeException();
        columns.get(aColumnIndex - 1).add(red);
    }

    public void playBlueAt(int aColumnIndex) {
        turnHandler = turnHandler.playBlue();
        if (!isWithinBoardLimits(aColumnIndex)) throw new RuntimeException();
        columns.get(aColumnIndex - 1).add(blue);
    }

    public boolean isColumnAvailable(int aColumnIndex) {
        return aColumnIndex > 0 && aColumnIndex <= columns.size();
    }

    public boolean positionContains(int numberOfColumn, int height, Character color) {
        if (!isWithinBoardLimits(numberOfColumn) || height > columns.get(numberOfColumn - 1).size()) return false;
        return columns.get(numberOfColumn - 1).get(height - 1).equals(color);
    }

    public boolean isWithinBoardLimits(int aColumnIndex) {
        return isColumnAvailable(aColumnIndex) && columns.get(aColumnIndex - 1).size() < maxHeight;
    }

    public boolean won(char player) {
        if (gameMode == 'A') return wonVertically(player) || wonHorizontally(player);
        if (gameMode == 'B') return wonDiagonally(player) || wonDiagonallyOpposite(player);
        return wonVertically(player) || wonHorizontally(player) || wonDiagonally(player) || wonDiagonallyOpposite(player);
    }

    private boolean wonVertically(char player) {
        return IntStream.range(0, columns.size()).anyMatch(aColumn -> IntStream.range(0, columns.get(aColumn).size() - 3).anyMatch(slot ->
                columns.get(aColumn).subList(slot, slot + 4).stream().allMatch(color -> color.equals(player))));
    }

    private boolean wonHorizontally(char player) {
        return IntStream.range(0, maxHeight)
                .anyMatch(row -> IntStream.range(0, columns.size() - 3).anyMatch(aColumn -> IntStream.range(0, 4).allMatch(offset -> positionContains(aColumn + offset + 1, row + 1, player))));
    }

    private boolean wonDiagonally(char player) {
        return IntStream.range( 1 - maxHeight, columns.size() + 1 )
                .anyMatch( aColumn -> IntStream.range( 0, maxHeight - 3 ).anyMatch( row -> IntStream.range( 0, 4 ).allMatch( offset -> positionContains( aColumn + offset + 1, row + offset + 1, player ) ) ) );
    }

    private boolean wonDiagonallyOpposite( char player) {
        return IntStream.range( 1 - maxHeight, columns.size() + 1 )
                .anyMatch( aColumn -> IntStream.range( 0, maxHeight - 3 ).anyMatch( row -> IntStream.range( 0, 4 ).allMatch( offset -> positionContains( aColumn + offset + 1, maxHeight - row - offset, player ) ) ) );
    }

//        public String show() {
//        StringBuilder result = new StringBuilder();
//        IntStream.range(0, numberOfRows).forEach(aRow -> {
//            result.append("|");
//            IntStream.range(0, numberOfColumns).forEach(column -> {
//                result.append(columns.get(column).get(numberOfRows - 1 - aRow));
//            });
//            result.append(" |\n");
//        });
//        return result.toString();
//    }

}
