package linea;

public class RedTurn extends TurnHandler {

    public TurnHandler playRed() {return new BlueTurn();}

    public TurnHandler playBlue() { throw new RuntimeException(PlayerCanOnlyParticipateInTheirTurn);}
}
