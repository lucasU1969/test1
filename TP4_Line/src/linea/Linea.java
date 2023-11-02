package linea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Linea {

    private int numberOfColumns;
    private int numberOfRows;
    private boolean isFinished = false;
    private List<List<String>> columns = new ArrayList<>();

    public Linea(int aNumberOfColumns , int aNumberOfRows, char symbol) {
        numberOfColumns = aNumberOfColumns;
        numberOfRows = aNumberOfRows;
        IntStream.range(0, numberOfColumns).forEach( a -> {columns.add(new ArrayList<>(Collections.nCopies(numberOfRows, " " + symbol)));});
    }

    public String show() {
        StringBuilder result = new StringBuilder();
        IntStream.range( 0, numberOfRows ).forEach( aRow -> {
            result.append("|");
            IntStream.range( 0, numberOfColumns ).forEach( column -> {
                result.append( columns.get(column).get(numberOfRows - 1 - aRow) );
            });
            result.append( " |\n" );
        });
        return result.toString();
    }


    public boolean finished() {
        return isFinished || isFull();
    }

    private boolean isFull() {
        return columns.stream().allMatch( column -> ! column.contains( " -" ));
    }

    public void playRedkAt(int aColumnIndex) {
        if ( isColumnWithinBounds(aColumnIndex) ) {
            columns.get(aColumnIndex).set(getAvailableIndex(aColumnIndex), " R");
        }
        if (redWonInTheColumn(aColumnIndex)) {
            isFinished = true;
        }

    }

    public void playBlueAt(int aColumnIndex) {
        if ( isColumnWithinBounds(aColumnIndex) ) {
            columns.get(aColumnIndex).set(getAvailableIndex(aColumnIndex), " B");
        }
    }

    public boolean isColumnWithinBounds(int anIndexOfColumn) {
        return anIndexOfColumn < numberOfColumns && anIndexOfColumn >= 0;
    }

    public boolean isRowWithinBounds(int anIndexOfRow) {
        return anIndexOfRow <= numberOfRows && anIndexOfRow > 0;
    }

    public boolean isRed(int x, int y) {
        return columns.get(x).get(y).equals(" R");
    }

    public boolean isBlue(int i, int i1) {
        return columns.get(i).get(i1).equals(" B");
    }

    public int getAvailableIndex(int index) {
        if (columns.get(index).contains(" -")) {
            return columns.get(index).indexOf(" -");
        }
        isFinished = true;
        throw new RuntimeException("Column is full");
    }

    public boolean redWonInTheColumn(int indexOfColumn) {
        ArrayList<Boolean> result = new ArrayList();
//        return columns.get(indexOfColumn).subList(indexOfRow +1, indexOfRow +3).stream().allMatch( slot -> slot == " R") ||
//                columns.get(indexOfColumn).subList(indexOfRow -3, indexOfRow).stream().allMatch( slot -> slot == " R");
        IntStream.range(0, numberOfRows-3).forEach( aRow -> result.add(columns.get(indexOfColumn).subList(aRow, aRow +4).stream().allMatch( slot -> slot == " R")) );
        return result.contains(true);
    }
}
