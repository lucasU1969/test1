package linea;

public abstract class TurnHandler {

    public String PlayerCanOnlyParticipateInTheirTurn = "Player can only participate in their turn";
    public String ItIsNotPossibleToPlayAfterTheGameIsFinished = "It is not possible to play after the game is finished";

    public abstract TurnHandler playRed();
    public abstract TurnHandler playBlue();
    public TurnHandler finished( Linea line) {
        if (line.finished()) return new FinishedGame();
        return this;
    }
}
