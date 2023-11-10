package linea;

public class FinishedGame extends TurnHandler{

    public TurnHandler playRed() {
        throw new RuntimeException(ItIsNotPossibleToPlayAfterTheGameIsFinished);
    }

    public TurnHandler playBlue() {
        throw new RuntimeException(ItIsNotPossibleToPlayAfterTheGameIsFinished);
    }

    public TurnHandler finished( Linea line ) {
        return this;
    }
}
