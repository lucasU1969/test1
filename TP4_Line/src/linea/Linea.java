package linea;

import java.util.stream.IntStream;

public class Linea {

    int numberOfColumns;
    int numberOfRows;
    boolean isFinished = false;

    public Linea(int aNumberOfColumns , int aNumberOfRows, char symbol) {
        numberOfColumns = aNumberOfColumns;
        numberOfRows = aNumberOfRows;
    }

    public String show() {
        StringBuilder rows = new StringBuilder().append("|");
        IntStream.range(0, numberOfColumns).forEach( column -> rows.append( " -" ));
        rows.append( " |\n" );
        StringBuilder result = new StringBuilder();
        IntStream.range(0, numberOfRows).forEach(row -> result.append(rows));
        return result.toString();
    }


    public boolean finished() {
        return isFinished;
    }

    public void playRedkAt(int aColumnIndex) {
    }

    public void playBlueAt(int aColumnIndex) {
    }

    public boolean isColumnWithinBounds(int anIndexOfColumn) {
        return anIndexOfColumn <= numberOfColumns && anIndexOfColumn > 0;
    }

    public boolean isRowWithinBounds(int anIndexOfRow) {
        return anIndexOfRow <= numberOfRows && anIndexOfRow > 0;
    }
}
